import pandas as pd

data=pd.read_csv('gpascore.csv')

#전처리작업
# print(data.isnull().sum())#null갯수알기
data=data.dropna()#Nan/빈칸은 제거해주는 함수
#data.fillna(100)#빈칸을 원하는 값으로 채워준다.여기선 100
#print(data['gpa'])#데이터에서 의 gpa열  출력
y데이터= data['admit'].values#전부다리스트로 담아줌.
x데이터=[]
for i,rows in data.iterrows():
    x데이터.append([rows['gre'],rows['gpa'],rows['rank']])
# print(x데이터)
# print(y데이터)




import numpy as np
import tensorflow as tf

#딥러닝 모델 만드는방법
model= tf.keras.models.Sequential([ 
    tf.keras.layers.Dense(64,activation='tanh'),#노드의 갯수 /관습으로 2의 제곱수로 맞춘다
    tf.keras.layers.Dense(128,activation='tanh'),#sigmoid,tanh,relus 등이있따.
    tf.keras.layers.Dense(1,activation='sigmoid'),#마지막 레이어는 잘 생각해야한다,마지막레이어는 항상 예측결과를 뱉어야함 0~1사이의확률 ->sigmoid
])

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
#optimizer leaernigrate 자동으로 해주는것 adam이 제일 좋음
#loss 오차 구하기 binary_crossentropy=>0~1일때주로사용

#fit(x데이터(학습),y데이터(결과),epochs=10(몇번 학습반복할건지))
#x데이터와 y데이터를 리스트형태로 넣으면안되고 numpy로 변환해서 넣어야한다
model.fit( np.array(x데이터), np.array(y데이터), epochs=1000 )
#loss 는 낮아야되고 정확성은 높아야한다

#예측
예측값=model.predict([[750,3.70,3],[400,2.2,1]])
print(예측값)
##딥러닝은 연구과정입니다. 그래서학습이 정말 중요합니다.