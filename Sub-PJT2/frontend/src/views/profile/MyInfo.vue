<template>
  <div class="myinfo">
    <div class="profile-img">
      <div id="header-img">
        <div>
          <img
            :src="filePath"
            alt=""
            width="100%"
            height="100%"
            style="border-radius: 50%;"
          />
        </div>
      </div>
      <h2 class="nickname">
        {{ nickname }} 님
        <span @click="goUpdate">
          <i class="fas fa-pen fa-sm"></i>
        </span>
      </h2>
    </div>
    <div class="myinfo-header">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="myinfo-contents">
      <div class="userinfo">
        <div class="content">
          <h1>이메일</h1>
          <h2>{{ email }}</h2>
        </div>
        <div class="content">
          <h1>로그인</h1>
          <h2>{{ emailCompany }}</h2>
        </div>
        <div>
          <p>
            <span @click="withdraw">
              회원 탈퇴
            </span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '@/api/UserApi'
import '@/assets/css/components/profile.scss'

export default {
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
    goUpdate() {
      this.$router.push({ name: 'MyInfoUpdate' })
    },
    withdraw() {
      if (
        confirm('회원 탈퇴를 하시면 돌이킬 수 없습니다. 그래도 하시겠습니까?')
      ) {
        let data = {
          id: this.uid,
        }
        UserApi.deleteMember(
          data,
          res => {
            console.log(res)
          },
          err => {
            console.log(err)
          },
        )
      }
    },
  },
  computed: {
    ...mapState(['filePath', 'nickname', 'email', 'emailCompany', 'uid']),
  },
}
</script>
