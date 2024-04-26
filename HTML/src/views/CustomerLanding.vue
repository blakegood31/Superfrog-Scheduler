<template>
    <div>

        <!-- progress bar -->
            <Progressbar />

        <div>
        <h2>TCU Customer Landing</h2>
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
        import { useRouter } from 'vue-router'


        const selectedDate = ref('');
        const router = useRouter();

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
    
    