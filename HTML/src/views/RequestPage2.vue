<template>
    <div>
        <!--TO DO: delete request_page_2.html-->
        <!-- progress bar -->
            <Progressbar />
        
        <!-- Go back to page 1 -->
        <button @click="goToPage1">Back</button>
    
        <!-- Move forward to page 3 -->
        <button @click="goToPage3">Continue</button>

        <div>
        <h3>Selected Date & Time</h3>
        <!--table w specific time information-->
        <!--TO DO: add specific time info from input-->
        <table>
            <tr>
                <th>Date</th>
                <td>08/31/2022</td>
            </tr>
            <tr>
                <th>Start Time</th>
                <td>9am</td>
            </tr>
            <tr>
                <th>End Time</th>
                <td>1pm</td>
            </tr>
        </table>
    </div>

    <!--event type drop down selection-->
    <div>
        <!--TO DO: store input-->
        <label for="event type">Event Type<br></label>
        <select name="event type" id="event type">
            <!--TO DO: add additional event types to drop down list-->
            <option value="TCU">TCU</option>
        </select>
    </div>

    <!--personal contact info-->
    <!--TO DO: store these inputs-->
    <div>
        <h2>Personal Contact Information</h2>
        <!--first name-->
        <label for="fname">Contact First Name<br></label>
        <input type="text" id="fname" name="fname"> 

        <!--last name-->
        <label for="lname">Contact Last Name<br></label>
        <input type="text" id="lname" name="lname"><br>

        <!--phone number-->
        <!--TO DO: add phone number validation?-->
        <label for="pnumber">Phone Number<br></label>
        <input type="text" id="pnumber" name="pnumber"> 

        <!--last name name-->
        <label for="email">Email<br></label>
        <input type="text" id="email" name="email"><br>
    </div>

    <div>
        <!--TO DO: store these inputs-->
        <h2>Event Information</h2>
        <!--event title-->
        <label for="etitle">Event Title<br></label>
        <input type="text" id="etitle" name="etitle"> 

        <!--org name-->
        <label for="orgname">Name of Organization<br></label>
        <input type="text" id="orgname" name="orgname"><br>

        <!--TO DO: validate if location is within 100 mile radius by entering the address after focus is lost-->
        <!--address-->
        <label for="address">Address of Appearance<br></label>
        <input type="text" id="address" name="address"><br>
        <p>Appearances are only available in the DFW area. If more than 2 miles from TCU, a $0.75 per mile transportation fee will be included in the appearance total.</p>
        
        <!--description-->
        <label for="description">Event Description<br></label>
        <textarea id="description" name="description" rows="4" cols="50"></textarea>

        <!--speacial instructions-->
        <label for="instructions">Special Instructions<br></label>
        <textarea id="instructions" name="instructions" rows="4" cols="50"></textarea><br>
        
        <!--outside orgs-->
        <label for="outorgs">List any other outside organizations involved in sponsoring the event<br></label>
        <textarea id="outorgs" name="outorgs" rows="4" cols="50"></textarea>

        <!--expenses & benefits-->
        <label for="benefits">Describe any expenses or benefits to the spirit team members<br></label>
        <textarea id="benefits" name="benefits" rows="4" cols="50"></textarea><br>
    </div>
    </div>
    </template>
    
    <script setup>
        import Progressbar from '../components/progressbar.vue';
        import { ref } from 'vue';

        //TO DO: validate moving on to next page 

        const goToPage1 = () => {
            // go to page 1
            router.push('/page1');
        };

        const goToPage3 = () => {
            // go to page 3 
            router.push('/page3');
        };
    </script>
    
    