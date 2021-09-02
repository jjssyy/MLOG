import tensorflow as tf

Pmodel=tf.keras.models.load_model('model1')

text=open('pianoabc.txt','r').read()

유니크text=list(set(text))
유니크text.sort()

text_to_num={}

for i,data in enumerate(유니크text):
    text_to_num[data]=i

숫자화text = []

for i in text:
    숫자화text.append(text_to_num[i])

import numpy as np
첫입력값=숫자화text[117:117+25]
첫입력값=tf.one_hot(첫입력값,31)
첫입력값=tf.expand_dims(첫입력값,axis=0)


예측값=Pmodel.predict(첫입력값)
예측값=np.arxmax(예측값[0])