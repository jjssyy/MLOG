<template>
  <div class="read-container-box">
    <p class="my-sub-title">이 날의 일기도 확인해보세요.</p>
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
</template>

<script>
export default {
  props: {
    diary: {
      type: Object,
      required: true,
    },
  },
}
</script>

<style></style>
