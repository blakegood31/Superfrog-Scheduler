<template>
    <div class="container">
      <div class="field">
        <h2>Event Information</h2>
        <label for="evdate">When is your event?</label>
        <input type="date" id="evdate" name="evdate" v-model="editRequest.selectedDate">
      </div>
  
      <div class="field">
        <h2>Personal Contact Information</h2>
        <label for="fname">Contact First Name</label>
        <input type="text" id="fname" name="fname" v-model="editRequest.firstName">
  
        <!-- Other contact fields -->
  
      </div>
  
      <div class="field">
        <h2>Event Information</h2>
        <label for="etitle">Event Title</label>
        <input type="text" id="etitle" name="etitle" v-model="editRequest.eventTitle">
  
        <!-- Other event fields -->
  
      </div>
  
      <div class="field">
        <h2>TCU Spirit Team Terms and Conditions</h2>
        <ul>
          <!-- TCU Spirit terms -->
        </ul>
        <input type="checkbox" id="confirmation" name="confirmation" value="terms" v-model="editRequest.termsAgreed">
        <label for="confirmation"> I agree to the terms & conditions of a TCU Spirit Appearance.</label>
      </div>
  
      <div class="options">
        <button @click="goToLanding">Back to Home</button>
        <button @click="submitEdit">Submit</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  
  const editRequest = ref({
      selectedDate: '',
      // Add other request fields here
  });
  
  // Fetch existing request details
  const requestId = router.currentRoute.value.params.requestId;
  fetch(`http://127.0.0.1:8081/requests/${requestId}`, {
      method: 'GET',
      headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('userToken')
      }
  })
  .then(response => response.json())
  .then(data => {
      editRequest.value = data;
  });
  
  const goToLanding = () => {
      router.push('/customer');
  };
  
  const submitEdit = () => {
      // Send updated data to backend
      // Then navigate back
      router.push('/requestconfirmation');
  };
  </script>

<style scoped>
    .container{
        height: 100vh;
        border: none;
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        padding-top: 0px;
        background-color: lightgrey;
        justify-content: center;
    }

    .container > .field{
        margin-left: 20px;
        margin-right: 20px;
    }

    .field{
        background-color: white;
        border-radius: 5px;
        border: 2px solid lightgray;
        padding-left: 5px;
        padding-right: 5px;
        padding-bottom: 15px;
        padding-top: 5px;
        margin: 20px 0px 20px 0px;
        display:flex;
        flex-direction: column;
        align-items: center;
        border: 2px solid black;
    }

    .field:hover{
        /* border: 2px solid #832cc9; */
        border-color:#832cc9;
        background-color: #f8f0ff;
    }

    .field > input:focus {
        outline-style:none;
        border-color: #832cc9;
        border-width: 3px;
        border-radius: 5px;
    }

    .field > p > input:focus {
        outline-style:none;
        border-color: #832cc9;
        border-width: 3px;
        border-radius: 5px;
    }

    .field > input:hover {
        border-color: #832cc9;
        border-radius: 2px;
    }

    .field > p > input:hover {
        border-color: #832cc9;
        border-radius: 2px;
    }

    input{
        text-align: center;
        margin-top: 5px;
        padding-top: 0px;
    }

    h2{
        margin-bottom: 5px;
        text-align: center;
    }

    .header {
        text-align: center;
        margin-bottom: 20px;
        background-color: #832cc9;
        padding-bottom: 15px;
        padding-top: 15px;
        color: white;
        -webkit-text-fill-color: white;
        -webkit-text-stroke: 1px black;
        font-weight: bold;
    }

    .header > div{
        margin-bottom: 10px;
    }

    .title{
        font-size: 40px;
        font-weight: bold;
    }

    .header > .description {
        font-size: 20px;
    }

    .statusTitle{
        margin-bottom: 10px;
    }

    .options{
        display: flex;
        justify-content: center;
    }

    .options > button{
        margin-right: 5px;
        margin-left: 5px;
    }

    
</style>
