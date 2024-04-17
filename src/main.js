import { createApp } from 'vue'
import ElementPlus from 'element-plus';
import './style.css'
import 'element-plus/lib/theme-chalk/index.css';
import App from './App.vue'

createApp(App)
    .use(ElementPlus)
    //register components
    .component('RequestPage1', RequestPage1)
    .component('RequestPage2', RequestPage1)
    .component('RequestPage3', RequestPage1)
    .component('RequestPage4', RequestPage1)
    .component('RequestPage5', RequestPage1)
    .mount('#app')
