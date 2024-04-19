<template>
    <div class="box" draggable="true" v-for="landmark in landmarksList" :key="landmark.key">
        <LandmarkCard :landmark="landmark" :itinerary="selectedItinerary" />
    </div>
</template>

<script>
import LandmarkCard from './LandmarkCard.vue';
// import draggable from 'vuedraggable';

export default {
    data() {
        return {
            array: []
        }
    },
    props: {
        landmarksList: {
            type: Object,
            required: true
        },
        selectedItinerary: {
            type: Object
        }
    },
    components: {
        LandmarkCard,
        // draggable
    }
}
</script>

<style>
box {
    cursor: move
}
</style>