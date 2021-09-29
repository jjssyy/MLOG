<template>
  <div style="background-color: #DAEEDC;">
    <p>내 일기장</p>
    <!-- Content -->
    <ReadContent :content="content"></ReadContent>
    <!-- additional Content -->
    <ReadAddContent></ReadAddContent>
    <button @click="moveUpdate">UPDATE</button>
    <button @click="clickDelete">DELETE</button>
    <div v-if="isClkDelBtn">
      <DeleteModal @answer="confirmDelete"></DeleteModal>
    </div>
  </div>
</template>

<script>
import ReadContent from '@/components/diary/ReadContent.vue'
import ReadAddContent from '@/components/diary/ReadAddContent.vue'
import DeleteModal from '@/components/diary/DeleteModal.vue'
import { fetchDiary, deleteDiary } from '@/api/diary.js'
export default {
  components: {
    ReadContent,
    ReadAddContent,
    DeleteModal,
  },
  data() {
    return {
      content: {},
      isClkDelBtn: false,
    }
  },
  methods: {
    moveUpdate() {
      this.$router.push(`/diary/${this.$route.params.date}/update`)
    },
    clickDelete() {
      this.isClkDelBtn = true
    },
    async confirmDelete(answer) {
      // true면 삭제해라.
      if (answer) {
        const response = await deleteDiary()
        console.log(response)
      }
    },
  },
  async created() {
    let date =
      this.$route.params.date.substring(0, 4) +
      '-' +
      this.$route.params.date.substring(4, 2) +
      '-' +
      this.$route.params.date.substring(6, 2)

    const data = {
      date: date,
    }
    const response = await fetchDiary(this.$store.state.uid, data)
    console.log(response.data)
    this.content = response.data
  },
}
</script>

<style></style>
