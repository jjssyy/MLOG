<template>
  <div id="music-diary" style="height: 770px; overflow-y: scroll;">
    <br />
    <div v-for="(music, index) in musicList" :key="music.id">
      <div v-if="index < 3">
        <h3
          v-if="index == 0"
          class="subtitle"
          style="justify-content: space-between"
        >
          <p>이 음악은 어때요?</p>
          <div class="center">
            <span @click="resetData">
              <i class="fas fa-redo fa-lg"></i>
            </span>
          </div>
        </h3>
        <MusicItem
          :playingId="playingId"
          :music="music"
          @playVideo="anotherStopVideo"
          @selectMusic="selectMusic"
        ></MusicItem>
      </div>
      <div v-else>
        <h3 v-if="index == 3" class="subtitle">
          <p>다른 분위기</p>
          <p>음악은 어때요?</p>
        </h3>
        <MusicItem
          :playingId="playingId"
          :music="music"
          @playVideo="anotherStopVideo"
          @selectMusic="selectMusic"
        ></MusicItem>
      </div>
    </div>
    <div style="display: flex; justify-content: flex-end;">
      <button @click="showAlert" class="createBtn">
        선택 완료
      </button>
    </div>
  </div>
</template>

<script>
// fetchRcdMusic
import { fetchRcdMusic, submitMusic } from '@/api/diary.js'
import MusicItem from '@/components/diary/MusicItem.vue'
import '@/assets/css/views/init.scss'
import '@/components/css/music.scss'
export default {
  components: {
    MusicItem,
  },
  data() {
    return {
      musicList: [],
      musicId: null,
      playingId: 'initial',
    }
  },
  async created() {
    const { data } = await fetchRcdMusic(
      this.$store.state.uid,
      this.$route.params.diaryid,
    )
    this.musicList = data.result
    console.log(this.musicList)
  },
  methods: {
    async resetData() {
      const { data } = await fetchRcdMusic(
        this.$store.state.uid,
        this.$route.params.diaryid,
      )
      if (this.musicId) {
        document
          .querySelector(`div[number="${this.musicId}"]`)
          .classList.remove('select-green')
      }
      this.musicId = null
      this.musicList = data.result
    },
    selectMusic(value) {
      if (this.musicId) {
        document
          .querySelector(`div[number="${this.musicId}"]`)
          .classList.remove('select-green')
      }
      this.musicId = value
      document
        .querySelector(`div[number="${this.musicId}"]`)
        .classList.add('select-green')
      console.log('선택한 음악', this.musicId)
    },
    async submitForm() {
      const data = {
        diary_id: this.$route.params.diaryid,
        mid: this.musicId,
      }
      this.$swal({
        icon: 'success',
        title: '완료되었습니다.',
        showConfirmButton: false,
        target: '#music-diary',
        width: '370px',
        timer: 1500,
        customClass: {
          container: 'modal-custom',
        },
      })
      const response = await submitMusic(this.$store.state.uid, data)
      setTimeout(() => {
        console.log('완료', response)
        this.$router.push(`/diary/${this.$route.params.date}`)
      }, 1500)
    },
    anotherStopVideo(value) {
      this.playingId = value
    },
    showAlert() {
      let result = []
      for (let i = 0; i < this.musicList.length; i++) {
        if (this.musicList[i].mid == this.musicId) {
          result = this.musicList[i]
          break
        }
      }
      // Use sweetalert2
      this.$swal({
        imageUrl: `${result.filePath}`,
        title: `${result.musicTitle} - ${result.musicArtist}`,
        showDenyButton: true,
        confirmButtonText: '완료',
        denyButtonText: '취소',
        target: '#music-diary',
        width: '370px',
        customClass: {
          title: 'sweetCustom',
          container: 'modal-custom',
        },
      }).then(result => {
        if (result.isConfirmed) {
          this.submitForm()
        }
      })
    },
  },
}
</script>

<style></style>
