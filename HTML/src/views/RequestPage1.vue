<template>
    <div>
        <!-- TO DO: date & time pickers-->
        <!-- progress bar -->
        <Progressbar />
      
        <!-- Date picker -->
        <div>
            <label for="evdate">When is your event?</label>
            <input type="date" id="evdate" name="evdate" v-model="state.selectedDate">
        </div>
      
        <!-- Go back to landing -->
        <button @click="state.router.push('/')">Back</button>
      
        <!-- Move forward to page 2 -->
        <button @click="goToPage2" :disabled="!state.selectedDate">Continue</button>
    </div>
</template>

<script setup>
    import Progressbar from '../components/progressbar.vue';
    import { ref } from 'vue';
    import { useStore } from 'pinia';
    import { useRouter } from 'vue-router';

    const selectedDate = ref('');
    const router = useRouter();
    const store = useStore();

    const goToPage2 = () => {
        if (selectedDate.value) {
            store.commit('updateRequestData', { selectedDate: selectedDate.value });
            router.push('/page2');
        }
    };
</script>