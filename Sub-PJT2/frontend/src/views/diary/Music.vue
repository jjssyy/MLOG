<template>
  <div>
    <h1>추천음악들 목록 페이지</h1>
    <div v-for="(music, index) in musicList" :key="music.id">
      <div v-if="index < 3">
        <h3 v-if="index == 0">3개 음악</h3>
        <MusicItem :music="music" @selectMusic="selectMusic"></MusicItem>
      </div>
      <div v-else>
        <h3 v-if="index == 3">2개 음악</h3>
        <MusicItem :music="music" @selectMusic="selectMusic"></MusicItem>
      </div>
    </div>
    <button @click="submitForm">선택 완료</button>
  </div>
</template>

<script>
// fetchRcdMusic
import { fetchDiary, fetchRcdMusic, submitMusic } from '@/api/diary.js'
import MusicItem from '@/components/diary/MusicItem.vue'
export default {
  components: {
    MusicItem,
  },
  data() {
    return {
      musicList: [],
      musicId: null,
      diaryId: null,
    }
  },
  async created() {
    let date =
      this.$route.params.date.substring(0, 4) +
      '-' +
      this.$route.params.date.substring(4, 6) +
      '-' +
      this.$route.params.date.substring(6, 8)
    const fetchDiaryData = {
      date: date,
    }
    const responseOne = await fetchDiary(this.$store.state.uid, fetchDiaryData)
    this.diaryId = responseOne.data.diaryInfo.diary_id
    const { data } = await fetchRcdMusic(this.$store.state.uid, this.diaryId)
    this.musicList = data.result
    console.log(this.musicList)
  },
  methods: {
    selectMusic(value) {
      this.musicId = value
      console.log('선택한 음악', this.musicId)
    },
    async submitForm() {
      const data = {
        diary_id: this.diaryId,
        mid: this.musicId,
      }
      const response = await submitMusic(this.$store.state.uid, data)
      console.log(response)
    },
  },
}
</script>

<style></style>
