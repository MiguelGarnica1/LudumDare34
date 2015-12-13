package com.mygdx.levelManager;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entity.Cat;
import com.mygdx.entity.Sushi;
import com.mygdx.handler.HUD;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class LevelManager {
	public enum LevelProgress {
		level0, level1, level2, level3, level4, level5, level6
	}

	private SceneLoader sl;
	private Viewport vp;
	private HUD hud;
	private ItemWrapper root;
	private Cat cat;

	BitmapFont font;

	private float[] time;
	private int[] maxSushi;
	
	private ArrayList<Sushi> sushis;

	private float timeElap;

	private int currentLevel;
	
	public LevelManager(SceneLoader sceneLoader) {
		sl = sceneLoader;
		vp = new FitViewport(800, 600);

		root = new ItemWrapper(sl.getRoot());
		cat = new Cat();
		root.getChild("player").addScript(cat);

		hud = new HUD(cat, this);

		
		maxSushi = new int[6];
		maxSushi[0] = 0;
		setMaxSushiValue(maxSushi);

		time = new float[6];
		time[0] = 0f;
		setTime(time);

		sushis = new ArrayList<Sushi>();
		sushis.add(new Sushi());
	}

	private void setMaxSushiValue(int[] arr) {
		int num = 5;
		for (int i = 1; i < arr.length; i++) {
			arr[i] = num + i*2;
		}
	}

	public void setTime(float[] arr) {
		float timet = 3f;
		for (int i = 1; i < arr.length; i++) {
			arr[i] = timet *= .65f;
		}
	}

	public void createSushi(int lvl) {
		if (timeElap > time[lvl]) {
			if (sushis.size() < 75) {
				Vector2 pos = new Vector2(100, 240 - hud.getSushi().getDimension().y / 2);
				for (Sushi s : sushis) {
					if (s.getPosition().y == 240 - hud.getSushi().getDimension().y / 2) {
						if (s.getPosition().x > pos.x + s.getDimension().x) {
							sushis.add(new Sushi());
							break;
						}
					}
				}
				
				if (sushis.isEmpty()) {
					sushis.add(new Sushi());
				}

			}
			timeElap = 0;
		}

	}

	public void update(float dt) {
		//if (cat.getHealth() > 0) {
			timeElap += Gdx.graphics.getDeltaTime();
			
			if(cat.getSushiEaten() == maxSushi[currentLevel]){
				cat.setSushiEaten(0);
				currentLevel++;
				System.out.println("Level up to: " + currentLevel);
				sushis.removeAll(sushis);
			}
			createSushi(currentLevel);
			for (int i = 0; i < sushis.size(); i++) {
				sushis.get(i).update(dt);
				if (sushis.get(i).isInPosition(370, 425, 240 - hud.getSushi().getDimension().y / 2)
						&& Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					if (hud.getSushi().getId() == sushis.get(i).getId()) {
						cat.getFat();
						cat.setSushiEaten(cat.getSushiEaten() + 1);
						sushis.remove(i);
					} else {
						sushis.remove(i);
						cat.setHealth(cat.getHealth() - 1);
						System.out.println("wrong one u fool");
					}
				}
				
				if(sushis.get(i).getTimeElapsed() >= 16.5){
					sushis.remove(i);
				}
			}
	//	}

		if (cat.getHealth() <= 0) {
			sl.loadScene("DeathScene", vp);
		}

	}

	public void removeAll() {
		sushis.removeAll(sushis);
		hud.dispose();
	}

	public void render() {

		//if (cat.getHealth() > 0) {
			for (Sushi sush : sushis) {
				sush.render(sl.getBatch());
			}
			hud.render(sl.getBatch());
			hud.update(Gdx.graphics.getDeltaTime());
		//}
	}

	public void levelAdvance() {

	}

	public void dispose() {

	}
	
	public int[] getMaxSushi() {
		return maxSushi;
	}

	public void setMaxSushi(int[] maxSushi) {
		this.maxSushi = maxSushi;
	}
}
