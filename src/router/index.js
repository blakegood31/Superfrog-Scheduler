import { createRouter, createWebHistory } from 'vue-router';
import store from '../store';

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
    routes: [
        { path: '/custland', component: CustomerLanding },
        { path: '/page1', component: RequestPage1 },
        { path: '/page2', component: RequestPage2 },
        { path: '/page3', component: RequestPage3 },
        { path: '/page4', component: RequestPage4 },
        { path: '/page5', component: RequestPage5 }
    ]
});



// use vuex store to help save input data across all request pages
// before moving on to next page, save inputs from current page
router.beforeEach((to, from, next) => {
    // Update the form data in the store based on the current route
    switch (to.path) {
        case '/page1':
            store.commit('updateRequestData', { selectedDate: '2099-01-01' }); 
            break;
        case '/page2':
            store.commit('updateRequestData', { description: '' });
            break;
        case '/page3':
            store.commit('updateRequestData', { description: '' });
            break;
        case '/page4':
            store.commit('updateRequestData', { description: '' });
            break;
        case '/page5':
            store.commit('updateRequestData', { description: '' });
            break;
        default:
            break;
    }
    next();
});


export default router;