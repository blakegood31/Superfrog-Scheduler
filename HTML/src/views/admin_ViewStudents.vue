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
                        <td>{{ student.first_name }}</td>
                        <td>{{ student.last_name }}</td>
                        <td>{{ student.email }}</td>
                        <td>{{ student.active ? "Enabled" : "Disabled" }}</td>
                        <td>
                            <button @click="toggleStudentStatus(student)">
                                {{ student.active ? "Disable" : "Enable" }}
                            </button>
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

<script>
import AdminHeader from '../components/adminHeader.vue';
import Sidebar from '../components/Sidebar.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
export default {
    name: "ViewStudents",
    data() {
    return {
        students: [],
        showActiveOnly: false,
        currentPage: 1,
        rowsPerPage: 5,
    };
    },
    mounted() {
    this.getStudents();
    },
    methods: {
    getStudents() {
        fetch("http://127.0.0.1:8081/students")
        .then(response => response.json())  // Convert the response to JSON
        .then(data => {
            this.students = data.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
    toggleStudentStatus(student) {
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
            this.getStudents();
        })
        .catch(error => {
            console.log(error);
        });
    },
    prevPage() {
        if (this.currentPage > 1) {
        this.currentPage--;
        }
    },
    nextPage() {
        if (this.currentPage < this.totalPages) {
        this.currentPage++;
        }
    },

    },
    computed: {
    totalPages() {
        let students = [];
        if (this.showActiveOnly) {
        students = this.students.filter(student => student.active);
        } else {
        students = this.students;
        }
        return Math.ceil(students.length / this.rowsPerPage);
    },
    displayedStudents() {
        let students = [];
        if (this.showActiveOnly) {
        students = this.students.filter(student => student.active);
        } else {
        students = this.students;
        }
        const startIndex = (this.currentPage - 1) * this.rowsPerPage;
        const endIndex = startIndex + this.rowsPerPage;
        return students.slice(startIndex, endIndex);
    },
    },
};
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