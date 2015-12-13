package com.mygdx.handler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Cat;
import com.mygdx.entity.Sushi;
import com.mygdx.levelManager.LevelManager;

public class HUD {

	private Cat cat;
	private Sushi hudSush;
	private Texture[] hearts;
	LevelManager lvm;
	
	private Vector2 position;
	private float timeElap;
	public static final float TIME = 2f;
	
	public HUD(Cat cat, LevelManager lvm) {
		this.cat = cat;
		hudSush = new Sushi();
		hearts = new Texture[3];
		this.lvm = lvm;
		
		for(int i =0; i< hearts.length; i++){
			hearts[i] = new Texture("Heart.png");
		}
		position = new Vector2(415, 300);
	}

	public void update(float delta) {
		timeElap += delta;
		if (timeElap > TIME) {
			hudSush.setSushiTexure(hudSush.generateRandomSushi(4));
			timeElap = 0;
		}
		

	}
	
	public void render(Batch batch) {
		
		hudSush.setPosition(position.x, position.y);
		hudSush.render(batch);
		batch.begin();
		for(int i = 1; i <= cat.getHealth();i++){
			batch.draw(hearts[i-1],300 + i * 40, 200, 40, 40);
		}
		batch.end();
		
	}

	public Sushi getSushi() {
		return hudSush;
	}

	public void dispose(){
		hudSush.dispose();
	}
}
