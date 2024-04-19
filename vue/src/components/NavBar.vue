<template>
  <!-- Carousel covers nav bar when shrunk  -->
  
     <nav class="bg-gradient-to-b from-bahama from-10% via-lightred via-70% to-boxes to-90% border-gray-200 dark:bg-gray-900">
    <div
      class="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl p-4"
    >
      <a
        href="https://flowbite.com"
        class="flex items-center space-x-3 rtl:space-x-reverse"
      >
        <img
          src="/src/assets/imgs/chicago-73.svg"
          class="h-8"
          alt="Chicago Logo"
        />
        <span
          class="font-head self-center text-3xl font-bold whitespace-nowrap text-black"
          >City Tours</span
        >
      </a>
      
      <div class="flex items-center space-x-6 rtl:space-x-reverse">
        
        <ul class="" v-if="!checkUser()">
          <router-link
            class="text-sm text-gray-500 dark:text-white hover:overline"
            :to="{ name: 'register' }"
            >Create Account</router-link
          >
        </ul>
        <ul class="" v-if="!checkUser()">
          <router-link
            class="text-sm text-add dark:text-white hover:overline"
            :to="{ name: 'login' }"
            >Sign In</router-link
          >
        </ul>
        <ul v-if="checkUser()">
          <router-link
            class="font-head text-sm text-add font-bold dark:text-white hover:overline"
            :to="{ name: 'logout' }"
            >Sign Out</router-link
          >
        </ul>
        <ul class="font-medium" v-if="checkUser()">
          <p class="font-head">Welcome to Chicago {{ this.$store.state.user.fName }}!</p>
        </ul>
      </div>
    </div>
  </nav>
  <nav class="bg-gray-50 dark:bg-gray-700">
    <div class="max-w-screen-xl px-4 py-3 mx-auto">
      <div class="flex items-center">
        <ul
          class="flex flex-row font-medium mt-0 space-x-8 rtl:space-x-reverse text-sm"
        >
          <li>
            <router-link
              class="font-bold text-gray-900 dark:text-white hover:overline"
              :to="{ name: 'home' }"
              >Home</router-link
            >
          </li>
          <li>
            <router-link
              class="font-bold text-gray-900 dark:text-white hover:overline"
              :to="{ name: 'landmarks' }"
              >Landmarks</router-link
            >
          </li>
          <li>
            <router-link
              class="font-bold text-gray-900 dark:text-white hover:overline"
              :to="{ name: 'ItineraryView' }"
              >Itinerary</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
</template>

<script>
  export default {
    data() {
      return {
      };
    },
    methods: {
      checkUser() {
        if (this.$store.state.token !== "") {
          return true;
        } else {
          return false;
        }
      }
    },
  };
</script>

<style scoped>

  header {
    height: 17svh;
  }

  .account-header {
    height: 4svh;
    background-color: black;
  }

  .account-header ul {
    margin-right: 12.5%;
  }

  .primary-nav-container {
    height: 14svh;
  }

  .primary-nav>* {
    text-align: center;
    font-size: 2rem;
    width: 15svw;
  }

  .primary-nav>li {
    @apply my-auto py-10;
    /*applies Tailwind classes*/
  }

  .subnav {
    display: none;
    position: absolute;
    top: 17svh;
    width: inherit;
    margin: auto;
    /*centers the text/link*/
    z-index: 20;
  }

  .primary-nav>li:hover .subnav {
    display: flex;
    flex-direction: column;
  }

  .header-link {
    @apply text-center text-2xl font-bold leading-9 tracking-tight;
    height: 100%;
  }
</style>
