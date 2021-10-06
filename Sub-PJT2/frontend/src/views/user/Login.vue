<template>
  <div class="user" id="login">
    <div class="loginBox">
      <br />
      <div class="loginContentBox">
        <p class="introText">
          과거를 회상
          <br />현재를 기억 <br />미래를 계획
        </p>
        <p class="loginText">Log in <span>to your acount</span></p>

        <kakaoLogin />
        <GoogleLogin />
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
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
};
</script>
