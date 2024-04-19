<template>
  <main class=" bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray">
  <div id="register" class="text-center">
    <form id="regForm" v-on:submit.prevent="register">
      <h1 class="font-head mt-5 text-center text-lg text-gray-900">Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
  
      <!--TODO                          TODO                          TODO  -->
      <!-- We need to bind each input to the correct data point in the data object. -->

      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="username">Username</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="fName">First Name</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" type="text" id="fname" v-model="user.fName" required autofocus />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="lName">Last Name</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" type="text" id="lname" v-model="user.lName" required autofocus />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="emailAddress">Email</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6" type="email" id="emailAddress" v-model="user.emailAddress" required autofocus />
      </div>
      <div class="form-input-group">  
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="address">Address</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6" type="text" id="address" v-model="user.address" required autofocus />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="phoneNumber">Phone Number</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6" type="tel" id="phoneNumber" v-model="user.phoneNumber" placeholder="Optional" autofocus />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="password">Password</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6" type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label class="font-head block text-sm font-medium leading-6 text-gray-900" for="confirmPassword">Confirm Password</label>
        <input class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-add sm:text-sm sm:leading-6" type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button class="font-head flex w-full justify-center rounded-md bg-background1 px-3 py-1.5 text-sm font-semibold leading-6 text-gray-900 shadow-sm hover:bg-add focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" type="submit">Create Account</button>
      <p class="mt-6 text-center text-sm text-gray-500"><router-link class="font-head" v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
  </main>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        fName: '',
        lName: '',
        emailAddress: '',
        address: '',
        phoneNumber: ''

      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>


h1{
  margin-bottom: 1rem;
}

#regForm > button{
    width: 60%;
    margin-top: 2rem;

    @apply mx-auto;

}

.form-input-group {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: row;
  @apply mx-auto;
}

.form-input-group label{
  width: 20%;
  text-align: left;
}

.form-input-group input{
  width: 58.5%;
  text-align: left;
}

label {
  margin-right: 0.5rem;
}

input[type=text], input[type=email], input[type=tel], input[type=password]{
  @apply px-5;
}

#register{
  width: 40%;
  @apply mx-auto;
}
</style>
