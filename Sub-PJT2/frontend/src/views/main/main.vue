<template>
  <div>
    <h1>main페이지이다.</h1>
    <!-- 총 일기 작성글 수 -->
    <MainTotalCnt :totalCnt="totalCnt"></MainTotalCnt>
    <!-- 해당 월의 차트 조회 -->
    <MainChart :chart="chart"></MainChart>
    <!-- 스크롤 메뉴 추가해야함 -->
  </div>
</template>

<script>
import MainTotalCnt from '@/components/main/MainTotalCnt.vue'
import MainChart from '@/components/main/MainChart.vue'
import { fetchTotalCnt, fetchChart } from '@/api/main.js'
export default {
  components: {
    MainTotalCnt,
    MainChart,
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
        id: 'null',
      }
      const response = await fetchTotalCnt(data)
      this.totalCnt = response['data']['total_cnt']
    },
    async loadChart() {
      let date = new Date()
      const data = {
        id: 'null',
        month: date.getMonth() + 1,
      }
      const response = await fetchChart(data)
      this.chart = response['data']
    },
  },
  async created() {
    await this.loadTotalCnt()
    await this.loadChart()
  },
}
</script>

<style></style>
