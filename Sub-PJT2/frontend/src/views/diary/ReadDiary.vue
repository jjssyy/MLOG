<template>
  <div
    id="read-diary"
    style="background-color: #DAEEDC; height: 770px; overflow-y: scroll;"
  >
    <Header></Header>
    <br />
    <!-- Content -->
    <ReadContent :diary="content"></ReadContent>
    <!-- additional Content -->
    <ReadAddContent :diary="addContent"></ReadAddContent>
    <div v-if="isClkDelBtn">
      <DeleteModal @answer="confirmDelete"></DeleteModal>
    </div>
  </div>
</template>

<script>
import ReadContent from '@/components/diary/ReadContent.vue'
import ReadAddContent from '@/components/diary/ReadAddContent.vue'
import Header from '@/components/profile/Header.vue'
import DeleteModal from '@/components/diary/DeleteModal.vue'
import { fetchDiary, deleteDiary } from '@/api/diary.js'

import '../../assets/css/views/init.scss'
import '@/components/css/diary.scss'

export default {
  components: {
    ReadContent,
    ReadAddContent,
    DeleteModal,
    Header,
  },
  data() {
    return {
      content: { diaryInfo: { content: '', sentiment: 0 } },
      addContent: {},
      isClkDelBtn: false,
    }
  },
  methods: {
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
      this.$route.params.date.substring(4, 6) +
      '-' +
      this.$route.params.date.substring(6, 8)

    const data = {
      date: date,
    }
    console.log(data)
    console.log(this.$store.state.uid)
    const response = await fetchDiary(this.$store.state.uid, data)
    console.log(response.data)
    this.content = response.data
    this.addContent = response.data.recommendDiary
  },
}
</script>

<style></style>
