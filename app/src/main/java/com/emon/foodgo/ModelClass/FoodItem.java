package com.emon.foodgo.ModelClass;

public class FoodItem {
    private String name, restaurant;
    private double rating;
    private int imageResId;
    private boolean isFavorite; // ✅ নতুন ফিল্ড

    public FoodItem(String name, String restaurant, double rating, int imageResId) {
        this.name = name;
        this.restaurant = restaurant;
        this.rating = rating;
        this.imageResId = imageResId;
        this.isFavorite = false; // ✅ 초기화
    }

    public String getName() { return name; }
    public String getRestaurant() { return restaurant; }
    public double getRating() { return rating; }
    public int getImageResId() { return imageResId; }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

