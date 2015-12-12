package com.mygdx.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Cat implements IScript{
	private Entity player;
	private Vector2 position;
	private Vector2 velocity;
	
	private TransformComponent transformComponent ;
	private DimensionsComponent dimensionsComponent;
	
	public static int sushiEaten = 0;
	public Cat(Entity entity){
		init(entity);
	}
	
	public void init(Entity entity){
		player = entity;

		// entity position(x,y,scale,rotation,etc.) data is kept in the TransformComponent
		// entity dimensions(width,height,boundbox,polygon) data is held in DimensionsComponent
		// box2d physicsBody is kept in physicsBodyComponent
		// ComponentRetriever is the fastest way to retrieve components
		transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
		dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
		
		velocity = new Vector2(.5f, .5f);
		position = new Vector2(getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		position = new Vector2(getX(), getY());
		
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
