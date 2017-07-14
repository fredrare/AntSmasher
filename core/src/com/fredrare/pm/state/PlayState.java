package com.fredrare.pm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fredrare.pm.AntSmasher;
import com.fredrare.pm.sprite.BlackAnt;
import com.fredrare.pm.sprite.BrownAnt;
import com.fredrare.pm.sprite.Bug;
import com.fredrare.pm.sprite.Scorpion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franco on 12/07/17.
 */

public class PlayState extends State implements Input.TextInputListener {
    private static int FREQUENCY=100;
    boolean alive;
    Texture mBackground;
    List<Bug> bugs;
    int x,type,count,score;
    public PlayState(StateManager manager) {
        super(manager);
        mBackground=new Texture("bg_game1.jpg");
        bugs=new ArrayList<Bug>();
        alive=true;
        count=FREQUENCY;
        score=0;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if(alive) {
                for (Bug ref:bugs) {
                    if(ref.getLife()){
                        TextureRegion image = ref.getRegion();
                        int
                                xTouch = Gdx.input.getX(),
                                yTouch = Gdx.input.getY();
                        int
                                xMin = (int) ref.getPosition().x,
                                xMax = xMin + image.getRegionWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH,
                                yMin = AntSmasher.HEIGHT - ((int) ref.getPosition().y) - image.getRegionHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT,
                                yMax = yMin + image.getRegionHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT;
                        if (xTouch <= xMax && xTouch >= xMin &&
                                yTouch <= yMax && yTouch >= yMin) {
                            FREQUENCY*=0.9;
                            AntSmasher.YSPEED*=1.05;
                            ref.die();
                            if(ref instanceof BlackAnt)
                                score++;
                            else if(ref instanceof BrownAnt)
                                score+=2;
                            else
                                score+=3;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        for (Bug ref2:bugs)
            ref2.dispose();
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(alive) {
            if (count++ >= FREQUENCY) {
                x = (int) (Math.random() * (AntSmasher.WIDTH-100*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH) + 1);
                type = (int) (Math.random() * 3);
                if (type == 0) {
                    BlackAnt bug = new BlackAnt(x);
                    bugs.add(bug);
                } else if (type == 1) {
                    BrownAnt bug = new BrownAnt(x);
                    bugs.add(bug);
                } else {
                    Scorpion bug = new Scorpion(x);
                    bugs.add(bug);
                }
                count = 0;
            }
            for (Bug ref : bugs){
                ref.update(dt);
                if(ref.getPosition().y<=0) {
                    alive = false;
                    Gdx.input.getTextInput(this, "Juego terminado", "", "Introduce tu nombre");
                    AntSmasher.YSPEED=-400;
                    FREQUENCY=100;
                    manager.set(new MenuState(manager));
                    break;
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(mBackground,0,0,AntSmasher.WIDTH,AntSmasher.HEIGHT);
        for(Bug ref:bugs) {
            if(ref.getLife())
                sb.draw(ref.getRegion(),
                        ref.getPosition().x,
                        ref.getPosition().y,
                        ref.getRegion().getRegionWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH,
                        ref.getRegion().getRegionHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT);
            else
                sb.draw(ref.getImage(),
                        ref.getPosition().x,
                        ref.getPosition().y,
                        ref.getImage().getWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH,
                        ref.getImage().getHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT);
        }
        sb.end();
    }

    @Override
    public void input(String text) {
        AntSmasher.firebase.addToFirebase(text,score);
        score=0;
    }

    @Override
    public void canceled() {
        AntSmasher.firebase.addToFirebase("Anónimo",score);
        score=0;
    }
}
