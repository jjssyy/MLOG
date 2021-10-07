<template>
  <div class="google-login">
    <div class="g-logo">
      <img
        src="@/assets/images/g_icon.png"
        alt=""
        width="40"
        @click="buttonClick"
      />
    </div>
    <!-- 
    <img
      src="@/assets/images/gicon.png"
      alt=""
      width="56%"
      height="53px"
      @click="buttonClick"
    /> -->
    <GoogleLogin
      id="googlebtn"
      :params="params"
      :renderParams="renderParams"
      :onSuccess="onSuccess"
      :onFailure="onFailure"
    ></GoogleLogin>
  </div>
</template>

<script>
import GoogleLogin from 'vue-google-login'
import UserApi from '@/api/UserApi.js'
import { mapState } from 'vuex'

export default {
  components: {
    GoogleLogin,
  },
  data() {
    return {
      params: {
        client_id:
          '512592128492-b88aomr2gk1n6ivkbs8h2t0lc04e97ng.apps.googleusercontent.com',
        ux_mode: 'popup',
        redirect_uri: 'http://localhost:8081/',
      },
      renderParams: {
        width: 50,
        height: 50,
        longtitle: false,
      },
    }
  },
  methods: {
    onSuccess(googleUser) {
      let data = {
        id_token: googleUser.getAuthResponse().id_token,
      }
      UserApi.googleLogin(
        data,
        res => {
          this.$store.commit('SAVE_TOKEN', data.id_token)
          this.$store.commit('LOGIN', res.data['User Dto'])
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
        },
      )
    },
    onFailure(error) {
      console.log(error)
    },
    buttonClick() {
      const test = document.querySelector('#googlebtn')
      test.click()
    },
  },
  computed: {
    ...mapState(['nickname', 'hasSurveyed']),
  },
}
</script>
