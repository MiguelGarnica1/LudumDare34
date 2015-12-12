package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Sushi {

	private Texture tex;
	private TextureRegion[] sushis;
	private TextureRegion reg;
	private int id;
	private Random randSushi;
	private Vector2 position;
	private float speed = 150;
	private float width = 100;

	public Sushi(int identifier) {
		id = identifier;
		tex = new Texture("sushi.png");
		sushis = new TextureRegion[4];
		randSushi = new Random();
		position = new Vector2(100,100);
		init();
	}

	public void init() {
		sushis[0] = new TextureRegion(tex, 0,0,32,32);
		sushis[1] = new TextureRegion(tex, 32,0,32,32);
		sushis[2] = new TextureRegion(tex, 0,32,32,32);
		sushis[3] =  new TextureRegion(tex, 32,32,32,32);
		setSushiTexure();
		
	}

	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			id = generateRandomSushi(4);
			setSushiTexure();
		}
		
	}
	
	public void render(Batch batch){
		batch.begin();
		batch.draw(reg, position.x, position.y, width/2, width/2, width, width, 1, 1, 0);
		batch.end();
	}
	
	public int generateRandomSushi(int bound){
		return randSushi.nextInt(bound)+ 1;
	}
	
	public void setSushiTexure(){
		if (id == 1) {
			reg = sushis[0];
		}
		if (id == 2) {
			reg = sushis[1];
		}
		if (id == 3) {
			reg = sushis[2];
		}
		if (id == 4) {
			reg = sushis[3];
		}
	}
	
	public void dispose(){
		getTex().dispose();
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Texture getTex() {
		return tex;
	}

	public TextureRegion getReg() {
		return reg;
	}

	public float getSpeed() {
		return speed;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	
	
}
