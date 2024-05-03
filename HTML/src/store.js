import { defineStore } from 'pinia';

export const useStore = defineStore({
    id: 'main',
    state: () => ({
        count: 0,
        requestData: {}
    }),
    actions: {
        increment() {
            this.count++;
        },
        updateRequestData(payload) {
            this.requestData = { ...this.requestData, ...payload, status: 'Pending' };
        },
        async sendRequestData() {
            try {
                const response = await fetch('http://localhost:8081/students', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.requestData)
                });
                const data = await response.json();
                // Handle the response from the backend
            } catch (error) {
                // Handle any errors
            }
        }
    }
});
