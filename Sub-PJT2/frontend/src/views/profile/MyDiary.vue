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
      myDiaryList: [
        {
          date: '20210202',
          content:
            '지는 노을에 그대를 숨겨두고 어딘지 묻지 않았지 \n 침묵은 다시 당신 이름이 되어 나의 내일을 채우네 \n ​아 잠시 기다렸던 마음은 참 빨라',
        },
      ],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyDiary(data)
    console.log(response.data)
    this.myDiaryList = response.data.diaryList
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
