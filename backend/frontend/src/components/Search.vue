<template>
  <b-container fluid id="search">

    <b-alert variant="danger" class="m-1" dismissible :show="exception" @closed="exception=false">
      <h4 class="alert-heading">{{errorInfo.errorStatusCode}}</h4>
      <p>
        {{errorInfo.errorText}}
      </p>
      <hr/>
      {{errorInfo.exception}}

    </b-alert>
    <b-container class="center">

      <b-row class="text-center">
        <b-col class="ssv-bold-label">Search a</b-col>
        <b-col cols="8">
          <b-form-input type="search" v-model="searchKey"></b-form-input>
        </b-col>
        <b-col>instagram/twitter</b-col>
        <b-col>
          <b-button variant="primary" v-on:click="searchFunction">Search</b-button>
        </b-col>
      </b-row>
    </b-container>

    <br/>
    <hr/>

    <b-container md>
      <b-col class="col-md-2"/>
      <col class="col-md-6">
      <b-table stripped hover :items="items.tweets" :fields="fields"></b-table>
      <b-col class="col-md-2"/>
    </b-container>

  </b-container>
</template>


<script>


  import Row from "bootstrap-vue/es/components/layout/row";

  export default {
    components: {Row},
    name: 'search',
    data() {
      return {
        fields: {
          "text": {label: "Tweet"},
          "from_user": {label: "User"},
          "createdAt": {label: "Tweet Date", formatter: "dd-MM-yyyy hh:mm:ss", sortable: true},
          "languageCode": {label: "Lang.", sortable: true},
          "retweetCount": {sortable: true}
        },
        searchKey: 'keyword',
        apiUrl: 'http://localhost:3333/search/',
        items: {},
        exception: false,
        errorInfo: {errorStatusCode: '', errorText: '', exception: ''}
      }
    },
    watched: {},

    methods: {
      searchFunction: function () {
        this.$axios.get(this.apiUrl + this.searchKey).then((response) => {
          this.items = response.data;
        }).catch(error => {
          this.exception = true
          this.errorInfo = {
            errorStatusCode: error.response.data.status,
            errorText: error.response.data.error,
            exception: error.response.data.message
          }
          console.log(this.errorInfo)
        })
      }
    }
  }


</script>

<style scoped>

</style>
