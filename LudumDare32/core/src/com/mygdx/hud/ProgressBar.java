package com.mygdx.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.levelManager.LevelManager;

public class ProgressBar {

	private Texture hudBackProgress;
	private Texture hudInsideProgress;
	private TextureRegion hudFrontProgress;

	private LevelManager lvm;

	private int section;
	private float width;

	public ProgressBar(LevelManager lvm) {
		this.lvm = lvm;

		hudBackProgress = new Texture("progressBar.png");
		hudInsideProgress = new Texture("inside.png");
		hudFrontProgress = new TextureRegion(hudInsideProgress);

	}

	public void update(float dt) {
		section = hudInsideProgress.getWidth() / lvm.getMaxSushi()[lvm.getCurrentLevel()];
		width = section * lvm.getCat().getSushiEaten();

		//System.out.println(width);
	}

	// x offset 5
	// y offset 9
	public void render(Batch batch) {
		batch.begin();
		batch.draw(hudBackProgress, 160, 100, 500, 50);
		batch.draw(hudFrontProgress, 165, 109, width, 35);
		batch.end();
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}
}
