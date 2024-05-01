<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Landing Page</title>
  <!-- Adjusted CSS file path -->
  <link rel="stylesheet" type="text/css" href="CSS/signin_page.css">
</head>

<body>
<div id="app" class="adminLogin">
  <div class="loginForm">
    <div class="tcuLogo"></div>
    <h2>Superfrog Director</h2>
    <form @submit.prevent="login">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required/>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required/>
      <div><button type="submit" class="loginButton">Login</button></div>
    </form>
    <div v-if="loginFailed" class="loginError">
      <p>Login unsuccessful. Please try again.</p>
    </div>
  </div>
</div>

<!-- Added Vue.js library -->
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>

<script>
  new Vue({
    el: '#app',
    data: {
      username: '',
      password: '',
      loginFailed: false
    },
    methods: {
      login() {
        // Redirect to a specific page after successful login
        fetch('http://localhost:8081/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(this.username + ':' + this.password)
          },
          body: JSON.stringify({
            username: this.username,
            password: this.password,
          }),
        }).then(response => {
          if(response.ok){
            console.log("Successful Login");
            console.log(response);
            this.loginFailed = false;
            return response.json();
          }
          else{
            console.log("Login Unsuccessful");
            this.username = '';
            this.password = '';
            this.loginFailed = true;
            console.log(response);
            throw new Error("Login failed")
          }
        }).then(data => {
          console.log(data.data.userInfo);
          const token = data.data.token;
          localStorage.setItem('userToken', token);
          localStorage.setItem('userInfo', JSON.stringify(data.data.userInfo));
          window.location.href = 'admin_landing.vue';
        }).catch(error => {
          console.error('Error: ', error);
        });
      }
    }
  });
</script>
</body>
</html>
