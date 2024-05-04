import { createRouter, createWebHistory } from 'vue-router';

//import route components
import CustomerLanding from '../views/CustomerLanding.vue';
import CustRequestPage from '../views/CustRequestPage.vue';
import CustEditRequestDetails from '../views/CustEditRequestDetails.vue';
import SignInPage from '../views/SignInPage.vue';
import AdminLanding from '../views/AdminLanding.vue';
import EditRequestDetails from '../views/EditRequestDetails.vue';
import ViewRequest from '../views/ViewRequest.vue';
import EditSuperfrogProfile from "@/views/EditSuperfrogProfile.vue";
import RequestConfirmation from '../views/RequestConfirmation.vue';

//create the router instance
const router = createRouter({
    //provide the history implementation to use
    history: createWebHistory(),
    routes: [
        { path: '/', component: SignInPage },
        { path: '/login', component: SignInPage},
        { path: '/admin', component: AdminLanding},
        { path: '/editRequest', component: EditRequestDetails},
        { path: '/editSuperfrogProfile', component: EditSuperfrogProfile},
        { path: '/viewRequest', component: ViewRequest},
        { path: '/customer', component: CustomerLanding},
        { path: '/custrequest', component: CustRequestPage },
        { path: '/editcustrequest', component: CustEditRequestDetails },
        { path: '/requestconfirmation', component: RequestConfirmation},
        { path: '/admin_ViewStudents', component: ViewStudents},
    ]
});

// create the store
/*const store = createStore({
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

});*/



// use vuex store to help save input data across all request pages
// before moving on to next page, save inputs from current page
/*router.beforeEach((to, from, next) => {
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
});*/

export default router;