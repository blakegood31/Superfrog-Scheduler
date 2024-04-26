import { createApp } from 'vue'
//import '../dist/style.css'
import App from './App.vue'
import router from './router';
import {store} from './router';
//import { useStore } from 'vuex';
import { createStore } from 'vuex';
import { useRouter } from 'vue-router'
import ElementPlus from 'element-plus'
import RequestPage1 from './views/RequestPage1.vue';
import RequestPage2 from './views/RequestPage2.vue';
import RequestPage3 from './views/RequestPage3.vue';
import RequestPage4 from './views/RequestPage4.vue';
import RequestPage5 from './views/RequestPage5.vue';
import SignInPage from './views/SignInPage.vue';
import AdminLanding from './views/AdminLanding.vue';


createApp(App)
    .use(ElementPlus)
    .use(router)
    .use(store)
    //register components
    .component('RequestPage1', RequestPage1)
    .component('RequestPage2', RequestPage1)
    .component('RequestPage3', RequestPage1)
    .component('RequestPage4', RequestPage1)
    .component('RequestPage5', RequestPage1)
    .component('SignInPage', SignInPage)
    .component('AdminLanding', AdminLanding)
    .mount('#app')

//createApp(App).mount('#app')
