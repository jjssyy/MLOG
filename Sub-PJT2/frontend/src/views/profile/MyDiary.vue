<template>
  <div class="mydiary">
    <div class="profile-header2">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-book"></i>
          <span>
            내 일기장
          </span>
        </h1>
      </div>
      <div class="mydiary-list">
        <MyDiaryItem
          v-for="(myDiary, idx) in myDiaryList"
          :key="idx"
          :myDiary="myDiary"
          :idx="idx"
        />
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/mydiary.scss'
import { getMyDiary } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'
import MyDiaryItem from '@/components/profile/MyDiaryItem.vue'

export default {
  components: {
    MyDiaryItem,
  },
  data() {
    return {
      myDiaryList: [],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyDiary(data)
    console.log(response.data)
    this.myDiaryList = response.data.DiaryList
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile' })
    },
  },
  computed: {
    ...mapState(['uid']),
  },
}
</script>
