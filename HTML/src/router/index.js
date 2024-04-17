import { createRouter, createWebHistory } from 'vue-router';
import CustomerLanding from '../views/CustomerLanding.vue';
import RequestPage1 from '../views/RequestPage1.vue';
import RequestPage2 from '../views/RequestPage2.vue';
import RequestPage3 from '../views/RequestPage3.vue';

const routes = [
    { path: '/custland', component: CustomerLanding },
    { path: '/page1', component: RequestPage1 },
    { path: '/page2', component: RequestPage2 },
    { path: '/page3', component: RequestPage3 }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;