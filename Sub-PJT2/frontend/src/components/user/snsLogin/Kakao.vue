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
import http from '@/util/http-common.js'
export default {
  created() {
    if (this.$route.query.code != null) {
      this.kakaoLogin(this.$route.query.code)
    }
  },
  methods: {
    loginWithKakao() {
      window.location.replace(
        `https://kauth.kakao.com/oauth/authorize?client_id=2e422a8e10fb191d40ff2b6fb7439b41&redirect_uri=http://localhost:8081&response_type=code`,
      )
    },
    kakaoLogin(code) {
      http.get('/member/kakao?code=' + code).then(response => {
        console.log(response)
        console.log('kakao login success')
      })
    },
  },
}
</script>
