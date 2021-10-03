<template>
  <div class="myplay-item">
    <div class="myplay-item-box" @click="goDiary">
      <div class="myplay-item-date">
        {{ customDate }}
      </div>
      <div class="myplay-item-contents">
        <h2>
          {{ myPlay.musicTitle }}
        </h2>
        <h3>
          {{ myPlay.musicArtist }}
        </h3>
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
    myPlay: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    goDiary() {
      this.$router.push({
        name: 'ReadDiary',
        params: { date: this.myPlay.date },
      })
    },
  },
  computed: {
    customDate() {
      return (
        this.myPlay.date.substring(0, 4) +
        '년 ' +
        this.myPlay.date.substring(4, 6) +
        '월 ' +
        this.myPlay.date.substring(6, 8) +
        '일 '
      )
    },
  },
}
</script>
