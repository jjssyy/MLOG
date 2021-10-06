<template>
  <div id="kakao-login" class="image-animation">
    <img
      src="@/assets/images/kakaoLogin.png"
      alt=""
      width="54%"
      height="47px"
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
          this.$router.push({ name: "InitNickname" });
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
        "https://kauth.kakao.com/oauth/authorize?client_id=2e422a8e10fb191d40ff2b6fb7439b41&redirect_uri=http://j5c104.p.ssafy.io&response_type=code"
      );
    },
  },
};
</script>
