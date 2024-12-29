<template>
  <v-dialog v-model="showDialog" max-width="400px" class="bg-transparent">
    <div class="dialogClass">
      Welcome, {{ currentUser }}! Let's create a new courier!

      <!-- Nume -->
      <v-textarea
        label="Name"
        v-model="addCourier.name"
        :error-messages="errors.name"
      ></v-textarea>

      <!-- Email -->
      <v-textarea
        label="Email"
        v-model="addCourier.email"
        :error-messages="errors.email"
      ></v-textarea>

      <!-- Manager -->
     <v-autocomplete
       :items="managersList"
       label="Assign a Manager"
       v-model="addCourier.manager"
       :error-messages="errors.manager"
       item-title="name"
       item-value="id"
       return-object
     ></v-autocomplete>


      <!-- Buttons -->
      <v-btn color="green" @click="createCourier">Create</v-btn>
      <v-btn color="red" @click="closeDialog">Cancel</v-btn>
    </div>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  name: "AddCourier",
  props: {
    currentUser: String,
  },
  data() {
    return {
      showDialog: false,
      addCourier: {
        name: "",
        email: "",
        manager: null, // Managerul selectat (ID)
      },
      managers: {}, // Obiectul pentru manageri
      errors: {
        name: null,
        email: null,
        manager: null,
      },
    };
  },
 computed: {
   // Convertim managerii într-o listă pentru a fi folosită în v-autocomplete
   managersList() {
     return Object.values(this.managers); // Returnează un array cu managerii
   }
 },
 async mounted() {
   try {
     // Fetch lista de manageri
     const response = await axios.get("http://localhost:8083/courier/managers");

     // Salvăm managerii într-un obiect, folosind ID-ul ca și cheie
     this.managers = response.data.reduce((acc, manager) => {
      acc[manager.id] = {
         id: manager.id,
         name: manager.name
       };
       return acc;
     }, {});
   } catch (error) {
     console.error("Error fetching managers:", error);
   }
 }
,
  methods: {
    async createCourier() {
      // Resetare erori
      this.errors = { name: null, email: null, manager: null };

      // Validare simplificată
      if (this.validateForm()) {
        try {
          const response = await axios.post("http://localhost:8083/courier", this.addCourier);
          console.log("Courier created successfully:", response.data);
          this.showDialog = false;

          // Resetare formular
          this.addCourier = { name: "", email: "", manager: null };
        } catch (error) {
          console.error("Error creating courier:", error);
        }
      } else {
        console.error("Validation failed. Errors:", this.errors);
      }
    },
    validateForm() {
      let isValid = true;

      // Validare pentru fiecare câmp
      if (!this.addCourier.name) {
        this.errors.name = "Name is required.";
        isValid = false;
      }
      if (!this.addCourier.email) {
        this.errors.email = "Email is required.";
        isValid = false;
      } else if (!this.isValidEmail(this.addCourier.email)) {
        this.errors.email = "Invalid email address.";
        isValid = false;
      }
      if (!this.addCourier.manager) {
        this.errors.manager = "Please select a manager.";
        isValid = false;
      }

      return isValid;
    },
    closeDialog() {
      this.showDialog = false;
      this.addCourier = { name: "", email: "", manager: null };
      this.errors = { name: null, email: null, manager: null };
    },
    isValidEmail(email) {
      const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return re.test(email);
    },
  },
};
</script>

<style scoped>
.dialogClass {
  padding: 20px;
  background-color: white;
}
</style>
