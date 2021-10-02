<template>
  <div class="myplaylist">
    <div class="profile-header2">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-music"></i>
          <span>
            내 플레이리스트
          </span>
        </h1>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/mydiary.scss'
import { getMyPlaylist } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      myPlayList: [],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyPlaylist(data)
    console.log(response.data)
    this.myPlayList = response.data
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
  },
  computed: {
    ...mapState(['uid']),
  },
}
</script>
