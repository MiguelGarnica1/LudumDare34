package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.mygdx.entity.Sushi;
import com.mygdx.handler.HUD;
import com.mygdx.levelManager.LevelManager;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class LudumDare32 extends ApplicationAdapter {

	private LevelManager lvlm;
	
	@Override
	public void create () {
		
		lvlm = new LevelManager();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		lvlm.render();
		lvlm.update();

		
	}
}
