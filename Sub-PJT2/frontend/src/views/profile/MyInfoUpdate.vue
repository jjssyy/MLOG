<template>
  <div class="myinfo-update">
    <button class="back" @click="goMyInfo">
      <i class="fas fa-arrow-left fa-2x"></i>
    </button>
    <div class="update-contents">
      <div id="header-img">
        <div>
          <label for="file">
            <img
              :src="tempImg"
              alt=""
              width="100%"
              height="100%"
              style="border-radius: 50%; cursor: pointer;"
            />
          </label>
          <div style="display: none">
            <input type="file" accept="image/*" id="file" @change="loadf" />
          </div>
        </div>
      </div>
      <input v-model.trim="newNickname" />
      <button class="save" @click="saveInfo">저장</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import '@/assets/css/components/profile.scss'
import UserApi from '../../api/UserApi'

export default {
  data() {
    return {
      isChange: false,
      tempImg: '',
      newNickname: '',
    }
  },
  computed: {
    ...mapState(['nickname', 'filePath', 'uid', 'hasSurveyed']),
  },
  created() {
    this.newNickname = this.nickname
    this.tempImg = this.filePath
  },
  methods: {
    goMyInfo() {
      this.$router.push({ name: 'MyInfo' })
    },
    loadf() {
      this.isChange = true
      var file = document.getElementById('file')
      this.tempImg = URL.createObjectURL(file.files[0])
    },
    saveInfo() {
      const formData = new FormData()
      formData.append('uid', this.uid)
      formData.append('nickname', this.newNickname)
      formData.append('filePath', this.tempImg)
      formData.append('hasSurveyed', this.hasSurveyed)

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
          this.$router.push({ name: 'MyInfo' })
          this.$store.commit('CHANGEPROFILE', res.data.userInfo)
        },
        err => {
          console.log(err)
        },
      )
    },
  },
}
</script>
