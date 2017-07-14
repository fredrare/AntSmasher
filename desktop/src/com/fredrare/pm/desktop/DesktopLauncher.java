package com.fredrare.pm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fredrare.pm.AntSmasher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=AntSmasher.WIDTH;
		config.height=AntSmasher.HEIGHT;
		config.title="Ant Smasher";
		new LwjglApplication(new AntSmasher(), config);
	}
}
