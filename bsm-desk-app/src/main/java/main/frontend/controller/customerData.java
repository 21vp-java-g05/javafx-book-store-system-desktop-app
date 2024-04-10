package main.frontend.controller;

public class customerData {

    private final Integer customerID;
    private final String fullName;
    private final String email; // Corrected email spelling
    private final String gender;
    private final String status;

    public customerData(Integer customerID, String fullName, String email, String gender, String status) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }
}