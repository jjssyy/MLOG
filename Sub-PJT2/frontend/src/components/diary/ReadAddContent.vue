<template>
  <div>
    <div class="read-container-box">
      <p class="my-sub-title">이 날의 일기도 확인해보세요.</p>
      <div v-if="isRecdDiary">
        <div class="diary-title-sub-two">
          <p class="diary-title-two">
            {{
              '' +
                diary.diaryDate.substring(0, 4) +
                '년 ' +
                diary.diaryDate.substring(5, 7) +
                '월 ' +
                diary.diaryDate.substring(8, 10) +
                '일 '
            }}
          </p>
          <button @click="moveAddDiary">보기</button>
        </div>
        <div class="diary-content">
          <textarea
            style="min-height: 2rem;"
            class="sub-textarea"
            readonly="readonly"
            id="content"
            type="text"
            v-model="diary.content"
          ></textarea>
        </div>
      </div>
      <div v-else>
        <div class="diary-title-sub-two">
          <p class="diary-title-two" style="padding-bottom: 2.1rem;">
            이전 일기가 없네요.<i style="font-size:1.6rem; font-style: normal"
              >&#128560;</i
            >
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    diary: {
      type: Object,
      required: true,
    },
    isRecdDiary: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    moveAddDiary() {
      let temp = this.diary.diaryDate
      let data =
        '' + temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10)
      console.log(data)
      this.$router.push(`/diary/${data}`)
    },
  },
}
</script>

<style></style>
