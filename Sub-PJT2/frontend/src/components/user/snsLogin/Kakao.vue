<template>
  <div id="kakao-login">
    <img
      src="@/assets/images/k_icon.png"
      alt=""
      width="35"
      @click="loginWithKakao"
    />
    <!-- <img
      src="@/assets/images/kakaoLogin.png"
      alt=""
      width="54%"
      height="47px"
      @click="loginWithKakao"
    /> -->
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '@/api/UserApi'

export default {
  created() {
    if (this.$route.query.code != null) {
      const data = {
        code: this.$route.query.code,
      }
      UserApi.kakaoLogin(
        data,
        res => {
          this.$store.commit('SAVE_TOKEN', res.data.token)
          this.$store.commit('LOGIN', res.data.userInfo)
          if (this.nickname == 'default') {
            this.$router.push({ name: 'InitNickname' })
          } else {
            if (this.hasSurveyed) {
              this.$router.push({ name: 'Main' })
            } else {
              this.$router.push({ name: 'SurveyStart' })
            }
          }
        },
        err => {
          console.log(err)
          console.log(err.response.status)
          if (err.response.status == 409) {
            alert('이메일을 사용 허가해주세요')
            //     window.location.replace(
            // "https://kauth.kakao.com/oauth/authorize?client_id=2e422a8e10fb191d40ff2b6fb7439b41&redirect_uri=http://j5c104.p.ssafy.io&response_type=code&scope=account_email"
            // );
          }
        },
      )
    }
  },
  methods: {
    loginWithKakao() {
      window.location.replace(
        'https://kauth.kakao.com/oauth/authorize?client_id=2e422a8e10fb191d40ff2b6fb7439b41&redirect_uri=http://j5c104.p.ssafy.io&response_type=code',
      )
    },
  },
  computed: {
    ...mapState(['hasSurveyed', 'nickname']),
  },
}
</script>
