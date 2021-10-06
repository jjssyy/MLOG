<template>
  <div>
    <div class="diary-content">
      <textarea id="content" type="text" v-model="content"></textarea>
    </div>
    <div :class="{ 'error-text': textLength > 255 ? true : false }">
      {{ textLength }}/255
    </div>
    <button
      :disabled="!content || textLength > 255"
      class="diary-btn"
      @click="showAlert"
    >
      작성 완료
    </button>
  </div>
</template>

<script>
import { createDiary } from '@/api/diary.js'
export default {
  data() {
    return {
      content: '',
      loading: false,
    }
  },
  methods: {
    async submitForm() {
      let date =
        this.$route.params.date.substring(0, 4) +
        '-' +
        this.$route.params.date.substring(4, 6) +
        '-' +
        this.$route.params.date.substring(6, 8)
      const data = {
        content: this.content,
        date: date,
      }
      console.log(this.$store.state.uid)
      console.log(data)
      this.$swal({
        title: '분석중',
        html: '잠시만 기다려 주세요.',
        timerProgressBar: true,
        target: '#create-diary',
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
      let response = await createDiary(this.$store.state.uid, data)
      console.log(response)
      if (response == 'error') {
        this.$swal({
          icon: 'error',
          title: '분석 실패',
          text: '다시 일기를 작성해주세요.',
          target: '#create-diary',
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
    testing() {
      console.log('완료..')
    },
    showAlert() {
      // Use sweetalert2
      this.$swal({
        title: '작성하시겠습니까?',
        showDenyButton: true,
        confirmButtonText: '작성',
        denyButtonText: '취소',
        target: '#create-diary',
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
  computed: {
    textLength() {
      return this.content.length
    },
  },
}
</script>

<style></style>
