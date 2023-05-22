import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

public class Enemy extends Hero {
    private boolean startLeft;

    public Enemy(Image startingImage, int spriteColumnSize, int spriteRowSize, int x, int y, Point2D startingSprite, int frames, boolean startLeft) {
        super(startingImage, spriteColumnSize, spriteRowSize, x, y, startingSprite, frames);
        this.startLeft = startLeft;
    }

    public void updateImageViewInScene(long time) {
        if (animationStage % animationRate == 0) {
            if (isWalkingLeft) {
                x -= speed;
                this.heroView.setScaleX(-1);
                if (currentFrame.getX() <= 0) {
                    currentFrame = new Point2D(frames, 0);
                } else {
                    currentFrame = new Point2D(currentFrame.getX() - 1, 0);
                }
                this.getHeroView().setViewport(new Rectangle2D(currentFrame.getX() * this.getSpriteColumnSize(), this.getSpriteRowSize(), this.getSpriteColumnSize(), this.getSpriteRowSize()));
                if (x < walkDest.getX()) {
                    isWalkingLeft = false;
                    this.getHeroView().setViewport(new Rectangle2D(3 * this.getSpriteColumnSize(), this.getSpriteRowSize(), this.getSpriteColumnSize(), this.getSpriteRowSize()));
                }
            } else if (isWalkingRight) {
                x += speed;
                this.heroView.setScaleX(1);
                if (currentFrame.getX() >= frames) {
                    currentFrame = new Point2D(0, 0);
                } else {
                    currentFrame = new Point2D(currentFrame.getX() + 1, 1);
                }
                this.getHeroView().setViewport(new Rectangle2D(currentFrame.getX() * this.getSpriteColumnSize(), this.getSpriteRowSize(), this.getSpriteColumnSize(), this.getSpriteRowSize()));
                if (x > walkDest.getX()) {
                    isWalkingRight = false;
                    this.getHeroView().setViewport(new Rectangle2D(3 * this.getSpriteColumnSize(), this.getSpriteRowSize(), this.getSpriteColumnSize(), this.getSpriteRowSize()));
                }
            }
            this.heroView.setX(x);
            animationStage = 0;
        }
        animationStage += 1;
    }
    public boolean isStartLeft() {
        return startLeft;
    }
}
