package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class SceneManager {
	
	private SceneLoader sl;
	private Viewport vp;
	private ItemWrapper root;
	private LevelManager lvm;
	
	private Cat cat;
	private boolean isMainGameLoaded = false;
	
	public SceneManager(){
		sl = new SceneLoader();
		vp = new FitViewport(800, 600);
		
		lvm = new LevelManager(sl);
		init();
	}
	
	public void init(){
		sl.loadScene("MainMenu", vp);
	}
	
	public void loadMainGame(){
		root = new ItemWrapper(sl.getRoot());
		cat = new Cat();
		root.getChild("player").addScript(cat);
		
		isMainGameLoaded = true;
	}
	
	public void update(float dt){
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			setScene("MainScene");
		}
		if(sl.getSceneVO().sceneName.equals("MainScene")&& !isMainGameLoaded) loadMainGame();
		if(sl.getSceneVO().sceneName.equals("MainScene")){
			lvm.update();
		}
	}
	
	public void render(){
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
		if(sl.getSceneVO().sceneName.equals("MainScene")){
			lvm.render();
		}
	}
	
	public void setScene(String scene){
		sl.loadScene(scene, vp);
	}
}