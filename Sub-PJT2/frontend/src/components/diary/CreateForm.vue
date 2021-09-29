<template>
  <div>
    <form @submit.prevent="submitForm">
      <textarea id="content" type="text" v-model="content"></textarea>
      <button type="submit">제출</button>
    </form>
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
        this.$route.params.date.substring(4, 2) +
        '-' +
        this.$route.params.date.substring(6, 2)
      const data = {
        content: this.content,
        diary_date: date,
      }
      console.log(this.$store.state.uid)
      console.log(data)
      const response = await createDiary(this.$store.state.uid, data)
      console.log(response)
    },
  },
}
</script>

<style></style>
