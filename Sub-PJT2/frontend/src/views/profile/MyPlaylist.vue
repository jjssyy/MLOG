<template>
  <div class="myplaylist">
    <div class="profile-header2">
      <button class="back" @click="goProfile">
        <i class="fas fa-arrow-left fa-2x"></i>
      </button>
    </div>
    <div class="profile-body">
      <div class="title">
        <h1>
          <i class="fas fa-music"></i>
          <span>
            내 플레이리스트
          </span>
        </h1>
      </div>
      <div class="myplay-list">
        <MyPlaylistItem
          v-for="(myPlay, idx) in myPlayList"
          :key="idx"
          :myPlay="myPlay"
          :idx="idx"
        />
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/views/mydiary.scss'
import { getMyPlaylist } from '@/api/ProfileApi.js'
import { mapState } from 'vuex'
import MyPlaylistItem from '@/components/profile/MyPlaylistItem.vue'

export default {
  components: {
    MyPlaylistItem,
  },
  data() {
    return {
      myPlayList: [
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
        {
          date: '20210910',
          musicTitle: '바라만본다',
          musicArtist: 'MSG워너비(MOM)',
          videoId: 'rM5JjNfPHA',
        },
      ],
    }
  },
  async created() {
    const data = {
      id: this.uid,
    }
    const response = await getMyPlaylist(data)
    console.log(response.data)
    this.myPlayList = response.data.playList
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
