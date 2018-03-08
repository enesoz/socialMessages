<template>
  <b-container fluid id="search">
    <b-container class="bv-exapmle-row">
      <b-row cols="1">
        <b-alert variant="danger" class="m-1" dismissable :show="exception">{{errorInfo.errorStatusCode}} -
          {{errorInfo.errorText}}
        </b-alert>
      </b-row>
      <b-row class="text-center">
        <b-col>Search on</b-col>
        <b-col cols="8">
          <b-form-input type="search" v-model="searchKey"></b-form-input>
        </b-col>
        <b-col>instagram/twitter</b-col>
        <b-col>
          <b-button variant="primary" v-on:click="searchFunction">Search</b-button>
        </b-col>
      </b-row>
    </b-container>

    <div class="row ">
      <div class="col-md-2"/>
      <col class="col-md-6">
      <b-table stripped hover :items="items" :fields="fields"></b-table>
    </div>

  </b-container>
</template>


<script>


  export default {
    name: 'search',
    data() {
      return {
        fields: [],
        searchKey: 'Enter a keyword',
        apiUrl: 'http://localhost:3333/search/',
        items: [],
        exception: false,
        errorInfo: {errorStatusCode: '', errorText: ''}
      }
    },
    watched: {},

    methods: {
      searchFunction: function () {
        this.$axios.get(this.apiUrl + this.searchKey).then((response) => {
          this.items = response.data;
        }).catch(error => {
          this.exception = true
          this.errorInfo.errorStatusCode = error.response.data.status
          this.errorInfo.errorText = error.response.data.error.concat('-').concat(error.response.data.exception);
        })
      }
    }
  }


</script>

<style scoped>

</style>
