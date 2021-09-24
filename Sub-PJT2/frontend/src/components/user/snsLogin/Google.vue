<template>
  <div class="google-login">
    <div class="g-logo">
      <!-- <img
        src="@/assets/images/g_icon.png"
        alt=""
        width="33"
        @click="onGoogleLogin"
      /> -->
      <GoogleLogin
        :params="params"
        :renderParams="renderParams"
        :onSuccess="onSuccess"
        :onFailure="onFailure"
      ></GoogleLogin>
    </div>
  </div>
</template>

<script>
import GoogleLogin from 'vue-google-login'
import UserApi from '@/api/UserApi.js'
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
          console.log(res)
        },
        error => {
          console.log(error)
        },
      )
    },
    onFailure() {},
  },
}
</script>
