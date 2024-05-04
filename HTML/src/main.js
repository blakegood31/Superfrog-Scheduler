import { createApp } from 'vue';
//import '../dist/style.css'
import App from './App.vue';
import router from './router';
//import {store} from './router';
import ElementPlus from 'element-plus';
import CustRequestPage from './views/CustRequestPage.vue';
import CustEditRequestDetails from './views/CustEditRequestDetails.vue';
import RequestConfirmation from './views/RequestConfirmation.vue';
import SignInPage from './views/SignInPage.vue';
import AdminLanding from './views/AdminLanding.vue';
import EditRequestDetails from './views/EditRequestDetails.vue';
import ViewRequest from './views/ViewRequest.vue';
import CustomerLanding from './views/CustomerLanding.vue';





createApp(App)
    .use(ElementPlus)
    .use(router)
    //.use(store)
    //register components
    .component('CustRequestPage', CustRequestPage)
    .component('CustEditRequestDetails', CustEditRequestDetails)
    .component('RequestConfirmation', RequestConfirmation)
    .component('SignInPage', SignInPage)
    .component('AdminLanding', AdminLanding)
    .component('EditRequestDetails', EditRequestDetails)
    .component('ViewRequest', ViewRequest)
    .component('CustomerLanding', CustomerLanding)
    .mount('#app');

//createApp(App).mount('#app')
