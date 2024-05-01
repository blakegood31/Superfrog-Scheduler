<template>
    <div id="app" class="adminLogin"> <!-- Added id="app" -->
        <div class="loginForm">
            <div class="tcuLogo"></div> <!-- Added TCU logo div -->
            <h2>Superfrog Director</h2>
            <form @submit.prevent="login"> <!-- Added submit.prevent to prevent default form submission -->
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="username" required/> <!-- Bound input to username data property -->
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="password" required/>
                <!-- Bound input to password data property -->
                <div><button type="submit" class="loginButton">Login</button></div>
                <!-- Changed type to submit and added @submit event -->
            </form>
            <div v-if="loginFailed" class="loginError">
                <p>Login unsuccessful. Please try again.</p>
            </div>
        </div>
    </div>
</template>
<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const username = defineModel('username');
    const password = defineModel('password');
    const loginFailed = defineModel('loginFailed');

    const login = () => {
        // Redirect to a specific page after successful login
        //window.location.href = 'request_page_1.html';
        console.log(username.value);
        console.log(password.value);
        loginFailed.value = false;
        fetch('http://localhost:8081/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa(username.value + ':' + password.value)
            },
            body: JSON.stringify({
                username: username.value ,
                password: password.value,
            }),
        }).then(response => {
            if(response.ok){
                console.log("Successful Login");
                console.log(response);
                loginFailed.value = false;
                return response.json();
            }
            else{
                console.log("Login Unsuccessful");
                username.value  = '';
                password.value = '';
                loginFailed.value = true;
                console.log(response);
                throw new Error("Login failed")
            }
        }).then(data => {
            console.log(data.data.userInfo);
            const token = data.data.token;
            localStorage.setItem('userToken', token);
            localStorage.setItem('userInfo', JSON.stringify(data.data.userInfo));
            if(data.data.userInfo.roles.split(' ').includes("admin") || data.data.userInfo.roles.split(' ').includes("superfrog")){
                router.push('/admin');
            }
            else{
                router.push('/customer');
            }
        }).catch(error => {
            loginFailed.value = true;
            console.error('Error: ', error);
        });
    }
</script>
<style scoped>
    .adminLogin {
        display: flex;
        height: 100vh;
        align-items: center;
        background-image: url('https://steve-lovelace.com/wordpress/wp-content/uploads/2015/01/tcu-campus-05.jpg');
        background-position: center;
        background-size: cover;
    }

    .tcuLogo {
        background-image: url('https://upload.wikimedia.org/wikipedia/en/thumb/7/72/Texas_Christian_University_seal.svg/1200px-Texas_Christian_University_seal.svg.png');
        background-size: contain;
        background-repeat: no-repeat;
        width: 200px;
        height: 200px;
        /* Adjust the height as needed */
        margin: 0 auto;
    }

    h2 {
        font-weight: normal;
        text-align: center;
    }

    label {
        display: block;
        margin-bottom: 5px;
    }

    input {
        width: 100%;
        padding: 5px;
        margin-bottom: 10px;
        border: 1px solid rgb(220, 220, 220);
        border-radius: 5px;
    }

    .loginForm {
        position: relative;
        width: 350px;
        margin: 0 auto;
        padding: 50px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #ffffff;
        box-shadow: 0 0 10px #ccc;
    }

    .loginButton {
        color: #ffff;
        display: block;
        width: 50%;
        margin: 0 auto;
        border: none;
        border-radius: 3px;
        padding: 5px 10px;
        background-color: #531e7e;
        cursor: pointer;
        transition: all 0.1s;
    }

    .loginButton:hover {
        background-color: #9079ab;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
    }

    .loginError {
        color: darkred;
        margin-top: 5px;
        text-align: center;
    }
</style>