## CNN은 Convolution Neural Network입니다.

## 딥러닝으로 이미지를 학습시키기

 

뉴럴 네트워크에 집어넣을 수 있는 건 숫자인데

어떻게 사진을 넣을지?..

이미지의 픽셀데이터는 숫자로 표현 가능합니다.

이미지는 픽셀 데이터가 몇백만 개 모여서 만들어진 정보입니다.

컬러 사진:rgb 60,140 88 이런 형태

흑백 사진: 0~255 하나의 숫자

 

 

## tensorflow에서 제공하는 데이터를 받고 학습시켜보기

①데이터를 받고 이미지 출력해보기

```
import tensorflow as tf
import matplotlib.pyplot as plt
(trainX,trainY),(testX,testY)=tf.keras.datasets.fashion_mnist.load_data()#구글이 호스팅 해주는 데이터셋 중 하나
#((trainX->쇼핑몰이미지 6만개,tainY->정답 6만개),(testX->테스트용,testY->테스트용))
print(trainX[0])
print(trainX.shape)#출력문 (60000, 28, 28)
#1.모델만들기 2.compile하고 3.fit하기
print(trainY)
#이미지의 종류가 10가지 카테고리가 존재
plt.imshow(trainX[0])#이미지세팅
plt.show()#이미지보여주기
```

사진 이미지 보여주기 할 때 필요한 사전 준비-> pip install matplotlib

②학습시켜보기

```
import tensorflow as tf
from tensorflow import keras
(trainX,trainY),(testX,testY)=tf.keras.datasets.fashion_mnist.load_data()#구글이 호스팅 해주는 데이터셋 중 하나

class_names=['T-shirt/top','Trouser','Pullover','Dress','Coat','Sandal','Shirt','Sneaker','Bag','Ankleboot']
model=tf.keras.Sequential([
    tf.keras.layers.Dense(128,input_shape=(28,28),activation='relu'),
    tf.keras.layers.Dense(64,activation='relu'),#relu:음수는 다0으로 만들기
    tf.keras.layers.Flatten(),#행렬을 1차원으로 압축해주는 라이브러리
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
model.fit(trainX,trainY,epochs=5)
```

model컴파일할 때 loss에서 

원핫인코딩일시 ->categorical_crossentropy

원핫인코딩이 아닐 시 -> sparse_categorical_crossentropy

원한잇코딩이란 

위의 문제처럼 카테고리 중 어디에 들어갈지 예측을 할 때에 

1차원으로

[1,3,4,1,2,0,... 등]으로 나타낼 수 있습니다.

이것을 원핫인코딩으로 하면

첫째 카테고리 일시->[1,0,0,0]

셋째 카테고리 일시->[0,0,1,0]

이렇게 하는 것이 원핫인코딩입니다.

그래서 [1,3,0,2]라는 정답 데이터라면

[ [0,1,0,0], [0,0,0,1], [1,0,0,0], [0,0,1,0]]

이렇게 표현이 가능합니다.

\-----------------------------

위의 코드는 

\1. 이미지의 픽셀 데이터를 2차원의 28x28 행렬로 바꾼 뒤 

\2. Flatten 레이어를 적용해서

\3. 마지막에 1차원의 리스트를 출력하는 모델입니다.

하지만 이미지를 Flatten 해버린다면 문제점이 존재합니다.

예측모델의 응용력이 없어진다거나 더 이상 그 이미지 그 자체로 분석이 어려워집니다.

 

해결책-> Convolution layer입니다.

\1. 이미지에서 중요한 정보를 추려서 복사본 20장을 만듭니다.

\2. 그곳에는 이미지의 중요한 feature, 특성이 담겨있습니다.

\3. 그래서 이거를 사용하여 학습하자.

이걸 이용하면 현재 이미지의 특성을 추출 후 딥러닝으로 학습시킬 수 있습니다.

만약 자동차의 이미지라면 자동차의 바퀴, 창문, 문짝 이런 걸 특성으로 추출할 수 있는 것입니다.

그럼 조금 더 높은 예측 정확도를 기대할 수 있으며 응용력 있는 모델을 만들어 낼 수 있습니다.



![img](https://blog.kakaocdn.net/dn/K2Ihe/btrdz85ZpEo/ku6RXWP7YHZ9KoCd4zeK6K/img.png)kernel과정

![img](https://blog.kakaocdn.net/dn/dojkpy/btrdru3gekD/5dlLzoXwinYMd5saQM98UK/img.png)kernel이란 특징을 말함 위의 사진은 세로선을 보기위하여 커널을 위와같이 설정.



하지만 Convolutional layer에서도 문제점이 존재합니다.



![img](https://blog.kakaocdn.net/dn/czqvBf/btrdwSXb3sE/hWFUsWPyAVoQrGDnNNne8k/img.png)땅에박혀있는 자동차의 바퀴만 자동차라 학습



해결책은 Pooling layer

이미지를 축소하는 것

중요한 이미지의 부분을 유지한 채로 가운데로 이동시켜주는 것.

종류로는 max Pooling, average Pooling이 존재.



![img](https://blog.kakaocdn.net/dn/DVRJu/btrdEjZO4es/v5CwA53vfavoGQkPMAdx71/img.png)이미지의 위치를 영향을 안받음



 

③정확도를 상승시키기 위한 컨볼루션 모델 만들기 & 학습

```
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
```

 