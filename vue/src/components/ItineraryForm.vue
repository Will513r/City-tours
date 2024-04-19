<template>
    <form v-on:submit.prevent="submitEditForm">
        <!-- first one is the name/date/delete itinerary -->
        <div class="inline-flex justify-between gap-12" role="group">
            <input
                class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:leading-6"
                type="text" id="name" v-model="editItinerary.name" placeholder="Itinerary Name" />
            <Calendar class="" v-model="editItinerary.date" placeholder="" />
            <input type="text"
                class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6"
                v-model="editItinerary.startPoint" placeholder="Where are you starting from?" />
        </div>
    </form>


</template>

<script>
    import ItineraryService from '../services/ItineraryService.js';
    import Calendar from 'primevue/calendar';
    import StartPointService from '../services/StartPointService.js';

    export default {
        components: {
            Calendar
        },
        props: {
            itinerary: {
                type: Object,
                required: true
            }
        },
        data() {
            return {
                editItinerary: {
                    id: this.itinerary.itineraryId,
                    userId: this.itinerary.userId,
                    name: this.itinerary.itineraryName,
                    date: this.itinerary.itineraryDate,
                    start: this.itinerary.startPoint
                },
                date: null,
            }
        },
        // added all of this, doesn't work
        methods: {
            // getItinerary(itineraryId) {
            //     ItineraryService.getItinerary(itineraryId)
            //         .then(response => {
            //             this.editItinerary = response.data;
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
            submitEditForm() { 
                //NEEDS TO CALL THE RIGHT ENDPOINT(S?), IF THE ENDPOINT IS A SUCCESS
                //UPDATE THE PROPER STORE DATA USING MUTATIONS (NEED TO MAKE THE MUTATIONS)
                //WE HAVE THE ITINERARY OBJECT (THIS.ITINERARY IN THIS COMPONENT), USE THE ITINERARY ID TO IDENTIFY WHICH
                //ITINERARY TO MODIFY IN THE STORE
                //ELSE REVERT THE EDIT ITINERARY MODEL TO BEFORE IT WAS CHANGED
                //AND POP UP WITH AN ERROR MSG
                

                
            }
        }
    }
</script>

<style scoped></style>