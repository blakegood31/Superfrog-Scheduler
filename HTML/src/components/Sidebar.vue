<template>
    <div class="container">
      <div class="sidebar">
        <div class="top">
          <div class="TCU_logo">
            <img src="https://dejpknyizje2n.cloudfront.net/gallery/tcu-horned-frogs-ncaa-logo-sticker-ncaa03-5076-dbf0a0.png" alt="TCU_logo">
          </div>
        </div>
        <ul>
          <li>
            <a href="#" @click="toggleStudents">
              <i class="bx bxs-id-card"></i>
              <span class="nav-item">SuperFrog Students</span>
              <i :class="{'bx bxs-chevron-down': !showStudents, 'bx bxs-chevron-up': showStudents}" id="btnStudents"></i>
            </a>
            <ul id="studentOptions" v-show="showStudents">
              <li>
                <a href="#">
                  <span class="nav-item">Add a New Student</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <span class="nav-item">View All Students</span>
                </a>
              </li>
            </ul>
          </li>
        </ul>
        <ul>
          <li>
            <a href="#" class="with-chevron" @click="toggleRequests">
              <i class="bx bxs-message-edit"></i>
              <span class="nav-item">Requests</span>
              <i :class="{'bx bxs-chevron-down': !showRequests, 'bx bxs-chevron-up': showRequests}" id="btnRequests"></i>
            </a>
            <ul id="requestOptions" v-show="showRequests">
              <li>
                <a href="#">
                  <span class="nav-item">Add a New Request</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <span class="nav-item">View All Requests</span>
                </a>
              </li>
            </ul>
          </li>
        </ul>
        <ul>
          <li>
            <a href="#">
              <i class="bx bxs-calendar"></i>
              <span class="nav-item">Calendar</span>
            </a>
          </li>
        </ul>
        <ul>
          <li>
            <a href="#">
              <i class="bx bxs-report"></i>
              <span class="nav-item">Reports</span>
            </a>
          </li>
        </ul>
      </div>
      <div class="page-content">
        <slot></slot>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        showStudents: false,
        showRequests: false
      };
    },
    methods: {
      toggleStudents() {
        this.showStudents = !this.showStudents;
      },
      toggleRequests() {
        this.showRequests = !this.showRequests;
      }
    }
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    width: 100%;
    height: 100vh; 
  }
  
  .page-content {
    width: 100%;
  }
  
  .TCU_logo img {
    width: 45%;
    height: auto;
    padding-left: 26%;
  }
  
  .sidebar {
    background-color: #531e7e;
    width: 24%;
    height: 100%;
    color: white;
    overflow: hidden;
  }
  
  .sidebar:hover {
    box-shadow: none;
  }
  
  .sidebar:not(:hover) {
    box-shadow: inset -4px -3px 6px 0px black;
  }
  
  .sidebar ul {
    list-style: none;
    padding: 0%;
    margin: 0%;
  }
  
  .sidebar a {
    text-decoration: none;
    font-size: 150%;
    color: inherit;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding: 25% 10% 5% 5%;
  }

  .sidebar i {
    margin-right: 1%;
  }
  .sidebar .nav-item {
    white-space: nowrap; /* Prevent text from wrapping */
}

  .sidebar a:hover {
    background-color: rgba(205, 205, 205, 0.1);
  }
  </style>
  

<!-- HOW TO IMPORT INTO OTHER VUE PAGES!
<template>
  <div>
    <Sidebar />
    //otehr content in your application
  </div>
</template>

<script>
import Sidebar from './Sidebar.vue'; // Import the Sidebar component

export default {
  components: {
    Sidebar // Register the Sidebar component
  },
  // Other script code of your Vue file
};
</script>

<style>
/* Styles specific to your App.vue file */
</style>
-->