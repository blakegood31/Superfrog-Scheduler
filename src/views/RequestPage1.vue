<template>
    <div>
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
            // Redirect to landing page
            window.location.href = 'customer_landing.html';
        };

        const goToPage2 = () => {
            // Redirect to page 2 if a date is selected
            if (selectedDate.value) {
                window.location.href = 'RequestPage2.vue';
            }
        };
    </script>
    
    <style>
    /* Add component-specific styles here */
    </style>
    