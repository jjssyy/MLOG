<template>
  <div class="user" id="login">
    <div class="loginBox">
      <div class="logo"></div>
      <h1 class="loginText">
        과거를 회상,
        <br />현재를 기억, <br />미래를 계획,
      </h1>

      <div class="sns-login">
        <h1>login</h1>
        <hr class="bar" />
        <div class="login-logo">
          <div class="naver">
            <img src="@/assets/images/n_icon.png" width="50" />
          </div>
          <kakaoLogin />
          <GoogleLogin />
          <div class="facebook">
            <img
              src="@/assets/images/f_icon.png"
              width="50"
              style="border-radius: 5px;"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../../components/css/user.scss'
import KakaoLogin from '../../components/user/snsLogin/Kakao.vue'
import GoogleLogin from '../../components/user/snsLogin/Google.vue'
// import UserApi from '../../api/UserApi'

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },
  created() {},
  watch: {},
  methods: {
    onLogin() {},
  },
}
</script>
