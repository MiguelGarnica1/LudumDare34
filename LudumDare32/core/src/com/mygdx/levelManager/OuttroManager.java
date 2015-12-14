package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OuttroManager {
	private SceneManager sm;
	private Viewport vp;

	private Texture[] outtroTexture;
	private Texture currentT;

	private String[] quotes;
	private int currentQ = 0;

	private Texture bubble;

	private BitmapFont font;

	public OuttroManager(SceneManager sm) {
		this.sm = sm;

		outtroTexture = new Texture[4];
		outtroTexture[0] = new Texture("outtro1.png");
		outtroTexture[1] = new Texture("outtro2.png");
		outtroTexture[2] = new Texture("outtro3.png");
		outtroTexture[3] = new Texture("outtro4.png");
		currentT = outtroTexture[0];

		quotes = new String[4];
		quotes[0] = "Cat1: urgg, these rich fat cat, ... dang ... blabg";
		quotes[1] = "Cat2: huh huhd ?";
		quotes[2] = "Cat2: fat ? cat ? kkk";
		quotes[3] = "Cat2: me want fat!";

		font = new BitmapFont();

		bubble = new Texture("Speech Rectangle.png");
	}

	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if (currentQ < 3) {
				currentQ++;
			}

		}

		if (currentQ == 0) {
			font.setColor(Color.BLUE);
		}
		if (currentQ == 1) {
			font.setColor(Color.TEAL);
		}

		if (currentQ == 2) {
			font.setColor(Color.TEAL);
		}

		if (currentQ == 3) {
			font.setColor(Color.TEAL);
		}

		if (currentQ == 0) {
			currentT = outtroTexture[0];
		}

		if (currentQ == 1) {
			currentT = outtroTexture[1];
		}

		if (currentQ == 2) {
			currentT = outtroTexture[2];
		}

		if (currentQ == 3) {
			currentT = outtroTexture[3];
		}

	}

	public void render(Batch batch) {
		batch.begin();
		batch.draw(currentT, 0, 0, sm.getVp().getCamera().viewportWidth, sm.getVp().getCamera().viewportHeight);
		if (currentQ < 3) {
			batch.draw(bubble, 300, 50, 400, 50);
			font.draw(batch, quotes[currentQ], 350, 80);
		}
		batch.end();
	}
	
	public void dispose(){
		
	}
}
