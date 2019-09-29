<template>
    <b-container>
        <b-row>
            <div class="m-md-0" v-bind:key="item.id" v-for="item in items.data">
                <b-img fluid-grow style="align-items: center" v-bind:src="item.images.standard_resolution.url"/>
            </div>
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
                fields: {
                    images: {label: "Photographs"}
                }
            }
        }
    }
</script>

<style scoped>

</style>
