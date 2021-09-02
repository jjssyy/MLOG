# 전이학습 : 누가 트레이닝해놓은 모델을 가지고 내 모델을 학습
# 사물인식은 전이학습으로 모델 성능 올림

import os
import tensorflow as tf
import shutil
import matplotlib.pyplot as plt
from tensorflow.python.keras import activations

# x은 숫자로 바꾼거, y는 답
train_ds = tf.keras.preprocessing.image_dataset_from_directory(
    'dataset/',
    # image_size = (64, 64),
    image_size = (150, 150),
    batch_size = 64,
    subset="training",
    validation_split=0.2,
    seed=1234,
)
val_ds = tf.keras.preprocessing.image_dataset_from_directory(
    'dataset/',
    # image_size = (64, 64),
    image_size = (150, 150),
    batch_size = 64,
    subset="validation",
    validation_split=0.2,
    seed=1234,
)

def Pretreatment(i, answer):
    i = tf.cast(i/255.0, tf.float32)
    return i, answer
train_ds = train_ds.map(Pretreatment)
val_ds = val_ds.map(Pretreatment)

import requests
url = 'https://storage.googleapis.com/mledu-datasets/inception_v3_weights_tf_dim_ordering_tf_kernels_notop.h5'
r = requests.get(url, allow_redirects=True)
open('inception_v3.h5', 'wb').write(r.content)

from tensorflow.keras.applications.inception_v3 import InceptionV3
inception_model = InceptionV3(input_shape=(150,150,3), include_top=False, weights=None)
inception_model.load_weights('inception_v3.h5')

inception_model.summary()

for i in inception_model.layers:
    i.trainable = False

# mixed6부터 학습해보고싶어?
# unfreeze = False
# for i in inception_model.layers:
#     if i.name == 'mixed6':
#         unfreeze = True
#     if unfreeze == True:
#         i.trainable = True

lastLayer = inception_model.get_layer('mixed7')

layer1 = tf.keras.layers.Flatten()(lastLayer.output)
layer2 = tf.keras.layers.Dense(1024, activation='relu')(layer1)
drop1 = tf.keras.layers.Dropout(0.2)(layer2)
layer3 = tf.keras.layers.Dense(1, activation='sigmoid')(drop1)

model = tf.keras.Model(inception_model.input, layer3)
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['acc'])
model.fit(train_ds, validation_data=val_ds, epochs=1)


# model.compile(loss='binary_crossentropy', optimizer=tf.keras.optimizers.Adam(lr=0.00001), metrics=['acc'])
# model.fit(train_ds, validation_data=val_ds, epochs=2)