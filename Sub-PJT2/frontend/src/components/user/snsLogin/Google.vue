<template>
  <div class="google-login image-animation">
    <img
      src="@/assets/images/googleLogin.png"
      alt=""
      width="56%"
      height="53px"
      @click="buttonClick"
    />
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
      console.log(googleUser)
      console.log(googleUser.getBasicProfile())
      console.log(googleUser.getAuthResponse().id_token)

      let data = {
        id_token: googleUser.getAuthResponse().id_token,
      }
      UserApi.googleLogin(
        data,
        res => {
          this.$store.commit('SAVE_TOKEN', data.id_token)
          this.$store.commit('LOGIN', res.data['User Dto'])
          if (this.hasSurveyed) {
            this.$router.push({ name: 'Main' })
          } else {
            this.$router.push({ name: 'InitNickname' })
          }
        },
        error => {
          console.log(error)
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
    ...mapState(['hasSurveyed']),
  },
}
</script>
