package com.mygdx.handler;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Sushi;

public class HUD {

	private Sushi hudSush;
	private Vector2 position;
	private float timeElap;
	public static final float TIME = 2f;
	public HUD() {
		hudSush = new Sushi();
		position = new Vector2(50, 350);
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
	}

	public Sushi getSushi() {
		return hudSush;
	}

	public void dispose(){
		hudSush.dispose();
	}
}
