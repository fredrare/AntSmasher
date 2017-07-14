package com.fredrare.pm.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fredrare.pm.AntSmasher;

/**
 * Created by Franco on 12/07/17.
 */

public class Scorpion extends Bug{

    public Scorpion(int x) {
        super(x);
        image=new Texture("Scorpion.png");
        animation=new Animation(new TextureRegion(image),4,0.5f);
        xspeed=((int)(Math.random()*2))==1?600:0;
        alive=true;
    }

    @Override
    public void update(float dt) {
        if(alive){
            animation.update(dt);
            if(position.x<=0)
                xspeed=600;
            else if(position.x>=AntSmasher.WIDTH-100*AntSmasher.WIDTH/AntSmasher.VIRTUALWIDTH)
                xspeed=-600;
            position.add(xspeed*dt, AntSmasher.YSPEED*1.5f*dt,0);
        }
    }

    @Override
    public void die() {
        alive=false;
        dispose();
        image=new Texture("DeadScorpion.png");
    }
}
