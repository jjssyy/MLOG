<template>
  <div class="user" id="login">
    <div class="loginBox">
      <br />
      <div class="loginContentBox">
        <p class="introText">
          <span class="intro">
            음악으로
            <br />하루를<br />기록하다<br />
          </span>
          <span style="">
            <p class="mlog">
              MLOG
            </p>
          </span>
        </p>
        <p class="loginText">Log in to your account</p>
        <div class="sns-login">
          <div class="login-logo">
            <kakaoLogin />
            <GoogleLogin />
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
