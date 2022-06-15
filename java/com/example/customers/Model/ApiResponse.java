package com.example.customers.Model;

import java.util.ArrayList;

public class ApiResponse {

    public ArrayList<ApiResponse.customers> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<ApiResponse.customers> customers) {
        this.customers = customers;
    }

    ArrayList<customers>customers;


    public class customers{

        String name;
        String status;
        String email;
        String id;
        String profileImage;


        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
