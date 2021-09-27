<template>
  <div v-if="survey_num >= '1' && survey_num <= '5'" class="survey-box">
    <div class="title">
      <h1 v-if="survey_num == '1'">당신은 평소에</h1>
      <h1 v-if="survey_num == '2'">당신은 행복할 때</h1>
      <h1 v-if="survey_num == '3'">당신은 짜증나거나 화날 때</h1>
      <h1 v-if="survey_num == '4'">당신은 우울하거나 슬플 때</h1>
      <h1 v-if="survey_num == '5'">당신은 불안하거나 공포스러울 때</h1>
      <h1>어떤 분위기의 음악을</h1>
      <h1>들으시나요? (최대 3가지)</h1>
    </div>
    <div class="music-list">
      <youtube
        id="genre-music"
        :video-id="musicVideoId || defaultVideo"
        :player-vars="playerVars"
        ref="youtube"
        style="display:none;"
      ></youtube>
      <div v-for="(musicItem, idx) in musicList" :key="idx">
        <div id="playerdiv">
          <div>
            {{ musicItem.genre }}
          </div>
          <div>
            <span v-show="playing[idx] == 0">
              <button @click="playVideo(idx)">
                <i class="fas fa-play"></i>
              </button>
            </span>
            <span v-show="playing[idx] == 1">
              <button @click="stopVideo(idx)">
                <i class="fas fa-pause"></i>
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
      musicVideoId: '',
      defaultVideo: '',
      playing: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      musicList: [
        { genre: 'dom_ballad', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_pop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_po', video_id: 'BBJa32lCaaY', music_title: 'vue' },
        { genre: 'over_pp', video_id: 'BBJa32lCaaY', music_title: 'vue' },
        { genre: 'ovedr_pop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'ovedr_psop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_fpop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
        { genre: 'over_pfop', video_id: 'lG0Ys-2d4MA', music_title: 'vue' },
      ],
    }
  },
  props: {
    survey_num: String,
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
      if (this.survey_num != '1') {
        let survey_num = parseInt(this.survey_num) - 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: String(survey_num) },
        })
        this.stopingIdx()
        this.musicVideoId = ''
      } else {
        this.stopingIdx()
        this.musicVideoId = ''
        this.$router.push({ name: 'SurveyStart' })
      }
    },
    nextPage() {
      if (this.survey_num != '5') {
        let survey_num = parseInt(this.survey_num) + 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: String(survey_num) },
        })
        this.stopingIdx()
        this.musicVideoId = ''
      } else {
        this.stopingIdx()
        this.musicVideoId = ''
        this.$router.push({ name: 'Main' })
      }
    },
    async playVideo(idx) {
      this.musicVideoId = this.musicList[idx].video_id
      await this.playingIdx(idx)
    },
    async stopVideo(idx) {
      this.playing[idx] = 0
      await this.player.stopVideo()
    },
    stopingIdx() {
      this.player.stopVideo()
      for (let i = 0; i < this.playing.length; i++) {
        this.playing[i] = 0
      }
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
