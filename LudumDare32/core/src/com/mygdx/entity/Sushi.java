package com.mygdx.entity;

import java.util.Random;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Sushi implements Component{

	private Texture tex;
	private TextureRegion[] sushis;
	private TextureRegion reg;
	private int id;
	private Random randSushi;
	private Vector2 position;
	private float speed = 2;
	private float width = 40;
	private Vector2[] points;

	
	private Vector2 dimension;
	
	public Sushi() {
	
		tex = new Texture("sushi.png");
		sushis = new TextureRegion[4];
		points = new Vector2[4];
		randSushi = new Random();
		dimension = new Vector2(width,width);
		position = new Vector2(100 - width/2, 240 - width/2);
		init();
	}

	public void init() {
		sushis[0] = new TextureRegion(tex, 0, 0, 60, 60);
		sushis[1] = new TextureRegion(tex, 60, 0, 60, 60);
		sushis[2] = new TextureRegion(tex, 0, 60, 60, 60);
		sushis[3] = new TextureRegion(tex, 60, 60, 60, 60);

		points[0] = new Vector2(100 - width/2, 42 - width/2);
		points[1] = new Vector2(100 - width/2 , 240 - width/2);
		points[2] = new Vector2(714 - width/2 , 240 - width/2);
		points[3] = new Vector2(714 - width/2, 42 - width/2);
		setSushiTexure(generateRandomSushi(4));
		
		
	}

	public void update() {
		move();
		isInPosition(370, 425, 240);

	}

	public void move() {

		if (getPosition().y < points[1].y && getPosition().x == points[1].x) {
			setPosition(getPosition().x, getPosition().y + getSpeed());
		}
		if (getPosition().x < points[2].x && getPosition().y == points[2].y) {
			setPosition(getPosition().x + getSpeed(), getPosition().y);
		}
		if (getPosition().y > points[3].y && getPosition().x == points[3].x) {
			setPosition(getPosition().x, getPosition().y - getSpeed());
		}
		if (getPosition().x > points[0].x && getPosition().y == points[0].y) {
			setPosition(getPosition().x - getSpeed(), getPosition().y);
		}

	}

	public void render(Batch batch) {
		batch.begin();
		batch.draw(reg, position.x, position.y, dimension.x / 2, dimension.y / 2, dimension.x, dimension.y, 1, 1, 0);
		batch.end();
	}

	public int generateRandomSushi(int bound) {
		return randSushi.nextInt(bound) + 1;
	}

	public void setSushiTexure(int id) {
		this.id = id;
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

	public boolean isInPosition(float xleft, float xright, float y) {
		if (getPosition().x > xleft && getPosition().x + dimension.x < xright) {
			if (getPosition().y == y) {
				System.out.println("EAT ME!");
				return true;
			}
		}
		return false;

	}

	public void dispose() {
		getTex().dispose();
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public Vector2 getDimension() {
		return dimension;
	}

	public void setDimension(Vector2 dimension) {
		this.dimension = dimension;
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

	public void setId(int id) {
		this.id = id;
	}

}
