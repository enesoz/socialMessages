<template>
  <div class=".container .container-fluid" id="search">
    <div class="page-header">
      <div class="row">
        <div class="col-md-5">Search on</div>
        <div class="col-md-5"><input type="search" v-model="searchKey"></input></div>
        <div class="col-md-5">
          <button class="glyphicon-search" v-on:click="searchFunction">Search</button>
        </div>
        #data table here
      </div>
    </div>
  </div>
</template>


<script>

  export default {
    name: 'search',
    props: ['searchKey'],
    data() {
      return {
        apiUrl: 'http://localhost:3333/search/'
      }
    },
    methods: {
      searchFunction: function () {
        this.$axios.get(this.apiUrl + this.searchKey).then((response) => {
          console.log(response.body)
        })
      }
    }
  }


</script>

<style scoped>

</style>
