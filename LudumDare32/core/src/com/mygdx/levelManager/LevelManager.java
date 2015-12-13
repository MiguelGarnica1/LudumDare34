package com.mygdx.levelManager;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.mygdx.entity.Sushi;
import com.mygdx.handler.HUD;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class LevelManager {
	public enum LevelProgress{
		level0, level1, level2, level3, level4, level5, level6
	}
	private SceneLoader sl;
	private Viewport vp;
	private HUD hud;
	private ItemWrapper root;
	private Cat cat;
	
	BitmapFont font;

	private float speed;
	private float rate; 
	private int[] maxSushi;
	private ArrayList<Sushi> sushis;
	
	private float timeElap;
	
	private LevelProgress currentLevel;
	
	public LevelManager(){
		sl = new SceneLoader();

		vp = new FitViewport(800,600);
		sl.loadScene("MainScene", vp);
		root = new ItemWrapper(sl.getRoot());
		cat = new Cat();
		root.getChild("player").addScript(cat);
		
		hud = new HUD();
		
		currentLevel = LevelProgress.level1;
		maxSushi = new int[7];
		setMaxSushiValue(maxSushi);
		sushis = new ArrayList<Sushi>();
		
		sushis.add(new Sushi());
	}
	
	private void setMaxSushiValue(int[] arr){
		for(int i = 0 ; i < arr.length; i++){
			arr[i] = i * 10;
		}
	}
	
	public void createSushi(int lvl){
		if(timeElap>3f){
			if(sushis.size()< maxSushi[lvl]){
				Vector2 pos = new Vector2(100, 240);
				for(Sushi s : sushis){
					if(s.getPosition().y == 240){
						if(s.getPosition().x > pos.x + s.getDimension().x){
							sushis.add(new Sushi());
							break;
						}
					}
				}
				
			}
			timeElap=0;
		}
		
	}
	
	public void update(){
		timeElap+=Gdx.graphics.getDeltaTime();
		createSushi(1);
		for(int i = 0; i<sushis.size(); i++){
			sushis.get(i).update();
			if(sushis.get(i).isInPosition(370, 425, 240) && Gdx.input.isKeyJustPressed(Keys.SPACE)){
				if(hud.getSushi().getId() == sushis.get(i).getId()){
					cat.getFat();
					sushis.remove(i);
				}else{
					
				}
			}
		}
		
	}
	
	public void render(){

		sl.getEngine().update(Gdx.graphics.getDeltaTime());

		hud.render(sl.getBatch());
		hud.update(Gdx.graphics.getDeltaTime());
		for(Sushi sush : sushis){
			sush.render(sl.getBatch());
		}
	}
	
	public void levelAdvance(){
		
	}
	
	public void dispose(){
		
	}
}
