<template>
  <div class="calendar-box">
    <p class="my-sub-title">나의 기록</p>
    <div class="container-box">
      <div class="ctr-box">
        <button
          type="button"
          title="prev"
          class="btn-cal prev"
          @click="loadYYMM(init.prevMonth())"
        ></button>

        <span class="cal-month" v-html="calMonth"></span>
        <span class="cal-year" v-html="calYear"></span>

        <button
          type="button"
          title="next"
          class="btn-cal next"
          @click="loadYYMM(init.nextMonth())"
        ></button>
      </div>
      <table class="cal-table">
        <thead>
          <tr>
            <th>Su</th>
            <th>Mo</th>
            <th>Tu</th>
            <th>We</th>
            <th>Th</th>
            <th>Fr</th>
            <th>Sa</th>
          </tr>
        </thead>
        <tbody
          class="cal-body"
          v-html="calBody"
          @click="changeTag($event)"
        ></tbody>
      </table>
    </div>

    <p style="font-weight: bold;">아직 이날의 일기를 쓰지 않았어요.</p>
    <button @click="moveCreate" class="createBtn">
      일기 쓰기
    </button>
    <!-- <div>이 날은 {{ sentiment }}</div>
    <p>MainCalendar 컴포넌트이다.</p>
    <p>monthDiary: {{ monthDiary }}</p> -->
  </div>
</template>

<script>
import '@/assets/css/views/init.scss'
import '@/components/css/calendar.scss'
export default {
  props: {
    monthDiary: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      currentDate: null,
      calMonth: null,
      calYear: null,
      calBody: null,
      clickedYear: null,
      clickedMonth: null,
      clickedDate: null,
      init: {
        monList: [
          'Jan. ',
          'Feb. ',
          'Mar. ',
          'Apr. ',
          'May. ',
          'Jun. ',
          'Jul. ',
          'Aug. ',
          'Sep. ',
          'Oct. ',
          'Nov. ',
          'Dec. ',
        ],
        dayList: [
          'Sunday',
          'Monday',
          'Tuesday',
          'Wednesday',
          'Thursday',
          'Friday',
          'Saturday',
        ],
        today: new Date(),
        monForChange: new Date().getMonth(),
        activeDate: new Date(),
        getFirstDay: (yy, mm) => new Date(yy, mm, 1),
        getLastDay: (yy, mm) => new Date(yy, mm + 1, 0),
        nextMonth: function() {
          let d = new Date()
          d.setDate(1)
          d.setMonth(++this.monForChange)
          this.activeDate = d
          return d
        },
        prevMonth: function() {
          let d = new Date()
          d.setDate(1)
          d.setMonth(--this.monForChange)
          this.activeDate = d
          return d
        },
        addZero: num => (num < 10 ? '0' + num : num),
        activeDTag: null,
        getIndex: function(node) {
          let index = 0
          while ((node = node.previousElementSibling)) {
            index++
          }
          return index
        },
      },
    }
  },
  methods: {
    moveCreate() {
      this.$router.push(`/diary/${this.currentDate}/create`)
    },
    loadYYMM(fullDate) {
      let yy = fullDate.getFullYear()
      let mm = fullDate.getMonth()
      let firstDay = this.init.getFirstDay(yy, mm)
      let lastDay = this.init.getLastDay(yy, mm)
      let markToday // for marking today date

      if (
        mm === this.init.today.getMonth() &&
        yy === this.init.today.getFullYear()
      ) {
        markToday = this.init.today.getDate()
      }
      this.calMonth = this.init.monList[mm]
      this.calYear = yy

      let trtd = ''
      let startCount
      let countDay = 0
      for (let i = 0; i < 6; i++) {
        trtd += '<tr>'
        for (let j = 0; j < 7; j++) {
          if (i === 0 && !startCount && j === firstDay.getDay()) {
            startCount = 1
          }
          if (!startCount) {
            trtd += '<td>'
          } else {
            let fullDate =
              yy +
              '.' +
              this.init.addZero(mm + 1) +
              '.' +
              this.init.addZero(countDay + 1)
            trtd += '<td class="day'
            if (markToday && markToday === countDay + 1) {
              trtd += ' today" '
            } else if (
              this.clickedDate === countDay + 1 &&
              this.init.activeDate.getFullYear() === this.clickedYear &&
              this.init.activeDate.getMonth() === this.clickedMonth
            ) {
              trtd += ' day-active" '
            } else {
              trtd += '"'
            }
            trtd += ` data-date="${countDay + 1}" data-fdate="${fullDate}">`
          }
          trtd += startCount ? ++countDay : ''
          if (countDay === lastDay.getDate()) {
            startCount = 0
          }
          trtd += '</td>'
        }
        trtd += '</tr>'
      }
      this.calBody = trtd
    },
    changeTag(e) {
      if (this.clickedDate) {
        document
          .querySelector(`td[data-date="${this.clickedDate}"]`)
          .classList.remove('day-active')
      }
      if (e.target.classList.contains('day')) {
        if (this.init.activeDTag) {
          this.init.activeDTag.classList.remove('day-active')
        }
        let day = Number(e.target.textContent)
        this.clickedYear = this.init.activeDate.getFullYear()
        this.clickedMonth = this.init.activeDate.getMonth()
        this.clickedDate = day
        e.target.classList.add('day-active')
        this.init.activeDTag = e.target
        this.init.activeDate.setDate(day)
        console.log(
          '여기',
          this.clickedYear,
          this.clickedMonth + 1,
          this.clickedDate,
        )
        let tempMonth = ''
        let tempDate = ''
        if ((this.clickedMonth + 1).toString().length == 1) {
          tempMonth = '0'
        }
        if (this.clickedDate.toString().length == 1) {
          tempDate = '0'
        }
        this.currentDate =
          this.clickedYear.toString() +
          tempMonth +
          (this.clickedMonth + 1).toString() +
          tempDate +
          this.clickedDate.toString()
      }
    },
    initCalendar() {
      this.loadYYMM(this.init.today)
    },
  },
  created() {
    this.initCalendar()
    let date = new Date()
    let tempMonth = ''
    let tempDate = ''
    if ((date.getMonth() + 1).toString().length == 1) {
      tempMonth = '0'
    }
    if (date.getDate().toString().length == 1) {
      tempDate = '0'
    }
    this.currentDate =
      date.getFullYear().toString() +
      tempMonth +
      (date.getMonth() + 1).toString() +
      tempDate +
      date.getDate().toString()
  },
}
</script>

<style></style>
