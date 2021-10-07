<template>
  <div>
    <div class="diary-container">
      <div class="diary-title">
        <span @click="previousPage">
          <i class="fas fa-angle-left" style="font-size: 2.5rem;"></i>
        </span>
        <div class="center">
          <p>내 일기장</p>
        </div>
      </div>
      <p class="diary-title">
        {{
          '' +
            this.date.substring(0, 4) +
            '년 ' +
            this.date.substring(4, 6) +
            '월 ' +
            this.date.substring(6, 8) +
            '일 '
        }}
        <i
          v-if="diary.diaryInfo.sentiment > 0.2"
          style="font-size:1.6rem; font-style: normal"
          >&#128522;</i
        >
        <i
          v-else-if="diary.diaryInfo.sentiment >= -0.2"
          style="font-size:1.6rem; font-style: normal"
          >&#128528;</i
        >
        <i v-else style="font-size:1.6rem; font-style: normal">&#128543;</i>
        <span
          v-if="!isUpdate"
          @click="ellipsisMenu"
          class="side-menu"
          :class="{ 'menu-border': isMenu }"
        >
          <i class="fas fa-ellipsis-v fa-lg"></i>
          <div v-if="isMenu">
            <div class="modal">
              <div class="dialog">
                <span @click="diaryUpdate">
                  <i class="far fa-edit"></i>
                  수정
                </span>
                <span @click="showDelete">
                  <i class="far fa-trash-alt"></i>
                  삭제
                </span>
              </div>
            </div>
          </div>
        </span>
      </p>
      <div style="position: relative; margin-top: 2.25rem;">
        <div class="music-title-container-two">
          <img :src="diary.diaryInfo.filePath" alt="음악포스터" />
          <div class="music-title-two">
            <p>{{ diary.diaryInfo.musicTitle }}</p>
            <p>{{ diary.diaryInfo.musicArtist }}</p>
          </div>
        </div>
        <div class="play-stop-btn">
          <div class="video-container">
            <youtube
              id="genre-music"
              :video-id="diary.diaryInfo.videoId"
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

      <div class="diary-content">
        <textarea
          readonly="readonly"
          id="content"
          type="text"
          v-model="diary.diaryInfo.content"
        ></textarea>
      </div>
      <div
        v-if="isUpdate"
        :class="{ 'error-text': textLength > 255 ? true : false }"
      >
        {{ textLength }}/255
      </div>
      <button
        v-if="isUpdate"
        style="width: 92.69px; background-color: #ff8585;"
        class="diary-btn"
        @click="cancelUpdate"
      >
        취소
      </button>
      <button
        v-if="isUpdate"
        class="diary-btn"
        :disabled="!diary.diaryInfo.content || textLength > 255"
        @click="showAlert"
      >
        수정 완료
      </button>
    </div>
  </div>
</template>

<script>
import { updateDiary, deleteDiary } from '@/api/diary.js'
export default {
  props: {
    diary: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      date: this.$route.params.date,
      isMenu: false,
      isUpdate: false,
      isStop: true,
      tempContent: '',
    }
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    },
    textLength() {
      return this.diary.diaryInfo.content.length
    },
  },
  methods: {
    async playStart() {
      this.isStop = false
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
    previousPage() {
      this.$router.push('/main')
    },
    ellipsisMenu() {
      this.isMenu = !this.isMenu
    },
    diaryUpdate() {
      let textArea = document.getElementById('content')
      textArea.readOnly = false
      textArea.focus()
      this.tempContent = this.diary.diaryInfo.content
      this.isUpdate = true
    },
    cancelUpdate() {
      let textArea = document.getElementById('content')
      textArea.readOnly = true
      this.isUpdate = false
      this.diary.diaryInfo.content = this.tempContent
    },
    async submitForm() {
      let date =
        this.$route.params.date.substring(0, 4) +
        '-' +
        this.$route.params.date.substring(4, 6) +
        '-' +
        this.$route.params.date.substring(6, 8)
      const data = {
        diary_id: this.diary.diaryInfo.diary_id,
        content: this.diary.diaryInfo.content,
        date: date,
      }
      this.$swal({
        title: '분석중',
        html: '잠시만 기다려 주세요.',
        timerProgressBar: true,
        target: '#read-diary',
        width: '370px',
        customClass: {
          container: 'modal-custom',
        },
        didOpen: () => {
          this.$swal.showLoading()
        },
      }).then(result => {
        if (result.dismiss === this.$Swal.DismissReason.timer) {
          console.log('I was closed by the timer')
        }
      })
      let response = await updateDiary(data)
      console.log(response)
      if (response == 'error') {
        this.$swal({
          icon: 'error',
          title: '분석 실패',
          text: '다시 일기를 작성해주세요.',
          target: '#read-diary',
          width: '370px',
          customClass: {
            container: 'modal-custom',
          },
        })
      } else {
        this.$router.push(
          `/diary/${this.$route.params.date}/${response.data['diary_id']}/music`,
        )
      }
    },
    showAlert() {
      // Use sweetalert2
      this.$swal({
        title: '수정하시겠습니까?',
        showDenyButton: true,
        confirmButtonText: '수정',
        denyButtonText: '취소',
        target: '#read-diary',
        width: '370px',
        customClass: {
          container: 'modal-custom',
        },
      }).then(result => {
        if (result.isConfirmed) {
          this.submitForm()
        }
      })
    },
    showDelete() {
      this.$swal({
        title: '삭제하시겠습니까?',
        icon: 'warning',
        target: '#read-diary',
        width: '370px',
        customClass: {
          container: 'modal-custom',
        },
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
      }).then(result => {
        if (result.isConfirmed) {
          this.$swal({
            icon: 'success',
            title: '삭제 완료되었습니다.',
            showConfirmButton: false,
            target: '#read-diary',
            width: '370px',
            timer: 1500,
            customClass: {
              container: 'modal-custom',
            },
          })
          setTimeout(() => {
            this.submitDelete()
          }, 1500)
        }
      })
    },
    async submitDelete() {
      const data = {
        diary_id: this.diary.diaryInfo.diary_id,
      }
      console.log(data)
      await deleteDiary(data)
      this.$router.push('/main')
    },
  },
}
</script>

<style></style>
