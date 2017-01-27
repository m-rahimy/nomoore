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

package net.bplaced.therefactory.nomoore.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Interactable {

    private Sprite sprite;
    private boolean visible = true;
    private boolean consumed = false;
    private boolean consumable = true;
    private final String name;

    public Interactable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPosition(float f, float g) {
        sprite.setPosition(f, g);
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean b) {
        this.consumed = b;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean b) {
        this.consumable = b;
    }

}
