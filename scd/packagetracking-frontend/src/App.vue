<template>
  <v-app>
    <v-main>
      <packages-view />
      <view-my-packages />
      <assign-package />

      <!-- Butoane pentru deschiderea dialogurilor -->
      <v-btn style="align-content: center" @click="openPackageDialog">
        Create new package
      </v-btn>
      <v-btn style="align-content: center" @click="openCourierDialog">
        Create new courier
      </v-btn>

      <!-- Dialoguri -->
      <add-package
          :currentUser="username"
          ref="addPackageDialog"
      ></add-package>
      <add-courier
          :currentUser="username"
          ref="addCourierDialog"
      ></add-courier>
    </v-main>
  </v-app>
</template>

<script>
import AddCourier from "@/components/AddCourier.vue";
import AddPackage from "@/components/AddPackage.vue";
import PackagesView from "@/components/PackagesView.vue";
import ViewMyPackages from "@/components/ViewMyPackages.vue";
import AssignPackage from "@/components/AssignPackage.vue";

export default {
  name: 'App',

  components: {
    PackagesView,
    AddPackage,
    AddCourier,
    ViewMyPackages,
    AssignPackage
  },

  data: () => ({
    username: "Utilizator nou"
  }),

  methods: {
    openPackageDialog() {
      this.$refs.addPackageDialog.showDialog = true;
    },
    openCourierDialog() {
      this.$refs.addCourierDialog.showDialog = true;
    }
  }
};
</script>
