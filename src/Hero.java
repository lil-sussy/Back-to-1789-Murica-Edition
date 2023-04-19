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

    private boolean isWalkingLeft = false;
    private boolean isWalkingRight = false;


    public Hero(Image startingImage, int spriteColumnSize, int spriteRowSize, int x, int y, Point2D startingSprite) {
        this.heroView = new ImageView(startingImage);
        this.spriteColumnSize = spriteColumnSize;
        this.spriteRowSize = spriteRowSize;
        this.heroView.setViewport(new Rectangle2D(startingSprite.getX() * spriteColumnSize, startingSprite.getY() * spriteRowSize, spriteColumnSize, spriteRowSize));
        this.x = x;
        this.y = y;
        this.startingSprite = startingSprite;
    }

    public void updateImageViewInScene(long time) {
        if (isWalkingLeft) {
            x -= 1;
        } else if (isWalkingRight) {
            x += 1;
        }
        this.heroView.setX(x);
    }

    public void walkLeft(Point2D dest) {
        isWalkingLeft = true;
    }

    public void walkRight(Point2D dest) {
        isWalkingRight = true;
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
}
