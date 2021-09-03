import tensorflow as tf
import numpy as np
from tensorflow import keras
from tensorflow.keras import layers
(trainX,trainY),(testX,testY)=tf.keras.datasets.fashion_mnist.load_data()#구글이 호스팅 해주는 데이터셋 중 하나

trainX=trainX/255.0
testX= testX/255.0

trainX=trainX.reshape((trainX.shape[0],28,28,1))
testX=testX.reshape((testX.shape[0],28,28,1))

class_names=['T-shirt/top','Trouser','Pullover','Dress','Coat','Sandal','Shirt','Sneaker','Bag','Ankleboot']
model=tf.keras.Sequential([
    tf.keras.layers.Conv2D(32,(3,3),padding="same",activation='relu', input_shape=(28,28,1)),#32개의  복사본의 feature를 생성해주셈, (3,3)은 커널의 가로세로 사이즈 (3,3)이 정석
    tf.keras.layers.MaxPooling2D((2,2)),#풀링사이즈 2,2
    # tf.keras.layers.Dense(128,input_shape=(28,28),activation='relu'),
    tf.keras.layers.Flatten(),#행렬을 1차원으로 압축해주는 라이브러리
    tf.keras.layers.Dense(64,activation='relu'),#relu:음수는 다0으로 만들기
    tf.keras.layers.Dense(10,activation='softmax'),#확률 예측 문제라면 마지막 레이어 노드수를 카테고리 갯수 만큼
    #sigmoid:결과를 0~1로압축
    # -binary 예측 문제에사용(합 불합), 마지막 노드 갯수는 1개
    #여러가지 카테고리 중 어떤 카테고리가확률이 높은지?
    # softmax:결과를 0~1로압축
    # -카테고리 예측문제에 사용, 노드는 카테고리갯수
    #예측한 10개 확률을 다 더하면 1나옴
])
#input_shape=28 28인 이유는 ->(60000,28,28)이여서
model.summary()#내가 디자인한 딥러닝 모델과 레이어들 미리보기, model에 input_shape=(28,28)을 삽입
model.compile( loss="sparse_categorical_crossentropy",optimizer="adam",metrics=['accuracy'])
model.fit(trainX,trainY,validation_data=(testX,testY),epochs=5)
#validation_data epoch 1회 끝날 때마다 채점 하는법

score=model.evaluate(testX,testY)#학습후 모델 평가하기
print(score)#결과 [0.261696994304657, 0.9089000225067139]Loss accuracy