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
        import { useStore } from 'vuex';
        import { useRouter } from 'vue-router';
        
        const router = useRouter();
        const selectedDate = ref('');
        const store = useStore();

        const goToLanding = () => {
            // go to landing page
            router.push('/');
        };

        const goToPage2 = () => {
            // go to page 2 if a date is selected
            if (selectedDate.value) {
                //save the input date using vuex store
                store.commit('updateRequestData', { selectedDate: selectedDate.value });
                router.push('/page2');
            }
        };
    </script>
    
    