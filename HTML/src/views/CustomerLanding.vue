<template>
    <div>
        <h2>TCU Customer Landing</h2>
    </div>
    <!--see if they need to make a request or edit a request-->
    <div>
        <!-- Go to CustRequestPage -->
        <button @click="goToRequest">Make a Request</button>
        <!-- Move to CustEditRequestDetails -->
        <button @click="goToEdit">Edit/Cancel a Request</button>
    </div>
</template>
<script setup>
    import { ref } from 'vue';
    import { useRouter } from 'vue-router'


    const selectedDate = ref('');
    const router = useRouter();

    const goToRequest = () => {
        // go to page 2
        router.push('/custrequest');
    };

    const goToEdit = () => {
        // go to edit/cancel customer request page
        router.push('/editcustrequest');
    };
</script> 

