<template>
    <div class="container">
        
        <div class="field">
            <div>
            <h2>Event Information</h2>
            <!-- Date picker -->
            <br>
            <label for="evdate">When is your event?</label>
            <input type="date" id="evdate" name="evdate" v-model="newRequest.selectedDate">
            <br>
        </div>

        <!-- Event type dropdown selection -->
        <div v-if="newRequest.selectedDate" >
            <br><br>
            <label for="eventType">Event Type</label>
            <select name="eventType" id="eventType" v-model="newRequest.eventType">
                <option value="TCU">TCU</option>
                <option value="notTCU">not TCU</option>
                <!-- Add additional event types here -->
            </select>

        </div></div>

        <!-- Personal contact info -->

        <div v-if="newRequest.eventType" class="field">
            <h2>Personal Contact Information</h2>

            <br>

            <label for="fname">Contact First Name</label>
            <input type="text" id="fname" name="fname" v-model="newRequest.firstName">

            <br><br>

            <label for="lname">Contact Last Name</label>
            <input type="text" id="lname" name="lname" v-model="newRequest.lastName">

            <br><br>

            <label for="pnumber">Phone Number</label>
            <input type="text" id="pnumber" name="pnumber" v-model="newRequest.phoneNumber">

            <br><br>

            <label for="email">Email</label>
            <input type="text" id="email" name="email" v-model="newRequest.email">

            <br><br>
        </div>

        <!-- Event Information -->
        <div v-if="newRequest.email" class=field>
            <h2>Event Information</h2>
            <label for="etitle">Event Title</label>
            <input type="text" id="etitle" name="etitle" v-model="newRequest.eventTitle">

            <br><br>

            <label for="orgname">Name of Organization</label>
            <input type="text" id="orgname" name="orgname" v-model="newRequest.orgName">

            <br><br>

            <label for="address">Address of Appearance</label>
            <input type="text" id="address" name="address" v-model="newRequest.address">
            <p><em>Appearances are only available in the DFW area. If more than 2 miles from TCU, a $0.75 per mile transportation fee will be included in the appearance total.</em></p>

            <br><br>

            <label for="description">Event Description</label><br>
            <textarea id="description" name="description" rows="4" cols="50" v-model="newRequest.description"></textarea>

            <br><br>

            <label for="instructions">Special Instructions</label><br>
            <textarea id="instructions" name="instructions" rows="4" cols="50" v-model="newRequest.specialInstructions"></textarea>

            <br><br>

            <label for="outorgs">List any other outside organizations involved in sponsoring the event</label><br>
            <textarea id="outorgs" name="outorgs" rows="4" cols="50" v-model="newRequest.other_orgs"></textarea>

            <br><br>

            <label for="benefits">Describe any expenses or benefits to the spirit team members</label><br>
            <textarea id="benefits" name="benefits" rows="4" cols="50" v-model="newRequest.expenses"></textarea>

            <br><br>

        </div>

        <div v-if="newRequest.expenses" class="field">
            <h2>TCU Spirit Team Terms and Conditions</h2><br>
            <ul>
                <li>Submission of a request does not guarantee the event will be accepted.</li>
                <li>TCU athletics takes priority over all other appearances.</li>
                <li>TCU Spirit has until 5 days prior to the date of the event to accept or decline a request</li>
                <li>Once approved via email, payment must be submitted a minimum of 24 hours prior to the event or it will be canceled.</li>
                <li>Even after an event is confirmed, it it still subjected to immediate cancellation due to a TCU Athletic Department function, weather or an emergency. Immediate notice will be given in the event of cacellation.</li>
                <li>The SuperFrog suit is NOT rented out to a third party.</li>
            </ul>

            <br>

            <input type="checkbox" id="confirmation" name="confirmation" value="terms" v-model="newRequest.termsAgreed">
            <label for="confirmation"> I agree to the terms & conditions of a TCU Spirit Appearance.</label>
        </div><br>
    
    <div class="options">
    <!-- Go back to landing -->
    <button @click="goToLanding">Back</button>

    <!-- Move forward to submission page -->
    <button @click="goToConfirm" :disabled="!newRequest.termsAgreed">Submit</button>
    
</div>
<br><br>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import { useStore } from 'vuex';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const newRequest = ref({
        selectedDate: '',
        eventType: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        eventTitle: '',
        orgName: '',
        address: '',
        description: '',
        specialInstructions: '',
        other_orgs: '',
        expenses: ''
    });

    const store = useStore();
    let termsAgreed = ref(false);

    const goToLanding = () => {
        // go to customer landing page
        router.push('/customer');
    };

    const goToConfirm = () => {
        //perform validation checks
        /*if (!validateRequest()) {
            return;
        }*/

        //set status to PENDING
        newRequest.value.status = "PENDING";

        const options = {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer' + localStorage.getItem('userToken')
            },
            body: JSON.stringify(newRequest.value)
        };

        console.log(options.body);
        const url = 'http://127.0.0.1:8081/requests';
        fetch(url, options)
            .then(response => {
                if(response.ok){
                    console.log(response);
                    return response.json();
                } else {
                    console.log("ERROR!");
                }
            }).then(data => {
                localStorage.setItem('requestToSubmit', JSON.stringify(data.data));
                //editedRequest.value = JSON.parse(localStorage.getItem('requestToSubmit'));
                //oldRequest.value = JSON.parse(localStorage.getItem('requestToSubmit'));
            });

        //send to submit
        router.push('/requestconfirmation');
    };

    //validations
    const validateRequest = () => {
        const { firstName, lastName, phoneNumber, email, eventType, eventTitle, orgName, address, specialInstructions, other_orgs, description } = newRequest.value;

        //check if empty
        if (!newRequest.firstName || !newRequest.lastName || !newRequest.phoneNumber || !newRequest.email || !newRequest.eventType || !newRequest.eventTitle || !newRequest.orgName || !newRequest.address || !newRequest.specialInstructions || !newRequest.other_orgs || !newRequest.description) {
            console.log("Please fill in all required fields.");
            return false;
        }

        // Check phone number format
        const phoneFormat = /^\(\d{3}\) \d{3}-\d{4}$/;
        if (!phoneFormat.test(newRequest.phoneNumber)) {
            console.log("Phone number format is incorrect. Please use (999) 999-9999 format.");
            return false;
        }

        // Check email format
        const emailFormat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailFormat.test(newRequest.email)) {
            console.log("Email format is incorrect. Please use a valid email address.");
            return false;
        }

        // TO DO: address validation

        return true;
    }
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
