package com.misfitwearables.foodcompare.data;

public class Category {

    private String name;
    private String photo;

    public Category(String name, String photo) {
        this.name = name;
        this.photo= photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
