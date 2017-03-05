/*
 * Copyright (C) 2016  Christian DeTamble
 *
 * This file is part of nO mooRe.
 *
 * nO mooRe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * nO mooRe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with nO mooRe.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.bplaced.therefactory.nomoore.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bplaced.therefactory.nomoore.MyGdxGame;
import net.bplaced.therefactory.nomoore.utils.Utils;

public class CutsceneModernTimes extends ScreenAdapter {

	private final SpriteBatch batch;
	private final ShapeRenderer sr;
	private final Sprite bg;
	private final FitViewport viewport;
	private final OrthographicCamera camera;
	private final BitmapFont font;
	private final Sprite girl;
	private String message = "";
	private int numTouches = -2;
	private final Sprite knife;
	private final Sprite girlStabbed1;
	private final Sprite girlStabbed2;
	private final Sprite girlStabbed3;
	private final Sprite girlStabbed4;
	private final MyGdxGame myGdxGame;
	private final Sprite human1;
	private final Sprite human2;
	private final Music iCutMyself;
	private boolean paused = true;
	private final Music dadFindsHer;
	private final Music iHavePaintedTheStone;
	private final Sound knifeDrop;
	private boolean alreadyPlayed = true;
	private boolean knifeDropPlayed;
	private AssetManager assetManager;

	public CutsceneModernTimes(MyGdxGame myGdxGame) {
		this.myGdxGame = myGdxGame;
		batch = myGdxGame.batch;
		sr = myGdxGame.sr;
		viewport = myGdxGame.viewport;
		camera = myGdxGame.camera;
		font = myGdxGame.font;

		bg = new Sprite(new Texture("sprites/curscene1bg.png"));
		girl = new Sprite(new Texture("sprites/girl.png"));
		knife = new Sprite(new Texture("sprites/knife.png"));
		human1 = new Sprite(new Texture("sprites/human1.png"));
		human2 = new Sprite(new Texture("sprites/human2.png"));
		girlStabbed1 = new Sprite(new Texture("sprites/girl_stabbed1.png"));
		girlStabbed2 = new Sprite(new Texture("sprites/girl_stabbed2.png"));
		girlStabbed3 = new Sprite(new Texture("sprites/girl_stabbed3.png"));
		girlStabbed4 = new Sprite(new Texture("sprites/girl_stabbed4.png"));

		assetManager = new AssetManager();
		assetManager.load("music/1_iCutMyself.mp3", Music.class);
		assetManager.load("music/2_dadFindsHer.mp3", Music.class);
		assetManager.load("music/iHavePaintedTheStone.mp3", Music.class);
		assetManager.load("music/knifeDrop.mp3", Sound.class);

		assetManager.finishLoading();

		iCutMyself = assetManager.get("music/1_iCutMyself.mp3", Music.class);
		dadFindsHer = assetManager.get("music/2_dadFindsHer.mp3", Music.class);
		iHavePaintedTheStone = assetManager.get("music/iHavePaintedTheStone.mp3", Music.class);
		knifeDrop = assetManager.get("music/knifeDrop.mp3", Sound.class);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);

		sr.setColor(Color.BLACK);
		sr.rect(-70, -70, viewport.getWorldWidth(), 70);
		sr.rect(-70, 220, viewport.getWorldWidth(), 70);
		sr.rect(-70, 0, 70, 220);
		sr.rect(500, 0, 70, 220);
		sr.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		if (numTouches == -2) {
			message = "";
			// String msg = "One Room. One Passion. 3 Ages.";
			// font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2,
			// 115);
		}

		if (numTouches == -1)
			message = "My name is Moore.";

		if (numTouches >= -1 && numTouches < 13)
			bg.draw(batch);

		if (numTouches >= -1 && numTouches < 7)
			batch.draw(girl.getTexture(), 250, 20);

		if (numTouches >= 3 && numTouches <= 6)
			batch.draw(knife.getTexture(), 243, 34);
		else if (numTouches == 7)
			batch.draw(girlStabbed1.getTexture(), 250, 20);
		else if (numTouches == 8) {
			batch.draw(girlStabbed2.getTexture(), 250, 20);
			batch.draw(knife.getTexture(), 230, 10);
		} else if (numTouches == 9) {
			batch.draw(girlStabbed3.getTexture(), 245, 0);
			batch.draw(knife.getTexture(), 230, 10);
		} else if (numTouches == 10) {
			batch.draw(girlStabbed4.getTexture(), 245, 0);
			batch.draw(knife.getTexture(), 230, 10);
		} else if (numTouches == 11) {
			batch.draw(girlStabbed4.getTexture(), 245, 0);
			batch.draw(knife.getTexture(), 230, 10);
			batch.draw(human1.getTexture(), 30, 20);
			message = "No!";
		} else if (numTouches == 12) {
			batch.draw(girlStabbed4.getTexture(), 245, 0);
			batch.draw(knife.getTexture(), 230, 10);
			batch.draw(human2.getTexture(), 220, 25);
			message = "NOOOOOOOOOOOOOOOO!!!!!";
		} else if (numTouches == 13) {
			message = "";
			String msg = "I have been cut.";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		} else if (numTouches == 14) {
			String msg = "5 years later.";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		}

		font.draw(batch, message, 265 - Utils.getFontWidth(message, font) / 2, -29);

		batch.end();
	}

	private void update() {
		if (alreadyPlayed && iHavePaintedTheStone.getPosition() >= 1.1) {
			alreadyPlayed = false;
			iCutMyself.play();
		}
		if (numTouches >= 14 && dadFindsHer.getPosition() > 20) {
			dispose();
			myGdxGame.showGameScreen();
		}
		if (paused && iCutMyself.isPlaying()) {
			numTouches++;
			paused = false;
		}
		if (paused)
			return;

		// System.out.println(numFrames);

		if (iCutMyself.getPosition() >= 2.425 && numTouches < 0 || iCutMyself.getPosition() >= 4.73 && numTouches < 1 || iCutMyself.getPosition() >= 7.066 && numTouches < 2 || iCutMyself.getPosition() >= 9.341 && numTouches < 3 || iCutMyself.getPosition() >= 11.826 && numTouches < 4
				|| iCutMyself.getPosition() >= 14.132 && numTouches < 5 || iCutMyself.getPosition() >= 16.407 && numTouches < 6 || dadFindsHer.getPosition() >= 0.052 && numTouches < 7 || dadFindsHer.getPosition() >= 1.781 && numTouches < 8 || dadFindsHer.getPosition() >= 3.584 && numTouches < 9
				|| dadFindsHer.getPosition() >= 5.425 && numTouches < 10 || dadFindsHer.getPosition() >= 8.309 && numTouches < 11 || dadFindsHer.getPosition() >= 10.398 && numTouches < 12 || dadFindsHer.getPosition() >= 12.571 && numTouches < 13
				|| dadFindsHer.getPosition() >= 15.604 && numTouches < 14) {
			numTouches++;
		}

		if (!knifeDropPlayed && dadFindsHer.getPosition() >= 1.66) {
			knifeDrop.play();
			knifeDropPlayed = true;
		}

		if (iCutMyself.getPosition() >= 20.6 && !dadFindsHer.isPlaying()) {
			dadFindsHer.play();
		}

		if (numTouches == 0) {
			message = "Drawing is my passion.";
		}
		if (numTouches == 1) {
			message = "But everyone hates me for that.";
		} else if (numTouches == 2) {
			message = "They all want me to do something else.";
		} else if (numTouches == 3) {
			message = "Nobody understands me.";
		} else if (numTouches == 4) {
			message = "I can't take it anymore.";
		} else if (numTouches == 5) {
			message = "I will end it all now.";
		} else if (numTouches == 6) {
			message = "..";
		} else if (numTouches == 7) {
			message = "";
		} else if (numTouches == 8) {
			message = "";
		}
	}

	@Override
	public void show() {
		super.show();
		iHavePaintedTheStone.play();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		iCutMyself.dispose();
		iHavePaintedTheStone.dispose();
		dadFindsHer.dispose();
		knifeDrop.dispose();
		// assetManager.dispose();
	}

}
