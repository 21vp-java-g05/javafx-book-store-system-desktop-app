package main.frontend.controller;

public class categoryData {
    private final Integer categoryId;
    private final String name;
    private final String status;

    public categoryData(Integer categoryId, String name, String status){
        this.categoryId = categoryId;
        this.name = name;
        this.status = status;
    }

    public Integer getCategoryId(){
        return categoryId;
    }

    public String getName(){
        return name;
    }

    public String getStatus(){
        return status;
    }
}
