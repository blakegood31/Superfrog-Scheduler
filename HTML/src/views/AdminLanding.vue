<template>
    <!-- <div class="container" id="app"> -->
    <div id="app">
        <Sidebar>
            <div class="main-content">
                <AdminHeader />
                <h2 class="pageTitle"> All Superfrog Requests</h2>
                <table v-if="showTable">
                    <tr>
                        <th>Event</th>
                        <th>Customer Name</th>
                        <th>Location</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Description</th>
                        <th>Superfrog</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    <tr v-for="request in allRequests">
                        <td>{{ request.eventTitle }}</td>
                        <td>{{ request.customer.lname }}, {{ request.customer.fname }}</td>
                        <td>{{ request.address }}</td>
                        <td>{{ request.startTime }}</td>
                        <td>{{ request.endTime }}</td>
                        <td>{{ request.description }}</td>
                        <td>{{ request.superfrog ? request.superfrog.lastName + ', ' + request.superfrog.firstName : 'Unassigned' }}</td>
                        <td><StatusBadge :customClass="request.status">{{request.status}}</StatusBadge></td>
                        <td>
                            <span>
                                <button v-if="canEdit && request.status !== 'REJECTED' && request.status !== 'CANCELLED' && request.status !== 'COMPLETED'" @click="editRequestDetails(request.id)">Edit</button>
                            </span>
                            <span>
                                <button @click="viewRequest(request.id)">View</button>
                            </span>
                            <span>
                                <button v-if="request.status === 'PENDING'">Approve</button>
                            </span>
                        </td>
                    </tr>
                </table>
            </div>
        </Sidebar>
    </div>
</template>
<script setup>
    import { useRouter } from 'vue-router';
    import AdminHeader from '../components/adminHeader.vue';
    import StatusBadge from '../components/statusBadge.vue';
    import Sidebar from '../components/Sidebar.vue'; 

    const router = useRouter();

    const allRequests = defineModel('allRequests');
    const showTable = defineModel('showTable');
    const canEdit = defineModel('canEdit');
    const canView = defineModel('canView');
    showTable.value = false;

    // Get all requests from backend
    const jwt = localStorage.getItem('userToken');
    fetch('http://localhost:8081/requests', {
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
        allRequests.value = data.data;
        showTable.value = true;
        console.log(allRequests.value);
    }).catch(error => {
        console.error('Error: ', error);
    });

    // If user is admin, show the button to edit requests
    if(JSON.parse(localStorage.getItem('userInfo')).roles.split(' ').includes("admin")){
        canEdit.value = true;
    }
    else{
        canEdit.value = false;
    }


    const authorizedRequest = () => {
        // Use token to make a request that requires authorization
        // Get the stored JWT
        const jwt = localStorage.getItem('userToken');
        // Make the request
        fetch('http://localhost:8081/users', {
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
        fetch('http://localhost:8081/users', {
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

    const editRequestDetails = (requestId) => {
        const userRoles = JSON.parse(localStorage.getItem('userInfo')).roles.split(' ');
        if(!userRoles.includes("customer") && !userRoles.includes("admin")){
            console.log("Cannot Edit");
            alert("Unauthorized Action: You do not have permission to edit requests.");
            return;
        }
        const jwt = localStorage.getItem('userToken');
        const url = 'http://localhost:8081/requests/' + requestId;
        fetch(url, {
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
            router.push('/editRequest');
        }).catch(error => {
            console.error('Error: ', error);
        });
    };

    const viewRequest = (requestId) => {
        const userRoles = JSON.parse(localStorage.getItem('userInfo')).roles.split(' ');
        const jwt = localStorage.getItem('userToken');
        const url = 'http://localhost:8081/requests/' + requestId;
        fetch(url, {
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
            localStorage.setItem('requestToView', JSON.stringify(data.data));
            router.push('/viewRequest');
        }).catch(error => {
            console.error('Error: ', error);
        });
    };

    const viewApprovedRequests = () => {
        const jwt = localStorage.getItem('userToken');
        fetch('http://localhost:8081/requests/approved', {
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
<style scoped>
    .main-content{
        height: 100vh;
        width: 100%;
        border: none;
        box-sizing: border-box;
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        padding: 0;
        margin: 0;
        background-color: white; 
        display: flex;
        flex-direction: column;
    }

    table {
        border-collapse: collapse;
        background-color: rgba(0, 0, 0, 0.05);
        margin-right: 2%;
        margin-left: 2%;

    }
    
    th, td {
        border: 1px solid black; 
        padding: 8px; 
    }
    
    th {
        background-color: #f2f2f2; 
    }

    td{
        text-align: center;
        
    }

    tr:hover {
        background-color: #c39fed;
        outline: none;
    } 

    .pageTitle{
        text-align: center;
        padding-bottom: 10px;
        margin-right: 20%;
        margin-left: 20%;
        margin-bottom: 20px;
        font-size: min(2vw, 35px);
        color: #531e7e;
        border-bottom: 1px solid black;
    }
</style>