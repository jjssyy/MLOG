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
        <i style="font-size:1.6rem; font-style: normal">&#128521;</i>
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
          style="min-height: auto;"
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
      <button v-if="isUpdate" class="diary-btn" @click="submitForm">
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
      const response = await updateDiary(data)
      console.log(response)
    },
  },
}
</script>

<style></style>
