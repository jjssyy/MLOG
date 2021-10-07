<template>
  <div :number="music.mid">
    <div style="position: relative;">
      <div class="music-title-container" @click="selectMusic">
        <img :src="music.filePath" alt="음악 포스터" />
        <div class="music-title">
          <p>{{ music.musicTitle }}</p>
          <p>{{ music.musicArtist }}</p>
        </div>
      </div>
      <div class="play-stop-btn">
        <div class="video-container">
          <youtube
            id="genre-music"
            :video-number="music.mid"
            :video-id="music.videoId"
            ref="youtube"
          ></youtube>
        </div>
        <button v-if="isStop" class="btn-reset" @click="playStart">
          <span>
            <i class="fas fa-play fa-lg"></i>
          </span>
        </button>
        <button v-if="!isStop" class="btn-reset" @click="stopStart">
          <span>
            <i class="fas fa-pause fa-lg"></i>
          </span>
        </button>
      </div>
    </div>
    <hr />
  </div>
</template>

<script>
export default {
  props: {
    music: {
      type: Object,
      required: true,
    },
    playingId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      selectedMusic: null,
      isStop: true,
    }
  },
  methods: {
    selectMusic() {
      this.$emit('selectMusic', this.music.mid)
    },
    async playStart() {
      this.isStop = false
      this.$emit('playVideo', this.music.videoId)
      await this.playVideo()
    },
    async stopStart() {
      this.isStop = true
      await this.stopVideo()
    },
    async playVideo() {
      await this.player.playVideo()
    },
    async stopVideo() {
      await this.player.stopVideo()
    },
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
  },
  watch: {
    playingId: async function() {
      if (this.music.videoId != this.playingId) {
        await this.stopStart()
      }
    },
  },
}
</script>

<style></style>
