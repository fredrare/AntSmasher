package com.fredrare.pm.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.fredrare.pm.AntSmasher;

/**
 * Created by Franco on 12/07/17.
 */

public abstract class Bug {
    protected boolean alive;
    protected Texture image;
    protected Vector3 position;
    protected int xspeed;
    protected Animation animation;

    public Bug(int x) {
        this.position=new Vector3(x, AntSmasher.HEIGHT,0);
    }

    public abstract void update(float dt);
    public abstract void die();
    public void dispose(){
        image.dispose();
    }
    public Vector3 getPosition(){
        return position;
    }
    public TextureRegion getRegion(){
        return animation.getFrame();
    }
    public Texture getImage(){
        return image;
    }
    public boolean getLife(){
        return alive;
    }
}
