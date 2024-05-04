<template>
    <div>
  
  
      <div>
        <div class="field">
          <h2>Thank you!</h2>
          <p>Your request has been cancelled.<br>
            Confirmation of this has sent to your email.<br>
          </p>
        </div>
      </div>
  
      <!-- Go back to page4 -->
      <button @click="goToHome">Back to Home</button>
  
  
    </div>
  </template>
  
  <script setup>

import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  
  const goToHome = () => {
      router.push('/customer');
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
  
  