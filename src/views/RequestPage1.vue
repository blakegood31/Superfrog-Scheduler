<template>
    <div>
        <!--TO DO: date & time pickers -->
        <!-- progress bar -->
            <Progressbar />
        <!-- Date picker -->
            <div>
                <label for="evdate">When is your event?</label>
                <input type="date" id="evdate" name="evdate" v-model="selectedDate">
            </div>
  
        <!-- Go back to landing -->
        <button @click="goToLanding">Back</button>
    
        <!-- Move forward to page 2 -->
        <button @click="goToPage2" :disabled="!selectedDate">Continue</button>
        </div>
    </template>
    
    <script setup>
        import Progressbar from '../components/progressbar.vue';
        import { ref } from 'vue';

        const selectedDate = ref('');

        const goToLanding = () => {
            // go to landing page
            router.push('/custland');
        };

        const goToPage2 = () => {
            // go to page 2 if a date is selected
            if (selectedDate.value) {
                router.push('/page2');
            }
        };
    </script>
    
    