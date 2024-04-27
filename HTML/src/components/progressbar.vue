<script setup>
import { ref } from 'vue'

defineProps({
  
})

</script>

<template>
  <div class="ProgressBar">
    <el-progress :text-inside="true" :stroke-width="26" :percentage="70" />
    <el-progress
      :text-inside="false"
      :stroke-width="24"
      :percentage="100"
      status="success">
    </el-progress>
  </div>
</template>

<style scoped>
.ProgressBar .el-progress--line {
  margin-bottom: 15px;
  max-width: 600px;
}
</style>