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

package net.bplaced.therefactory.nomoore;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bplaced.therefactory.nomoore.constants.Configuration;
import net.bplaced.therefactory.nomoore.cutscenes.CutsceneMedieval;
import net.bplaced.therefactory.nomoore.cutscenes.CutsceneModernTimes;
import net.bplaced.therefactory.nomoore.cutscenes.CutsceneStoneAge;
import net.bplaced.therefactory.nomoore.screens.ScreenGame;
import net.bplaced.therefactory.nomoore.screens.ScreenOutro;
import net.bplaced.therefactory.nomoore.utils.IAndroidInterface;

public class MyGdxGame extends Game {

	private IAndroidInterface androidInterface;
	public OrthographicCamera camera;
	public BitmapFont font;
	public FitViewport viewport;
	public SpriteBatch batch;
	public ShapeRenderer sr;
	public Sprite fade;

	private CutsceneMedieval cutsceneMedieval;
	private CutsceneStoneAge cutsceneStoneAge;
	private CutsceneModernTimes cutsceneModernTimes;

	private ScreenGame gameScreen;
	private ScreenOutro outroScreen;

	public MyGdxGame(IAndroidInterface androidInterface) {
		this();
		this.androidInterface = androidInterface;
	}

	public MyGdxGame() {
	}

	@Override
	public void create() {
		if (androidInterface != null)
			androidInterface.tryToStopMusicApp();

		batch = new SpriteBatch();
		sr = new ShapeRenderer();

		camera = new OrthographicCamera();
		viewport = new FitViewport(Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT, camera);
		camera.position.set(viewport.getWorldWidth() / 2 - (viewport.getWorldWidth() - 500) / 2, viewport.getWorldHeight() / 2 - (viewport.getWorldHeight() - 220) / 2, 0);
		camera.update();

		fade = new Sprite(new Texture("sprites/fade.png"));
		fade.setBounds(0, -100, viewport.getWorldWidth(), viewport.getWorldHeight() - 40);

		font = new BitmapFont(Gdx.files.internal("fonts/amiga4everpro2.fnt"));

		showModernTimesCutscene(); // start the game
	}

	private void showModernTimesCutscene() {
		if (cutsceneModernTimes == null)
			cutsceneModernTimes = new CutsceneModernTimes(this);
		setScreen(cutsceneModernTimes);
	}

	public void showGameScreen() {
		if (gameScreen == null)
			gameScreen = new ScreenGame(this);
		setScreen(gameScreen);
	}

	public void showMedievalCutscene() {
		if (cutsceneMedieval == null)
			cutsceneMedieval = new CutsceneMedieval(this);
		setScreen(cutsceneMedieval);
	}

	public void showStoneAgeCutscene() {
		if (cutsceneStoneAge == null)
			cutsceneStoneAge = new CutsceneStoneAge(this);
		if (gameScreen != null)
			gameScreen.cleanUpMainThemeMusic();
		setScreen(cutsceneStoneAge);
	}

	public void showOutroScreen() {
		if (outroScreen == null)
			outroScreen = new ScreenOutro(this);
		if (gameScreen != null)
			gameScreen.cleanUpMainThemeMusic();
		setScreen(outroScreen);
	}

	public void shutdown() {
		if (gameScreen != null)
			gameScreen.dispose();
		if (cutsceneModernTimes != null)
			cutsceneModernTimes.dispose();
		if (cutsceneMedieval != null)
			cutsceneMedieval.dispose();
		if (cutsceneStoneAge != null)
			cutsceneStoneAge.dispose();
		if (outroScreen != null)
			outroScreen.dispose();
		// sr.dispose();
		font.dispose();
		Gdx.app.exit();
	}

	public void setHasShownStoneAgeCutscene(boolean hasShownStoneAgeCutScene) {
		gameScreen.setHastShownStoneAgeCutscene(hasShownStoneAgeCutScene);
	}

}
