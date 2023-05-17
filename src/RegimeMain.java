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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
            Image background = new Image("file:./resources/images/game_background.jpg");
            ImageView backgroundView=new ImageView(background);
            backgroundView.setViewport(new Rectangle2D(0,0,1600,800));
            root.getChildren().add(pane);
            pane.getChildren().add(backgroundView);

            Image castle = new Image("file:./resources/images/castle.png");
            ImageView castleView = new ImageView(castle);
            pane.getChildren().add(castleView);
            castleView.setX(800 - castle.getWidth() / 2);
            castleView.setY(160);
            castleView.setScaleX(0.85);
            castleView.setScaleY(0.85);

            Hero FDR = new Hero(new Image("file:./resources/images/hero_sprite_sheet.png"), 708, 929, 0, 0, new Point2D(3, 1), 14);
            pane.getChildren().add(FDR.getHeroView());
            FDR.getHeroView().setScaleX(0.25);
            FDR.getHeroView().setScaleY(0.25);
            FDR.setX(800 - FDR.getSpriteColumnSize() / 2);
            FDR.setY((int) ((background.getHeight())/10));

            EagleProjectile eagle = new EagleProjectile(new Image("file:./resources/images/eagle.png"), 10);
            pane.getChildren().add(eagle.getEagleView());
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long time) {
                    FDR.updateImageViewInScene(time);
                    eagle.updateImageViewInScene(time);
                }
            };

            timer.start();

            backgroundView.requestFocus();
            backgroundView.setOnKeyPressed(ev -> {
                if (ev.getCode() == KeyCode.SPACE) {
                    System.out.println("EAGLE!");
                    if (FDR.getCurrentFrame().getY() == 0) {
                        eagle.enable(((int) FDR.getCurrentFrame().getY()) == 0, (int) (FDR.getHeroView().getX()),
                                ((int) ((background.getHeight())/6)));
                    } else {
                        eagle.enable(((int) FDR.getCurrentFrame().getY()) == 0, (int) (FDR.getHeroView().getX()),
                                ((int) ((background.getHeight())/6)));
                    }
                }
            });

            backgroundView.setOnMouseClicked(mouseEvent -> {
                System.out.println("MARCHE!!!");
                if (mouseEvent.getX() < FDR.getHeroView().getX() + (FDR.getSpriteColumnSize() / 2)) {
                    FDR.walkLeft(new Point2D(mouseEvent.getX() - (FDR.getSpriteColumnSize() / 2), 0));
                } else {
                    FDR.walkRight(new Point2D(mouseEvent.getX() - (FDR.getSpriteColumnSize() / 2), 0));
                }
                System.out.println(mouseEvent.getX());
                System.out.println(mouseEvent.getY());
            });
        }

        public static void main(String[] args) {
            launch(args);
        }
}
