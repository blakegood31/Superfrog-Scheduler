<template>
    <div class="container" id="app">
        <!-- Title -->
        <div class="header">
            <div class="title">Admin Portal</div>
            <!-- Description -->
            <div class="description">Test Page For User Authentication -- Admin & SF Student</div>
            <!-- Button Box -->
            <button @click="viewRequest">View Request</button>
        </div>
        <div class="field">
            <h2>Event Title</h2>
            <input type="text" v-model="editedRequest.eventTitle" @input="resizeInput('title')" @focus="resizeInput('title')" id="title">
        </div>
        </br>
        </br>
        <div class="field">
            <h2>Event Location</h2>
            <input type="text" v-model="editedRequest.address" @input="resizeInput('address')" @focus="resizeInput('address')" id="address">
        </div>
        </br>
        </br>
        <div class="field">
            <h2>Date & Time</h2>
            <input type="text" v-model="editedRequest.startTime">
            <input type="text" v-model="editedRequest.endTime">
        </div>
        </br>
        </br>
        <div class="field">
            <h2>Event Description</h2>
            <input type="text" v-model="editedRequest.description" @input="resizeInput('description')" @focus="resizeInput('description')" id="description">
        </div>
        </br>
        </br>
        <div class="field">
            <h2>Special Instructions</h2>
            <input type="text" v-model="editedRequest.specialInstructions" @input="resizeInput('instructions')" @focus="resizeInput('instructions')" id="instructions">
        </div>
        </br>
        </br> 
        <div class="field">
            <h2>Involved Organizations</h2>
            <input type="text" v-model="editedRequest.other_orgs" @input="resizeInput('other-orgs')" @focus="resizeInput('other-orgs')" id="other-orgs">
        </div> 
        </br>
        </br>
        <div class="field">
            <h2>Status</h2>
            <p><span :class="editedRequest.status">{{editedRequest.status}}</span></p>
        </div>
        <div class="field" v-if="isApproved && isAdmin">
            <h2>Assigned Student</h2>
            <select v-model="editedRequest.superfrog">
                <option value="null" :selected="editedRequest.superfrog === null">No Student Assigned</option>
                <option v-for="student in students" :value="student" :selected="editedRequest.superfrog && editedRequest.superfrog.value === student.value">{{ student.firstName }} {{ student.lastName }}</option>
            </select>
        </div>
        </br>
        </br>
        <div id="cancel-reason">
            <input type="text" v-if="showCancelTextbox" v-model="cancelReason" placeholder="Enter reason for cancellation">
        </div>
        <div class="options">
            <button @click="saveChanges">Save Changes</button>
            <button @click="undoChanges">Undo Changes</button>
            <button @click="getRequestById">Get the request by ID</button>
            <button v-if="isApproved" @click="cancelRequest">Cancel Request</button>
        </div>
    </div> 
 
</template>
<script setup>
    import { useRouter } from 'vue-router';
    const router = useRouter();

    const editedRequest = defineModel('editedRequest');
    const oldRequest = defineModel('oldRequest');
    const userInfo = defineModel('userInfo');
    const isApproved = defineModel('isApproved');
    const showCancelTextbox = defineModel('showCancelTextbox');
    const secondClick = defineModel('secondClick');
    const cancelReason = defineModel('cancelReason');
    const students = defineModel('allStudents');
    const isAdmin = defineModel('isAdmin');

    editedRequest.value = JSON.parse(localStorage.getItem('requestToEdit'));
    oldRequest.value = JSON.parse(localStorage.getItem('requestToEdit'));
    userInfo.value = JSON.parse(localStorage.getItem('userInfo'));
    isApproved.value = editedRequest.value.status === "APPROVED" || editedRequest.value.status === "ASSIGNED";
    showCancelTextbox.value = false;
    secondClick.value = false;
    cancelReason.value = "";
    if(userInfo.value.roles.split(' ').includes("admin")){
        const jwt = localStorage.getItem('userToken');
        console.log("Getting all students");
        fetch('http://localhost:8080/students', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then(response => {
            if(response.ok){
                console.log("Successful Token Usage");
                isAdmin.value = true;
                console.log(isAdmin);
                return response.json();
            }
            else{
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            console.log(data);
            students.value = data.data;
            console.log(students.value);
        }).catch(error => {
            // Request should cause an error
            console.error('Error: ', error);
        });
    }

    const editRequestDetails = () => {
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
        }).catch(error => {
            // Request should cause an error
            console.error('Error: ', error);
        });
    };

    const viewRequest = () => {
        console.log("EDITED: " + JSON.stringify(editedRequest.value));
        console.log("ORIGINAL: " + JSON.stringify(oldRequest.value));
        console.log(userInfo.value);
    };

    const saveChanges = () => {
        // If the user confirms they want to save changes
        if(finalizeChanges("save")){
            // Set new status to pending if a customer made the change
            if(userInfo.value.roles.split(' ').includes("customer")){
                console.log("User is a customer!");
                editedRequest.value.status = "PENDING";
            }
            // If we removed a superfrog from being assigned, set the value to null and update status
            if(editedRequest.value.superfrog === "null"){
                editedRequest.value.superfrog = null;
                if (editedRequest.value.status === 'ASSIGNED'){
                    editedRequest.value.status = "APPROVED";
                }
                console.log(editedRequest.value.superfrog);
            }
            // Make sure status is assigned if a student has been assigned to the request
            else{
                if(editedRequest.value.status === "APPROVED"){
                    editedRequest.value.status = "ASSIGNED";
                }
            }
            const options = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('userToken')
                },
                body: JSON.stringify(editedRequest.value)
            };
            console.log(options.body);
            const url = 'http://127.0.0.1:8080/requests/' + editedRequest.value.id;
            fetch(url, options)
                .then(response => {
                    if(response.ok){
                        console.log(response);
                        return response.json();
                    }
                    else{
                        console.log("ERROR!");
                    }
                }).then(data => {
                    console.log(data.data);
                    localStorage.setItem('requestToEdit', JSON.stringify(data.data));
                    editedRequest.value = JSON.parse(localStorage.getItem('requestToEdit'));
                    oldRequest.value =JSON.parse(localStorage.getItem('requestToEdit'));
                });
        }
    };

    const undoChanges = () => {
        if(finalizeChanges("undo")){
            editedRequest.value = JSON.parse(localStorage.getItem('requestToEdit'));
        }
        return;
    };

    const finalizeChanges = (actionName) =>{
        var result = confirm("Would you like to " + actionName + " changes to the request?");
        if(result){
            return true
        }
        else{
            alert("Changes not saved.")
            return false
        }
    };

    const getRequestById = () => {
        const options = {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('userToken')
            }
        };
        const url = 'http://127.0.0.1:8080/requests/' + editedRequest.value.id;
        fetch(url, options)
            .then(response => {
                if(response.ok){
                    console.log(response);
                    return response.json();
                }
                else{
                    console.log("ERROR!");
                }
            }).then(data => {
                console.log(data.data);
            });
    };

    const cancelRequest = () => {
        showCancelTextbox.value = true;
        if(secondClick.value){
            if(cancelReason.value.length === 0){
                alert("You must enter a reason for cancellation");
                return;
            }
            var result = confirm("Are you sure you want to cancel this request?");
            if(result){
                const options = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
                    }
                };
                const url = 'http://127.0.0.1:8080/requests/' + editedRequest.value.id + "/cancel";
                fetch(url, options)
                    .then(response => {
                        if(response.ok){
                            console.log(response);
                            return response.json();
                        }
                        else{
                            console.log("ERROR!");
                        }
                    }).then(data => {
                        console.log(data.data);
                        console.log(cancelReason.value);
                        localStorage.setItem('requestToEdit', JSON.stringify(data.data));
                        editedRequest.value = JSON.parse(localStorage.getItem('requestToEdit'));
                        oldRequest.value =JSON.parse(localStorage.getItem('requestToEdit')); 
                        isApproved.value = false;
                    });
                secondClick.value = false;
                showCancelTextbox.value = false;
                cancelReason.value = "";
            }
            else{
                alert("Request not canceled.")
                showCancelTextbox.value = false;
                secondClick.value = false;
            } 
        }
        else{
            secondClick.value = true;
        }    
    };

    const resizeInput = (eltName) => {
        const inputElt = document.getElementById(eltName);
        const inputLength = inputElt.value.length;
        inputElt.size = inputLength;
        console.log("Resizing");
    }
</script>
<style scoped>
    .container{
        padding-left: 20px;
        padding-right: 20px;
        height: 100vh;
        border: none;
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        padding-top: 20px;
        background-color: #832cc9;
        justify-content: center;
    }

    .field{
        background-color: white;
        border-radius: 5px;
        border: 2px solid lightgray;
        padding-left: 5px;
        padding-right: 5px;
        padding-bottom: 10px;
        margin: 10px 0px 10px 0px;
        display:flex;
        flex-direction: column;
        align-items: center;
    }

    .field:hover{
        border: 2px solid black;
    }

    .field > input:focus {
        outline-style:none; 
        border-color: #832cc9;
        border-width: 3px;
        border-radius: 5px;
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
    }
    .header > div{
        margin-bottom: 10px;
    }

    .title{
        font-size: 40px;
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
        margin-top: 40px;
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