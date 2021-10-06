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
          <div id="header-img">
            <div>
              <img
                :src="profileImg"
                style="cursor: pointer; border-radius: 50%;"
                width="100%"
                height="100%"
              />
            </div>
          </div>
        </label>
        <div style="display: none">
          <input type="file" accept="image/*" id="file" @change="loadf" />
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
      isChange: false,
      profileImg: '',
    }
  },
  created() {
    this.profileImg = this.$store.state.filePath
  },
  methods: {
    prevPage() {
      this.$router.push({ name: 'InitNickname' })
    },
    nextPage() {
      const formData = new FormData()
      formData.append('uid', this.$store.state.uid)
      formData.append('nickname', this.$store.state.initNickname)
      formData.append('filePath', this.profileImg)

      const file = document.getElementById('file').files[0]
      if (this.isChange && file == null) {
        this.$swal({
          icon: 'warning',
          title: '이미지를 선택해주세요',
          showConfirmButton: false,
          target: '.init-box',
          width: '370px',
          timer: 1500,
          customClass: {
            container: 'modal-custom',
          },
        })
        return
      } else if (this.isChange) {
        formData.append('image', file)
      }

      let data = {
        uid: this.$store.state.uid,
        formData: formData,
      }
      UserApi.initProfile(
        data,
        res => {
          this.$router.push({ name: 'SurveyStart' })
          this.$store.commit('CHANGEPROFILE', res.data.userInfo)
        },
        err => {
          console.log(err)
        },
      )
    },
    loadf() {
      this.isChange = true
      var file = document.getElementById('file')
      this.profileImg = URL.createObjectURL(file.files[0])
    },
  },
  computed: {
    ...mapState(['uid', 'initNickname', 'initProfileimg']),
  },
}
</script>
