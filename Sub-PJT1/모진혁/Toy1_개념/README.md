## 텐서 플로우 설치

CMD창 들어가서 pip install tensorflow

 

## **텐서플로우는 사용하는 이유**

수학에서의 행렬, 벡터를 파이썬으로 표현하고 싶을 때 사용하면 유용합니다.

AI를 구현할 때에 행렬과 비슷한 것들을 많이 사용하게 되는데 행렬의 덧셈이나 곱셉을 코드로 구현한다면 코드가 길고 복잡해지기 때문에 이러한 것들을 매우 쉽게 코드 한 줄로 처리할 수 있게 도와주는 라이브러리인 텐서플로우를 사용합니다.

```
import tensorflow as tf

텐서 =  tf.constant([3,4,5])
텐서2 =  tf.constant([6,7,8])
텐서3= tf.constant([[1,2],[3,4]])


#shape 모양을 알려주는 것 몇차원인지 알 수 있는 것.
print(텐서.shape)#결과값 (3,) 자료가 3개 들어있습니다
print(텐서3.shape)#결과값 (2,2)   2행 2열이라는 것을 알려주는 것 

w= tf.Variable(1.0)
# 변수라는 뜻 
# 벽영이 쉽게쉽게 가능함
# tf.constant는 변경이 어려움\
print(텐서+텐서2)#결과값 tf.Tensor([ 9 11 13], shape=(3,), dtype=int32)
#W갑 변경하기 assing으로가능
w.assign(2)
#w값 불러오기
print(w.numpy())#결과값 2.0
```

## 딥러닝으로 간단한 수학 문제 풀어보기

```
import tensorflow as tf

#간단한 수학문제풀기
#키=[170,180,175,160]
#신발=[260,270,265,255]
#문제 키와신발은 어떤관련?이 있나용
#???=a*170+b
#키=170,신발 =260
#문제 키로 신발사이즈를 추론해보자
#신발 = 키 * a + b 키와 신발이 연관이 있을 것이다 예측

키=170
신발 =260
#신발=키*a +b
a=tf.Variable(0.1)
b=tf.Variable(0.2)

def 손실함수():
    return tf.square(260-(170*a+b))#(실제값 - 예측값)->(오차)제곱ㅇ르 진행해야한다.

opt=tf.keras.optimizers.Adam(learning_rate=0.1)#경사하강법 도와주는 고마운 친구
for i in range(300):
    opt.minimize(손실함수,var_list=[a,b])#경사하강법으로 업데이트할 목록들(1번진행)
    print(a.numpy(),b.numpy())
#a는 1.52 b는 1.62가나옴
#예측한 신발사이즈=키* 1.52+1.62
```

 



![img](https://blog.kakaocdn.net/dn/dyOquT/btrdqLXXooh/NbNLFjMmzkeOXkGlgdCsm1/img.png)신발과 키의 그래프



## 데이터가 많은 경우의 딥러닝

```
import tensorflow as tf

train_x=[1,2,3,4,5,6,7]
train_y=[3,5,7,9,11,13,15]
#x에다가 무슨짓을하면 y사가 냐올가요?
#답은 x에 2곱하고 플러스 1입니다

#①모델만들기 ②optimizer,손실함수 정하고 ③학습하기
a=tf.Variable(0.1)
b=tf.Variable(0.1)


def 손실함수():
    #mean squared error  mean은 평균 squared 제곱
    #(예측값-실제)^2 하나씩 다더한다음 나누기 7
    예측_y=train_x*a +b#train_x가 list여도 잘 적용이 됩니다.
    return tf.keras.losses.mse(train_y,예측_y)

opt=tf.keras.optimizers.Adam(learning_rate=0.1)



for i in range(3000):
    opt.minimize(손실함수,var_list=[a,b])
    print(a.numpy(),b.numpy())
#원하는결과 
#1.9999999 1.0000005
# 1.99 1.0 이나옴 2와 1
```

 