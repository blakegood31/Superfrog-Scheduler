<script setup>
import CustomerLanding from './CustomerLanding.vue';
import RequestPage1 from './RequestPage1.vue';
import RequestPage2 from './RequestPage2.vue';
import RequestPage3 from './RequestPage3.vue';
import RequestPage4 from './RequestPage4.vue';
import RequestPage5 from './RequestPage5.vue';
</script>

<template>
  <div>
    <router-view></router-view>
    <CustomerLanding />
    <RequestPage1 />
    <RequestPage2 />
    <RequestPage3 />
    <RequestPage4 />
    <RequestPage5 />
  </div>
</template>


