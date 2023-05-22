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
    private ImageView heroView;
    private int spriteColumnSize;
    private int spriteRowSize;
    private int x;
    private int y;

    private Point2D startingSprite;

    private Point2D currentFrame;

    private boolean isWalkingLeft = false;
    private boolean isWalkingRight = false;
    private Point2D walkDest = new Point2D(0, 0);
    private int frames;
    private boolean animate = false;

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
        if (isWalkingLeft) {
            x -= 1;
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
            x += 1;
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
