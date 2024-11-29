<template>
  <v-app>
    <v-main>
      <packages-view/>

      <v-btn style="align-content: center" @click="openDialog">
        Create new package
      </v-btn>

      <add-package
          :currentUser="username"
          ref="addPostDialog"
      ></add-package>
    </v-main>
  </v-app>
</template>

<script>
import PackagesView from './components/PackagesView.vue'
import AddPackage from "@/components/AddPackage.vue";

export default {
  name: 'App',

  components: {
    PackagesView,
    AddPackage
  },

  data: () => ({
    username: "Nume utilizator"
  }),
  mounted() {
    this.$refs.addPostDialog.showDialog = false
  },
  methods: {
    openDialog() {
      this.$refs.addPostDialog.showDialog = true
    }
  }
}
</script>