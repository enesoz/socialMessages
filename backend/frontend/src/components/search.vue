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
    data: {
      searchKey: 'Beni ara'
    },
    methods: {
      searchFunction: function () {
        console.log('search Me')
      }
    }
  }


</script>

<style scoped>

</style>
