<template>
  <div style="background-color: #DAEEDC;">
    <h1>일기 작성 페이지</h1>
    <!-- 일기 작성 폼 -->
    <CreateForm></CreateForm>
  </div>
</template>

<script>
import CreateForm from '@/components/diary/CreateForm.vue'
import '../../assets/css/views/init.scss'
import '@/components/css/diary.scss'
export default {
  components: {
    CreateForm,
  },
}
</script>

<style></style>
