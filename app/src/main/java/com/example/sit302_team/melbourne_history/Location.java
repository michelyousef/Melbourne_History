package com.example.sit302_team.melbourne_history;

/**
 * Created by michale on 1/26/2017.
 */

// Create a class
public class Location {

    // Initialize Variable
    String location_name;
    String location_descriptions;
    Integer location_icons;

    // Create constructor
    public Location(String name, String description, Integer icon) {
        super();
        this.location_name = name;
        this.location_descriptions = description;
        this.location_icons = icon;
    }

    // Get name function
    public String getName() {
        return location_name;
    }

    // Set name function
    public void setName(String name) {
        this.location_name = name;
    }

    // Get description function
    public String getDescription(){
        return location_descriptions;
    }

    // Set description function
    public void setDescription(String description){
        this.location_descriptions = description;
    }

    // Get icon function
    public Integer getIcon() {
        return location_icons;
    }

    // Set icon function
    public void setIcon(Integer icon) {
        this.location_icons = icon;
    }
}
