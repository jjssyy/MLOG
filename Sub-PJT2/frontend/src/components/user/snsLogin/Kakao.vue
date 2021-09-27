<template>
  <div id="kakao-login">
    <img
      src="@/assets/images/k_icon.png"
      alt=""
      width="28"
      @click="loginWithKakao"
    />
  </div>
</template>

<script>
import UserApi from "@/api/UserApi";
export default {
  created() {
    if (this.$route.query.code != null) {
      const data = {
        code: this.$route.query.code,
      };
      UserApi.kakaoLogin(
        data,
        (res) => {
          this.$store.commit("SAVE_TOKEN", res.data.token);
          this.$store.commit("LOGIN", res.data.userInfo);
          // TODO 메인페이지로 이동
        },
        (err) => {
          console.log(err);
        }
      );
    }
  },
  methods: {
    loginWithKakao() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=2e422a8e10fb191d40ff2b6fb7439b41&redirect_uri=http://localhost:8081&response_type=code"
      );
    },
  },
};
</script>
