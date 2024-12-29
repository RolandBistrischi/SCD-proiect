<template>
  <v-container>
    <h2 class="text-h5">Assign Packages</h2>

    <!-- Buton pentru a deschide meniul de assign -->
    <v-btn color="primary" @click="showAssignDialog = true">Assign Package</v-btn>

    <!-- Dialog pentru a selecta curierul și pachetul -->
    <v-dialog v-model="showAssignDialog" max-width="500px">
      <v-card>
        <v-card-title class="headline">Assign Package to Courier</v-card-title>

        <v-card-text>

          <v-select
            v-model="selectedCourier"
            :items="couriersList"
            item-title="name"
            item-value="id"
            label="Select Courier"
            return-object
            outlined
            :rules="[v => !!v || 'Courier is required']"
          />


          <v-select
            v-model="selectedPackage"
            :items="unassignedPackages"
            item-title="deliveryAddress"
            item-value="id"
            label="Select Package"
            return-object
            outlined
            :rules="[v => !!v || 'Package is required']"
          />
        </v-card-text>

        <v-card-actions>
          <!-- Butonul pentru asignare care va verifica validitatea formularului -->
          <v-btn color="green" @click="assignPackage" :disabled="!isValidForm">Assign</v-btn>
          <v-btn color="red" @click="showAssignDialog = false">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- Tabel pentru pachete neassignate -->
    <v-data-table :items="unassignedPackages" item-key="id">
      <template v-slot:[`item.actions`]="{ item }">
        <v-btn color="primary" @click="assignToCourier(item.id)">Assign</v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "AssignPackage",
  data() {
    return {
      unassignedPackages: [],
      couriersList: [],
      selectedCourier: null, // Curierul selectat din dropdown
      selectedPackage: null, // Pachetul selectat din dropdown
      showAssignDialog: false, // Control pentru dialogul de assign
    };
  },
  computed: {
    // Verifică dacă formularul este complet
    isValidForm() {
      return this.selectedCourier && this.selectedPackage;
    },
  },
  methods: {
    async fetchData(endpoint, listName) {
      try {
        const response = await axios.get(endpoint);
        this[listName] = response.data;
      } catch (error) {
        console.error(`Error fetching data from ${endpoint}:`, error);
        alert("Something went wrong. Please try again later.");
      }
    },
    async assignPackage() {
      if (this.selectedCourier && this.selectedPackage) {
        await this.assignToPackage(this.selectedPackage.id, this.selectedCourier.id);
        this.showAssignDialog = false;
      }
    },
    async assignToCourier(packageId) {
      if (this.selectedCourier) {
        await this.assignToPackage(packageId, this.selectedCourier.id);
      } else {
        alert("Please select a courier before assigning.");
      }
    },
    async assignToPackage(packageId, courierId) {
      try {
        await axios.put(
          `http://localhost:8083/package/assignPackage/${packageId}/${courierId}`
        );
        this.fetchData("http://localhost:8083/package/unassigned", "unassignedPackages");
      } catch (error) {
        console.error("Error assigning package:", error);
        alert("Error assigning package. Please try again.");
      }
    },
  },
  mounted() {
    this.fetchData("http://localhost:8083/package/unassigned", "unassignedPackages");
    this.fetchData("http://localhost:8083/courier", "couriersList");
  },
};
</script>

<style scoped>
.mt-5 {
  margin-top: 20px;
}
</style>
