text=open('pianoabc.txt','r').read()
#문자인데 숫자로 바꾸는법
#A는 1 B는2 C는 3 임의로 바꾸면 됩니다.

#문자 숫자로 바꾸는법
#1.Bag of words(단어주머니 )만들기
#2.문자 ->숫자로 변환기 만들기
#3.원본데이터를 숫자로 바꾸기
유니크text=list(set(text))#단어주머니 완성
유니크text.sort()#뒤죽박죽을 방지

text_to_num={}

for i,data in enumerate(유니크text):#i는 index /data는 유니크text의 데이턱밧
    text_to_num[data]=i# 문자를 숫자 변환 완료.!

숫자화text = []

for i in text:
    숫자화text.append(text_to_num[i])#원본데이터를 숫자로 바꾸기 완료

#trainX는 무작위로 순서대로 ?개픽 그다음 데이터가 trainY

#X,Y데이터셋 만들기
X=[]
Y=[]

for i in range(0,len(숫자화text)-25):
   X.append(숫자화text[i:i+25])
   Y.append(숫자화text[i+25])

import tensorflow as tf

X=tf.one_hot(X,31)
Y=tf.one_hot(Y,31)

model= tf.keras.models.Sequential([
    tf.keras.layers.LSTM(100, input_shape=(25,31)),#node수,lstm은 actionvation은 따로 안넣어두됩니다.
    tf.keras.layers.Dense(31, activation='softmax'),#문자 31개중 마지막에 올 문자를 예측 하는 문제
])
model.compile(loss='categorical_crossentropy',optimizer='adam',metrics=['accuracy'])
model.fit(X,Y, batch_size=64,epochs=1,verbose=2)#64개 학습후 w값 업데이트  자주w 값 업데이트 하고싶을시,epochs는 많이해야합니다.
