import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
from tensorflow.keras.callbacks import TensorBoard
import time
from tensorflow.keras.callbacks import EarlyStopping

(trainX, trainY), (testX, testY) = tf.keras.datasets.fashion_mnist.load_data()
trainX = trainX / 255.0
testX = testX / 255.0
trainX = trainX.reshape((trainX.shape[0], 28, 28, 1))
testX = testX.reshape((testX.shape[0], 28, 28, 1))


class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankleboot']

# model만들기
model = tf.keras.Sequential([
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu", input_shape=(28, 28, 1)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Flatten(input_shape=(28, 28, 1)),
    tf.keras.layers.Dense(64, activation="relu"),
    tf.keras.layers.Dense(10, activation="softmax"),
])
model.summary()

model.compile(loss="sparse_categorical_crossentropy", optimizer="adam", metrics=['accuracy'])
# 로그파일 생성(시각화를 위한)
tensorboard = TensorBoard(log_dir='logs/{}'.format('firstModel' + str(int(time.time()))))
model.fit(trainX, trainY, validation_data=(testX, testY), epochs=3, callbacks=[tensorboard])

# model만들기
model = tf.keras.Sequential([
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu", input_shape=(28, 28, 1)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu", input_shape=(28, 28, 1)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Flatten(input_shape=(28, 28, 1)),
    tf.keras.layers.Dense(64, activation="relu"),
    tf.keras.layers.Dense(10, activation="softmax"),
])
model.summary()

model.compile(loss="sparse_categorical_crossentropy", optimizer="adam", metrics=['accuracy'])
# 로그파일 생성(시각화를 위한)
tensorboard = TensorBoard(log_dir='logs/{}'.format('secondModel' + str(int(time.time()))))
# patience는 epochs가 3번진행했는데 이후 개선이 없으면 중지, val_accuracy는 max, val_loss는 min
es = EarlyStopping(monitor='val_accuracy', patience=3, mode='max')
model.fit(trainX, trainY, validation_data=(testX, testY), epochs=3, callbacks=[tensorboard, es])

# tensorboard --logdir logs
# Early Stopping
# epochs=100을 하고 알아서 스탑
