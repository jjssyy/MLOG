<template>
  <div class="init-box">
    <div class="nickname-box">
      <div class="input-title">
        <h1>닉네임을</h1>
        <h1>설정해주세요</h1>
      </div>
      <div class="input-box">
        <input
          v-model="nickname"
          maxlength="12"
          @keyup.enter="nextPage"
          type="text"
        />
        <p>{{ nickname.length }} / 12</p>
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
  created() {
    this.nickname = this.initNickname
  },
  methods: {
    nextPage() {
      if (this.nickname === 'defalut') {
        this.$swal({
          icon: 'warning',
          title: '이 닉네임은 사용할 수 없습니다. 다른 닉네임을 적어주세요.',
          showConfirmButton: false,
          target: '.init-box',
          width: '370px',
          timer: 1500,
          customClass: {
            container: 'modal-custom',
          },
        })
      } else if (this.nickname === '') {
        this.$swal({
          icon: 'warning',
          title: '닉네임을 1자 이상 적어주세요.',
          showConfirmButton: false,
          target: '.init-box',
          width: '370px',
          timer: 1500,
          customClass: {
            container: 'modal-custom',
          },
        })
      } else {
        this.$store.state.initNickname = this.nickname
        this.$router.push({ name: 'InitProfileImg' })
      }
    },
  },
  computed: {
    ...mapState(['initNickname']),
  },
}
</script>
