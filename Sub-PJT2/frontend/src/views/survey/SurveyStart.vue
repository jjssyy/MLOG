<template>
  <div class="init-box">
    <div class="surveyStart-box">
      <div class="input-title">
        <h1>당신의 음악 취향을</h1>
        <h1>알려주세요</h1>
      </div>
      <div class="logo"></div>
      <div class="btn-box2">
        <button @click="prevPage">이전</button>
        <button @click="startSurvey">시작하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import '../../assets/css/views/init.scss'

export default {
  methods: {
    startSurvey() {
      this.$router.push({ name: 'Survey', params: { survey_num: '1' } })
    },
    prevPage() {
      this.$router.push({ name: 'InitProfileImg' })
    },
  },
}
</script>
