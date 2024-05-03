import { createRouter, createWebHistory } from 'vue-router';
import { createPinia } from 'pinia';

const pinia = createPinia();

//import route components
import CustomerLanding from '../views/CustomerLanding.vue';
import RequestPage1 from '../views/RequestPage1.vue';
import RequestPage2 from '../views/RequestPage2.vue';
import RequestPage3 from '../views/RequestPage3.vue';
import RequestPage4 from '../views/RequestPage4.vue';
import RequestPage5 from '../views/RequestPage5.vue';
import SignInPage from '../views/SignInPage.vue';
import AdminLanding from '../views/AdminLanding.vue';
import EditRequestDetails from '../views/EditRequestDetails.vue';
import ViewRequest from '../views/ViewRequest.vue';

//create the router instance
const router = createRouter({
    //provide the history implementation to use
    history: createWebHistory(),
    routes: [
        { path: '/', component: SignInPage },
        { path: '/login', component: SignInPage},
        { path: '/admin', component: AdminLanding},
        { path: '/editRequest', component: EditRequestDetails},
        { path: '/viewRequest', component: ViewRequest},
        { path: '/customer', component: CustomerLanding},
        { path: '/page1', component: RequestPage1 },
        { path: '/page2', component: RequestPage2 },
        { path: '/page3', component: RequestPage3 },
        { path: '/page4', component: RequestPage4 },
        { path: '/page5', component: RequestPage5 }
    ]
});

// create the store
const store = pinia.state({
    state () {
        return{
            count: 0,
            requestData: {}
        }
    },
    mutations: {
        increment(state) {
            state.count++
        },
        updateRequestData(state, payload){
            state.requestData = payload;
        }
    }

});



// use pinia to help save input data across all request pages
// before moving on to next page, save inputs from current page
router.beforeEach((to, from, next) => {
    const store = useStore();
    switch (to.path) {
        case '/page1':
            store.updateRequestData({ selectedDate: '2099-01-01' });
            break;
        case '/page2':
            store.updateRequestData({ description: '' });
            break;
        case '/page3':
            store.updateRequestData({ description: '' });
            break;
        case '/page4':
            store.updateRequestData({ description: '' });
            break;
        case '/page5':
            store.updateRequestData({ description: '' });
            break;
        default:
            break;
    }
    next();
});

export {store};
export default router;