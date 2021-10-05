<template>
  <div>
    <div>
      <div @click="selectMusic">
        <img :src="music.file_path" alt="음악 포스터" />
        {{ music.musicTitle }}
        {{ music.musicArtist }}
      </div>
      <div>
        {{ music.videoId }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    music: {
      type: Object,
      required: true,
    },
  },
  methods: {
    selectMusic() {
      this.$emit('selectMusic', this.music.mid)
    },
  },
}
</script>

<style></style>
