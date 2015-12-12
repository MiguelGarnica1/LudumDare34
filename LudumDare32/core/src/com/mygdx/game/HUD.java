package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class HUD {

	private Sushi hudSush;
	private Vector2 position;
	private float timeElap;
	public static final float TIME = 2f;
	public HUD() {
		hudSush = new Sushi(1);
		position = new Vector2(50, 350);
	}

	public void update(float delta) {
		timeElap += delta;
		if (timeElap > TIME) {
			if (hudSush.getId() < 4) {
				hudSush.setId(hudSush.generateRandomSushi(4));
			} else {
				hudSush.setId(1);
			}
			hudSush.setSushiTexure();
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

}
