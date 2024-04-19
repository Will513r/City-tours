<template>
    <div
        class="mx-auto max-w-full max-h-svh bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray gap-4">
        <!-- <div class="h-svh w-1/2"> -->
        <div id="currentState" class="flex justify-evenly">
            <ItineraryForm class="my-5" :itinerary="itinerary" />
        </div>
        <!-- </div> -->
        <div class="h-svh flex flex-row">
            <div class="w-1/2">
                <ItineraryDraggable :landmarksList="itinerary.landmarks" :selectedItinerary="itinerary" />
                <!--  -->
            </div>
            <div class="w-1/2">
                <map-box></map-box>
            </div>
        </div>
    </div>
</template>

<script>
import MapBox from '../components/MapBox.vue';
import ItineraryForm from '../components/ItineraryForm.vue';
import StartPointService from '../services/StartPointService.js';
import ItineraryDraggable from '../components/ItineraryDraggable.vue';

export default {
    components: {
        ItineraryForm,
        MapBox,
        ItineraryDraggable

    },
    data() {
        return {
            itinerary: {},
            startingPoint: {},
            showForm: false
        }
    },

    methods: {
        getItinerary(itineraryId) {
            let arr = this.$store.state.itineraryList;
            let dto = arr.filter(o => {
                return o.itineraryId == itineraryId;
            })
            this.itinerary = dto[0];
        }
        // getStartPointByItineraryId(itineraryId) {
        //     StartPointService
        //         .getStartPointByItineraryId(itineraryId)
        //         .then(response => {
        //             this.startingPoint = response.data;
        //         })
        //         .catch(error => {
        //             if (error.response) {
        //                 if (error.response.status == 404) {
        //                     this.$router.push({ name: 'NotFoundView' });
        //                 } else {
        //                     window.alert("Alert! Error! Error!");
        //                 }
        //             }
        //         })
        // },
    },

    created() {
        this.getItinerary(this.$route.params.itineraryId);
        // this.getStartPointByItineraryId(this.$route.params.itineraryId);
    }
}
</script>

<style scoped></style>
