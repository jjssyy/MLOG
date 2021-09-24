<template>
  <div id="playerdiv">
    <div>
      {{ musicItem.genre }}
      {{ playing }}
    </div>
    <youtube
      :video-id="musicItem.video_id"
      ref="youtube"
      @playing="playing"
      style="display:none;"
    ></youtube>
    <div>
      <span v-show="playing[idx] == false">
        <button @click="playVideo(idx)">
          <i class="fas fa-play"></i>
        </button>
      </span>
      <span v-show="playing[idx] == true">
        <button @click="stopVideo(idx)">
          <i class="fas fa-stop"></i>
        </button>
      </span>
    </div>
  </div>
</template>

<script>
import '../../assets/css/views/survey.scss'
import { mapState } from 'vuex'

export default {
  props: {
    musicItem: {
      type: [Array, Object],
    },
    idx: Number,
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
    ...mapState(['playing']),
  },
  methods: {
    playVideo(idx) {
      this.$store.dispatch('playMusic', idx)
      this.player.playVideo()
    },
    stopVideo(idx) {
      this.$store.dispatch('stopMusic', idx)
      this.player.stopVideo()
    },
  },
}
</script>
