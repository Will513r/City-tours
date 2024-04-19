<template>
  <main class=" bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray">
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8" id="login">
    <form v-on:submit.prevent="login">
      <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Please Sign In</h2>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm" role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label class="block text-sm font-medium leading-6 text-gray-900" for="username">Username</label>
        <input
          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6"
          type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label class="block text-sm font-medium leading-6 text-gray-900" for="password">Password</label>
        <input
          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6"
          type="password" id="password" v-model="user.password" required />
      </div>
      <button
        class="flex w-full justify-center rounded-lg bg-background1 px-3 py-1.5 text-sm font-semibold leading-6 text-gray-900 shadow-sm hover:bg-add focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
        type="submit">Sign in</button>
      <p class="mt-10 text-center text-sm text-gray-500">
        <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
</main>
</template>

<script>
  import authService from "../services/AuthService";

  export default {
    components: {},
    data() {
      return {
        user: {
          username: "",
          password: ""
        },
        invalidCredentials: false,

       
      };
    },
    methods: {
      login() {
        authService
          .login(this.user)
          .then(response => {
            if (response.status == 200) {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              this.$router.push("/");

            }
          })
          .catch(error => {
            const response = error.response;

            if (response.status === 401) {
              this.invalidCredentials = true;
            }
          });
      },
      
    }
  };
</script>

<style scoped>
  .form-input-group {
    margin-bottom: 1rem;
  }

  label {
    margin-right: 0.5rem;
  }

  input[type=text],
  input[type=password] {
    @apply px-5
  }

  #login {
    width: 40%;
    @apply mx-auto;
  }
</style>