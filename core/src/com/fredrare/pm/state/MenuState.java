package com.fredrare.pm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fredrare.pm.AntSmasher;

/**
 * Created by Franco on 12/07/17.
 */

public class MenuState extends State {
    private Texture mBackground,mTitle,mPlayButton;
    public MenuState(StateManager manager) {
        super(manager);
        mBackground=new Texture("bg_game5.jpg");
        mTitle=new Texture("title.png");
        mPlayButton=new Texture("start1.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            int
                    xTouch=Gdx.input.getX(),
                    yTouch=Gdx.input.getY();
            int
                    xMin=(AntSmasher.WIDTH-mPlayButton.getWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH)/2,
                    xMax=xMin+mPlayButton.getWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH,
                    yMin=AntSmasher.HEIGHT-50*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT-mPlayButton.getHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT,
                    yMax=yMin+mPlayButton.getHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT;
            if(xTouch<=xMax && xTouch>=xMin &&
                    yTouch<=yMax && yTouch>=yMin){
                manager.set(new PlayState(manager));
            }
        }
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mTitle.dispose();
        mPlayButton.dispose();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(mBackground,0,0,AntSmasher.WIDTH,AntSmasher.HEIGHT);
        float ratio=(float)AntSmasher.WIDTH/(float)mTitle.getWidth();
        sb.draw(mTitle,
                (AntSmasher.WIDTH-mTitle.getWidth()*ratio)/2,
                AntSmasher.HEIGHT-mTitle.getHeight()*ratio-50*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT,
                mTitle.getWidth()*ratio,
                mTitle.getHeight()*ratio);
        sb.draw(mPlayButton,
                (AntSmasher.WIDTH-mPlayButton.getWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH)/2,
                50*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT,
                mPlayButton.getWidth()*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH,
                mPlayButton.getHeight()*AntSmasher.HEIGHT/AntSmasher.VIRTUALHEIGHT);
        sb.end();
    }
}
