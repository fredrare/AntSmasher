package com.fredrare.pm.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fredrare.pm.AntSmasher;

/**
 * Created by Franco on 12/07/17.
 */

public abstract class State {
    protected StateManager manager;

    public State(StateManager manager) {
        this.manager = manager;
    }


    public abstract void handleInput();

    public abstract void dispose();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

}
