<template>
    <div class="AdminHeader">
        <div class="title">Admin Portal</div>
        <!-- Description -->
        <!-- <div class="description">Test Page For User Authentication -- Admin & SF Student</div> -->
    </div>
</template>
<script setup>
    import { useRouter } from 'vue-router';
    const router = useRouter();
</script>
<style scoped>
    .AdminHeader {
        width: auto;
        text-align: center;
        margin-bottom: 20px;
        background-color: #531e7e;
        padding-bottom: 15px;
        padding-top: 15px;
        color: white;
        -webkit-text-fill-color: white;
        -webkit-text-stroke: 1px black;
        font-weight: bold;
    }

    .AdminHeader > div{
        margin-bottom: 10px;
    }

    .AdminHeader > .title{
        font-size: min(4vw, 45px);
        font-weight: bold;
    }

    .AdminHeader > .description {
        font-size: 20px;
    }
</style>