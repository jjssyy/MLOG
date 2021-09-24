<template>
  <div>
    <p>MainChart 컴포넌트이다.</p>
    <p>chart: {{ chart }}</p>
  </div>
</template>

<script>
export default {
  props: {
    chart: {
      type: Object,
      required: true,
    },
  },
}
</script>

<style></style>
