<template>
  <div>
    <h2 @click="logout">로그아웃</h2>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.dispatch('logout')
      this.$router.push({ name: 'Login' })
    },
  },
}
</script>
