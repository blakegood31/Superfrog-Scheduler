<template>
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
                    <i :class="{'bx bxs-chevron-down': !showStudents, 'bxs-chevron-up': showStudents}" id="btnStudents"></i>
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
                    <i :class="{'bx bxs-chevron-down': !showRequests, 'bxs-chevron-up': showRequests}" id="btnRequests"></i>
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
    .TCU_logo img {
        width: 45%;
        height: auto;
        padding-left: 26%;
    }

    .sidebar {
        position: absolute;
        top: 0;
        left: 0;
        background-color: #531e7e;
        width: 25%;
        height: 100%;
        color: white;
        overflow: hidden;
        transition: width 0.3s ease;
    }

    .sidebar ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .sidebar a {
        text-decoration: none;
        font-size: 250%;
        color: inherit;
        display: flow;
        justify-content: space-between;
        align-items: center;
        padding: 25px;
        transition: background-color 0.3s ease;
    }

    .sidebar a:hover {
        background-color: rgba(205, 205, 205, 0.1);
        transition: all 0.5s ease;
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