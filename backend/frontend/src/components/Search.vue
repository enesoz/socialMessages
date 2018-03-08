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


    <b-form-group id="fieldsetHorizontal"
                  horizontal
                  :label-cols="3"
                  breakpoint="md"
                  description="Let us know your name."
                  label="Enter a keyword"
                  label-for="inputHorizontal">
      <b-form-input type="search" v-model="searchKey" id="inputHorizontal"></b-form-input>

      <b-form-checkbox-group v-model="selected"
                             :options="options" label-class="text-sm-left"
                             name="checkboxInline">
      </b-form-checkbox-group>
      <b-button variant="primary" v-on:click="searchFunction">Search</b-button>
    </b-form-group>

    <br/>
    <hr/>

    <b-container fluid>
      <b-row>
        <b-col class="col-md-1"/>
        <b-col class="col-md-10">
          <b-table stripped hover caption :items="items.tweets" :fields="tweetBriefs" :current-page="currentPage"
                   :per-page="perPage">
          </b-table>
        </b-col>
        <b-col class="col-md-1"/>
      </b-row>
      <b-pagination v-if="items.tweets && items.tweets.length > 1" align="center" :total-rows="items.tweets.length"
                    v-model="currentPage" :per-page="perPage">
      </b-pagination>
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
        //datatables
        tweetBriefs: {
          "text": {label: "Tweet", tdClass: "leftClass"},
          "from_user": {label: "User"},
          "createdAt": {
            label: "Tweet Date", formatter: (value, key, item) => {
              return (new Date()).toLocaleDateString() + ' ' + (new Date().toLocaleTimeString())
            }, sortable: true
          },
          "languageCode": {label: "Lang.", sortable: true},
          "retweetCount": {label: "RT Count", sortable: true}
        },
        items: {},
        //paginator
        currentPage: 1,
        perPage: 10,
        //radiogroup
        selected: {},
        options: [
          {
            value: 'twitter', text: 'tweeter', default: true
          },
          {
            value: 'instagram', text: 'instagram'
          }],
        searchKey: 'keyword',
        apiUrl: 'http://localhost:3333/search/',
        exception: false,
        errorInfo: {errorStatusCode: '', errorText: '', exception: ''}
      }
    },
    watched: {},

    methods: {
      searchFunction: function () {
        this.$axios.get(this.apiUrl + this.searchKey, {
          params: {
            type: this.selected
          }
        }).then((response) => {
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
    },
    computed: {
      formatted() {
        return new Date(this.value)
      },
    }
  }


</script>

<style scoped>
  leftClass {
    text-align: left;
  }
</style>
