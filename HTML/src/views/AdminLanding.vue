<template>
    <div class="container" id="app">
        <!-- Title -->
        <div class="title">Admin Portal</div>
        <!-- Description -->
        <div class="description">Test Page For User Authentication -- Admin & SF Student</div>
        <!-- Button Box -->
        <button @click="authorizedRequest">Click to visit priviledged page</button>
        <button @click="unauthorizedRequest">Click to visit priviledged page without token</button>
        <button @click="editRequestDetails">Click to edit a request's details as admin</button>
        <button @click="viewApprovedRequests">Click to view approved requests -- Admin Only</button>

    </div>
</template>
<script setup>
    import { useRouter } from 'vue-router';
    const router = useRouter();
    
    const authorizedRequest = () => {
        // Use token to make a request that requires authorization
        // Get the stored JWT
        const jwt = localStorage.getItem('userToken');
        // Make the request
        fetch('http://localhost:8080/users', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then(response => {
            if(response.ok){
                console.log("Successful Token Usage");
                return response.json();
            }
            else{
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            // Request should be successful
            console.log(data);
        }).catch(error => {
            console.error('Error: ', error);
        });
    };

    const unauthorizedRequest = () => {
        // Test method for making a priviledged request with an invalid token
        // Make the request
        fetch('http://localhost:8080/users', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + "BaDT0k3nTe5T"
            }
        }).then(response => {
            if(response.ok){
                console.log("Successful Token Usage");
                return response.json();
            }
            else{
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            console.log(data);
        }).catch(error => {
            // Request should cause an error
            console.error('Error: ', error);
        });
    };

    const editRequestDetails = () => {
        const userRoles = JSON.parse(localStorage.getItem('userInfo')).roles.split(' ');
        if(!userRoles.includes("customer") && !userRoles.includes("admin")){
            console.log("Cannot Edit");
            alert("Unauthorized Action: You do not have permission to edit requests.");
            return;
        }
        const jwt = localStorage.getItem('userToken');
        fetch('http://localhost:8080/requests/3131313131', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then(response => {
            if(response.ok){
                console.log("Successful Token Usage");
                return response.json();
            }
            else{
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            console.log(data);
            localStorage.setItem('requestToEdit', JSON.stringify(data.data));
            window.location.href = "edit_request_details.html";
        }).catch(error => {
            console.error('Error: ', error);
        });
    };

    const viewApprovedRequests = () => {
        const jwt = localStorage.getItem('userToken');
        fetch('http://localhost:8080/requests/approved', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then(response => {
            if(response.ok){
                console.log("Successful Token Usage");
                return response.json();
            }
            else{
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            console.log(data);
            localStorage.setItem('requestToEdit', JSON.stringify(data.data));
            //window.location.href = "edit_request_details.html";
        }).catch(error => {
            console.error('Error: ', error);
        });
    };
</script>