package main.frontend.controller;

public class customerData {

    public static Integer customerID;
    public static String fullName;
    public static String email; // Corrected email spelling
    public static String gender;
    public static String status;

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