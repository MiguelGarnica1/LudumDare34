package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class SceneManager {

	private SceneLoader sl;
	private Viewport vp;
	public Viewport getVp() {
		return vp;
	}

	private ItemWrapper root;

	private boolean isPlayClicked = false;

	
	private LevelManager lvm;
	private IntroManager im;
	private OuttroManager om;
	// private Cat cat;
	private boolean isMainGameLoaded = false;
	private boolean isIntroLoaded = false;
	private boolean isOutroLoaded = false;
	
	public SceneManager() {
		sl = new SceneLoader();
		vp = new FitViewport(800, 600);

		init();
		

	}

	public void init() {
		sl.loadScene("MainMenu", vp);
		root = new ItemWrapper(sl.getRoot());
		sl.addComponentsByTagName("button", ButtonComponent.class);
	}



	
	public void update(float dt) {
		updateButtons();
		if (isPlayClicked) {
			setScene("IntroScene");
			isPlayClicked = false;
		}
		
		if (sl.getSceneVO().sceneName.equals("IntroScene") && !isIntroLoaded)
			loadIntroGame();
		if (sl.getSceneVO().sceneName.equals("IntroScene")) {
			im.update(dt);
		}
		
		System.out.println(sl.getSceneVO().sceneName);
		if (sl.getSceneVO().sceneName.equals("MainScene") && !isMainGameLoaded)
			loadMainGame();
		if (sl.getSceneVO().sceneName.equals("MainScene")) {
			lvm.update(dt);
		}
		
		if (sl.getSceneVO().sceneName.equals("OuttroScene") && !isOutroLoaded)
			loadOuttroGame();
		if (sl.getSceneVO().sceneName.equals("OuttroScene")) {
			om.update(dt);
		}
		
		if(sl.getSceneVO().sceneName.equals("DeathScene")){
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				setScene("MainScene");
				isMainGameLoaded = false;
			}
		}
		
		
	}

	public void updateButtons(){
		
		if(sl.getSceneVO().sceneName.equals("MainMenu")){
			
			//System.out.println("IN BUTTON LOOPO");
			root.getChild("button1").getEntity().getComponent(ButtonComponent.class).addListener(new ButtonComponent.ButtonListener() {
				
				@Override
				public void touchUp() {}
				@Override
				public void touchDown() {}
				@Override
				public void clicked() {
					// TODO Auto-generated method stub
					//System.out.println("CLICKED");
					isPlayClicked = true;
					
				}
			});
		}
		
		
	}
	public void loadIntroGame() {
		im = new IntroManager(this);
		isIntroLoaded = true;
	}
	
	public void loadOuttroGame() {
		om = new OuttroManager(this);
		isOutroLoaded  = true;
	}
	
	public void loadMainGame() {
		lvm = new LevelManager(sl);
		isMainGameLoaded = true;
	}
	
	public void render() {
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
		if (sl.getSceneVO().sceneName.equals("MainScene")) {
			lvm.render();
		}
		
		if (sl.getSceneVO().sceneName.equals("IntroScene")) {
			im.render(sl.getBatch());
		}
		
		if(sl.getSceneVO().sceneName.equals("OuttroScene")){
			om.render(sl.getBatch());
		}
	}

	public void setScene(String scene) {
		sl.loadScene(scene, vp);
	}
	
	public void dispose(){
		im.dispose();
		lvm.dispose();
		om.dispose();
	}
}
