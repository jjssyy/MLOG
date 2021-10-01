<template>
  <div class="diaryreport">
    <div class="profile-header">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-chart-bar"></i>
          <span>
            내 일기 보고서
          </span>
        </h1>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/report.scss'
import { getMyDiaryReport } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      startDate: '',
      endDate: '',
    }
  },
  async created() {
    this.startDate = this.getDay(false)
    this.endDate = this.getDay(true)
    console.log(this.startDate)
    console.log(this.endDate)
    const data = {
      id: this.uid,
      startDate: this.startDate,
      endDate: this.endDate,
    }
    const response = await getMyDiaryReport(data)
    console.log(response.data)
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
    getDay(num) {
      var date = new Date()
      var year = date.getFullYear()
      var month = ('0' + (1 + date.getMonth())).slice(-2)
      var day
      if (num) {
        day = ('0' + date.getDate()).slice(-2)
      } else {
        day = '01'
      }

      return year + month + day
    },
  },
  computed: {
    ...mapState(['uid']),
  },
}
</script>
