<template>
  <h1>추천음악들 목록 페이지</h1>
</template>

<script>
// fetchRcdMusic
import { fetchDiary, fetchRcdMusic } from '@/api/diary.js'
export default {
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
    console.log(responseOne.data.diaryInfo.diary_id)
    const responseTwo = await fetchRcdMusic(
      this.$store.state.uid,
      responseOne.data.diaryInfo.diary_id,
    )
    console.log(responseTwo)
    // const responseTwo = await fetchRcdMusic()
    // console.log(responseTwo)
  },
}
</script>

<style></style>
