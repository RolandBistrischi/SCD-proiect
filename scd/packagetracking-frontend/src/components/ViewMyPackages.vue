<template>
  <v-container>
    <h2 class="text-h5">My Packages</h2>
    <v-btn text="Refresh" @click="fetchMyPackages" :disabled="!selectedCourier">Refresh</v-btn>

    <!-- Select pentru curieri -->
    <v-select
      v-model="selectedCourier"
      :items="couriersList"
      item-title="name"
      item-value="id"
      label="Select Courier"
      outlined
      @change="fetchMyPackages"
    ></v-select>

   <v-container class="mt-5">
     <v-row>
       <v-col
         v-for="item in myPackages"
         :key="item.id"
         cols="12" md="4"
       >
         <v-card class="pa-4">
           <v-card-title>{{ item.deliveryAddress }}</v-card-title>
           <v-card-subtitle>Pay on Delivery: {{ item.pay_on_delivery }}</v-card-subtitle>
           <v-card-subtitle>Status: {{ item.status }}</v-card-subtitle>
           <v-card-actions>
             <v-btn
               v-if="item.status !== 'DELIVERED'"
               color="primary"
               @click="markDelivered(item.id)"
               style="border: 2px solid red;"
             >
               Mark as Delivered
             </v-btn>
           </v-card-actions>
         </v-card>
       </v-col>
     </v-row>
   </v-container>







  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "ViewMyPackages",
  props: ["currentUser"],
  data() {
    return {
      myPackages: [],
      couriersList: [],
      selectedCourier: null, // Curierul selectat
    };
  },
  methods: {
    async fetchCouriers() {
      try {
        const response = await axios.get("http://localhost:8083/courier");
        this.couriersList = response.data;
      } catch (error) {
        console.error("Error fetching couriers:", error);
      }
    },
    async fetchMyPackages() {
      if (!this.selectedCourier) {
        this.myPackages = [];
        return;
      }
      try {
        const response = await axios.get(
          `http://localhost:8083/package/${this.selectedCourier}`
        );
        this.myPackages = response.data.map(pkg => ({
          id: pkg.id,
          deliveryAddress: pkg.deliveryAddress,
          pay_on_delivery: pkg.pay_on_delivery,
          status: pkg.status,
        }));
      } catch (error) {
        console.error("Error fetching my packages:", error);
      }
    },
    async markDelivered(packageId) {
      try {
        await axios.put(`http://localhost:8083/package/deliverPackage/${packageId}`);
        this.fetchMyPackages(); // Refresh after marking as delivered
      } catch (error) {
        console.error("Error marking package as delivered:", error);
      }
    },
  },
  mounted() {
    this.fetchCouriers(); // Încarcă lista de curieri la montare
  },
};
</script>

<style scoped>
.mt-5 {
  margin-top: 20px;
}


</style>
