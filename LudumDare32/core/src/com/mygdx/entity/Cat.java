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

public class Cat implements IScript{
	private Entity player;
	private Vector2 position;
	private Vector2 velocity;
	
	private TransformComponent transformComponent ;
	private DimensionsComponent dimensionsComponent;
	private SpriteAnimationComponent spriter;
	private SpriteAnimationStateComponent spriterState;
	
	public static int sushiEaten = 0;
	private int health;
	public Cat(){
		health = 3;
	}
	
	@Override
	public void init(Entity entity){
		player = entity;

		// entity position(x,y,scale,rotation,etc.) data is kept in the TransformComponent
		// entity dimensions(width,height,boundbox,polygon) data is held in DimensionsComponent
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
	
	@Override
	public void act(float delta) {
		position = new Vector2(getX(), getY());
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			transformComponent.scaleX +=0.1f;
			transformComponent.scaleY +=0.1f;
			transformComponent.y += 0.1f;
			spriterState.set(spriter.frameRangeMap.get("eat"), 10, Animation.PlayMode.LOOP);
		}
	}
	
	public void translate(Vector2 target){

	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float getX(){
		return transformComponent.x;
	}
	
	public float getY(){
		return transformComponent.y;
	}
	
	public float getWidth(){
		return dimensionsComponent.width;
	}
	
	public float getHeight(){
		return dimensionsComponent.height;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}



}
