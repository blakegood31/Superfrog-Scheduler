<template>
  <div class="container">
    <!-- Title -->
    <div class="title">SuperFrog Scheduler</div>
    <!-- Description -->
    <div class="description">Add TCU Spirit to your Event! Request SuperFrog, TCU Cheerleaders, & Showgirls to your wedding, grad party, and more!</div>
    <!-- Button Box -->
    <div class="button-box">
      <router-link to="/pricing" class="button">
        <i class="material-icons">payments</i>
        <span>Pricing</span>
      </router-link>
      <router-link to="/range" class="button">
        <i class="material-icons">location_on</i>
        <span>Range</span>
      </router-link>
      <router-link to="/request" class="button">
        <i class="material-icons">calendar_month</i>
        <span>Request</span>
      </router-link>
      <router-link to="/modify-request" class="button">
        <i class="material-icons">edit</i>
        <span>Modify<br>a Request</span>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminLanding'
  // You can add component logic here if needed
}
</script>

<style scoped>
/* Paste the CSS styles here */
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('https://example.com/path/to/your/image.jpg'); /* Update with your local image path */
  /* Background image URL */
  background-size: cover;
  /* Cover the entire viewport */
  background-position: center;
  /* Center the background image */
  color: rgb(94, 13, 160);
  /* Text color */
}

.container {
  border-radius: 15px;
  background-color: rgba(94, 13, 160, 0.8);
  width: 70%;
  /* Adjust the width as needed */
  padding-top: 35%;
  /* Adjust the height (aspect ratio) as needed */
  text-align: center;
  position: relative;
  /* Needed for absolute positioning of children */
}

.title {
  font-size: 90px;
  font-weight: bold;
  color: white;
  margin-bottom: 10px;
  position: absolute;
  top: 50px; /* Adjust the vertical position */
  left: 50%;
  transform: translateX(-50%);
  white-space: nowrap; /* Prevent text from wrapping */
}

.description {
  font-size: 30px;
  color: white;
  margin-bottom: 30px;
  position: absolute;
  top: 200px; /* Adjust the vertical position */
  left: 50%;
  transform: translateX(-50%);
}

.button-box {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  justify-content: center;
}

.button {
  padding: 10px 70px;
  margin: 20px;
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-decoration: none;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.button i.material-icons {
  font-size: 70px;
  margin-bottom: 10px;
}

.button span {
  font-size: 15px;
}

.button:hover {
  background-color: #9079ab;
}

