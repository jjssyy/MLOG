import os
import tensorflow as tf
import shutil
import matplotlib.pyplot as plt

for i in os.listdir('train/'):
    if 'cat' in i:
        shutil.copyfile('train/' + i, 'dataset/cat/' + i)
    if 'dog' in i:
        shutil.copyfile('train/' + i, 'dataset/dog/' + i)

# x은 숫자로 바꾼거, y는 답
train_ds = tf.keras.preprocessing.image_dataset_from_directory(
    'dataset/',
    # 모든 이미지가 64x64
    image_size = (64, 64),
    # 32개 넣고 w값 계산해보고 갱신하고 다시 32개넣고 w값 계산해서 갱신
    batch_size = 64,
    # 80% 데이터 가지고 있음
    subset="training",
    validation_split=0.2,
    seed=1234,
)
val_ds = tf.keras.preprocessing.image_dataset_from_directory(
    'dataset/',
    # 모든 이미지가 64x64
    image_size = (64, 64),
    # 32개 넣고 w값 계산해보고 갱신하고 다시 32개넣고 w값 계산해서 갱신
    batch_size = 64,
    # 20% 데이터 가지고 있음
    subset="validation",
    validation_split=0.2,
    seed=1234,
)

# print(train_ds)
def Pretreatment(i, answer):
    i = tf.cast(i/255.0, tf.float32)
    return i, answer
train_ds = train_ds.map(Pretreatment)
val_ds = val_ds.map(Pretreatment)


model = tf.keras.Sequential([
    # 사진뒤집기(증강)
    tf.keras.layers.experimental.preprocessing.RandomFlip('horizontal', input_shape=(64, 64, 3)),
    tf.keras.layers.experimental.preprocessing.RandomRotation(0.1),
    tf.keras.layers.experimental.preprocessing.RandomZoom(0.1),
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu"),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu"),
    tf.keras.layers.MaxPooling2D((2, 2)),
    # 학습데이터 외우기를 막기 윗레이어의 노드를 일부 제거
    # overfitting 줄이기
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.Conv2D(32, (3, 3), padding="same", activation="relu"),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(64, activation="relu"),
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.Dense(1, activation="sigmoid"),
])
model.summary()

model.compile(loss="binary_crossentropy", optimizer="adam", metrics=['accuracy'])
model.fit(train_ds, validation_data=val_ds, epochs=3)

# for i, answer in train_ds.take(1):
#     print(i)
#     print('f', answer)
#     plt.imshow(i[0].numpy().astype('uint8'))
#     plt.imshow(i[1].numpy().astype('uint8'))
#     plt.show()

# 하나에 이미지로 뻥튀기 가능한 augmentation 이미지 증강-> overfitting덜일어남
# case1. 이미지하나를 사본생성해서 증간된 이미지를 모델학습
# 초기데이터 적으면 증강해봤자 효과 별로..
# 초기데이터 많으면 용량이 너무 많아진다.
# case2. 첫번째 layer에 집어 넣기전에 이미지에 변형을 준다.
# img사이즈 키우면 더 좋아진다.
