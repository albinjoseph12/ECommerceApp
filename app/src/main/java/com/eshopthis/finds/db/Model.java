package com.eshopthis.finds.db;

public class Model {
    private String id, title, price, description;
    private String imageUrl; // for image URLs
    private int imageResId; // for local images (resource IDs)

    // Constructor for URLs
    public Model(String id, String title, String price, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Constructor for resource IDs
    public Model(String id, String title, String price, String description, int imageResId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
}
