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