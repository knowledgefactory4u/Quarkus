<template>
  <div>
    <h3>User</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <div v-if="errors.length">
          <div
            class="alert alert-danger"
            v-bind:key="index"
            v-for="(error, index) in errors"
          >
            {{ error }}
          </div>
        </div>
        <fieldset class="form-group">
          <label>First Name</label>
          <input type="text" class="form-control" v-model="firstName" />
        </fieldset>
        <fieldset class="form-group">
          <label>Last Name</label>
          <input type="text" class="form-control" v-model="lastName" />
        </fieldset>
        <fieldset class="form-group">
          <label>Email Id</label>
          <input type="text" class="form-control" v-model="emailId" />
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>
  </div>
</template>
<script>
import UserDataService from "../service/UserDataService";

export default {
  name: "User",
  data() {
    return {
      firstName: "",
      lastName: "",
      emailId: "",
      errors: [],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
    refreshUserDetails() {
      UserDataService.retrieveUser(this.id).then((res) => {
        this.firstName = res.data.firstName;
        this.lastName = res.data.lastName;
        this.emailId = res.data.emailId;
      });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.firstName) {
        this.errors.push("Enter valid values");
      } else if (this.firstName.length < 5) {
        this.errors.push("Enter atleast 5 characters in First Name");
      }
      if (!this.lastName) {
        this.errors.push("Enter valid values");
      } else if (this.lastName.length < 5) {
        this.errors.push("Enter atleast 5 characters in Last Name");
      }
      if (this.errors.length === 0) {
        if (this.id == -1) {
          UserDataService.createUser({
            firstName: this.firstName,
            lastName: this.lastName,
            emailId: this.emailId,
          }).then(() => {
            this.$router.push("/users");
          });
        } else {
          UserDataService.updateUser(this.id, {
            id: this.id,
            firstName: this.firstName,
            lastName: this.lastName,
            emailId: this.emailId,
          }).then(() => {
            this.$router.push("/users");
          });
        }
      }
    },
  },
  created() {
    this.refreshUserDetails();
  },
};
</script>
