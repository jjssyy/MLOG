<template>
  <div class="diaryreport">
    <div class="profile-header2">
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
      <div class="diary-chart">
        <vc-donut
          background="white"
          foreground="grey"
          :size="50"
          unit="%"
          :thickness="33"
          :sections="sectionTwo"
          :total="chart.total"
          :start-angle="0"
          :auto-adjust-text-size="true"
          @section-click="handleSectionClick"
        >
        </vc-donut>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/report.scss'
import 'vue-css-donut-chart/dist/vcdonut.css'
import { getMyDiaryReport } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'

export default {
  props: {
    chart: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      startDate: '',
      endDate: '',
    }
  },
  async created() {
    var date = new Date()
    this.startDate = this.getDay()
    this.endDate = this.getToday(date)
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
    getDay() {
      var now = new Date() // 현재 날짜 및 시간
      var oneMonthAgo = new Date(now.setMonth(now.getMonth() - 1)) // 한달 전
      return this.getToday(oneMonthAgo)
    },
    getToday(date) {
      var year = date.getFullYear()
      var month = ('0' + (1 + date.getMonth())).slice(-2)
      var day = ('0' + date.getDate()).slice(-2)
      return year + '-' + month + '-' + day
    },
  },
  computed: {
    ...mapState(['uid']),
  },
}
</script>
