<template> 
  <color-detail :is-edit='false'></color-detail>
</template>
<script>
  import ColorDetail from './components/colorDetail'
  export default {
    name: 'addColor',
    components: { ColorDetail }
  }
</script>
<style>
</style>


