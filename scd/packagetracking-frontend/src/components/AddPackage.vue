<template>
  <v-dialog v-model="showDialog" max-width="400px" class="bg-transparent">
    <div class="dialogClass">
      Welcome, {{currentUser}}! Let's create a new package!
      <v-textarea
          label="delivery address"
          v-model="currentPackage.deliveryAddress"
      ></v-textarea>
<!--      TODO: add package details here-->

      <v-btn color="green" @click="createPackage">Create</v-btn>
      <v-btn color="red" @click="closeDialog">Cancel</v-btn>
    </div>
  </v-dialog>
</template>

<script>
import axios from 'axios';

export default {

  name: 'AddPackage',
  props: {
    currentUser: String
  },
  data() {
    return {
      showDialog: Boolean,
      currentPackage: {
        deliveryAddress: ""
      }
    }
  },
  methods: {
    async createPackage() {
      try {
        const response = await axios.post('http://localhost:8083/package', this.currentPackage)
        console.log("package created successfully:", response.data);
        this.showDialog = false
      } catch (error) {
        console.error("Error creating package:", error);
      }
    },
    closeDialog() {
      this.showDialog = false;
      this.package = { deliveryAddress: "" };
    },
  }
};
</script>

<style scoped>
.dialogClass {
  padding: 20px;
  background-color: white;
}
</style>