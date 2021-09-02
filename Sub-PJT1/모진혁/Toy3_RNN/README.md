## RNN 개념

순서가 중요한 데이터는 어떻게 학습할까?

I went to the library to 빈칸 

빈칸에는 어떤 단어가 올지 딥러닝으로 푼다고 가정해봅시다.

딥러닝 input에는 숫자만 들어가야 하는데 어떻게 할 것인가?



![img](https://blog.kakaocdn.net/dn/bCAiIX/btrdInvy4tx/NUtlUX7OGM6a17B0FrMu0k/img.png)해결책



그리고 본론으로 들어가 Dense 레이어에서 집어넣을 땐 



![img](https://blog.kakaocdn.net/dn/d5GcD4/btrdFnJy0Na/UfyHO8NmujXiP1GSeh0uMK/img.png)



위의 사진과 같이 연관도가 있지 단어들의 순서 정보는 사라집니다.

 

그래서 순서도를 생각하는 것이 RNN 레이어입니다(Recurrent Neural Network)

글자들을 하나씩 순서대로 집어넣어 보기

연관 도도 생각하게 하는 것.



![img](https://blog.kakaocdn.net/dn/M0XGX/btrdDyx33QH/nWUl2KYgFYlU24kYJRnMc1/img.png)

![img](https://blog.kakaocdn.net/dn/c7gtoA/btrdHfYRJyy/A3hMnsA3g5A2EmXCLYJ8k1/img.png)위의 사진과 같이 재사용하는것 입니다.(went를 넣을때에 h1도 함께사용하여 예측해라)



위와 같이 RNN을 사용하면 일반 Dense 레이어보단 단어의 순서의 의미가 존재합니다.

RNN은 Sequence 데이터 학습에 좋습니다.

자료의 순서 & 자료 간 연결성이 있을 때 도입하면 좋은 성과가 나옵니다.

###  

###  

### RNN의 활용

\1. 이미지 자동 캡션

\2. 글의 감정 분석

\3. 번역

\-----------------------------------------------------------------

## LSTM, GRU

Simple RNN의 문제; Diminishing Gradient



![img](https://blog.kakaocdn.net/dn/3eOK1/btrdHe6IE0f/grzaFeGthKTKHxk99CI4v1/img.png)



위의 사진에서 빨간색의 I의 비중으로 시각화하였습니다.

예측을 진행할수록 첫 번째의 단어가 비중이 줄어드는 문제점이 존재합니다.(중요 단어가 앞에 존재한다면 정확도가 하락합니다.)

이러한 문제점을 가져 RNN 대체로 :LSTM 레이어입니다.

 

LSTM(Long Short Term Memory)

정보 중 중요해 보이는 기억은 장기기억으로 기억을 보존을 진행하는 과정을 추가.

텐서 플로우-> keras.layers.LSTM()

GRU도 존재하는데 연산과정이 더 간단하고 아웃풋이 하나뿐이라 학습시간이 덜 걸립니다. 

LSTM GRU 중 더 좋은걸 사용하면 됩니다.