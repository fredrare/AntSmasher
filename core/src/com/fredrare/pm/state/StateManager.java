package com.fredrare.pm.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Franco on 12/07/17.
 */

public class StateManager {
    private Stack<State> mStates;

    public StateManager() {
        this.mStates = new Stack<State>();
    }
    public void push(State state){
        mStates.push(state);
    }
    public void pop(){
        mStates.pop().dispose();
    }
    public void set(State state){
        mStates.pop();
        mStates.push(state);
    }
    public void update(float dt){
        mStates.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        mStates.peek().render(sb);
    }
}
