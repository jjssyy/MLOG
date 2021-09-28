<template>
  <div class="count-box" style="margin-bottom:1rem;">
    <p class="my-sub-title-two">내 일기장은 {{ totalCnt }} 장</p>
  </div>
</template>

<script>
import '@/components/css/maincount.scss'
export default {
  props: {
    totalCnt: {
      type: Number,
      required: true,
    },
  },
}
</script>

<style></style>
