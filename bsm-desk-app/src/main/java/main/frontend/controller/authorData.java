package main.frontend.controller;

public class authorData {
    private final Integer authorId;
    private final String name;
    private final String status;

    public authorData(Integer authorId, String name, String status){
        this.authorId = authorId;
        this.name = name;
        this.status = status;
    }

    public Integer getAuthorId(){
        return authorId;
    }

    public String getName(){
        return name;
    }

    public String getStatus(){
        return status;
    }
}
