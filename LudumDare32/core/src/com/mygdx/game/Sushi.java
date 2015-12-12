package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Sushi {

	private Texture tex;
	private TextureRegion reg;
	private int id;
	
	private Vector2 position;
	private float speed = 150;
	private float width = 100;

	public Sushi(int identifier) {
		id = identifier;
		tex = new Texture("sushi.png");
		position = new Vector2(100,100);
		init();
	}

	public void init() {
		if (id == 1) {
			reg = new TextureRegion(tex, 0,0,32,32);
		}
		if (id == 2) {
			reg = new TextureRegion(tex, 32,0,32,32);
		}
		if (id == 3) {
			reg = new TextureRegion(tex, 0,32,32,32);
		}
		if (id == 4) {
			reg = new TextureRegion(tex, 32,32,32,32);
		}
	}
	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			if(id<4){
				id++;
			}else{
				id = 1;
			}
		}
		init();
	}
	
	public void render(Batch batch){
		batch.begin();
		batch.draw(reg, position.x, position.y, width/2, width/2, width, width, 1, 1, 0);
		batch.end();
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
