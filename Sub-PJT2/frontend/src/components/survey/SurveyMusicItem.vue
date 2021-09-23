<template>
  <div id="playerdiv">
    <div>
      {{ idx }}
    </div>
    <youtube
      :video-id="musicItem.video_id"
      ref="youtube"
      @playing="playing"
      style="display:none;"
    ></youtube>
    <div>
      <span v-show="playing == true">
        <button @click="playVideo">
          <i class="fas fa-play"></i>
        </button>
      </span>
      <span v-show="playing == false">
        <button @click="stopVideo">
          <i class="fas fa-stop"></i>
        </button>
      </span>
    </div>
  </div>
</template>

<script>
import '../../assets/css/views/survey.scss'

export default {
  data() {
    return {
      playing: true,
    }
  },
  props: {
    musicItem: {
      type: [Array, Object],
    },
    idx: String,
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
  },
  methods: {
    playVideo() {
      this.playing = false
      this.player.playVideo()
    },
    stopVideo() {
      this.playing = true
      this.player.stopVideo()
    },
  },
}
</script>
