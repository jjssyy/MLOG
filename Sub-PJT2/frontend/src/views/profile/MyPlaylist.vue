<template>
  <div class="myplaylist">
    <div class="profile-header2">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-music"></i>
          <span>
            내 플레이리스트
          </span>
        </h1>
      </div>
      <youtube
        id="genre-music"
        :video-id="musicVideoId || defaultVideo"
        :player-vars="playerVars"
        ref="youtube"
        style="display:none;"
      ></youtube>
      <div class="myplay-list">
        <div v-for="(myPlay, idx) in myPlayList" :key="idx">
          <div class="myplay-item">
            <div class="myplay-item-date">
              {{ myPlay | customDate }}
            </div>
            <div class="myplay-item-box">
              <div class="myplay-item-contents">
                <div class="myplay-item-right" @click="goDiary(idx)">
                  <div class="myplay-item-img">
                    <img :src="myPlay.filePath" alt="" />
                  </div>
                  <div class="myplay-item-title">
                    <h2 style="cursor: pointer;">
                      {{ myPlay.musicTitle }}
                    </h2>
                    <p style="cursor: pointer;">
                      {{ myPlay.musicAritst }}
                    </p>
                  </div>
                </div>
                <div>
                  <span v-show="playingIdx != idx">
                    <button class="myplay-btn" @click="playVideo(idx)">
                      <i class="fas fa-play"></i>
                    </button>
                  </span>
                  <span v-show="playingIdx == idx">
                    <button class="myplay-btn" @click="stopVideo()">
                      <i class="fas fa-pause"></i>
                    </button>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/mydiary.scss'
import { getMyPlaylist } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      paramsdate: '',
      playerVars: {
        autoplay: 1,
      },
      musicVideoId: '',
      defaultVideo: '',
      playingIdx: -1,
      myPlayList: [],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyPlaylist(data)
    this.myPlayList = response.data.MusicInfoList
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
    goDiary(idx) {
      this.customDate2(this.myPlayList[idx])
      this.$router.push({
        name: 'ReadDiary',
        params: { date: this.paramsdate },
      })
    },
    async playVideo(idx) {
      this.musicVideoId = this.myPlayList[idx].videoId
      this.playingIdx = idx
      await this.player.playVideo()
    },
    async stopVideo() {
      this.playingIdx = -1
      await this.player.stopVideo()
    },
    customDate2(myPlay) {
      this.paramsdate =
        myPlay.date.substring(0, 4) +
        myPlay.date.substring(5, 7) +
        myPlay.date.substring(8, 10)
    },
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
    ...mapState(['uid']),
  },
  filters: {
    customDate(myPlay) {
      return (
        myPlay.date.substring(0, 4) +
        '년 ' +
        myPlay.date.substring(5, 7) +
        '월 ' +
        myPlay.date.substring(8, 10) +
        '일 '
      )
    },
  },
}
</script>
