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
    }
  },
  props: {
    myDiary: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    goDiary() {
      this.$router.push({
        name: 'ReadDiary',
        params: { date: this.myDiary.diaryDate },
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
