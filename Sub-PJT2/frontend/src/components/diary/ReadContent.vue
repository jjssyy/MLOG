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
        {{ customDate }}
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
                <span @click="clickDelete">
                  <i class="far fa-trash-alt"></i>
                  삭제
                </span>
              </div>
            </div>
          </div>
        </span>
      </p>
      <div class="diary-content">
        <textarea
          readonly="readonly"
          id="content"
          type="text"
          v-model="diary.diaryInfo.content"
        ></textarea>
      </div>
      <button
        v-if="isUpdate"
        style="width: 92.69px; background-color: #ff8585;"
        class="diary-btn"
        @click="cancelUpdate"
      >
        취소
      </button>
      <button v-if="isUpdate" class="diary-btn" @click="showAlert">
        수정 완료
      </button>
    </div>
  </div>
</template>

<script>
import { updateDiary } from '@/api/diary.js'
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
    }
  },
  computed: {
    customDate() {
      return (
        this.date.substring(0, 4) +
        '년 ' +
        this.date.substring(4, 6) +
        '월 ' +
        this.date.substring(6, 8) +
        '일 '
      )
    },
  },
  methods: {
    previousPage() {
      this.$router.go(-1)
    },
    ellipsisMenu() {
      this.isMenu = !this.isMenu
    },
    diaryUpdate() {
      let textArea = document.getElementById('content')
      textArea.readOnly = false
      textArea.focus()
      this.isUpdate = true
    },
    cancelUpdate() {
      let textArea = document.getElementById('content')
      textArea.readOnly = true
      this.isUpdate = false
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
      const response = await updateDiary(data)
      console.log(response)
      this.$router.push(`/diary/${this.$route.params.date}/music`)
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
  },
}
</script>

<style></style>
