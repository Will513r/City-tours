<template>
    <section class="flex flex-col">
        <div class="flex max-h-96">
            <img-carousel :photos="imageURLs" ></img-carousel>
        </div>
        
        <div class="grid">
            <div class="inline-flex rounded-md justify-between gap-x-8 py-5 min-h-24" role="group">
                <h1
                    class="px-4 py-2 shadow text-gray-900 bg-background1 rounded-md basis-2/3 tracking-wide text-center">
                    {{ landmark.landmarkName }}
                </h1>
                <h2
                    class="px-4 py-2 text-xl shadow font-medium text-gray-900 bg-background1 rounded-md basis-1/3 items-center justify-center inline-flex text-center align-bottom">
                    <!-- {{ landmark.likes }} --> likes
                </h2>
            </div>

            <!-- want to align the text in the middle, need a break -->
            <div class="inline-flex rounded-md justify-between gap-x-4 min-h-20" role="group">
                <h3 class="px-4 py-2 shadow text-gray-900 bg-background1 rounded-md basis-1/3 text-center">
                    {{ landmark.address }}
                </h3>
                <h3
                    class="px-4 py-2 shadow text-gray-900 bg-background1 rounded-md basis-1/3 items-center justify-center inline-flex text-center align-bottom">
                    Type of Venue: {{ landmark.venueTypeName }}
                </h3>
                <!-- Can we make it a drop down button that's binded to the person's lists/add to a new list? -->
                <button type="button"
                    class="px-4 py-2 text-2xl shadow font-medium text-gray-900 bg-background1 rounded-md hover:bg-add hover:text-white focus:z-10 dark:border-white ring-red-100 dark:text-white dark:hover:text-white dark:hover:bg-gray-700 dark:focus:ring-red">
                    <!-- bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 -->
                    Add to Itinerary
                </button>
            </div>
        </div>

        <div class="grid">
            <div class="inline-flex justify-between py-10 gap-x-8 min-h-96" role="group">
                <p class="py-2 font-medium shadow rounded-md basis-1/3 bg-background1">
                    <!-- we want to go through like a loop... we added a daytime list where I tried to style, but I do not know how to link it!!  -->
                    <DayTimeList v-bind="landmark" />
                </p>
                <p
                    class="leading-loose whitespace-break-spaces border pl-5 py-4 text-xl font-medium shadow bg-background1 rounded-md basis-2/3 ">
                    {{ landmark.description }}</p>
            </div>
        </div>

    </section>
</template>

<script>
    import DayTimeList from './DayTimeList.vue';
    import ImgCarousel from './ImgCarousel.vue';
    export default {
        components: {
            DayTimeList,
            ImgCarousel
        },
        props: {
            landmark: Object
        },
        data() {
            return {
                imageURLs: [
                    "/src/assets/imgs/bunkinghamfountain/BuckinghamFountain-2.jpg",
                    "/src/assets/imgs/navypier/NavyPier2.png"
                ]
            }
        },
        created() {
            this.checkImage()
        },
        methods: {
            checkImage() {
                if (this.landmark.imageURLs.length > 0) {
                    for (let o of this.landmark.imageURLs) {
                        this.imageURLs.push(o.imageUrl);
                    }
                } else {
                    this.imageURLs.push("src/assets/imgs/bunkinghamfountain/BuckinghamFountain-1.jpg");
                }
            }
        },
        mounted(){
            this.caro +=1;
        },
        computed: {
            images() {
                let images = [];
                if (this.landmark.imageURLs.length > 0) {
                    for (let o of this.landmark.imageURLs) {
                        images.push(o.imageUrl);
                    }
                }

                if (images.length == 0) {
                    return this.$store.state.homePhotos;
                } else {
                    return images;
                }
            }
        }
    }
</script>

<style scoped>
    h1 {
        @apply text-3xl font-bold
    }

    h3 {
        @apply text-xl font-medium
    }
</style>