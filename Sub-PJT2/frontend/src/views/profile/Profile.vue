<template>
  <div class="profile">
    <div class="profile-header2">
      <button class="back" @click="goMain">
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
      <h3 class="logout" @click="logout">
        <span>
          로그아웃
        </span>
      </h3>
    </div>
    <div class="profile-contents">
      <div class="task">
        <h1>일기장</h1>
        <div class="subtask">
          <h2>
            <i class="fas fa-book"></i>
            <span @click="goMyDiary">
              내 일기장
            </span>
          </h2>
          <h2>
            <i class="fas fa-music"></i>
            <span @click="goMyPlaylist">
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
            <span @click="goMyDiaryReport">
              내 일기 보고서
            </span>
          </h2>
          <h2>
            <i class="fas fa-chart-pie"></i>
            <span @click="goEmoReport">
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
            <span @click="resetSurvey">
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
import SurveyApi from '@/api/SurveyApi'
import '@/assets/css/components/profile.scss'

export default {
  methods: {
    logout() {
      this.$store.dispatch('logout')
      this.$router.push({ name: 'Login' })
    },
    goMain() {
      this.$router.push({ name: 'Main' })
    },
    goMyInfo() {
      this.$router.push({ name: 'MyInfo' })
    },
    goMyDiary() {
      this.$router.push({ name: 'MyDiary' })
    },
    goMyPlaylist() {
      this.$router.push({ name: 'MyPlaylist' })
    },
    goMyDiaryReport() {
      this.$router.push({ name: 'DiaryReport' })
    },
    goEmoReport() {
      this.$router.push({ name: 'EmotionReport' })
    },
    resetSurvey() {
      this.$swal({
        icon: 'question',
        text:
          '설문조사 초기화를 하시면 지금까지의 추천 데이터가 다 사라집니다. 그래도 초기화하겠습니까?',
        showConfirmButton: true,
        showCancelButton: true,
        target: '.profile',
        width: '370px',
        customClass: {
          container: 'modal-custom',
        },
      }).then(result => {
        if (result.isConfirmed) {
          let data = {
            id: this.uid,
          }
          SurveyApi.resetSurvey(
            data,
            res => {
              console.log(res)
              this.$router.push({ name: 'SurveyStart' })
            },
            err => {
              console.log(err)
            },
          )
        }
      })
    },
  },
  computed: {
    ...mapState(['filePath', 'nickname', 'uid']),
  },
}
</script>
