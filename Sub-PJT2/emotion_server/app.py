from flask import Flask, request, json
from numpy.lib.function_base import angle
import get_model as m 
from koalanlp.Util import initialize, finalize
from koalanlp.proc import SentenceSplitter
from koalanlp import API


app = Flask(__name__)


model = m.get_model()

# target = ["친구랑 놀고 다시 혼자가 되면 머릿속이 복잡해진다.","지금은 기분이 묘하구나","기분이 짜증난다."]


@app.route("/predict", methods=['POST'])
def predict():
    initialize(OKT='LATEST')  #: HNN=2.0.3
    splitter = SentenceSplitter(API.OKT)
    print(request.is_json)
    params= request.get_json()
    text = params['content']
    sentence = splitter(text)
    result=[0,0,0,0,0]
    dict1= {"neutral" : 0, "sadness" : 1, "fear" : 2, "anger" : 3, "joy" : 4}
    print(sentence)
    result=model.Predict(sentence)
    dict1["neutral"]=result[0]
    dict1["sadness"]=result[1]
    dict1["fear"]=result[2]
    dict1["anger"]=result[3]
    dict1["joy"]=result[4]
    json_val=json.dumps(dict1)
    finalize()
    # print(result)

    return json_val

if __name__ == "__main__":
    app.run(host='0.0.0.0')