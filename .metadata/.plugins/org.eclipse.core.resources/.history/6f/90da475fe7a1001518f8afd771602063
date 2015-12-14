package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class SceneManager {
	
	private SceneLoader sl;
	private Viewport vp;
	private ItemWrapper root;
	
	private boolean isClicked = false;

	private LevelManager lvm;
	
	//private Cat cat;
	private boolean isMainGameLoaded = false;
	
	public SceneManager(){
		sl = new SceneLoader();
		vp = new FitViewport(800, 600);
	
		
		init();
	}
	
	public void init(){
		sl.loadScene("MainMenu", vp);
		root = new ItemWrapper(sl.getRoot());
		sl.addComponentsByTagName("button", ButtonComponent.class);
	}
	
	public void loadMainGame(){
		lvm = new LevelManager(sl);	
		isMainGameLoaded = true;
	}
	
	public void update(float dt){
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			setScene("MainScene");
		}
		if(sl.getSceneVO().sceneName.equals("MainMenu")){
			
			System.out.println("IN BUTTON LOOPO");
			root.getChild("button1").getEntity().getComponent(ButtonComponent.class).addListener(new ButtonComponent.ButtonListener() {
				
				@Override
				public void touchUp() {}
				@Override
				public void touchDown() {}
				@Override
				public void clicked() {
					// TODO Auto-generated method stub
					System.out.println("CLICKED");
					isClicked = true;
					
	
				}
			});
		}
		if(isClicked){
			setScene("MainMenu");
			isClicked=false;
		}
		
			if(sl.getSceneVO().sceneName.equals("MainScene")&& !isMainGameLoaded) loadMainGame();
			if(sl.getSceneVO().sceneName.equals("MainScene")){
			lvm.update(dt);
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
