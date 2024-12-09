package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship {
    private ImageView imageView;
    private double movementSpeed;
    private int damage;
    public int health = 100;

    // Constructor to initialize the ship
    public Ship(ImageView imageView, double movementSpeed, int damage) {
        this.imageView = imageView;
        this.movementSpeed = movementSpeed;
        this.damage = damage;
    }

    // Getter and Setter for movementSpeed
    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    // Getter and Setter for damage
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Getter for ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Set position of the ship
    public void setPosition(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }

    // Move the ship by a specified amount
    public void move(double deltaX, double deltaY) {
        imageView.setX(imageView.getX() + deltaX);
        imageView.setY(imageView.getY() + deltaY);
    }
    
    public double getX() {
    	return this.imageView.getX();
    }
    
    public double getY() {
    	return this.imageView.getY();
    }
    
    public Image getImage(){
    	return this.imageView.getImage();
    }
    
    
    
    
}

