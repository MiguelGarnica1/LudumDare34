package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class IntroManager {
	private SceneManager sm;

	private Texture[] introTexture;
	private Texture currentT;

	private String[] quotes;
	private int currentQ = 0;


	private Texture bubble;

	private BitmapFont font;
	private BitmapFont next;

	private Music[] dialouge;
	private Music mus;

	public IntroManager(SceneManager sm) {
		this.sm = sm;

		introTexture = new Texture[3];
		introTexture[0] = new Texture("Intro Scene 1.png");
		introTexture[1] = new Texture("Intro Scene 2.png");
		introTexture[2] = new Texture("Intro Scene 3.png");
		currentT = introTexture[0];


		quotes = new String[5];
		quotes[0] = "Jaried: Meow..., fat cats get all the good things in life. "
				+ "\nThey get all the pussy(cats), cars, and dough. "
				+ "\nIf you wanna live the good life, you gotta be a fat cat.";
		quotes[1] = "Cosmo: Dang i want that! Guess i'll become a fat cat. ";
		quotes[2] = "Cosmo: Thxs for the moula, >;3. I'll be \"borrowing\" " + "\nit for a while.  ";
		quotes[3] = "Fat Cat: DAMN YOU ALLEY CAT.";
		quotes[4] = "Jaried: Damn Cosmo, you crazy " + "\n*as he watches Cosmo flee*";

		font = new BitmapFont();
		next = new BitmapFont();
		dialouge = new Music[5];
		dialouge[0] = Gdx.audio.newMusic(Gdx.files.internal("SnowTestes1.mp3"));
		dialouge[1] = Gdx.audio.newMusic(Gdx.files.internal("Cosmo Line 1.mp3"));
		dialouge[2] = Gdx.audio.newMusic(Gdx.files.internal("Cosmo Line 2.mp3"));
		dialouge[3] = Gdx.audio.newMusic(Gdx.files.internal("Fat line.mp3"));
		dialouge[4] = Gdx.audio.newMusic(Gdx.files.internal("SnowTestes2.mp3"));

		dialouge[1].setLooping(false);
		dialouge[2].setLooping(false);
		dialouge[3].setLooping(false);
		dialouge[4].setLooping(false);

		mus = Gdx.audio.newMusic(Gdx.files.internal("musicIntro.mp3"));
		mus.play();
		mus.setLooping(true);
		bubble = new Texture("Speech Rectangle.png");


	}

	private float timeElapsed1;
	private float timeElapsed2;
	private float timeElapsed3;
	private float timeElapsed4;
	private float timeElapsed5;
	
	public void update(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.justTouched()) {
			currentQ++;
		}

		timeElapsed1 += dt;
		
		
		//jerry
		if (currentQ == 0) {
			font.setColor(Color.BLUE);
			if(timeElapsed1 < 10){
				dialouge[0].play();
			}

		}
		
		//comos
		if (currentQ == 1) {
			font.setColor(Color.TEAL);
			dialouge[0].stop();
			timeElapsed2 += dt;
			if(timeElapsed2 < 4){
				dialouge[1].play();
			}
		}

		//cosmos
		if (currentQ == 2) {
			font.setColor(Color.TEAL);
			dialouge[1].stop();
			timeElapsed3 += dt;
			
			if(timeElapsed3 < 4){
				dialouge[2].play();
			}
		}
		
		//fat cat
		if (currentQ == 3) {
			font.setColor(Color.CHARTREUSE);
			dialouge[2].stop();
			timeElapsed4 += dt;
			if(timeElapsed4 < 2){
				dialouge[3].play();
			}
		}
		
		// jerry
		if (currentQ == 4) {
			font.setColor(Color.BLUE);
			dialouge[3].stop();
			timeElapsed5 += dt;
			if(timeElapsed5 < 3){
				dialouge[4].play();
			}
		}

		if (currentQ == 0) {
			currentT = introTexture[0];
		}

		if (currentQ == 1) {
			currentT = introTexture[1];
		}

		if (currentQ == 3 || currentQ == 2 || currentQ == 4) {
			currentT = introTexture[2];
		}

		if (currentQ == 5) {
			dialouge[4].stop();
			mus.stop();
			sm.setScene("MainScene");
		}
	}

	public void render(Batch batch) {
		batch.begin();

		batch.draw(currentT, 0, 0, sm.getVp().getCamera().viewportWidth, sm.getVp().getCamera().viewportHeight);
		batch.draw(bubble, 300, 10, 430, 100);
		font.draw(batch, quotes[currentQ], 330, 80);
		next.draw(batch, "ENTER to go to next scene", 600, 580);
		batch.end();
	}

	public void dispose() {
		for (Music m : dialouge) {
			m.dispose();
		}
	}

}
