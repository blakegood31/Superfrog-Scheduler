<template>
<div id="app">    
    <Sidebar>
        <div class="container" id="app">
            <AdminHeader/>
            <p id="view-students"> Students </p>
            <label>
                <input type="checkbox" v-model="showActiveOnly"> Show active students only
            </label>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email Address</th>
                        <th>Account Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(student, index) in displayedStudents" :key="student.id">
                        <td>{{ student.id }}</td>
                        <td>{{ student.firstName }}</td>
                        <td>{{ student.lastName }}</td>
                        <td>{{ student.email }}</td>
                        <td>{{ student.active ? "Enabled" : "Disabled" }}</td>
                        <td>
                            <button @click="toggleStudentStatus(student)">
                                {{ student.active ? "Disable" : "Enable" }}
                            </button>
                            <button @click="viewDetails(student.id)">View Details</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="pagination">
                <button :disabled="currentPage === 1" @click="prevPage">Prev</button>
                <span>{{ currentPage }} / {{ totalPages }}</span>
                <button :disabled="currentPage === totalPages" @click="nextPage">Next</button>
            </div>
        </div>
    </Sidebar>
</div>
</template>

<script setup>
    import AdminHeader from '../components/adminHeader.vue';
    import Sidebar from '../components/Sidebar.vue';
    import { useRouter } from 'vue-router';
    import { computed } from 'vue';


    const router = useRouter();

    const students = defineModel('students');
    const showActiveOnly = defineModel('showActiveOnly');
    const currentPage = defineModel('currentPage');
    const rowsPerPage = defineModel('rowsPerPage');

    students.value = [];
    showActiveOnly.value = false;
    currentPage.value = 1;
    rowsPerPage.value = 5;

    fetch("http://127.0.0.1:8081/students")
        .then(response => response.json())  // Convert the response to JSON
        .then(data => {
            students.value = data.data;
        })
        .catch(error => {
            console.log(error);
        });
        

    const getStudents = () => {
        fetch("http://127.0.0.1:8081/students")
        .then(response => response.json())  // Convert the response to JSON
        .then(data => {
            students.value = data.data;
        })
        .catch(error => {
            console.log(error);
        });
    };

    const toggleStudentStatus = (student) => {
        let reason = '';
        if (student.active) {
        reason = prompt('Please enter a reason for disabling the student:');
        if (!reason) {
            return;
        }
        }
        const url = `http://127.0.0.1:8081/users/${student.email}/${student.active ? 'disable' : 'enable'}`;
        const token = localStorage.getItem('token');
        const headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
        };
        fetch(url, {
            method: 'PUT',
            headers: headers,
            body: JSON.stringify({ reason })
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            getStudents();
        })
        .catch(error => {
            console.log(error);
        });
    };

    const viewDetails = (studentid) => {
        const token = localStorage.getItem('token');
        const url = `http://localhost:8081/students/${studentid}`;

        fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        }).then(response => {
            if (response.ok) {
                console.log("Successful Token Usage");
                return response.json();
            } else {
                console.log("Token Usage Unsuccessful");
                console.log(response);
                throw new Error("Token usage failed");
            }
        }).then(data => {
            console.log(data);
            localStorage.setItem('studentToView', JSON.stringify(data)); // Store the student data for later use
            this.$router.push('/viewStudentDetails'); 
        }).catch(error => {
            console.error('Error: ', error);
        });
    };

    const prevPage = () => {
        if (currentPage.value > 1) {
        currentPage.value--;
        }
    };

    const nextPage = () => {
        if (currentPage.value < totalPages.value) {
        currentPage.value++;
        }
    };

    const totalPages = computed(() => {
        let totalPageStudents = [];
        if (showActiveOnly.value) {
            totalPageStudents = students.value.filter(student => student.active);
        } else {
            totalPageStudents = students.value;
        }
        return Math.ceil(totalPageStudents.length / rowsPerPage.value);
    });

    const displayedStudents = computed(() => {
        let studentsToDisplay = [];
        if (showActiveOnly.value) {
            studentsToDisplay = students.value.filter(student => student.active);
        } else {
            studentsToDisplay = students.value;
        }
        const startIndex = (currentPage.value - 1) * rowsPerPage.value;
        const endIndex = startIndex + rowsPerPage.value;
        return studentsToDisplay.slice(startIndex, endIndex);
    });
</script>

<style scoped>
.container {
    height: 71vh;
    width: auto;
}
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

th {
    background-color: #531e7e;
    color: white;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

</style>