import { createRouter, createWebHistory } from 'vue-router';

//import route components
import CustomerLanding from '../views/CustomerLanding.vue';
import RequestPage1 from '../views/RequestPage1.vue';
import RequestPage2 from '../views/RequestPage2.vue';
import RequestPage3 from '../views/RequestPage3.vue';
import RequestPage4 from '../views/RequestPage4.vue';
import RequestPage5 from '../views/RequestPage5.vue';

//create the router instance
const router = createRouter({
    //provide the history implementation to use
    history: createWebHistory(),
});

const routes = [
    { path: '/custland', component: CustomerLanding },
    { path: '/page1', component: RequestPage1 },
    { path: '/page2', component: RequestPage2 },
    { path: '/page3', component: RequestPage3 },
    { path: '/page4', component: RequestPage4 },
    { path: '/page5', component: RequestPage5 }
];


export default router;