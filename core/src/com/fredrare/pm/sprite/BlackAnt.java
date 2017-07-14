package com.fredrare.pm.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fredrare.pm.AntSmasher;

/**
 * Created by Franco on 12/07/17.
 */

public class BlackAnt extends Bug{

    public BlackAnt(int x) {
        super(x);
        image=new Texture("BlackAnt.png");
        animation=new Animation(new TextureRegion(image),4,0.5f);
        xspeed=0;
        alive=true;
    }

    @Override
    public void update(float dt) {
        if(alive){
            animation.update(dt);
            position.add(xspeed, AntSmasher.YSPEED*dt,0);
        }
    }

    @Override
    public void die() {
        alive=false;
        dispose();
        image=new Texture("DeadBlackAnt.png");
    }
}
