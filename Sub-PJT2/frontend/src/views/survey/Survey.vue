<template>
  <div v-if="survey_num >= 1 && survey_num <= 5" class="survey-box">
    <div class="title">
      <h1 v-if="survey_num == 1">당신은 평소에</h1>
      <h1 v-if="survey_num == 2">당신은 행복할 때</h1>
      <h1 v-if="survey_num == 3">당신은 짜증나거나 화날 때</h1>
      <h1 v-if="survey_num == 4">당신은 우울하거나 슬플 때</h1>
      <h1 v-if="survey_num == 5">당신은 불안하거나 공포스러울 때</h1>
      <h1>어떤 분위기의 음악을</h1>
      <h1>들으시나요? (최대 3가지)</h1>
    </div>
    <div class="music-list">
      <div v-for="(musicItem, idx) in musicList" :key="idx">
        <survey-music-item
          :musicItem="musicItem"
          :idx="idx"
        ></survey-music-item>
      </div>
    </div>
    <div class="survey-footer">
      <div class="prev" @click="prevPage">
        <i class="fas fa-arrow-left"></i>
      </div>
      <div class="next" @click="nextPage">
        <i class="fas fa-arrow-right"></i>
      </div>
    </div>
  </div>
</template>

<script>
import '../../assets/css/views/survey.scss'
import SurveyApi from '../../api/SurveyApi'
import SurveyMusicItem from '../../components/survey/SurveyMusicItem'

export default {
  components: {
    SurveyMusicItem,
  },
  data() {
    return {
      musicList: {
        dom_ballad: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        over_pop: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        over_po: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        over_pp: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        ovedr_pop: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        ovedr_psop: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        over_fpop: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        over_pfop: { video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
      },
    }
  },
  props: {
    survey_num: Number,
  },
  created() {
    SurveyApi.getSurveyMusicList(
      res => {
        console.log(res.data)
        this.musicList = res.data
      },
      err => {
        console.log(err)
      },
    )
  },
  methods: {
    prevPage() {
      if (this.survey_num != 1) {
        let survey_num = parseInt(this.survey_num) - 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: survey_num },
        })
      } else {
        this.$router.push({ name: 'SurveyStart' })
      }
    },
    nextPage() {
      if (this.survey_num != 5) {
        let survey_num = parseInt(this.survey_num) + 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: survey_num },
        })
      } else {
        this.$router.push({ name: '' })
      }
    },
  },
}
</script>
