<template>
  <div>


    <div>
      <div class="field">
        <h2>Thank you!</h2>
        <p>Your request has been submitted.<br>
          An email of your request is sent to your email.<br>
          Here is your request ID.</p>
        <!--request id-->
        <!--TO DO: change to unique request id-->
        <h2>{{ requestId }}</h2>
        <h2>SG1025602DGH</h2>
      </div>
    </div>

    <!-- Go back to page4 -->
    <button @click="goToHome">Back to Home</button>


  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
        
const router = useRouter();
const selectedDate = ref('');

const goToHome = () => {
  // go back to request
  router.push('/custlanding');
};

const fetchRequestId = () => {
  const url = 'http://localhost:8081/generateRequestId'; 
  fetch(url)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Failed to fetch request ID');
      }
    })
    .then(data => {
      requestId.value = data.requestId; 
      console.log('Request ID fetched:', requestId.value);
    })
    .catch(error => {
      console.error('Error fetching request ID:', error);
    });
};

onMounted(fetchRequestId);

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

