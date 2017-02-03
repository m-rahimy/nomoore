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

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import net.bplaced.therefactory.nomoore.utils.IAndroidInterface;

public class AndroidLauncher extends AndroidApplication implements IAndroidInterface {

	public static final String SERVICECMD = "com.android.music.musicservicecommand";
	public static final String CMDNAME = "command";
	public static final String CMDSTOP = "stop";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useWakelock = true;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		initialize(new MyGdxGame(this), config);
	}

	@Override
	public void tryToStopMusicApp() {
		AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		if (mAudioManager.isMusicActive()) {
		    Intent i = new Intent(SERVICECMD);
		    i.putExtra(CMDNAME, CMDSTOP);
		    AndroidLauncher.this.sendBroadcast(i);
		}
	}
}
