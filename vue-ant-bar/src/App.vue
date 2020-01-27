<template>
  <a-locale-provider :locale=this.$zh>
    <router-view/>
  </a-locale-provider>
</template>

<script>

  export default {
    name: 'app',
    components: {}
  }
</script>

<style>
</style>
