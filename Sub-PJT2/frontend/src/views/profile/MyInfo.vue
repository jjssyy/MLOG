<template>
  <div class="myinfo">
    <div class="profile-img">
      <div id="header-img">
        <div>
          <img
            :src="filePath"
            alt=""
            width="100%"
            height="100%"
            style="border-radius: 50%;"
          />
        </div>
      </div>
    </div>
    <div class="myinfo-header">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="myinfo-contents">
      <div class="userinfo">
        <h2 class="nickname">{{ nickname }} 님</h2>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '@/assets/css/components/profile.scss'

export default {
  goProfile() {
    this.$router.push({ name: 'Profile' })
  },
  computed: {
    ...mapState(['filePath', 'nickname']),
  },
}
</script>
