<template>
  <div class="adminLogin">
    <div class="reportPage">
      <h2>Superfrog Report</h2>
      <div>
        <form @submit.prevent="submitReport">
          <label>Superfrog ID:</label>
          <input type="text" v-model="superfrogId" />
          <label>Report Summary:</label>
          <textarea v-model="reportSummary" maxlength="200"></textarea>
          <label>Rating</label>
          <input type="number" v-model="rating" max="5" step=".5" /><span> / 5</span>
          <br>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      superfrogId: '',
      reportSummary: '',
      rating: null
    };
  },
  methods: {
    submitReport() {
      // Here you can handle the submission logic, like sending data to the server
      console.log('Superfrog ID:', this.superfrogId);
      console.log('Report Summary:', this.reportSummary);
      console.log('Rating:', this.rating);
    }
  }
};
</script>

<style scoped>
.adminLogin {
  /* Your styles for adminLogin */
}

.reportPage {
  /* Your styles for reportPage */
}

/* You can include the CSS rules from your admin_report_page.css here */
</style>
