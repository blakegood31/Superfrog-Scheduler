<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const props = defineProps({
    customClass: {
        type: String,
        default: '',
    },
    });
</script>

<template>
    <span :class="customClass"><slot></slot></span>
</template>
<style scoped>

    .REJECTED {
        background-color: rgb(255, 230, 230);
        color: rgba(230, 0, 0, 1);
        border: 2px solid rgba(230, 0, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .CANCELLED {
        background-color: rgb(255, 230, 230);
        color: rgba(230, 0, 0, 1);
        border: 2px solid rgba(230, 0, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .APPROVED {
        background-color: rgb(230, 255, 230);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .COMPLETED {
        background-color: rgb(230, 255, 230);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .ASSIGNED {
        background-color: rgb(230, 255, 230);
        color: rgba(65, 215, 55, 1);
        border: 2px solid rgba(65, 215, 55, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .PENDING {
        background-color: rgb(255, 249, 223);
        color: rgba(245, 195, 0, 1);
        border: 2px solid rgba(245, 195, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }

    .INCOMPLETE {
        background-color: rgb(255, 249, 223);
        color: rgba(245, 195, 0, 1);
        border: 2px solid rgba(245, 195, 0, 1);
        padding: 4px 8px;
        text-align: center;
        border-radius: 5px;
        font-weight: bold;
    }
</style>