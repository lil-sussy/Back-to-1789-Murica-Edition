import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class Stage extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long time) {

                }
            };

            timer.start();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
