from koalanlp.Util import initialize, finalize
from koalanlp.proc import SentenceSplitter
from koalanlp import API

initialize(OKT='LATEST')  #: HNN=2.0.3

splitter = SentenceSplitter(API.OKT)


# text = input("분석할 문장을 입력하세요>> ").strip()
text="안녕하세요저의이름은 무엇입니다.!!@!@#!@ 당신의 이름은 무엇입니까?. 저의이름은 이것입니다.".strip()
sentences = splitter(text)

print("===== Sentence =====")
print(sentences)
for i, sent in enumerate(sentences):
    print("[%s] %s" % (i, sent))

finalize()