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
      <div v-if="num >= 0">
        <youtube
          id="genre-music"
          :video-id="musicList[num].video_id"
          :player-vars="playerVars"
          ref="youtube"
          style="display:none;"
        ></youtube>
      </div>
      <div v-for="(musicItem, idx) in musicList" :key="idx">
        <div id="playerdiv">
          <div>
            {{ musicItem.genre }}
            {{ playing[idx] }}
          </div>
          <div>
            <span v-show="playing[idx] == 0">
              <button @click="playVideo(idx)">
                <i class="fas fa-play"></i>
              </button>
            </span>
            <span v-show="playing[idx] == 1">
              <button @click="stopVideo(idx)">
                <i class="fas fa-stop"></i>
              </button>
            </span>
          </div>
        </div>
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

export default {
  data() {
    return {
      playerVars: {
        autoplay: 1,
      },
      num: -1,
      playing: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      musicList: [
        { genre: 'dom_ballad', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_pop', video_id: 'BBJa32lCaaY', music_title: 'vue' },
        { genre: 'over_po', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_pp', video_id: 'BBJa32lCaaY', music_title: 'vue' },
        { genre: 'ovedr_pop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'ovedr_psop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_fpop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_pfop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
      ],
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
        console.log(this.musicList)
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
        this.stopingIdx()
        this.num = -1
      } else {
        this.$router.push({ name: 'SurveyStart' })
        this.stopingIdx()
      }
    },
    nextPage() {
      if (this.survey_num != 5) {
        let survey_num = parseInt(this.survey_num) + 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: survey_num },
        })
        this.stopingIdx()
        this.num = -1
      } else {
        this.$router.push({ name: 'Main' })
      }
    },
    playVideo(idx) {
      this.num = idx
      this.playingIdx(idx)
      console.log('재생')
    },
    stopVideo(idx) {
      this.playing[idx] = false
      this.player.stopVideo()
      this.stopingIdx()
      console.log('스탑')
    },
    stopingIdx() {
      this.player.stopVideo()
      for (let i = 0; i < this.playing.length; i++) {
        this.playing[i] = 0
      }
      console.log(this.playing)
    },
    playingIdx(idx) {
      for (let i = 0; i < this.playing.length; i++) {
        if (i != idx) {
          this.playing[i] = 0
        } else {
          this.playing[idx] = 1
        }
      }
    },
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
  },
}
</script>
