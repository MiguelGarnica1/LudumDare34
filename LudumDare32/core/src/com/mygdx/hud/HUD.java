package com.mygdx.hud;

import java.util.ArrayList;

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
	private LevelManager lvm;
	private ProgressBar progressBar;
	
	private Texture chuteRight_I;
	private Texture chuteRight_O;
	private Texture chuteRight;

	private Texture chuteDown_I;
	private Texture chuteDown_O;
	private Texture chuteDown;
	
	private Vector2 position;
	private float timeElap;
	public static final float TIME = 2f;

	public HUD(Cat cat, LevelManager lvm) {
		this.cat = cat;
		hudSush = new Sushi();
		hearts = new Texture[3];
		this.lvm = lvm;
		progressBar = new ProgressBar(lvm);

		
		chuteRight_I = new Texture("GreenBox_2.png");
		chuteRight_O = new Texture("GreenBox_1.png");
		chuteRight = chuteRight_O;
		
		chuteDown_I = new Texture("RedBox_2.png");
		chuteDown_O = new Texture("RedBox_1.png");
		chuteDown = chuteDown_O;
		
		for (int i = 0; i < hearts.length; i++) {
			hearts[i] = new Texture("Heart.png");
		}
		position = new Vector2(380, 163);
	}
	float spawnTimeElapsed;
	float timeFlickerRight;
	float timeFlickerDown;
	public void update(float dt) {
		timeElap += dt;
		spawnTimeElapsed += dt;
		
		
		if (timeElap > TIME) {
			hudSush.setSushiTexure(hudSush.generateRandomSushi(4));
			timeElap = 0;
		}
		
		flickerRight(dt,lvm.getTime()[lvm.getCurrentLevel()]);
		flickerDown(dt,lvm.getSushis());
		progressBar.update(dt);

	}

	public void flickerRight(float dt, float spawnTime){
		timeFlickerRight += dt;
		if(timeFlickerRight > 0f && timeFlickerRight < .2f){
			chuteRight = chuteRight_I;
		}
		if(timeFlickerRight > .2f ){
			chuteRight = chuteRight_O;
		}
		if(timeFlickerRight > spawnTime){
			timeFlickerRight = 0;
		}
		
	}
	
	//TODO : FIX FLICKERING For down
	public void flickerDown(float dt, ArrayList<Sushi> sushis){
		timeFlickerDown += dt;
		if(timeFlickerDown > 0f && timeFlickerDown < .2f){
			chuteDown = chuteDown_I;
		}
		if(timeFlickerDown > .2f ){
			chuteDown = chuteDown_O;
		}
		for(Sushi s: sushis){
			if(s.isInRangeY(224, 174, 100 - 20)){
				timeFlickerDown = 0;
				break;
			}
		}
	}
	
	public void render(Batch batch) {

		hudSush.setPosition(position.x, position.y);
		hudSush.render(batch);
		batch.begin();
		for (int i = 1; i <= cat.getHealth(); i++) {
			batch.draw(hearts[i - 1], 300 + i * 40, 200, 40, 40);
		}
		
		batch.draw(chuteRight,85, 210, 65, 65);
		batch.draw(chuteDown,77, 180, 50, 45);
		batch.end();

		progressBar.render(batch);
	}

	public Sushi getSushi() {
		return hudSush;
	}

	public void dispose() {
		hudSush.dispose();
	}
}
