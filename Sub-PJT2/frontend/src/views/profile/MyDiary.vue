<template>
  <div class="mydiary">
    <div class="profile-header">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-book"></i>
          <span>
            내 일기장
          </span>
        </h1>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/mydiary.scss'
import { getMyDiary } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      myDiaryList: [],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyDiary(data)
    console.log(response.data)
    this.myDiaryList = response.data
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
