package main.frontend.controller;

public class userAccountData {
    private Integer accountID;
    private String username;
    private boolean status;
    private int role;
    private String password;
    private String mail;

    public userAccountData(Integer accountID, String username, boolean status, int role, String password, String email){
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

    public int getRole(){
        return role;
    }
    public boolean getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getmail() {
        return mail;
    }
}
