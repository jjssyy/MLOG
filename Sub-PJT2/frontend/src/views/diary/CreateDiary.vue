<template>
  <div
    id="create-diary"
    style="background-color: #DAEEDC; position: relative;  height: 770px; overflow-y: scroll;"
  >
    <Header></Header>
    <br />
    <div class="diary-container">
      <div class="diary-title">
        <span @click="previousPage">
          <i class="fas fa-angle-left" style="font-size: 2.5rem;"></i>
        </span>
        <div class="center">
          <p>내 일기장</p>
        </div>
      </div>
      <p class="diary-title">{{ customDate }}</p>
      <!-- 일기 작성 폼 -->
      <CreateForm></CreateForm>
    </div>
  </div>
</template>

<script>
import CreateForm from '@/components/diary/CreateForm.vue'
import '../../assets/css/views/init.scss'
import '@/components/css/diary.scss'
import Header from '@/components/profile/Header.vue'
export default {
  components: {
    CreateForm,
    Header,
  },
  data() {
    return {
      date: this.$route.params.date,
      isLoading: false,
    }
  },
  computed: {
    customDate() {
      return (
        this.date.substring(0, 4) +
        '년 ' +
        this.date.substring(4, 6) +
        '월 ' +
        this.date.substring(6, 8) +
        '일 '
      )
    },
  },
  methods: {
    previousPage() {
      this.$router.go(-1)
    },
  },
}
</script>

<style></style>
