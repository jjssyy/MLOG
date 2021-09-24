<template>
  <div>
    <p>MainTotalCnt 컴포넌트이다.</p>
    <p>내 일기장은 {{ totalCnt }}</p>
  </div>
</template>

<script>
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
