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
                <td><span :class="request.status">{{request.status}}</span></td>
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
</template>
<script setup>
    import { useRouter } from 'vue-router';
    const router = useRouter();

    const allRequests = defineModel('allRequests');
    const showTable = defineModel('showTable');
    const canEdit = defineModel('canEdit');
    const canView = defineModel('canView');
    showTable.value = false;

    // Get all requests from backend
    const jwt = localStorage.getItem('userToken');
    fetch('http://localhost:8080/requests', {
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

    const editRequestDetails = (requestId) => {
        const userRoles = JSON.parse(localStorage.getItem('userInfo')).roles.split(' ');
        if(!userRoles.includes("customer") && !userRoles.includes("admin")){
            console.log("Cannot Edit");
            alert("Unauthorized Action: You do not have permission to edit requests.");
            return;
        }
        const jwt = localStorage.getItem('userToken');
        const url = 'http://localhost:8080/requests/' + requestId;
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
        const url = 'http://localhost:8080/requests/' + requestId;
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
<style scoped>
    .container{
        height: 100vh;
        border: none;
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        padding-top: 0px;
        margin: 0 auto;
        background-color: white;
    }

    table {
        border-collapse: collapse; /* Merge cell borders */
        width: 95%; /* Set table width */
        margin: 20px auto; /* Center the table horizontally */
        background-color: rgba(0, 0, 0, 0.05);
    }
    
    th, td {
        border: 1px solid black; /* Add border to table cells */
        padding: 8px; /* Optional: add padding */
    }
    
    th {
        background-color: #f2f2f2; /* Optional: add background color to headers */
    }

    td{
        text-align: center;
        width: auto;
    }

    tr:hover {
        background-color: #c39fed; /* Change background color when row is focused */
        outline: none;
    }

    tr:hover > td > .REJECTED{
        background-color: rgb(255, 230, 230);
    }

    tr:hover > td > .CANCELLED{
        background-color: rgb(255, 230, 230);
    }

    tr:hover > td > .ASSIGNED{
        background-color: rgb(230, 255, 230);
    }

    tr:hover > td > .APPROVED{
        background-color: rgb(230, 255, 230);
    }

    tr:hover > td > .COMPLETED{
        background-color: rgb(230, 255, 230);
    }

    tr:hover > td > .INCOMPLETE{
        background-color: rgb(255, 249, 223);
    }

    tr:hover > td > .PENDING{
        background-color: rgb(255, 249, 223);
    }

    .REJECTED {
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
    }
</style>