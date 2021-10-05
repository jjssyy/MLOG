<template>
  <div class="emotionreport">
    <div class="profile-header2">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-chart-pie"></i>
          <span>
            긍·부정 보고서
          </span>
        </h1>
      </div>
      <div class="chart-date">
        <div>
          <v-date-picker v-model="range" is-range>
            <template v-slot="{ inputValue, inputEvents }">
              <div class="date-select">
                <input :value="inputValue.start" v-on="inputEvents.start" />
                <span>
                  <i class="fas fa-arrow-right"></i>
                </span>
                <input :value="inputValue.end" v-on="inputEvents.end" />
              </div>
            </template>
          </v-date-picker>
        </div>
        <div class="diary-chart">
          <vc-donut
            background="white"
            foreground="#e9e9e9"
            :size="50"
            unit="%"
            has-legend
            legend-placement="bottom"
            :thickness="75"
            :sections="section"
            :total="DiaryList.length || defaultTotal"
            :start-angle="0"
            :auto-adjust-text-size="true"
            @section-click="handleSectionClick"
          >
          </vc-donut>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/report.scss'
import 'vue-css-donut-chart/dist/vcdonut.css'
import { getEmotionReport } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'
import VDatePicker from 'v-calendar/lib/components/date-picker.umd'

export default {
  components: {
    VDatePicker,
  },
  data() {
    return {
      section: [],
      range: {
        start: '',
        end: '',
      },
      startDate: '',
      endDate: '',
      month: 0,
      defaultTotal: 1,
      DiaryList: [],
    }
  },
  async created() {
    var date = new Date()

    var oneMonthAgo = new Date(date.setMonth(date.getMonth() - 1))
    var Today = new Date()

    this.range.start = oneMonthAgo
    this.range.end = Today

    this.startDate = this.getDay(oneMonthAgo)
    this.endDate = this.getDay(Today)
    this.getEmotionCnt()
  },
  watch: {
    range(oldValue) {
      this.startDate = this.getDay(oldValue.start)
      this.endDate = this.getDay(oldValue.end)
      this.getEmotionCnt()
    },
  },
  methods: {
    async getEmotionCnt() {
      const data = {
        id: this.uid,
        startDate: this.startDate,
        endDate: this.endDate,
      }
      const response = await getEmotionReport(data)
      this.DiaryList = response.data.DiaryList
      this.calChart()
    },
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
    getDay(date) {
      var year = date.getFullYear()
      var month = ('0' + (1 + date.getMonth())).slice(-2)
      var day = ('0' + date.getDate()).slice(-2)
      return year + '-' + month + '-' + day
    },
    handleSectionClick(section) {
      console.log(`${section.label} clicked.`)
    },
    calChart() {
      let positive = 0
      let negative = 0
      let neutral = 0
      let noneValue = 0
      for (let i = 0; i < this.DiaryList.length; i++) {
        if (this.DiaryList[i].sentiment > 0.2) {
          positive += 1
        } else if (this.DiaryList[i].sentiment >= -0.2) {
          neutral += 1
        } else {
          negative += 1
        }
      }
      if (positive == 0 && neutral == 0 && negative == 0) {
        noneValue = this.DiaryList.length
      }
      if (noneValue != 0) {
        this.section = [
          { label: '작성글 없음', value: noneValue, color: '#e9e9e9' },
        ]
      } else {
        this.section = [
          { label: '긍정', value: positive, color: '#83c9e7' },
          { label: '중립', value: neutral, color: '#81c147' },
          { label: '부정', value: negative, color: '#ff8585' },
        ]
      }
    },
  },
  computed: {
    ...mapState(['uid']),
  },
}
</script>
