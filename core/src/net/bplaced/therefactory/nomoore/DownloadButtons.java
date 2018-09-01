package net.bplaced.therefactory.nomoore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.bplaced.therefactory.nomoore.constants.Configuration;
import net.bplaced.therefactory.nomoore.utils.MathUtils;

public class DownloadButtons {

    private final Sprite spriteDownload;
    private final Sprite spriteStar;
    private final Rectangle rectangleStar;
    private final Rectangle rectangleDownload;
    private float elapsedTime;
    private float opacity;

    public DownloadButtons() {
        spriteStar = new Sprite(new Texture("sprites/yellow_star.png"));
        spriteStar.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        spriteDownload = new Sprite(new Texture("sprites/download.png"));
        spriteDownload.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        rectangleStar = new Rectangle(350, -50, 200, 50);
        rectangleDownload = new Rectangle(rectangleStar.x, rectangleStar.y + rectangleStar.height, 200, 50);

        spriteStar.setPosition(rectangleStar.x, rectangleStar.y);
        spriteDownload.setPosition(rectangleDownload.x, rectangleDownload.y);
    }

    public void debug(ShapeRenderer sr) {
        sr.rect(rectangleStar.x, rectangleStar.y, rectangleStar.width, rectangleStar.height);
        sr.rect(rectangleDownload.x, rectangleDownload.y, rectangleDownload.width, rectangleDownload.height);
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(spriteDownload, spriteDownload.getX(), spriteDownload.getY(), 0, 0,
                spriteDownload.getWidth() / 1.5f, spriteDownload.getHeight() / 1.5f,
                MathUtils.oscilliate(elapsedTime, 0.9f, 1.1f, 1.8f),
                MathUtils.oscilliate(elapsedTime, 0.9f, 1.1f, -1.8f),
                MathUtils.oscilliate(elapsedTime, -2f, 2f, 2f));
        batch.draw(spriteStar, spriteStar.getX(), spriteStar.getY(), 0, 0,
                spriteStar.getWidth() / 1.5f, spriteStar.getHeight() / 1.5f,
                MathUtils.oscilliate(elapsedTime, 0.9f, 1.1f, 1.8f),
                MathUtils.oscilliate(elapsedTime, 0.9f, 1.1f, -1.8f),
                MathUtils.oscilliate(elapsedTime, -2f, 2f, 2f));
        font.draw(batch, "Download Soundtrack", rectangleDownload.x + spriteDownload.getWidth() - 10, rectangleDownload.y + rectangleDownload.height / 2 + 5);
        font.draw(batch, "Rate the Game", rectangleStar.x + spriteStar.getWidth() - 10, rectangleStar.y + rectangleStar.height / 2 + 5);
    }

    public void touchDown(Vector2 unprojected) {
        if (rectangleStar.contains(unprojected)) {
            Gdx.net.openURI(Configuration.GooglePlayStorePageURI);
        } else if (rectangleDownload.contains(unprojected)) {
            Gdx.net.openURI(Configuration.SoundtrackURI);
        }
    }

    public void update(float delta) {
        elapsedTime += delta;
        opacity += 0.004f;
        opacity = Math.min(1, opacity);
    }

    public void render(ShapeRenderer sr) {
        sr.setColor(0, 0, 0,  1-opacity);
        sr.rect(Math.min(rectangleStar.x, rectangleDownload.x), Math.min(rectangleStar.y, rectangleDownload.y), 200, 100);
    }
}
