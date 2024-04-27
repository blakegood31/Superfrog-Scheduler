<template>
    <div>

        <!-- progress bar -->
            <Progressbar />

        <div>
        <h2>TCU Spirit Team Terms and Conditions</h2>
        <!--bulleted term & condition statements-->
        <ul>
            <li>Submission of a request does not guarantee the event will be accepted.</li>
            <li>TCU athletics takes priority over all other appearances.</li>
            <li>TCU Spirit has until 5 days prior to the date of the event to accept or decline a request.</li>
            <li>Once approved via email, payment must be submitted a minimum of 24 hours prior to the event or it will be canceled.</li>
            <li>Even after an event is confirmed, it is still subjected to immediate cancellation due to a TCU Athletic Department function, weateher or an emergency. Immediate notice will be given in the event of cancellation.</li>
            <li>The SuperFrog suit is NOT rented out to a third party.</li>
        </ul>
    </div>
    <!--checkbox for customer to confirm agreement to terms & conditions-->
    <!--TO DO: validate this checkbox to require the customer to check it before moving on-->
    <div>
        <input type="checkbox" id="confirmation" name="confirmation" value="terms">
        <label for="confirmation"> I agree to the terms & conditions of a TCU Spirit Appearance.</label><br>
    </div>
  
        <!-- Go back to page2 -->
        <button @click="goToPage2">Back</button>
    
        <!-- Move forward to page 4 -->
        <button @click="goToPage4">Continue</button>
        </div>
    </template>
    
    <script setup>
        import Progressbar from '../components/progressbar.vue';
        import { ref } from 'vue';
        import { useRouter } from 'vue-router';
        
        const router = useRouter();
        const selectedDate = ref('');

        const goToPage2 = () => {
            // go to page 2
            router.push('/page2');
        };

        //TO DO: add any validations to move on
        const goToPage4 = () => {
            // go to page 4
            router.push('/page4');
        };
    </script>
    
    