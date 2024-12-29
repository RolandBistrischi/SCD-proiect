/*import { createRouter, createWebHistory } from 'vue-router';
import AddCourier from '../components/AddCourier.vue';
import AddPackage from '../components/AddPackage.vue';
import PackagesView from '../components/PackagesView.vue';
import ViewMyPackages from '../components/ViewMyPackages.vue';
import AssignPackage from '../components/AssignPackage.vue';

const routes = [
  { path: "/add-courier", component: AddCourier },
  { path: "/add-package", component: AddPackage },
  { path: "/packages", component: PackagesView },
  { path: "/my-packages", component: ViewMyPackages },
  { path: "/assign-packages", component: AssignPackage },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
*/

import { createRouter, createWebHistory } from 'vue-router';
import PackagesView from '@/components/PackagesView.vue';
import AddPackage from '@/components/AddPackage.vue';
import AddCourier from '@/components/AddCourier.vue';
import ViewMyPackages from '@/components/ViewMyPackages.vue';
import AssignPackage from '@/components/AssignPackage.vue';

const routes = [
  {
    path: '/packages',
    name: 'Packages',
    component: PackagesView
  },
  {
    path: '/add-package',
    name: 'AddPackage',
    component: AddPackage
  },
  {
    path: '/add-courier',
    name: 'AddCourier',
    component: AddCourier
  },
  {
    path: '/view-my-packages',
    name: 'ViewMyPackages',
    component: ViewMyPackages
  },
  {
    path: '/assign-packages',
    name: 'AssignPackage',
    component: AssignPackage
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
