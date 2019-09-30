<template>
    <b-container>
        <b-row>
            <b-carousel :interval="4000"
                        background="#ababab"
                        controls
                        fade
                        img-height="480"
                        img-width="1024"
                        indicators
                        ref="myCarousel"
                        style="text-shadow: 1px 1px 2px #333;">
                <b-carousel-slide img v-model="items.data.images.standard_resolution.url"/>
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
            prev() {
                this.$refs.myCarousel.prev()
            },
            next() {
                this.$refs.myCarousel.next()
            }
        }, computed: {
            onlyImg: function () {
                console.log(this.items.data);
                return typeof this.items.data != "undefined" ? this.items.data.map(a => a.images.standard_resolution.url) : "";
            }
        }
    }
</script>

<style scoped>

</style>
