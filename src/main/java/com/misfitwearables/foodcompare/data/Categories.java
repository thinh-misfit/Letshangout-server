package com.misfitwearables.foodcompare.data;

import java.util.Vector;

public class Categories {

    private Vector<Category> categories;
    
    public Categories() {
        
    }
    
    public Categories(Vector<Category> cats) {
        categories = new Vector<Category>(cats);
    }

    public Vector<Category> getCategories() {
        return categories;
    }

    public void setCategories(Vector<Category> categories) {
        this.categories = categories;
    }
    
}
