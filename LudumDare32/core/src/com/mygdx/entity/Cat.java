package com.mygdx.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Cat implements IScript {
	private enum CatAnimation {
		stand, eat
	}

	CatAnimation catAnimation;
	CatAnimation previousAnimation;
	
	private Entity player;
	private Vector2 position;
	private Vector2 velocity;

	private TransformComponent transformComponent;
	private DimensionsComponent dimensionsComponent;
	private SpriteAnimationComponent spriter;
	private SpriteAnimationStateComponent spriterState;

	private int sushiEaten;

	public int getSushiEaten() {
		return sushiEaten;
	}

	public void setSushiEaten(int sushiEaten) {
		this.sushiEaten = sushiEaten;
	}

	private int health;
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Cat(){
		health = 3;
		catAnimation = CatAnimation.stand;
	}

	@Override
	public void init(Entity entity) {
		player = entity;

		// entity position(x,y,scale,rotation,etc.) data is kept in the
		// TransformComponent
		// entity dimensions(width,height,boundbox,polygon) data is held in
		// DimensionsComponent
		// box2d physicsBody is kept in physicsBodyComponent
		// ComponentRetriever is the fastest way to retrieve components
		transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
		dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);

		spriter = ComponentRetriever.get(entity, SpriteAnimationComponent.class);
		spriterState = ComponentRetriever.get(entity, SpriteAnimationStateComponent.class);

		System.out.println(spriter.currentAnimation);

		velocity = new Vector2(.5f, .5f);
		position = new Vector2(getX(), getY());
	}

	float elapsedTime = 0;

	@Override
	public void act(float delta) {
		position = new Vector2(getX(), getY());
		//If space is press, size increase, and change animationState to eat
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			catAnimation = CatAnimation.eat;
		}
		
		if (catAnimation == CatAnimation.eat && previousAnimation == CatAnimation.stand) {
			spriterState.set(spriter.frameRangeMap.get("eat"), 5, Animation.PlayMode.NORMAL);
		}
		
		if(catAnimation == CatAnimation.eat){
			elapsedTime += delta;
		}
		
		if (spriterState.get().isAnimationFinished(elapsedTime)) {
			elapsedTime = 0;
			spriterState.set(spriter.frameRangeMap.get("stand"), 10, Animation.PlayMode.LOOP);
			catAnimation = CatAnimation.stand;
		}
		
//		System.out.println("now: "+ catAnimation);
//		System.out.println("previous: "+ previousAnimation);
//		System.out.println("time: "+ elapsedTime);
		previousAnimation = catAnimation;
	}

	public void getFat(){
		transformComponent.scaleX += 0.5f;
		transformComponent.scaleY += 0.5f;
		transformComponent.y += 5f;
		System.out.println("get fat");
	}
	public void translate(Vector2 target) {

	}

	public Vector2 getPosition() {
		return position;
	}

	public float getX() {
		return transformComponent.x;
	}

	public float getY() {
		return transformComponent.y;
	}

	public float getWidth() {
		return dimensionsComponent.width;
	}

	public float getHeight() {
		return dimensionsComponent.height;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
