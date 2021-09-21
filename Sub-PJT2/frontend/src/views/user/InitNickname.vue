<template>
  <div class="init-box">
    <div class="logo"></div>
    <div class="nickname-box">
      <div class="input-title">
        <h1>닉네임을</h1>
        <h1>설정해주세요</h1>
      </div>
      <div class="input-box">
        <input v-model="nickname" @keyup.enter="nextPage" type="text" />
      </div>
      <div class="btn-box1">
        <button class="next" @click="nextPage">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '../../assets/css/views/init.scss'

export default {
  data() {
    return {
      nickname: '',
    }
  },
  methods: {
    nextPage() {
      this.$store.state.initNickname = this.nickname
      this.$router.push({ name: 'InitProfileImg' })
    },
  },
  computed: {
    ...mapState(['initNickname']),
  },
}
</script>
