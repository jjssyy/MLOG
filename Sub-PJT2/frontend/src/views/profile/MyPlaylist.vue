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
                <div @click="goDiary(idx)">
                  <h2 style="cursor: pointer;">
                    {{ myPlay.musicTitle }}
                  </h2>
                  <p style="cursor: pointer;">
                    {{ myPlay.musicArtist }}
                  </p>
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
    console.log(response.data)
    this.myPlayList = response.data.playList
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
    goDiary(idx) {
      this.$router.push({
        name: 'ReadDiary',
        params: { date: this.myPlayList[idx].date },
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
        myPlay.date.substring(4, 6) +
        '월 ' +
        myPlay.date.substring(6, 8) +
        '일 '
      )
    },
  },
}
</script>
