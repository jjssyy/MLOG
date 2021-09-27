<template>
  <div id="app">
    <transition
      name="router-animation"
      mode="out-in"
      enter-active-class="animated fadeIn fast"
      leave-active-class="animated fadeOut faster"
    >
      <router-view></router-view>
    </transition>
  </div>
</template>
<script>
import './components/css/style.scss'
export default {
  name: 'app',
}
</script>
<style>
@import 'https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css';
</style>
