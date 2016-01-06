package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.levelManager.SceneManager;

public class LudumDare32 extends ApplicationAdapter {
	private SceneManager sm;
	@Override
	public void create () {
		sm = new SceneManager();

		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render();
		

		
	}
}
