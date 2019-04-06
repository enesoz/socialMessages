<template>
  <div class="hello">
    <h1>{{ msg }}</h1>

    <br>
    <router-link to="search">Click to begin search messages</router-link>
  </div>
</template>

<script>
  import Link from "bootstrap-vue/es/components/link/link";

  export default {
    components: {Link},
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to My Vue.js App'
      }
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
