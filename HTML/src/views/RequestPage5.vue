<template>
  <div>

    <!-- progress bar -->
    <Progressbar />

    <div>
      <div>
        <h2>Thank you!</h2>
        <p>Your request has been submitted.<br>
          An email of your request is sent to your email.<br>
          Here is your request ID.</p>
        <!--request id-->
        <!--TO DO: change to unique request id-->
        <h2>SG1025602DGH</h2>
      </div>
    </div>

    <!-- Go back to page4 -->
    <button @click="goToPage4">Back</button>


  </div>
</template>

<script setup>
import Progressbar from '../components/progressbar.vue';
import { ref } from 'vue';

const selectedDate = ref('');

const goToPage4 = () => {
  // go to page 4
  router.push('/page4');
};

</script>

