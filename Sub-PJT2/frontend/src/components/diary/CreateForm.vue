<template>
  <div>
    <div class="diary-content">
      <textarea id="content" type="text" v-model="content"></textarea>
    </div>
    <button class="diary-btn" @click="submitForm">작성 완료</button>
  </div>
</template>

<script>
import { createDiary } from '@/api/diary.js'
export default {
  data() {
    return {
      content: null,
    }
  },
  methods: {
    async submitForm() {
      let date =
        this.$route.params.date.substring(0, 4) +
        '-' +
        this.$route.params.date.substring(4, 6) +
        '-' +
        this.$route.params.date.substring(6, 8)
      const data = {
        content: this.content,
        date: date,
      }
      console.log(this.$store.state.uid)
      console.log(data)
      const response = await createDiary(this.$store.state.uid, data)
      console.log(response)
      this.$router.push(`/diary/${this.$route.params.date}/music`)
    },
  },
}
</script>

<style></style>
