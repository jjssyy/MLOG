<template>
  <div>
    <div class="profile-header">
      <h2 @click="goProfile">{{ nickname }} 님</h2>
      <div id="header-img" @click="goProfile">
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
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '@/assets/css/components/profile.scss'

export default {
  computed: {
    ...mapState(['filePath', 'nickname']),
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
  },
}
</script>
