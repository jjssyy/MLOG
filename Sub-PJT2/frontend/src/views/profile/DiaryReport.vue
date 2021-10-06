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
            :thickness="33"
            :sections="section"
            :total="total || defaultTotal"
            :start-angle="0"
            :auto-adjust-text-size="true"
            @section-click="handleSectionClick"
          >
            <h1>{{ calPercent }}%</h1>
          </vc-donut>
        </div>
      </div>
      <div class="report-ment">
        <h2 v-if="calPercent >= 70">
          일기를 {{ calPercent }}%나 쓰셨네요.
          <br />
          정말 잘하고 있어요! 😆
        </h2>
        <h2 v-else-if="calPercent >= 40">
          일기를 {{ calPercent }}% 쓰셨군요.
          <br />
          지금도 잘하고 있어요! 😉
        </h2>
        <h2 v-else-if="calPercent > 0">
          일기를 {{ calPercent }}% 쓰셨군요.
          <br />
          조금 더 분발해주세요! 😎
        </h2>
        <h2 v-else-if="calPercent == 0">
          일기를 아직 안쓰셨네요.
          <br />
          오늘 하루를 기록해보세요. 😊
        </h2>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/report.scss'
import 'vue-css-donut-chart/dist/vcdonut.css'
import { getMyDiaryReport } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'
import VDatePicker from 'v-calendar/lib/components/date-picker.umd'

export default {
  components: {
    VDatePicker,
  },
  data() {
    return {
      diaryCnt: 0,
      section: [],
      range: {
        start: '',
        end: '',
      },
      defaultTotal: 1,
      startDate: '',
      endDate: '',
      month: 0,
      total: 0,
    }
  },
  async created() {
    var date = new Date()
    var oneMonthAgo = new Date(date.setMonth(date.getMonth() - 1))
    var Today = new Date()
    var milliSecond = Today - oneMonthAgo

    this.range.start = oneMonthAgo
    this.range.end = Today

    this.startDate = this.getDay(oneMonthAgo)
    this.endDate = this.getDay(Today)
    this.getDiaryCnt(milliSecond)
  },
  watch: {
    range(oldValue) {
      var milliSecond2 = oldValue.end - oldValue.start
      this.startDate = this.getDay(oldValue.start)
      this.endDate = this.getDay(oldValue.end)
      this.getDiaryCnt(milliSecond2)
    },
  },
  methods: {
    async getDiaryCnt(milli) {
      if (this.startDate != this.endDate) {
        this.total = Math.floor(milli / 1000 / 60 / 60 / 24) + 1
      } else {
        this.total = 1
      }
      const data = {
        id: this.uid,
        startDate: this.startDate,
        endDate: this.endDate,
      }
      const response = await getMyDiaryReport(data)
      this.diaryCnt = response.data.count
      this.section = [
        { label: '작성글수', value: this.diaryCnt, color: '#83c9e7' },
      ]
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
  },
  computed: {
    calPercent() {
      if (this.range.start != this.range.end) {
        return parseInt((this.diaryCnt / this.total) * 100)
      } else {
        return 0
      }
    },
    ...mapState(['uid']),
  },
}
</script>
