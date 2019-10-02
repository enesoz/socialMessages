<template>
    <b-container>
        <b-row>
            <b-carousel :interval="4000"
                        background="#ababab"
                        controls
                        fade
                        img-height="720"
                        img-width="720"
                        indicators
                        ref="myCarousel"
                        style="text-shadow: 1px 1px 2px #333;">
                <b-carousel-slide :caption="prepareCaption(item.caption)"
                                  :img-src="item.images.standard_resolution.url"
                                  :key="item.id"
                                  style="height: 720px;width: 720px"
                                  v-for="item in items.data"
                ></b-carousel-slide>
            </b-carousel>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "instaTable",
        props: {
            items: {}
        },

        data() {
            return {
                slide: 0,
                sliding: null
            }
        },
        methods: {
            prepareCaption: function (caption) {
                return caption == "undefined" || caption == null ? "" : caption.text;
            }
        }
    }
</script>

<style scoped>

</style>
