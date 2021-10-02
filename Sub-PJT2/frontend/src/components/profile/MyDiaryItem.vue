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
        params: { date: this.myDiary.date },
      })
    },
  },
  computed: {
    customDate() {
      return (
        this.myDiary.date.substring(0, 4) +
        '년 ' +
        this.myDiary.date.substring(4, 6) +
        '월 ' +
        this.myDiary.date.substring(6, 8) +
        '일 '
      )
    },
  },
}
</script>
