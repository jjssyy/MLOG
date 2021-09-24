<template>
  <div id="playerdiv">
    <div>
      {{ musicItem.genre }}
    </div>
    <!-- <div id="youtubeP" data-url="musicItem.video_id"></div> -->
    <div>
      <span>
        <button @click="playVideo(idx)">
          <i class="fas fa-play"></i>
        </button>
      </span>
      <span>
        <button @click="stopVideo()">
          <i class="fas fa-stop"></i>
        </button>
      </span>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '../../assets/css/views/survey.scss'

// var videos = document.getElementsByClassName('youtubeP')
// for (var i = 0; i < videos.length; i++) {
//   var youtube = videos[i]
//   var iframe = document.createElement('iframe')
//   var geturl = youtube.getAttribute('data-url')
//   iframe.setAttribute(
//     'src',
//     'https://www.youtube.com/embed/' + geturl + '?&enablejsapi=1&html5-1',
//   )
//   iframe.setAttribute('frameborder', 0)
//   youtube.appendChild(iframe)
//   youtube.onPlayerReady = function(event) {
//     event.target.mute()
//   }
//   var player // eslint-disable-line no-unused-vars
//   player = window.YT.Player(youtube, {
//     playerVars: {
//       width: '100%',
//       height: '100%',
//       autoplay: 1,
//       controls: 0,
//       autohide: 1,
//       wmode: 'opaque',
//       showinfo: 0,
//       loop: 1,
//       mute: 1,
//       rel: 0,
//       playlist: geturl,
//     },
//     videoId: geturl,
//     events: {
//       onReady: youtube.onPlayerReady,
//     },
//   })
//   //player
// }
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
    idx: Number,
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
    ...mapState(['playoh']),
  },
  methods: {
    playVideo(idx) {
      var genre = document.querySelector('#genre-music')
      console.log(genre.src)
      console.log(this.playoh)
      this.$store.state.playoh = idx
      // this.$store.dispatch('stopMusic')
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
