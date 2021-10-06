<template>
  <div class="mydiary-item">
    <div class="item-box" @click="goDiary">
      <div class="item-date">
        {{ customDate }}
      </div>
      <div class="item-contents">
        {{ myDiary.content }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: '',
      paramsdate: '',
    }
  },
  props: {
    myDiary: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    customDate2(myDiary) {
      this.paramsdate =
        myDiary.diaryDate.substring(0, 4) +
        myDiary.diaryDate.substring(5, 7) +
        myDiary.diaryDate.substring(8, 10)
    },
    goDiary() {
      this.customDate2(this.myDiary)
      this.$router.push({
        name: 'ReadDiary',
        params: { date: this.paramsdate },
      })
    },
  },
  computed: {
    customDate() {
      return (
        this.myDiary.diaryDate.substring(0, 4) +
        '년 ' +
        this.myDiary.diaryDate.substring(5, 7) +
        '월 ' +
        this.myDiary.diaryDate.substring(8, 10) +
        '일 '
      )
    },
  },
}
</script>
