package com.adarsh.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Character {
    private static final int GRAVITY = 15;
    private static final int MOVEMENT = 150;
    private Vector3 position;
    private static Vector3 velocity;
    private Texture character;
    private Rectangle bounds;

    public Character(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        character = new Texture("character.png");
        bounds = new Rectangle(x, y, character.getWidth(), character.getHeight());
    }

    public void update(float dt){

        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1/dt);
        if(position.y < 0)
            position.y = 0;
        if(position.y > 365)
            position.y = 365;
        bounds.setPosition(position.x, position.y);
    }

    public Texture getTexture() {
        return character;
    }

    public Vector3 getPosition() {
        return position;
    }

    public static void jump(){
        velocity.y = -400;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        character.dispose();
    }
}
