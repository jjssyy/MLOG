<template>
  <div class="init-box">
    <div class="profileimg-box">
      <div class="input-title">
        <h1>사용할</h1>
        <h1>프로필 사진을</h1>
        <h1>설정해주세요</h1>
      </div>
      <div class="image-box">
        <label for="file">
          <img v-if="profileImg" :src="profileImg" style="cursor: pointer;" />
          <img
            v-else
            src="@/assets/images/profile_default.png"
            style="cursor: pointer;"
            width="100"
          />
        </label>
        <div style="display:none;">
          <input type="file" accept="image/*" id="file" />
        </div>
      </div>
      <div class="btn-box2">
        <button class="prev" @click="prevPage">이전</button>
        <button class="next" @click="nextPage">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '../../assets/css/views/init.scss'
import UserApi from '../../api/UserApi'

export default {
  data() {
    return {
      profileImg: '',
    }
  },
  methods: {
    prevPage() {
      this.$router.push({ name: 'InitNickname' })
    },
    nextPage() {
      this.$store.state.initProfileimg = this.profileImg
      this.$router.push({ name: 'SurveyStart' })
      let data = {
        uid: this.uid,
        nickname: this.initNickname,
        file_path: this.initProfileimg,
      }
      UserApi.initProfile(
        data,
        res => {
          console.log(res)
          this.$store.state.filePath = this.initProfileImg
          this.$store.state.nickname = this.initNickname
        },
        err => {
          console.log(err)
        },
      )
    },
  },
  computed: {
    ...mapState(['uid', 'initNickname', 'initProfileimg']),
  },
}
</script>
