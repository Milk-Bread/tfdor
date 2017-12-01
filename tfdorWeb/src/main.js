// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router/router.js';
import MintUI from 'mint-ui';
import 'mint-ui/lib/style.css';
import './assets/css/my-mint.scss';
// import MuseUI from 'muse-ui';
// import 'muse-ui/dist/muse-ui.css';
// Vue.use(MuseUI);

Vue.use(MintUI);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
});
