<template>
  <div>
    <div class="container-box">
      <!-- <p>MainChart 컴포넌트이다.</p>
    <p>chart: {{ chart }}</p> -->
      <div class="chart-box-one">
        <p class="my-sub-title" style="margin-bottom:1rem;">
          이번 달 일기 감정 비율
        </p>
        <vc-donut
          background="white"
          foreground="grey"
          :size="50"
          unit="%"
          :thickness="75"
          has-legend
          legend-placement="bottom"
          :sections="sectionsOne"
          :total="100"
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
          이번 달 일기 작성 비율
        </p>
        <vc-donut
          background="white"
          foreground="grey"
          :size="50"
          unit="%"
          :thickness="33"
          :sections="sectionsTwo"
          :total="100"
          :start-angle="0"
          :auto-adjust-text-size="true"
          @section-click="handleSectionClick"
        >
          <h1>78%</h1>
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
      sectionsOne: [
        { label: '긍정', value: 40, color: '#83c9e7' },
        { label: '부정', value: 60, color: '#ff8585' },
      ],
      sectionsTwo: [
        { label: '작성글수', value: 80, color: '#83c9e7' },
        { label: '', value: 20, color: '#e9e9e9' },
      ],
    }
  },
  methods: {
    handleSectionClick(section) {
      console.log(`${section.label} clicked.`)
    },
  },
}
</script>

<style></style>
