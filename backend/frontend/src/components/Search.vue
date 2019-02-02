<template>


  <b-container fluid id="search" v-on:keyup.enter="searchFunction">
    <br/>
    <b-alert :show="exception" @dismissed="exception=false" class="m-1" dismissible id="errorLbl" variant="danger">
      {{errorInfo.exception}}
    </b-alert>
    <br/>
    <b-container>
      <b-row>
        <b-col cols="8">
          <b-form-group horizontal
                        label="Search"
                        label-cols="3"
                        breakpoints="xl"
                        v-bind:description="description"
                        label-class="text-sm-right"
                        label-for="inputHorizontal">
            <b-input autofocus id="inputHorizontal" size="xs" type="search" v-model="searchKey"></b-input>
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
    <div class="footer-copyright text-center py-3 bottom">
      <b-container fluid>
        &copy; 2018 Copyright: <a href="https://github.com/enesoz"> enesozdemir </a>
      </b-container>
    </div>
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
        errorInfo: {exception: ''},
        description: ''
      }
    },
    watch: {
      description: function (event) {
        if (event) {
          return 'Typing...';
        } else {
          return ' Type a word';
        }
      }
    }
    ,

    methods: {
      searchFunction: function () {
        this.$axios.post(this.apiUrl, {
          searched: this.searchKey,
          type: this.selected
        }).then((response) => {
          this.items = response.data;
          this.exception = false;
        }).catch(error => {
          this.exception = true,
            this.errorInfo = {
              exception: error.response.data.message
            }
          console.log(this.errorInfo)
        })
      }
    }
    ,
    computed: {
      formatted() {
        return new Date(this.value)
      }
      ,
    }
  }


</script>

<style scoped>
  leftClass {
    text-align: left;
  }
</style>
