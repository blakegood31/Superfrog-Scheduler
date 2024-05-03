<template>
  <div id="app">
    <Sidebar>
      <div class="container">
        <AdminHeader/>
        <!-- First Name -->
        <div class="field">
          <h2>First Name</h2>
          <input type="text" v-model="superfrogProfile.firstName" @input="resizeInput('firstName')" @focus="resizeInput('firstName')" id="firstName">
        </div>
        <!-- Last Name -->
        <div class="field">
          <h2>Last Name</h2>
          <input type="text" v-model="superfrogProfile.lastName" @input="resizeInput('lastName')" @focus="resizeInput('lastName')" id="lastName">
        </div>
        <!-- Phone Number -->
        <div class="field">
          <h2>Phone Number</h2>
          <input type="text" v-model="superfrogProfile.phoneNumber" @input="resizeInput('phoneNumber')" @focus="resizeInput('phoneNumber')" id="phoneNumber">
        </div>
        <!-- Email -->
        <div class="field">
          <h2>Email</h2>
          <input type="email" v-model="superfrogProfile.email" @input="resizeInput('email')" @focus="resizeInput('email')" id="email">
        </div>
        <!-- Buttons for managing profile changes -->
        <div class="options">
            <button @click="backToAll">Back to All Requests</button>
          <button @click="saveChanges">Save Changes</button>
          <button @click="undoChanges">Undo Changes</button>
        </div>
      </div>
    </Sidebar>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import AdminHeader from '../components/adminHeader.vue';
import Sidebar from '../components/Sidebar.vue';
const backToAll = () => {
  router.push('/Admin');
}

const router = useRouter();
const superfrogId = 'specific_superfrog_id';  // This ID should be dynamically set based on your application's context
const superfrogProfile = reactive({
  firstName: '',
  lastName: '',
  phoneNumber: '',
  email: ''
});

const fetchProfile = async () => {
  try {
    const response = await fetch(`/students/${superfrogId}`, {  // Adjusted from /api/students to /students if needed
      headers: { 'Authorization': 'Bearer ' + localStorage.getItem('userToken') }
    });
    if (response.ok) {
      const data = await response.json();
      Object.assign(superfrogProfile, {
        firstName: data.firstName,   // Adjusted from first_name to firstName
        lastName: data.lastName,     // Adjusted from last_name to lastName
        phoneNumber: data.phoneNumber,  // Adjusted from phone_number to phoneNumber
        email: data.email
      });
    } else {
      throw new Error("Failed to fetch Superfrog profile.");
    }
  } catch (error) {
    console.error("Error fetching Superfrog profile:", error);
  }
}

const saveChanges = async () => {
  if (confirm("Are you sure you want to save changes?")) {
    const options = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
      },
      body: JSON.stringify({
        firstName: superfrogProfile.firstName,
        lastName: superfrogProfile.lastName,
        phoneNumber: superfrogProfile.phoneNumber,
        email: superfrogProfile.email
      })
    };

    const url = `/students/${superfrogId}/profile`;
    try {
      const response = await fetch(url, options);
      if (response.ok) {
        const updatedData = await response.json();
        console.log("Profile updated successfully:", updatedData);
        Object.assign(superfrogProfile, updatedData);
        alert("Profile updated successfully!");
      } else {
        const errorData = await response.json();
        console.error("Failed to update profile:", errorData.message);
        alert("Failed to update profile: " + errorData.message);
      }
    } catch (error) {
      console.error("Error updating profile:", error);
      alert("Error updating profile: " + error.message);
    }
  }
}
onMounted(fetchProfile);
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
.options > button {
  margin: 5px;
  padding: 8px 15px;
  font-size: 20px;
  border-radius: 5px;
}

.field{
  background-color: white;
  border-radius: 5px;
  border: 2px solid lightgray;
  padding: 15px;
  margin: 20px 20px;
  display:flex;
  flex-direction: column;
  align-items: center;
  border: 2px solid black;
}

.field:hover{
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
}

h2{
  margin-bottom: 5px;
  text-align: center;
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
