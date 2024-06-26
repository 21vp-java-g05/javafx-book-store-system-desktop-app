package main.frontend.controller;

public class publisherData {
    private final Integer publisherId;
    private final String name;
    private final String status;

    public publisherData(Integer publisherId, String name, String status){
        this.publisherId = publisherId;
        this.name = name;
        this.status = status;
    }

    public Integer getPublisherId(){
        return publisherId;
    }

    public String getName(){
        return name;
    }

    public String getStatus(){
        return status;
    }
}