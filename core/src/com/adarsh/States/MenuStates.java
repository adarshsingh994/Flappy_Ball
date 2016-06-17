package com.adarsh.States;

import com.adarsh.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Adarsh on 6/16/2016.
 */
public class MenuStates extends States {

    private Texture playButton;
    public MenuStates(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture("playbutton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playButton, 600, 300);
        sb.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
