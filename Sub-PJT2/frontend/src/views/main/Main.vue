<template>
  <div style="background-color: #DAEEDC;">
    <Header></Header>
    <br />
    <!-- 총 일기 작성글 수 -->
    <MainTotalCnt :totalCnt="totalCnt"></MainTotalCnt>
    <!-- 달력(해당 월의 일기 정보 조회) -->
    <MainCalendar :monthDiary="monthDiary"></MainCalendar>
    <!-- 해당 월의 차트 조회 -->
    <MainChart :chart="chart"></MainChart>
    <br />
  </div>
</template>

<script>
import MainTotalCnt from '@/components/main/MainTotalCnt.vue'
import MainChart from '@/components/main/MainChart.vue'
import MainCalendar from '@/components/main/MainCalendar.vue'
import Header from '@/components/profile/Header.vue'
import { fetchTotalCnt, fetchChart, fetchMonthDiary } from '@/api/main.js'
export default {
  components: {
    MainTotalCnt,
    MainChart,
    MainCalendar,
    Header,
  },
  data() {
    return {
      totalCnt: 0,
      chart: {},
      monthDiary: {},
    }
  },
  methods: {
    async loadTotalCnt() {
      const data = {
        id: null,
      }
      const response = await fetchTotalCnt(data)
      this.totalCnt = response['data']['total_cnt']
    },
    async loadChart() {
      let date = new Date()
      const data = {
        id: null,
        month: date.getMonth() + 1,
      }
      const response = await fetchChart(data)
      this.chart = response['data']
    },
    async loadMonthDiary() {
      let date = new Date()
      const data = {
        id: null,
        month: date.getMonth() + 1,
      }
      const response = await fetchMonthDiary(data)
      this.monthDiary = response['data']
    },
  },
  async created() {
    await this.loadTotalCnt()
    await this.loadChart()
    await this.loadMonthDiary()
  },
}
</script>

<style></style>
