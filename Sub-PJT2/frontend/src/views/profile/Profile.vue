<template>
  <div class="profile">
    <div class="profile-header2">
      <button class="back">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
      <div class="userinfo">
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
        <h2 class="nickname">{{ nickname }} 님</h2>
      </div>
      <h3 class="logout" @click="logout">로그아웃</h3>
    </div>
    <div class="profile-contents">
      <div class="task">
        <h1>일기장</h1>
        <div class="subtask">
          <h2>
            <i class="fas fa-book"></i>
            <span>
              내 일기장
            </span>
          </h2>
          <h2>
            <i class="fas fa-music"></i>
            <span>
              내 플레이리스트
            </span>
          </h2>
        </div>
      </div>
      <div class="task">
        <h1>분석</h1>
        <div class="subtask">
          <h2>
            <i class="fas fa-chart-bar"></i>
            <span>
              내 일기 보고서
            </span>
          </h2>
          <h2>
            <i class="fas fa-chart-pie"></i>
            <span>
              긍·부정 보고서
            </span>
          </h2>
        </div>
      </div>
      <div class="task2">
        <h1>설정</h1>
        <div class="subtask">
          <h2 @click="goMyInfo">
            <i class="fas fa-cog"></i>
            <span>
              내 정보
            </span>
          </h2>
          <h2>
            <i class="fas fa-undo-alt"></i>
            <span>
              설문조사 초기화
            </span>
          </h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '@/assets/css/components/profile.scss'

export default {
  methods: {
    logout() {
      this.$store.dispatch('logout')
      this.$router.push({ name: 'Login' })
    },
    goMyInfo() {
      this.$router.push({ name: 'MyInfo' })
    },
  },
  computed: {
    ...mapState(['filePath', 'nickname']),
  },
}
</script>
