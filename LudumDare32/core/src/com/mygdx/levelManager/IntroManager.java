package com.mygdx.levelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroManager {
	private SceneManager sm;
	private Viewport vp;
	
	private Texture[] introTexture;
	private Texture currentT;
	
	private String[] quotes;
	private int currentQ = 0;
	
	private Texture bubble;
	private float width, height;
	
	private BitmapFont font;
	public IntroManager(SceneManager sm){
		this.sm = sm;
		
		introTexture = new Texture[3];
		introTexture[0] = new Texture("Intro Scene 1.png");
		introTexture[1] = new Texture("Intro Scene 2.png");
		introTexture[2] = new Texture("Intro Scene 3.png");
		currentT = introTexture[0];
		
		quotes = new String[4];
		quotes[0] = "CAT1: urgg, these rich fat cat, ... dang ... blabg";
		quotes[1] = "CAT2: huh huhd ?";
		quotes[2] = "CAT2: fat ? cat ? kkk";
		quotes[3] = "CAT2: ME WANT FAT!";
		
		bubble = new Texture("Speech Rectangle.png");
		font = new BitmapFont();
		
		width = 500;
		height = 100;
	}
	
	public void update(float dt){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			currentQ++;
		}
		if(currentQ == 0 )font.setColor(Color.GREEN);
		if(currentQ == 1)font.setColor(Color.TEAL);
		if(currentQ == 2)font.setColor(Color.TEAL);
		if(currentQ == 3)font.setColor(Color.TEAL);
		
		
		if(currentQ == 1 || currentQ == 0){
			
			currentT = introTexture[0];
		}
		
		if(currentQ == 2){
			currentT = introTexture[1];
		}
		
		if(currentQ == 3){
			currentT = introTexture[2];
		}
		
		if(currentQ == 4){
			sm.setScene("MainScene");
		}
	}
	
	public void render(Batch batch){
		batch.begin();
		batch.draw(currentT, 0,0, sm.getVp().getCamera().viewportWidth, sm.getVp().getCamera().viewportHeight);
		batch.draw(bubble, 250, 0, width, height );
		font.draw(batch, quotes[currentQ], 300, 50);
		batch.end();
	}
	
	public void dispose(){
		for(Texture t: introTexture){
			t.dispose();
		}
		font.dispose();
		bubble.dispose();
	}
	
}
