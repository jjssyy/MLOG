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
        <div
          id="playerdiv"
          :class="{ 'yes-choice': clicked[idx], 'no-choice': !clicked[idx] }"
          @click="selectGenre(idx)"
        >
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
import SurveyApi from '@/api/SurveyApi'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      count: 0,
      emotion: ['neutral', 'joy', 'sadness', 'anger', 'fear'],
      neutral: [],
      joy: [],
      sadness: [],
      anger: [],
      fear: [],
      surveyMusicSelect: Array.from(Array(5), () => Array(3).fill(null)),
      playerVars: {
        autoplay: 1,
      },
      musicVideoId: '',
      defaultVideo: '',
      clicked: [
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
      ],
      playing: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      musicList: [
        // { genre: '국내 발라드', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '팝/어쿠스틱', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '락/메탈', videoId: 'BBJa32lCaaY', music_title: 'vue' },
        // { genre: '알앤비', videoId: 'BBJa32lCaaY', music_title: 'vue' },
        // { genre: '힙합', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '트로트', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '인디', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '클래식', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '뉴에이지', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // {
        //   genre: '댄스/일레트로닉',
        //   videoId: 'lG0Ys-2d4MA',
        //   music_title: 'vue',
        // },
        // { genre: 'CCM', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
        // { genre: '포크/블루스', videoId: 'lG0Ys-2d4MA', music_title: 'vue' },
      ],
    }
  },
  props: {
    survey_num: String,
  },
  async created() {
    SurveyApi.getSurveyMusicList(
      res => {
        console.log(res)
        this.musicList = res.data.Survey
        console.log(this.musicList)
      },
      err => {
        console.log(err)
      },
    )
  },
  methods: {
    async endSurvey() {
      var frm = new FormData()
      // for (let i = 0; i < this.emotion.length; i++) {
      //   this.saveSurveyList(i)
      // }
      for (let i = 0; i < 3; i++) {
        if (this.surveyMusicSelect[0][i] != null) {
          frm.append(
            'neutralList[' + i + ']',
            this.musicList[this.surveyMusicSelect[0][i]].genre,
          )
        }
        if (this.surveyMusicSelect[1][i] != null) {
          frm.append(
            'joyList[' + i + ']',
            this.musicList[this.surveyMusicSelect[1][i]].genre,
          )
        }
        if (this.surveyMusicSelect[2][i] != null) {
          frm.append(
            'sadnessList[' + i + ']',
            this.musicList[this.surveyMusicSelect[2][i]].genre,
          )
        }
        if (this.surveyMusicSelect[3][i] != null) {
          frm.append(
            'angetList[' + i + ']',
            this.musicList[this.surveyMusicSelect[3][i]].genre,
          )
        }
        if (this.surveyMusicSelect[4][i] != null) {
          frm.append(
            'fearList[' + i + ']',
            this.musicList[this.surveyMusicSelect[4][i]].genre,
          )
        }
      }
      const data = {
        id: this.uid,
      }
      // console.log(data.neutralList)
      // console.log(data.joyList)
      // console.log(typeof data.neutralList)
      // console.log(typeof data.neutralList[0])
      // console.log(typeof data)

      for (var key of frm.keys()) {
        console.log(key)
      }

      for (var value of frm.values()) {
        console.log(value)
      }
      SurveyApi.enrollSurvey(
        data,
        frm,
        res => {
          console.log(res)
        },
        err => {
          console.log(err)
        },
      )
    },
    saveSurveyList(num) {
      for (let i = 0; i < 3; i++) {
        if (this.surveyMusicSelect[num][i] != null) {
          this[this.emotion[num]].push(
            this.musicList[this.surveyMusicSelect[num][i]].genre,
          )
        }
      }
    },
    prevPage() {
      if (this.survey_num != '1') {
        this.selectMusicList(this.survey_num)
        let survey_num = parseInt(this.survey_num) - 1
        this.$router.push({
          name: 'Survey',
          params: { survey_num: String(survey_num) },
        })
        this.stopingIdx()
        this.musicVideoId = ''
        this.selected(survey_num)
      } else {
        this.stopingIdx()
        this.musicVideoId = ''
        this.count = 0
        this.$router.push({ name: 'SurveyStart' })
      }
    },
    nextPage() {
      if (this.survey_num != '5') {
        if (this.count > 0) {
          this.selectMusicList(this.survey_num)
          let survey_num = parseInt(this.survey_num) + 1
          this.$router.push({
            name: 'Survey',
            params: { survey_num: String(survey_num) },
          })
          this.stopingIdx()
          this.musicVideoId = ''
          this.selected(survey_num)
        } else {
          alert('최소 1가지 이상 골라주세요')
        }
      } else {
        this.selectMusicList(this.survey_num)
        this.stopingIdx()
        this.musicVideoId = ''
        this.count = 0
        this.endSurvey()
        this.$router.push({ name: 'Main' })
      }
    },
    selectGenre(idx) {
      if (this.count >= 3 && this.clicked[idx] == false) {
        alert('최대 3가지만 골라주세요.')
        return
      }
      this.$set(this.clicked, idx, !this.clicked[idx])
      if (this.clicked[idx]) {
        this.count += 1
      } else {
        this.count -= 1
      }
    },
    async playVideo(idx) {
      this.musicVideoId = this.musicList[idx].videoId
      await this.playingIdx(idx)
    },
    async stopVideo(idx) {
      this.$set(this.playing, idx, 0)
      await this.$nextTick()
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
          this.$set(this.playing, idx, 1)
        }
      }
    },
    selectMusicList(sn) {
      let count = 0
      for (let i = 0; i <= this.clicked.length; i++) {
        if (this.clicked[i] == true) {
          this.surveyMusicSelect[sn - 1][count] = i
          this.clicked[this.surveyMusicSelect[sn - 1][count++]] = false
        }
      }
    },
    selected(sn) {
      if (this.surveyMusicSelect[sn - 1]) {
        for (let i = 0; i <= this.surveyMusicSelect[sn - 1].length; i++) {
          if (this.surveyMusicSelect[sn - 1][i] != null) {
            this.clicked[this.surveyMusicSelect[sn - 1][i]] = true
          }
        }
        this.count = this.clicked.filter(element => true === element).length
      }
    },
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
    ...mapState(['uid']),
  },
}
</script>
