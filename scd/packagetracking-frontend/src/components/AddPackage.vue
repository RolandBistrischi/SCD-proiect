<template>
  <v-dialog v-model="showDialog" max-width="400px" class="bg-transparent">
    <div class="dialogClass">
      <h3>Welcome, {{ currentUser }}! Let's create a new package!</h3>

      <!-- Delivery Address -->
      <v-textarea
        label="Delivery address"
        v-model="currentPackage.deliveryAddress"
        :error-messages="errors.deliveryAddress"
        outlined
      ></v-textarea>

      <!-- Pay on Delivery -->
      <v-radio-group
        v-model="currentPackage.pay_on_delivery"
        :error-messages="errors.pay_on_delivery"
        label="Pay on delivery?"
        outlined
      >
        <v-radio label="Yes" :value="true"></v-radio>
        <v-radio label="No" :value="false"></v-radio>
      </v-radio-group>

      <!-- Action Buttons -->
      <v-btn color="green" @click="createPackage" :disabled="isFormInvalid">Create</v-btn>
      <v-btn color="red" @click="closeDialog">Cancel</v-btn>
    </div>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  name: "AddPackage",
  props: {
    currentUser: String,
  },
  data() {
    return {
      showDialog: false,
      currentPackage: {
        deliveryAddress: "",
        pay_on_delivery: null, // Explicit null for initial state
      },
      errors: {
        deliveryAddress: null,
        pay_on_delivery: null,
      },
    };
  },
  computed: {
    // Computed property to determine if the form is valid
    isFormInvalid() {
      return !this.currentPackage.deliveryAddress || this.currentPackage.pay_on_delivery === null;
    },
  },
  methods: {
    // Validare si creare pachet
    async createPackage() {
      // Resetare erori
      this.errors = {
        deliveryAddress: null,
        pay_on_delivery: null,
      };

      // Validare
      let hasError = false;
      if (!this.currentPackage.deliveryAddress) {
        this.errors.deliveryAddress = "Delivery address is required.";
        hasError = true;
      }
      if (this.currentPackage.pay_on_delivery === null) {
        this.errors.pay_on_delivery = "Please select an option for payment on delivery.";
        hasError = true;
      }

      // Dacă există erori de validare, opriți procesul
      if (hasError) {
        console.error("Validation failed. Errors:", this.errors);
        return;
      }

      // Creare pachet
      try {
        const response = await axios.post(
          "http://localhost:8083/package",
          this.currentPackage
        );
        console.log("Package created successfully:", response.data);
        this.showDialog = false;

        // Resetarea formularului
        this.resetForm();
      } catch (error) {
        console.error("Error creating package:", error);
      }
    },
    // Închidere dialog și resetare formular
    closeDialog() {
      this.showDialog = false;
      this.resetForm();
    },
    // Resetarea formularului
    resetForm() {
      this.currentPackage = {
        deliveryAddress: "",
        pay_on_delivery: null,
      };
      this.errors = {
        deliveryAddress: null,
        pay_on_delivery: null,
      };
    },
  },
};
</script>

<style scoped>
.dialogClass {
  padding: 20px;
  background-color: white;
}

.v-btn {
  margin-top: 20px;
}
</style>
