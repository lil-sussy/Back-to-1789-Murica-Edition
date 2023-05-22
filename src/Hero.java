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

public class Hero {
    protected ImageView heroView;
    protected int spriteColumnSize;
    protected int spriteRowSize;
    protected int x;
    protected int y;

    protected Point2D startingSprite;

    protected Point2D currentFrame;

    protected boolean isWalkingLeft = false;
    protected boolean isWalkingRight = false;
    protected Point2D walkDest = new Point2D(0, 0);
    protected int frames;
    protected final int animationRate  = 4;
    protected int animationStage = 0;
    protected final int speed = 3;

    public Hero(Image startingImage, int spriteColumnSize, int spriteRowSize, int x, int y, Point2D startingSprite, int frames) {
        this.heroView = new ImageView(startingImage);
        this.spriteColumnSize = spriteColumnSize;
        this.spriteRowSize = spriteRowSize;
        this.heroView.setViewport(new Rectangle2D(startingSprite.getX() * spriteColumnSize, startingSprite.getY() * spriteRowSize, spriteColumnSize, spriteRowSize));
        this.x = x;
        this.y = y;
        this.startingSprite = startingSprite;
        this.currentFrame = startingSprite;
        this.frames = frames;
    }

    public void updateImageViewInScene(long time) {
        if (animationStage % animationRate == 0) {
            if (isWalkingLeft) {
                x -= speed;
                if (currentFrame.getX() <= 0) {
                    currentFrame = new Point2D(frames, 0);
                } else {
                    currentFrame = new Point2D(currentFrame.getX() - 1, 0);
                }
                this.getHeroView().setViewport(new Rectangle2D(currentFrame.getX() * this.getSpriteColumnSize(), 0, this.getSpriteColumnSize(), this.getSpriteRowSize()));
                if (x < walkDest.getX()) {
                    isWalkingLeft = false;
                    this.getHeroView().setViewport(new Rectangle2D(3 * this.getSpriteColumnSize(), 0, this.getSpriteColumnSize(), this.getSpriteRowSize()));
                }
            } else if (isWalkingRight) {
                x += speed;
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

    public void walkLeft(Point2D dest) {
        this.getHeroView().setViewport(new Rectangle2D(3 * this.getSpriteColumnSize(), 0, this.getSpriteColumnSize(), this.getSpriteRowSize()));
        this.isWalkingLeft = true;
        this.isWalkingRight = false;
        this.walkDest = dest;
    }

    public void walkRight(Point2D dest) {
        this.getHeroView().setViewport(new Rectangle2D(3 * this.getSpriteColumnSize(), this.getSpriteRowSize(), this.getSpriteColumnSize(), this.getSpriteRowSize()));
        this.isWalkingRight = true;
        this.isWalkingLeft = false;
        this.walkDest = dest;
    }

    public ImageView getHeroView() {
        return heroView;
    }

    public int getSpriteColumnSize() {
        return spriteColumnSize;
    }

    public int getSpriteRowSize() {
        return spriteRowSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        this.heroView.setX(x);
    }

    public void setY(int y) {
        this.y = y;
        this.heroView.setY(y);
    }

    public Point2D getCurrentFrame() {
        return currentFrame;
    }
}
