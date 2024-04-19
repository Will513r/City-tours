<template>
    <div>
        <!-- 
            for each landmark in the landmarksList (prop that is passed through the LandmarksList Component) 
            bind the landmark to the card's landmark prop
        -->
        <landmark-card v-for="landmark in landmarksList" :landmark="landmark" :key="landmark.id" :itinerary="selectedItinerary" class="my-16"></landmark-card>
    </div>
</template>

<script>
import LandmarkCard from './LandmarkCard.vue';
    export default {
        props:{
            landmarksList:{
                type: Object,
                required: true
            },
            selectedItinerary:{
                type: Object
            }
        },
        components:{
            LandmarkCard
        }
    }
</script>

<style scoped>

</style>