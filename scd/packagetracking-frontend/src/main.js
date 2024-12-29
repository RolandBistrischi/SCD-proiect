/*import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'

loadFonts()

createApp(App)
  .use(vuetify)
  .mount('#app')*/



import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './router/router'  // Importă router-ul

loadFonts()

createApp(App)
  .use(vuetify)  // Adaugă Vuetify la aplicație
  .use(router)   // Adaugă router-ul
  .mount('#app')  // Montează aplicația

