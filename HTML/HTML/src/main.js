import { createApp } from 'vue'
import '../dist/style.css'
import App from './App.vue'
import router from './router';

createApp(App)
    .use(ElementPlus)
    .use(router)
    //register components
    .component('RequestPage1', RequestPage1)
    .component('RequestPage2', RequestPage1)
    .component('RequestPage3', RequestPage1)
    .component('RequestPage4', RequestPage1)
    .component('RequestPage5', RequestPage1)
    .mount('#app')

createApp(App).mount('#app')
