import pandas as pd
from tensorflow import keras
from tensorflow.python.keras import activations
from tensorflow.python.keras.backend import dropout
from tensorflow.python.keras.feature_column.dense_features_v2 import DenseFeatures

data =pd.read_csv('train.csv')

print(data.isnull().sum())

평균=data['Age'].mean()#나이의 평균값
최빈값=data['Embarked'].mode()
data['Age'].fillna(value=30,inplace=True)#null값에 넣어주라
data['Embarked'].fillna(value='S',inplace=True)
print(data.isnull().sum())

import tensorflow as tf
정답=data.pop('Survived')#열만 분리
ds=tf.data.Dataset.from_tensor_slices((dict(data),정답))
print(ds)
#data를 정의해야 넣을수있습니다 학습데이터로
#숫자로집어넣을거 numeric_column
#뭉퉁그러셔 집어넣을거: Bucketized_column
#종류몇개없는 카테고리화 해서 집어넣을거(원핫):indicator_collumn
#종류가 너무많은 카텔고리: embedding_column

feature_columns=[]
feature_columns.append( tf.feature_column.numeric_column('Fare') )#숫자 그대로 집어넣고싶은 칼럼
feature_columns.append( tf.feature_column.numeric_column('Parch') )
feature_columns.append( tf.feature_column.numeric_column('SibSp') )
feature_columns.append( tf.feature_column.numeric_column('Age') )

#숫자를 범위로 바꾸기
Age=tf.feature_column.numeric_column('Age')
Age_bucket=tf.feature_column.bucketized_column(Age,boundaries=[10,20,30,40,50,60])
feature_columns.append(Age_bucket)
# print(feature_columns)

#카테고리화 해서 집어넣기
# tf.feature_column.sequence_categorical_column_with_vocabulary_list('Sex',유니크한Sex컬럼에 들어가있는 문자리스트)
vocab=data['Sex'].unique()
cat=tf.feature_column.categorical_column_with_vocabulary_list('Sex',vocab)
ont_hot=tf.feature_column.indicator_column(cat)
feature_columns.append(ont_hot)
# print(vocab)#['male','female']
vocab=data['Embarked'].unique()
cat=tf.feature_column.categorical_column_with_vocabulary_list('Embarked',vocab)
ont_hot=tf.feature_column.indicator_column(cat)
feature_columns.append(ont_hot)

vocab=data['Pclass'].unique()
cat=tf.feature_column.categorical_column_with_vocabulary_list('Pclass',vocab)
ont_hot=tf.feature_column.indicator_column(cat)
feature_columns.append(ont_hot)

#embedding
vocab=data['Ticket'].unique()
cat=tf.feature_column.categorical_column_with_vocabulary_list('Ticket',vocab)
ont_hot=tf.feature_column.embedding_column(cat,dimension=9)
feature_columns.append(ont_hot)

model=tf.keras.Sequential([
    tf.keras.layers.DenseFeatures(feature_columns),#dict
    tf.keras.layers.Dense(128,activation='relu'),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.Dense(1 ,activation='sigmoid'),
])
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['acc'])

ds_batch=ds.batch(32)#rank 0에러 해결

model.fit(ds_batch,shuffle=True,epochs=20)