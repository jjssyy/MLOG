<template>
  <div>
    <div>
      페이지를 찾을 수 없습니다.
    </div>
    <div @click="prev">
      prev
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    prev() {
      this.$router.go(-1)
    },
  },
}
</script>
