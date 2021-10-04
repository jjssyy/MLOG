<template>
  <div>
    <div class="container-box">
      <!-- <p>MainChart 컴포넌트이다.</p>
    <p>chart: {{ chart }}</p> -->
      <div class="chart-box-one">
        <p class="my-sub-title" style="margin-bottom:1rem;">
          {{ chart.month }}월 일기 감정 비율
        </p>
        <vc-donut
          background="white"
          foreground="grey"
          :size="50"
          unit="%"
          :thickness="75"
          has-legend
          legend-placement="bottom"
          :sections="sectionOne"
          :total="chart.count == 0 ? chart.total : chart.count"
          :start-angle="0"
          :auto-adjust-text-size="true"
          @section-click="handleSectionClick"
        >
          <!-- <h1>긍부정</h1> -->
        </vc-donut>
      </div>
    </div>
    <div class="container-box">
      <div class="chart-box-two">
        <p class="my-sub-title" style="margin-bottom:1rem;">
          {{ chart.month }}월 일기 작성 비율
        </p>
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
          <h1>{{ parseInt((this.chart.count / this.chart.total) * 100) }}%</h1>
        </vc-donut>
      </div>
    </div>
  </div>
</template>

<script>
import '@/components/css/calendar.scss'
import 'vue-css-donut-chart/dist/vcdonut.css'
export default {
  props: {
    chart: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      sectionOne: [],
      sectionTwo: [],
    }
  },
  watch: {
    chart: function() {
      let positive = 0
      let negative = 0
      let neutral = 0
      let noneValue = 0
      for (let i = 0; i < this.chart.count; i++) {
        if (this.chart.sentiment[i] > 0.2) {
          positive += 1
        } else if (this.chart.sentiment[i] >= -0.2) {
          neutral += 1
        } else {
          negative += 1
        }
      }
      if (positive == 0 && neutral == 0 && negative == 0) {
        noneValue = this.chart.total
      }
      if (noneValue != 0) {
        this.sectionOne = [
          { label: '작성글 없음', value: noneValue, color: '#e9e9e9' },
        ]
      } else {
        this.sectionOne = [
          { label: '긍정', value: positive, color: '#83c9e7' },
          { label: '중립', value: neutral, color: '#81c147' },
          { label: '부정', value: negative, color: '#ff8585' },
        ]
      }
      this.sectionTwo = [
        { label: '작성글수', value: this.chart.count, color: '#83c9e7' },
        {
          label: '',
          value: this.chart.total - this.chart.count,
          color: '#e9e9e9',
        },
      ]
    },
  },
  methods: {
    handleSectionClick(section) {
      console.log(`${section.label} clicked.`)
    },
  },
}
</script>

<style></style>
