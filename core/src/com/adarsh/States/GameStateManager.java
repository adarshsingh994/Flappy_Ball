package com.adarsh.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Adarsh on 6/16/2016.
 */
public class GameStateManager {
    private Stack<States> states;

    public GameStateManager(){
        states = new Stack<States>();
    }

    public void push(States state){
        states.push(state);
    }
    public void pop(){
        states.pop().dispose();
    }
    public void set(States state){
        states.pop().dispose();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
