import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
# tensorflow에서 제공하는 data set
# data를 load한다.
(trainX, trainY), (testX, testY) = tf.keras.datasets.fashion_mnist.load_data()
trainX = trainX / 255.0
testX = testX / 255.0
trainX = trainX.reshape((trainX.shape[0], 28, 28, 1))
testX = testX.reshape((testX.shape[0], 28, 28, 1))
# 이미지 데이터(6만개), 정답(6만개)
# 트레인용, 테스트용
# ((trainX, trainY), (testX, testY))

# 28x28
# print(trainX[0])
# (60000, 28, 28) -> 28x28행렬이 6만개 있다.
# print(trainX.shape)

# print(trainY)
# [9 0 0 ... 3 0 5]
# 정답을 label

# 이미지 띄워보기
# plt.imshow(trainX[0])
# 흑백으로 바꾸기
# plt.gray()
# color를 수치화
# plt.colorbar()
# plt.show()

class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankleboot']

# model만들기
model = tf.keras.Sequential([
    # activation="relu" 음수 다 0으로 만들기
    # 복사본 개수, kernel 가로세로 사이즈
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu", input_shape=(28, 28, 1)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    # 칼라는 (28, 28, 3)
    # tf.keras.layers.Dense(128, input_shape=(28, 28), activation="relu"),
    # [0.1 0.4 0.1 0.1 ...] -> T-shirt일 확률이 0.1
    # 있어도되고 없어도되지만 0~1사이 sigmoid(0~1압축, binary예측문제(붙는다 안붙는다.) 마지막Dense는 1), 카테고리 예측문제는 softmax
    # 1차원으로 바꿔주기 10으로 맞추기위해서
    tf.keras.layers.Flatten(input_shape=(28, 28, 1)),
    tf.keras.layers.Dense(64, activation="relu"),
    tf.keras.layers.Dense(10, activation="softmax"),
])
# 카테고리 예측문제에서 쓰는 loss
# loss="sparse_categorical_crossentropy" -> 답이 [1, 2, 1, ..., 1]일때
# loss="categorical_crossentropy" -> 답이 [[0,1,0,0], [1, 0, 0, 0], ...]일때

# 모델 아웃라인 출력
# input_shape=(28, 28) 작성
model.summary()

model.compile(loss="sparse_categorical_crossentropy", optimizer="adam", metrics=['accuracy'])
model.fit(trainX, trainY, validation_data=(testX, testY), epochs=3)

# 모델 평가
# score = model.evaluate(testX, testY)
# print(score)
# convolutional layer
# 각각의 특징이있는 이미지 복사본을 20장만들고 학습
# 3*3에서서 중요한거 뽑아서 하나씩 배치
# 이미지 특징을 인식
# Pooling layer -> 위치를 인식
# val_accuracy 높일 방법 -> denselayer추가? conv_pooling추가?

# 다시 훈련안하도록 모델 공유

# case1. 모델 전체 저장
# model.save('model/model1')
# # model 불러오기
# loadModel = tf.keras.models.load_model('model/model1')
# loadModel.summary()
# loadModel.evaluate(testX, testY)

# # case2. w값만 저장/로드
# # 덮어씌기된다. 안되게 하려면 경로에 {epoch} 변수명을 입력
# callBack = tf.keras.callbacks.ModelCheckpoint(
#     filepath='checkpoint/mnist{epoch}',
#     # val_acc만 제일 높은거만 저장한다. 아래 두줄
#     monitor='val_acc',
#     mode='max',
#     save_weights_only=True,
#     save_freq='epoch'
# )
# model.fit(trainX, trainY, validation_data=(testX, testY), epochs=1, callbacks=[callBack])

# # 로드
# # model만든거, summery, compile 후
# model.load_weights('checkpoint/mnist')
# model.evaluate(testX, testY)