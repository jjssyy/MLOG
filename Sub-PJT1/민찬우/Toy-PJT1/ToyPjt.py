import numpy as np
import pandas as pd
import tensorflow as tf

# 데이터 불러오기
data = pd.read_csv('gpascore.csv')

# 데이터 전처리(csv파일중간에 빈값이 있으므로 값 채워넣어야함)
# print(data.isnull().sum())
# Nan or 빈값있는 행을 제거
data = data.dropna()
# gpa의 column을 모두 출력
# data['gpa']
# data['gpa'].min() or .max() 최솟값 최댓값
# data['gpa'].count() 갯수
# 빈값 채워넣기
# data.fillna(100)

# 딥러닝 모델 만드는 방법(모델 디자인)
model = tf.keras.models.Sequential([
    # Hidden layer 3줄 & 각각의 layer의 node개수(2의 제곱수로 지정)
    tf.keras.layers.Dense(64, activation='tanh'),
    tf.keras.layers.Dense(128, activation='tanh'),
    # 마지막은 하나의 노드
    tf.keras.layers.Dense(1, activation='sigmoid'),
])
# 경사하강법과 손실함수
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# 학습 시키기
# x는 학습데이터([[],[],[]]), y([답,답,답]or [[답],[답],[답]])는 실제값
x = []
# iterrows() 한행씩 출력할 수 있다.
for i, rows in data.iterrows():
    x.append([rows['gre'], rows['gpa'], rows['rank']])

y = data['admit'].values

model.fit(np.array(x), np.array(y), epochs=2000)

# 예측
result = model.predict([[750, 3.70, 3], [400, 2.2, 1]])
# [[0.4688169 ] -> rank = 3에 붙을 확률
# [0.46881694]] -> rank = 1에 붙을 확률
print(result)

# 데이터전처리와 파라미터튜닝으로 인해 성능 향상 시킬 수 있다.
# 실험이 매우 중요.(80%이상이 최고성능)