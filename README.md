# SSAFY PJT II Mlog

## 목차

- [프로젝트 소개](#프로젝트-소개)
- [프로젝트 명세](#프로젝트-명세)
  - [개발 환경](#개발-환경)
  - [핵심 라이브러리](#핵심-라이브러리)
    <br>

## 프로젝트 소개

- 서비스명 : Mlog?
- 소재 : 일기 & 감정분석 & 음악추천
- 사용자의 일기 감정분석을 통한 음악 추천 프로그램
  1. 설문조사를 통해 사용자의 음악 취향을 분석한다.
  2. 일기에 대한 감정분석을 한다.
  3. 분석 결과에 따른 음악을 추천한다.
  4. 다른 날짜의 비슷한 감정을 느낀 일기도 추천해 준다.
  5. 사용자의 일기에 대한 긍부정 보고서와 일기 작성 비율 보고서를 제공한다.
     <br>

## 프로젝트 명세

### 개발 환경

---
## :video_game: 기술 스택

### :robot: AI
<details>
    <summary>AI 자세히 살펴보기 🔥</summary>
    <ul>
      <li>기술스택 ⚙</li>
    </ul>
    <ul>
        <li>Pytorch</li>
        <li>numpy</li>
        <li>gluonnlp</li>
        <li>pandas</li>
    </ul>
    <ul>
      <li>인공지능 모델 ⚙</li>
    </ul>
    <ul>
        <li>KoBERT</li>
    </ul>
</details>

### 💻Back-End

<details>
    <summary>Back 자세히 살펴보기 🔥</summary>
    <ul>
      <li>기술스택 ⚙</li>
    </ul>
    <ul>
        <li>Spring-Boot : 2.3.9</li>
        <li>Spring-Boot-Data-JPA</li>
        <li>spring-boot-starter-validation</li>
        <li>spring-boot-starter-jdbc</li>
        <li>openvidu-java-client : 2.17.0</li>
        <li>spring-cloud-starter-aws : 2.2.5</li>
        <li>lombok</li>
        <li>mysql : 8.0.22</li>
    </ul>
</details>

### ✨Front-End / Android

- **지원 환경** : Web / Mobile 
<details>
    <summary>Front 자세히 살펴보기 🌈</summary>
    <ul>
        <li>기술스택 ⚙</li>
    </ul>   
    <ul>
        <li>JS, HTML, CSS</li>
        <li>SCSS</li>
        <li>vue/cli 4.5.13</li>
        <li>vue 2.6.10</li>
    </ul>
    <li>--------------------------------------------------------------------------------------</li>
    <ul>
        <li>라이브러리 📚</li>
    </ul>   
    <ul>
        <li>axios</li>
        <li>eslint & prettier</li>
        <li>node-sass</li>
        <li>sass-loader</li>
        <li>aos</li>
        <li>bootstrap</li>
        <li>bootstrap-vue</li>
        <li>jwt-decode</li>
        <li>less-loader</li>
        <li>v-calendar</li>
        <li>vue-compare-image</li>
        <li>vue-easy-range-date-picker</li>
        <li>vue-google-login</li>
        <li>vue-infinite-loading</li>   
        <li>vue-typer</li>
        <li>vue2-datepicker</li>
        <li>vue2-daterange-picker</li>
        <li>vuejs-countdown</li>
        <li>vuelendar</li>
        <li>vuelidate</li>
        <li>vuex</li>
        <li>vuex-persistedstate</li>
    </ul>
</details>


### 🌏Infra

  <details>
      <summary>개발, CI/CD 자세히 살펴보기 🔥</summary>
  </details>

-------------------------------------------------

#### Design

- **Framework 사용** : O 
  - [Vuetify](https://vuetifyjs.com/)
  - [Sass](https://sass-lang.com/)
  - [BootstrapVue](https://bootstrap-vue.org/)
  - [AntDesign of Vue](https://antdv.com/docs/vue/introduce-cn/)
- **Design Tool 사용** : 
- **담당자** : 민찬우, 송지현
  <br>

--------------------
### 핵심 라이브러리
기본 제공하는 라이브러리 외 핵심 기능 구현에 사용한 라이브러리가 있다면 작성해주세요.  
- **카카오 로그인 API**

  - **링크** : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
  - **소개** : 카카오에서 제공하는 로그인 API
  - **사용 기능** : 카카오 로그인 API -> 소셜로그인 이용
  - **담당자** : 양동현

- **구글 로그인 API**

  - **링크** : https://www.npmjs.com/package/vue-google-login
  - **소개** : 구글에서 제공하는 로그인 API
  - **사용 기능** : 구글 로그인 API -> 소셜로그인 이용
  - **담당자** : 전시영
