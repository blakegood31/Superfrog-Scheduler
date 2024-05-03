<template>
    <!-- <div class="container" id="app"> -->
    <div id="app">
        <Sidebar>
            <div class="main-content">
                <AdminHeader />
                <button v-if="canEditSuperfrog" @click="editSuperfrogProfile" class="editsf">Edit Superfrog Profile</button>
                <h2 class="pageTitle"> All Superfrog Requests</h2>

                <!-- <button id="showFieldsGridButton" @click="showFieldSelections"><img id="filterIcon" src="HTML/src/assets/funnel.png"></button> -->
                <div class="hidden" id="showFieldGrid">
                    <label for="checkbox1">
                        <input type="checkbox" id="checkbox1" v-model="showColumns.eventTitle"> Event Title
                    </label>
                    <label for="checkbox2">
                        <input type="checkbox" id="checkbox2" v-model="showColumns.cname"> Customer Name
                    </label>
                    <label for="checkbox3">
                        <input type="checkbox" id="checkbox3" v-model="showColumns.location"> Location
                    </label>
                    <label for="checkbox4">
                        <input type="checkbox" id="checkbox4" v-model="showColumns.startTime"> Start Time
                    </label>
                    <label for="checkbox5">
                        <input type="checkbox" id="checkbox5" v-model="showColumns.endTime"> End Time
                    </label>
                    <label for="checkbox6">
                        <input type="checkbox" id="checkbox6" v-model="showColumns.description"> Description
                    </label>
                    <label for="checkbox7">
                        <input type="checkbox" id="checkbox7" v-model="showColumns.superfrog"> Superfrog
                    </label>
                    <label for="checkbox8">
                        <input type="checkbox" id="checkbox8" v-model="showColumns.status"> Status
                    </label>
                    <label for="checkbox9">
                        <input type="checkbox" id="checkbox9" v-model="showColumns.distance"> Distance
                    </label>
                    <label for="checkbox10">
                        <input type="checkbox" id="checkbox10" v-model="showColumns.eventType"> Event Type
                    </label>
                    <label for="checkbox11">
                        <input type="checkbox" id="checkbox11" v-model="showColumns.actions"> Actions
                    </label>
                    <button @click="saveShowSelection" id="showFieldButton">Save Selection</button>
                </div>
                <table v-if="showTable">
                    <tr>
                        <th class="eventTitle-Th">Event Title</th>
                        <th class="cname-Th">Customer Name</th>
                        <th class="location-Th">Location</th>
                        <th class="startTime-Th">Start Time</th>
                        <th class="endTime-Th">End Time</th>
                        <th class="description-Th">Description</th>
                        <th class="superfrog-Th">Superfrog</th>
                        <th class="status-Th">Status</th>
                        <th class="distance-Th">Distance</th>
                        <th class="eventType-Th">Event Type</th>
                        <th class="actions-Th">Actions</th>
                    </tr>
                    <tr v-for="request in allRequests">
                        <td class="eventTitle">{{ request.eventTitle }}</td>
                        <td class="cname">{{ request.customer.lname }}, {{ request.customer.fname }}</td>
                        <td class="location">{{ request.address }}</td>
                        <td class="startTime">{{ request.startTime }}</td>
                        <td class="endTime">{{ request.endTime }}</td>
                        <td class="description">{{ request.description }}</td>
                        <td class="superfrog">{{ request.superfrog ? request.superfrog.lastName + ', ' + request.superfrog.firstName : 'Unassigned' }}</td>
                        <td class="status"><StatusBadge :customClass="request.status">{{request.status}}</StatusBadge></td>
                        <td class="distance">{{ request.milesFromTCU }}</td>
                        <td class="eventType">{{ request.eventType }}</td>
                        <td class="actions">
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
                <!--<img src="/Users/blake/School/WebTech/Superfrog-Scheduler/HTML/src/assets/tcu-1-logo-svg-vector.svg" alt="Superfrog">-->
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
    const canEditSuperfrog = defineModel('canEditSuperfrog');
    const showColumns = defineModel('showColumns');

    showTable.value = false;

    showColumns.value = {
        eventTitle: true,
        cname: true,
        location: true,
        startTime: true,
        endTime: true,
        description: true,
        superfrog: true,
        status: true,
        distance: true,
        eventType: true,
        actions: true
    };

    const saveShowSelection = () => {
        const colDict = showColumns.value;
        Object.keys(colDict).forEach(key => {
            console.log(key + " " +colDict[key]);
            const headerId = key + "-Th";
            console.log(headerId);
            const header = document.querySelectorAll('.' + headerId);
            const data = document.querySelectorAll('.' + key);
            console.log(header);
            console.log(data);
            if(colDict[key] !== true){
                header.forEach(elt => {
                    elt.classList.add("hidden");
                });
                data.forEach(elt => {
                    elt.classList.add("hidden");
                });
            }
            else{
                header.forEach(elt => {
                    elt.classList.remove("hidden");
                });
                data.forEach(elt => {
                    elt.classList.remove("hidden");
                });
            }
        })
    };

    const showFieldSelections = () => {
        const grid = document.getElementById('showFieldGrid');
        grid.classList.toggle("hidden");
        grid.classList.toggle("checkbox-grid");

    }

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
    if(JSON.parse(localStorage.getItem('userInfo')).roles.split(' ').includes("superfrog")){
      canEditSuperfrog.value = true;
    }
    else{
      canEditSuperfrog.value = false;
    }


    // If user is admin, show the button to edit requests/mark incompletete
    if(JSON.parse(localStorage.getItem('userInfo')).roles.split(' ').includes("admin")){
        canEdit.value = true;

    }
    else{
        canEdit.value = false;
    }


    const testShow = () => {
        console.log(showColumns.value);
    }

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

    const editSuperfrogProfile = (requestID) => {
        router.push('/EditSuperfrogProfile')
    }


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
        }).catch(error => {
            console.error('Error: ', error);
        });
    };
</script>
<style scoped>
    #filterIcon {
        width: 75%;
        height: 75%;
    }

    #showFieldsGridButton {
        margin-right: 80%;
        margin-left: 2%;
        width: min(60px, 5vw);
        height: min(60px, 5vw);
        text-align: center;
        background-color: #531e7e;
        border: 1px solid black;
        border-radius: 5px;
        margin-bottom: 10px;
    }

    #showFieldsGridButton:hover {
        box-shadow: 0 2px 4px rgba(0, 0, 0, 1.0);
    }

    #showFieldsGridButton:active {
        box-shadow: none;
    }
    .editsf{
      margin: 2%;
      padding: 1%;
      font-size: 100%;
      width: 20%;
    }

    #showFieldButton {
        display: flex;
        justify-content: center;
        width: 100%;
        height: 140%;
        font-size: 110%;
    }

    #showFieldsGridLabel{
        color: #531e7e;
        margin-left: 2%;
        margin-right: 89%;
        border-bottom: 2px solid #531e7e;
        text-align: center;
    }
    .hidden {
        display: none;
    }

    .checkbox-grid {
        display: grid;
        grid-template-rows: repeat(3, max(15px, 15%)); 
        grid-template-columns: repeat(4, max(110px, 6vw));
        gap: 8px;
        font-size: 80%;
        padding-bottom: 10px;
        padding-left: 2%;
    }

    /* img{
        width: 30%;
        height: 30%;
        padding-left: 40%;
        padding-right: 40%;
        animation: rotation 1s infinite;
    } */

    @keyframes rotation {
        0% {
            transform: rotate(0deg);
            animation-timing-function: ease-in;
        }
        40% {
            transform: rotate(180deg);
            animation-timing-function: ease-out;
        }
        100% {
            transform: rotate(360deg);
        }
    }
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
    }
    
    th {
        background-color: lightgrey; 
        padding: 3px;
    }

    td{
        text-align: center;
        padding-top: 10px; 
        padding-bottom: 10px;
        padding-right: 12px;
        padding-left: 12px;

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