<template>
  <div style="background-color: #DAEEDC; height: 770px; overflow-y: scroll;">
    <Header></Header>
    <br />
    <!-- 총 일기 작성글 수 -->
    <MainTotalCnt :totalCnt="totalCnt"></MainTotalCnt>
    <!-- 달력(해당 월의 일기 정보 조회) -->
    <MainCalendar @moveMonth="moveMonth"></MainCalendar>
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
import { fetchTotalCnt, fetchChart } from '@/api/main.js'
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
    }
  },
  methods: {
    async loadTotalCnt() {
      const data = {
        id: this.$store.state.uid,
      }
      const response = await fetchTotalCnt(data)
      this.totalCnt = response.data.totalCnt
    },
    async moveMonth(month, start, end, total) {
      const data = {
        startDate: start,
        endDate: end,
        id: this.$store.state.uid,
      }
      const response = await fetchChart(data)
      this.chart = response.data.data[0]
      this.chart['month'] = month
      this.chart['total'] = total
      // this.month = month
      // this.total = total
    },
  },
  async created() {
    await this.loadTotalCnt()
  },
}
</script>

<style></style>
