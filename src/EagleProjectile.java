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

public class EagleProjectile {
    private ImageView eagleView;
    private int x;
    private int y;
    private int damage;

    private boolean flyLeft;
    private boolean disabled;
    public EagleProjectile(Image eagleImage, int damage) {
        this.eagleView = new ImageView(eagleImage);
        this.damage = damage;
        this.disabled = true;
    }

    public void updateImageViewInScene(long time) {
        if (disabled) {
            this.eagleView.setVisible(false);
            this.eagleView.setX(0);
            this.eagleView.setY(0);
        } else {
            this.eagleView.setY(y);
            this.eagleView.setVisible(true);
            if (flyLeft) {
                this.eagleView.setScaleX(1);
                x -= 2;
                this.eagleView.setX(x);
            } else {
                this.eagleView.setScaleX(-1);
                x += 2;
                this.eagleView.setX(x);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void enable(boolean flyLeft, int x, int y) {
        this.flyLeft = flyLeft;
        this.disabled = false;
        this.x = x;
        this.y = y;
    }

    public void disable() {
        this.disabled = true;
    }

    public ImageView getEagleView() {
        return this.eagleView;
    }
}
