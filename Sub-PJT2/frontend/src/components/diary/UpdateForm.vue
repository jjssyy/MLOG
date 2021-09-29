<template>
  <div>
    <form @submit.prevent="submitForm">
      <!-- model하고 연결 아직 안했음(v-model="미정")-->
      <textarea id="content" type="text"></textarea>
      {{ content }}
      <button>수정 완료</button>
    </form>
  </div>
</template>

<script>
import { fetchDiary, updateDiary } from '@/api/diary.js'
export default {
  data() {
    return {
      content: {},
    }
  },
  methods: {
    async submitForm() {
      const response = await updateDiary()
      console.log(response)
    },
  },
  async created() {
    const data = {
      date: this.$route.params.date,
    }
    const response = await fetchDiary(this.$store.state.uid, data)
    console.log(response.data)
    this.content = response.data
  },
}
</script>

<style></style>
