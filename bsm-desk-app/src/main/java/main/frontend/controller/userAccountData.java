package main.frontend.controller;

public class userAccountData {
    private Integer accountID;
    private String username;
    private String status;
    private String role;
    private String password;
    private String mail;

    public userAccountData(Integer accountID, String username, String status, String role, String password, String email){
        this.accountID = accountID;
        this.username = username;
        this.status = status;
        this.role = role;
        this.password = password;
        this.mail = email;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public String getRole(){
        return role;
    }
    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getmail() {
        return mail;
    }
}
