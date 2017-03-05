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

package net.bplaced.therefactory.nomoore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bplaced.therefactory.nomoore.MyGdxGame;
import net.bplaced.therefactory.nomoore.utils.Utils;

public class ScreenOutro extends ScreenAdapter {

	private final SpriteBatch batch;
	private final FitViewport viewport;
	private final OrthographicCamera camera;
	private final BitmapFont font;
	private int numTouches;
	private final Music theEnd;
	private final MyGdxGame myGdxGame;
	private AssetManager assetManager;

	public ScreenOutro(MyGdxGame myGdxGame) {
		this.myGdxGame = myGdxGame;
		batch = myGdxGame.batch;
		viewport = myGdxGame.viewport;
		camera = myGdxGame.camera;
		font = myGdxGame.font;

		assetManager = new AssetManager();
		assetManager.load("music/6_theEnd.mp3", Music.class);
		assetManager.finishLoading();
		theEnd = assetManager.get("music/6_theEnd.mp3", Music.class);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		if (numTouches == 0) {
			String msg = "\"Let's talk about that drawing thing...\"";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		} else if (numTouches == 1) {
			String msg = "Crafted with <3 in The Refactory.";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		} else if (numTouches == 2) {
			String msg = "Made in 48 hours for Ludum Dare 37.";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		} else if (numTouches == 3) {
			String msg = "My software is and stays open source, free of charge and ad-free.";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		} else if (numTouches == 4) {
			String msg = "- THE END -";
			font.draw(batch, msg, 250 - Utils.getFontWidth(msg, font) / 2, 115);
		}

		batch.end();
	}

	private void update() {
		if (theEnd.getPosition() >= 5.96 && numTouches < 1 || theEnd.getPosition() >= 9.771 && numTouches < 2 || theEnd.getPosition() >= 13.565 && numTouches < 3 || theEnd.getPosition() >= 17.93 && numTouches < 4) {
			numTouches++;
		}

		if (theEnd.getPosition() >= 26.2) {
			dispose();
			myGdxGame.shutdown();
		}
	}

	@Override
	public void show() {
		super.show();
		theEnd.play();
	}

	@Override
	public void dispose() {
		super.dispose();
		theEnd.dispose();
		// assetManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

}
