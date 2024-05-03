<template>
  <div id="app">
    <Sidebar>
      <div class="container" id="app">
        <!-- Title -->
        <!-- <div class="header">
            <div class="title">Admin Portal</div>
            <div class="description">Test Page For User Authentication -- Admin & SF Student</div>
        </div> -->
        <AdminHeader/>
        <div class="field">
          <h2>Event Title</h2>
          <p>{{ request.eventTitle }}</p>
        </div>
        <div class="field">
          <h2>Event Location</h2>
          <p>{{ request.address }}</p>
        </div>
        <div class="field">
          <h2>Date & Time</h2>
          <p> Start: {{request.startTime}}</p>
          <p> End: {{request.endTime}}</p>
        </div>
        <div class="field">
          <h2>Event Description</h2>
          <p> {{ request.description }}</p>
        </div>
        <div class="field">
          <h2>Special Instructions</h2>
          <p>{{ request.specialInstructions }}</p>
        </div>
        <div class="field">
          <h2>Involved Organizations</h2>
          <p>{{ request.other_orgs }}</p>
        </div>
        <div class="field">
          <h2>Distance from TCU</h2>
          <p>{{ request.milesFromTCU }} miles</p>
        </div>
        <div class="field">
          <h2>Event Type</h2>
          <p>{{ request.eventType }}</p>
        </div>
        <div class="field">
          <h2 class="statusTitle">Status</h2>
          <!-- <p><span :class="request.status">{{request.status}}</span></p> -->
          <StatusBadge :customClass="request.status">{{ request.status }}</StatusBadge>
          <button v-if="(request.status === 'INCOMPLETE' || request.status === 'ASSIGNED')" @click="markAsCompleted(request.id)">Mark as Completed</button>

        </div>
        <div class="field">
          <h2>Assigned Student</h2>
          <p>{{ request.superfrog ? request.superfrog.firstName + ' ' + request.superfrog.lastName : 'Unassigned' }}</p>
        </div>
        <div>
          <button @click="backToAll">Back to All Requests</button>
        </div>
      </div>
    </Sidebar>
  </div>

</template>
<script setup>
import { ref } from 'vue';
import AdminHeader from '../components/adminHeader.vue';
import Sidebar from '../components/Sidebar.vue';
import StatusBadge from '../components/statusBadge.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const request = ref(JSON.parse(localStorage.getItem('requestToView') || '{}'));

const markAsCompleted = async (id) => {
  request.value.status = 'COMPLETED';
  // Add a confirmation dialog
  if (confirm("Are you sure you want to mark this request as completed?")) {
    await saveChanges(id, request.value.status);
  } else {
    console.log('Update canceled by user');
  }
};

const saveChanges = async (id, status) => {
  const apiURL = `http://127.0.0.1:8081/requests/${id}/status/${status}`;
  const response = await fetch(apiURL, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    }
  });


  if (response.ok) {
    const updatedRequest = await response.json();
    localStorage.setItem('requestToView', JSON.stringify(updatedRequest));
    console.log('Update successful:', updatedRequest);
  } else {
    console.error('Failed to update:', await response.text());
  }
};




const backToAll = () => {
  router.push('/admin');
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

    .field > input:hover {
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

    /* .REJECTED {
        margin-top: 40px;
        background-color: rgba(255, 50, 40, 0.1);
        color: rgba(230, 0, 0, 1);
        border: 2px solid rgba(230, 0, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .CANCELLED {
        margin-top: 40px;
        background-color: rgba(255, 50, 40, 0.1);
        color: rgba(230, 0, 0, 1);
        border: 2px solid rgba(230, 0, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .APPROVED {
        margin-top: 40px;
        background-color: rgba(75, 225, 65, 0.1);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .COMPLETED {
        margin-top: 40px;
        background-color: rgba(75, 225, 65, 0.1);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .ASSIGNED {
        background-color: rgba(75, 225, 65, 0.1);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .PENDING {
        margin-top: 40px;
        background-color: rgba(245, 195, 0, 0.15);
        color: rgba(245, 195, 0, 1);
        border: 2px solid rgba(245, 195, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .INCOMPLETE {
        margin-top: 40px;
        background-color: rgba(245, 195, 0, 0.15);
        color: rgba(245, 195, 0, 1);
        border: 2px solid rgba(245, 195, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    } */

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

    #cancel-reason{
        display: flex;
        margin-bottom: 10px;
        justify-content: center;
    }
    #cancel-reason > input{
        width: 60vw;
        height: 20px;
        border: 1px solid red;
        border-radius: 5px;
        background-color: rgba(255, 235, 235, 1);
    }

    #cancel-reason > input:focus {
        border: 2px solid red;
        background-color: white;
        outline-style: none;
    }

</style>

