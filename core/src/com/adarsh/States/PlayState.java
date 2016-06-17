package com.adarsh.States;

import com.adarsh.MyGame;
import com.adarsh.sprites.Character;
import com.adarsh.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Adarsh on 6/16/2016.
 */
public class PlayState extends States {

    private static final int TUBE_SPACING = 300;
    private static final int TUBE_COUNT = 4;

    private Character character;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        character = new Character(10, 50);
        cam.setToOrtho(false, MyGame.WIDTH/2, MyGame.HEIGHT/2);
        tubes = new Array<Tube>();
        for(int i = 0; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Character.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        character.update(dt);
        cam.position.x = character.getPosition().x + 80;

        for(int i = 0; i<tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(character.getPosition().y <= 0 || character.getPosition().y >= 365)
                gsm.set(new PlayState(gsm));

//            if(tube.collides(character.getBounds()))
//                gsm.set(new PlayState(gsm));

        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(character.getTexture(), character.getPosition().x, character.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        character.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
    }
}
