package com.spaceshooter.game.com.spaceshooter.game.collision;

public class collision {
    private float x, y;
    float width, height;

    public collision(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y){
        this.x = x;
        this.y = y;

    }

    public boolean collideReact (collision rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }
}
