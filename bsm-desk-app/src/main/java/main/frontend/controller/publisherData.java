package main.frontend.controller;

public class publisherData {
    private Integer publisherId;
    private String name;
    private String image;

    public publisherData(Integer publisherId, String name, String image){
        this.publisherId = publisherId;
        this.name = name;
        this.image = image;
    }

    public Integer getPublisherId(){
        return publisherId;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }
}