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


public class RegimeMain extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Back to 1789");
            Group root = new Group();
            Group gameRoot = new Group();
            Scene scene = new Scene(root, 1600, 800,true);
            Scene nextScene = new Scene(gameRoot, 1600,800,true);
            primaryStage.setScene(scene);
            primaryStage.show();

            Pane pane = new Pane();
            Image background = new Image("file:./resources/images/game_background-transformed.jpeg");
            ImageView backgroundView=new ImageView(background);
            backgroundView.setViewport(new Rectangle2D(0,0,1600,800));
            root.getChildren().add(pane);
            pane.getChildren().add(backgroundView);

            Image FDR = new Image("file:./resources/images/hero_right_walk.png");
            ImageView heroView = new ImageView(FDR);
            heroView.setViewport(new Rectangle2D(2124,0,708,929));
            pane.getChildren().add(heroView);
            heroView.setScaleX(0.25);
            heroView.setScaleY(0.25);
            heroView.setX(800);
            heroView.setY((background.getHeight())/10);

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
