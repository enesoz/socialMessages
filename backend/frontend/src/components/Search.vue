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

    <twitterTable :items="items" v-if="selected === 'twitter' "/>

    <insta-table :items="items" v-if="selected === 'instagram'"/>

    <div class="footer-copyright text-center py-3 bottom">
      <b-container fluid>
        &copy; 2018 Copyright: <a href="https://github.com/enesoz"> enesozdemir </a>
      </b-container>
    </div>
  </b-container>

</template>


<script>
  import TwitterTable from "./TwitterTable";
  import InstaTable from "./InstagramTable";

  export default {
    components: {InstaTable, TwitterTable},
    name: "search",
    data() {
      return {
        //datatables
        items: {},
        //radiogroup
        selected: "twitter",
        //input for search
        searchKey: "",
        apiUrl: "http://localhost:3333/search/",
        exception: false,
        errorInfo: {exception: ""},
        typing: false
      };
    },
    computed: {
      description: function (event) {
        if (this.searchKey.length <= 0) {
          return "Type a word";
        } else {
          return "<br/>";
        }
      }
    },

    methods: {
      searchFunction: function () {
        this.$axios
          .post(this.apiUrl, {
          searched: this.searchKey,
          type: this.selected
          })
          .then(response => {
          this.items = response.data;
          this.exception = false;
          })
          .catch(error => {
            (this.exception = true),
              (this.errorInfo = {
              exception: error.response.data.message
              });
            console.log(this.errorInfo);
          });
      },
    }
  };
</script>

<style scoped>
  leftClass {
    text-align: left;
  }
</style>
