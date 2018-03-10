<template>


  <b-container fluid id="search">
    <br/>
    <b-alert variant="danger" class="m-1" dismissible :show="exception" @closed="exception=false">
      <h4 class="alert-heading">{{errorInfo.errorStatusCode}}</h4>
      <p>
        {{errorInfo.errorText}}
      </p>
      <hr/>
      {{errorInfo.exception}}
    </b-alert>
    <b-container>
      <b-row>
        <b-col cols="8">
          <b-form-group horizontal
                        label="Search"
                        label-cols="3"
                        breakpoints="xl"
                        description="Type a word "
                        label-class="text-sm-right"
                        label-for="inputHorizontal">
            <b-input type="search" v-model="searchKey" id="inputHorizontal" size="xs"></b-input>
          </b-form-group>
        </b-col>
        <b-col>
          <b-form-group horizontal>
            <b-form-radio-group id="radios2" v-model="selected" name="radioSubComponent">
              <b-form-radio value="twitter">
                <icon name="twitter" scale="1"/>
              </b-form-radio>
              <b-form-radio value="instagram">
                <icon name="instagram" scale="1"/>
              </b-form-radio>
            </b-form-radio-group>
          </b-form-group>
        </b-col>
        <b-col>
          <b-button variant="primary" v-on:click="searchFunction">Search</b-button>
        </b-col>
      </b-row>
    </b-container>
    <br/>
    <hr/>

    <b-container fluid>
      <b-row>
        <b-col class="col-md-1"/>
        <b-col class="col-md-10">
          <b-table stripped hover caption :items="items.tweets" :fields="tweetBriefs" :current-page="currentPage"
                   :per-page="perPage">
            <template slot="profileImageUrl" slot-scope="data">
              <div>
                <b-img rounded="circle" width="50" height="50" alt="img" class="m-1"
                       :src="data.item.profileImageUrl">
                </b-img>
              </div>
            </template>
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
          profileImageUrl: {label: "Profile Photo"},
          "text": {label: "Tweet", tdClass: "text-left"},
          "from_user": {label: "User"},
          "createdAt": {
            label: "Tweet Date", formatter: (value, key, item) => {
              return (new Date(value)).toLocaleDateString() + ' ' + (new Date(value).toLocaleTimeString())
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
        selected: 'twitter',
        //input for search
        searchKey: '',
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
          this.exception = true,
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
