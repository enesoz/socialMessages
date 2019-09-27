<template>
  <b-container fluid>
    <b-row>
      <b-col class="col-md-1"/>
      <b-col class="col-md-10">
        <b-table :fields="tweetBriefs" :items="items.tweets" caption hover stripped>
          <template slot="profileImageUrl" slot-scope="data">
            <div>
                <b-img :src="data.item.profileImageUrl" alt="img" class="m-1" height="40" rounded="circle"
                       width="40">
              </b-img>
            </div>
          </template>
        </b-table>
      </b-col>
      <b-col class="col-md-1"/>
    </b-row>
    <b-pagination :per-page="perPage" :total-rows="items.tweets.length" align="center"
                  v-if="items.tweets && items.tweets.length > 1" v-model="currentPage">
    </b-pagination>
  </b-container>
</template>
<script>
  export default {
    name: "twitterTable",
    props: {
      items: {}
    },
    data() {
      return {
        tweetBriefs: {
          profileImageUrl: {label: "Profile Photo"},
          text: {label: "Tweet", tdClass: "text-left"},
          from_user: {label: "User"},
          createdAt: {
            label: "Tweet Date",
            formatter: (value, key, item) => {
              return (
                new Date(value).toLocaleDateString() +
                " " +
                new Date(value).toLocaleTimeString()
              );
            },
            sortable: true
          },
          languageCode: {label: "Lang.", sortable: true},
          retweetCount: {label: "RT Count", sortable: true}
        },
        //paginator
        currentPage: 1,
        perPage: 10
      };
    }
  };
</script>
<style scoped>
  leftClass {
    text-align: left;
  }
</style>
