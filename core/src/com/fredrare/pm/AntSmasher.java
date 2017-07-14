package com.fredrare.pm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fredrare.pm.state.MenuState;
import com.fredrare.pm.state.State;
import com.fredrare.pm.state.StateManager;

public class AntSmasher extends ApplicationAdapter {
	public static int WIDTH,HEIGHT,YSPEED=-400;
	public static final int VIRTUALWIDTH=480,VIRTUALHEIGHT=800;
	public static FirebaseService firebase;
	SpriteBatch batch;
	StateManager manager;
	public AntSmasher(FirebaseService firebase){
		this.firebase=firebase;
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager=new StateManager();
		manager.push(new MenuState(manager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		HEIGHT=Gdx.graphics.getHeight();
		WIDTH=Gdx.graphics.getWidth();
		manager.update(Gdx.graphics.getDeltaTime());
		manager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public interface FirebaseService{
		public void addToFirebase(String user,int score);
	}
}
