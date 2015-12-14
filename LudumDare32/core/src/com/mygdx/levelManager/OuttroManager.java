package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class OuttroManager {
	private SceneManager sm;

	private Texture[] outtroTexture;
	private Texture currentT;

	private String[] quotes;
	private int currentQ = 0;

	private Music[] dialouge;
	
	private Texture bubble;
	private BitmapFont font;

	private Music mus;
	public OuttroManager(SceneManager sm) {
		this.sm = sm;

		outtroTexture = new Texture[4];
		outtroTexture[0] = new Texture("OutroScene 1.png");
		outtroTexture[1] = new Texture("OutroScene 2.png");
		outtroTexture[2] = new Texture("OutroScene 3.png");
		outtroTexture[3] = new Texture("end.png");
		currentT = outtroTexture[0];

		quotes = new String[3];
		quotes[0] = "Jerry: Why are you so fat?";
		quotes[1] = "Cosmo: I wanted the fat cat life, so i got fat.";
		quotes[2] = "Jerry: How you so stupid";

		dialouge = new Music[3];
		dialouge[0] = Gdx.audio.newMusic(Gdx.files.internal("SnowTestes3.mp3"));
		dialouge[1] = Gdx.audio.newMusic(Gdx.files.internal("Cosmo Line 3.mp3"));
		dialouge[2] = Gdx.audio.newMusic(Gdx.files.internal("SnowTestes4.mp3"));


		
		
		dialouge[0].setLooping(false);
		dialouge[1].setLooping(false);
		dialouge[2].setLooping(false);

		mus = Gdx.audio.newMusic(Gdx.files.internal("musicOutro.mp3"));
		mus.setLooping(false);
		font = new BitmapFont();
		
		mus.play();
		bubble = new Texture("Speech Rectangle.png");
	}

	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if (currentQ < 3) {
				currentQ++;
			}
		}
		System.out.println(currentQ);
		if (currentQ == 0) {
			font.setColor(Color.BLUE);
			dialouge[0].play();
		}
		if (currentQ == 1) {
			font.setColor(Color.TEAL);
			dialouge[0].stop();
			dialouge[1].play();
		}

		if (currentQ == 2) {
			font.setColor(Color.BLUE);
			dialouge[1].stop();
			dialouge[2].play();
		}

		if (currentQ == 3){
			dialouge[2].stop();
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
		mus.dispose();
	}
}
