package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.mygdx.entity.Sushi;
import com.mygdx.handler.HUD;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class LudumDare32 extends ApplicationAdapter {
	private SceneLoader sl;
	private Viewport vp;
	private Sushi sush;
	private HUD hud;
	
	private ItemWrapper root;
	private Cat cat;
	@Override
	public void create () {
		sl = new SceneLoader();

		vp = new FitViewport(800,600);

		sl.loadScene("MainScene", vp);
		root = new ItemWrapper(sl.getRoot());
		cat = new Cat();
		root.getChild("player").addScript(cat);
		
		sush = new Sushi(2);
		
		hud = new HUD();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sl.getEngine().update(Gdx.graphics.getDeltaTime());

		sush.render(sl.getBatch());
		sush.update();
		
		hud.render(sl.getBatch());
		hud.update(Gdx.graphics.getDeltaTime());

		
	}
}
