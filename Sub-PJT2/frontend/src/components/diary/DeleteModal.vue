<template>
  <div>
    <p>정말로 삭제?</p>
    <button @click="emitEvent(true)">예</button>
    <button @click="emitEvent(false)">아니요</button>
  </div>
</template>

<script>
export default {
  methods: {
    emitEvent(answer) {
      this.$emit('answer', answer)
    },
  },
}
</script>

<style></style>
