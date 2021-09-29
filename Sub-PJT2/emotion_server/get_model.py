import torch
from kobert.pytorch_kobert import get_pytorch_kobert_model
from kobert.utils import get_tokenizer
import gluonnlp as nlp
from torch import nn
from torch.utils.data import Dataset, DataLoader
from collections import Counter
import numpy as np

class BERTClassifier(nn.Module):
    def __init__(self,
                bert,
                hidden_size = 768,
                num_classes = 5,
                dr_rate = None,
                params = None):
        super(BERTClassifier, self).__init__()
        self.bert = bert
        self.dr_rate = dr_rate
                
        self.classifier = nn.Linear(hidden_size , num_classes)
        if dr_rate:
            self.dropout = nn.Dropout(p=dr_rate)
    
    def gen_attention_mask(self, token_ids, valid_length):
        attention_mask = torch.zeros_like(token_ids)
        for i, v in enumerate(valid_length):
            attention_mask[i][:v] = 1
        return attention_mask.float()

    def forward(self, token_ids, valid_length, segment_ids):
        attention_mask = self.gen_attention_mask(token_ids, valid_length)
        
        _, pooler = self.bert(input_ids = token_ids, token_type_ids = segment_ids.long(), attention_mask = attention_mask.float().to(token_ids.device))
        if self.dr_rate:
            out = self.dropout(pooler)
        return self.classifier(out)


class BERTDatasetForTest(Dataset):
    def __init__(self, dataset, bert_tokenizer, max_len,
                pad, pair):
        transform = nlp.data.BERTSentenceTransform(
            bert_tokenizer, max_seq_length=max_len, pad=pad, pair=pair)
        
        # texts = dataset["sentence"].tolist()
        texts = dataset
        # labels = dataset["Emotion"].tolist()

        self.sentences = [transform([text]) for text in texts]
        # self.labels = [np.int32(emotion2label[label]) for label in labels]

    def __getitem__(self, i):
        return (self.sentences[i])

    def __len__(self):
        return (len(self.sentences))

def get_label_probability(result, label2emotion):
  # result : tensor
    ret_dict = {emotion : 0 for emotion in list(label2emotion.values())}

    result = result.tolist()
    num_results = len(result)
    key_list = list(Counter(result).keys())
    emotion_list = list(label2emotion[key] for key in key_list)

    counted_value_list = list(Counter(result).values())
    prob_value_list = list(counted_value/num_results for counted_value in counted_value_list)

    for emotion, prob_value in zip(emotion_list, prob_value_list):
        ret_dict[emotion] = prob_value
    # print(ret_dict)
    # ret_dict = {emotion_list[i] : prob_value_list[i] for i in range(len(key_list))}

    return ret_dict

###======================
# device 설정
class get_model():
    def __init__(self):
        self.device = torch.device("cpu")
        # model load
        self.bertmodel, self.vocab = get_pytorch_kobert_model()
        self.tokenizer = get_tokenizer()
        self.tok = nlp.data.BERTSPTokenizer(self.tokenizer, self.vocab, lower=False)

        ##=================모델설정
        # 좀더 봐야함
        self.max_len = 64
        self.batch_size = 64

        # label transformer
        self.multi_emotion2label = {"neutral" : 0, "sadness" : 1, "fear" : 2, "anger" : 3, "joy" : 4}
        self.multi_label2emotion = { 0 : "neutral", 1 : "sadness",  2 : "fear",  3 : "anger",  4 : "joy"}

        # model load
        self.multi_model = BERTClassifier(self.bertmodel, num_classes=5, dr_rate=0.5).to(self.device)
        self.multi_model.load_state_dict(torch.load("emotion_server\multi_label_model.pt", map_location=torch.device('cpu')))


##모델실행함수
## 매번 실행될 함수#######################################

    def softmax(vals, idx):
        valscpu = vals.cpu().detach().squeeze(0)
        a = 0
        print(valscpu)
        for i in valscpu:
            a += np.exp(i)
        return ((np.exp(valscpu[idx]))/a).item() * 100


    def Predict(self,sentence):
        # def soft(val,idx):
        #     a=0
        #     for i in val:
        #         a+=np.exp(i)
        #     return ((np.exp(val[idx]))/a)*100
        print(sentence)
        sentence_dataset = BERTDatasetForTest(sentence, self.tok, self.max_len, True, False)
        sentence_dataloader = torch.utils.data.DataLoader(sentence_dataset, batch_size=self.batch_size, num_workers=0)
        print(sentence_dataloader)
        #Multi Model
        self.multi_model.eval()
        for batch_id, (token_ids, valid_length, segment_ids) in enumerate(sentence_dataloader):
            token_ids = token_ids.long().to(self.device)
            segment_ids = segment_ids.long().to(self.device)
            valid_length= valid_length
            multi_out = self.multi_model(token_ids, valid_length, segment_ids)
            prediction=multi_out.cpu().detach().numpy().argmax()
            idx=multi_out.argmax().cpu().item()
        print(multi_out)
        # for i in range(5) :
        #   print("신뢰도는:", "{:.2f}%".format(softmax(multi_out,i)))
        print("===정답========")
        #   print("신뢰도는:", "{:.2f}%".format(softmax(multi_out,idx)))
        result=[0,0,0,0,0]
        for i in range(len(sentence)):
            for j in range(5):
                result[j]+=multi_out[i][j].cpu().detach().squeeze(0).item()
        for i in range(5):
            result[i]=round(result[i]/len(sentence),2)
        # Output Check
        multi_max_vals, multi_max_indices = torch.max(multi_out, 1)
        multi_predicted_emotion = list(self.multi_label2emotion[label] for label in multi_max_indices.tolist())

        # for i in range(5) :
        #   print(self.multi_label2emotion[i],"일 확률:", "{:.2f}%".format(soft(result,i)))
        print("multi result")
        for text, emotion in zip(sentence, multi_predicted_emotion):
            print(f"{emotion} : {text}")
        ###################################################################
        multi_prob_dict = get_label_probability(multi_max_indices, self.multi_label2emotion)
        neg_prob = multi_prob_dict["sadness"]+multi_prob_dict["fear"]+multi_prob_dict["anger"]
        pos_prob = multi_prob_dict["joy"]
        # print(multi_prob_dict)
        # print(neg_prob)
        # print(pos_prob)
        return result

# target = [
#         "친구랑 놀고 다시 혼자가 되면 머릿속이 복잡해진다.","지금은 기분이 묘하구나","기분이 짜증난다."]
# Predict(target)