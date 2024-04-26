import { createApp } from 'vue'
import '../dist/style.css'
import App from './App.vue'
import router from './router'


createApp(App)
    .use(ElementPlus)
    .use(router)
    //register components
    .component('RequestPage1', RequestPage1)
    .component('RequestPage2', RequestPage2)
    .component('RequestPage3', RequestPage3)
    .component('RequestPage4', RequestPage4)
    .component('RequestPage5', RequestPage5)
    .mount('#app')

