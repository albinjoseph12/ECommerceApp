package com.eshopthis.finds.db;

public class CategoryItem {
    private String name;
    private int imageResourceId; // Resource ID for the category image

    public CategoryItem(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // Setters, if needed
    public void setName(String name) {
        this.name = name;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
