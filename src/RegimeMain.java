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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.ArrayList;
import java.util.Random;

import java.io.File;


public class RegimeMain extends Application {
        private Random rand = new Random();
        private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private Enemy addEnemy(boolean leftSide) {
            Enemy spawnedEnemy = new Enemy(new Image("file:./resources/images/peasant_sprite_sheet.png"),
                    103, 114, 800, 800, new Point2D(3, 1), 7, leftSide);
            spawnedEnemy.setY(500);
            if (leftSide) {
                spawnedEnemy.setX(0);
            } else {
                spawnedEnemy.setX(1500);
            }
            spawnedEnemy.getHeroView().setScaleX(1.25);
            spawnedEnemy.getHeroView().setScaleY(1.25);
            return spawnedEnemy;
        }
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Back to 1789");

            Group root = new Group();
            Scene scene = new Scene(root, 1600, 800,true);
            primaryStage.setScene(scene);
            primaryStage.show();

            Pane pane = new Pane();
            Image background = new Image("file:./resources/images/game_background.jpg");
            ImageView backgroundView=new ImageView(background);
            backgroundView.setViewport(new Rectangle2D(0,0,1600,800));
            root.getChildren().add(pane);
            pane.getChildren().add(backgroundView);

            Group loseRoot = new Group();
            Scene losingScene = new Scene(loseRoot, 1280,720,true);
            Image loseBackground = new Image("file:./resources/images/lose_screen.jpg");
            ImageView loseBackgroundView=new ImageView(loseBackground);
            loseBackgroundView.setViewport(new Rectangle2D(0,0,1280,800));
            loseRoot.getChildren().add(loseBackgroundView);

            Group winRoot = new Group();
            Scene winScene = new Scene(winRoot, 752,752,true);
            Image winBackground = new Image("file:./resources/images/win_screen.jpg");
            ImageView winBackgroundView=new ImageView(winBackground);
            winBackgroundView.setViewport(new Rectangle2D(0,0,1280,800));
            winRoot.getChildren().add(winBackgroundView);

            Media backgroundMusic = new Media(new File("resources/audio/GAME_INTRO.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setAutoPlay(true);
            MediaView mediaView = new MediaView(mediaPlayer);
            root.getChildren().add(mediaView);

            Image castle = new Image("file:./resources/images/castle.png");
            ImageView castleView = new ImageView(castle);
            pane.getChildren().add(castleView);
            castleView.setX(800 - castle.getWidth() / 2);
            castleView.setY(160);
            castleView.setScaleX(0.85);
            castleView.setScaleY(0.85);

            Hero TR = new Hero(new Image("file:./resources/images/hero_sprite_sheet.png"), 708, 929, 0, 0, new Point2D(3, 1), 14);
            pane.getChildren().add(TR.getHeroView());
            TR.getHeroView().setScaleX(0.25);
            TR.getHeroView().setScaleY(0.25);
            TR.setX(800 - TR.getSpriteColumnSize() / 2);
            TR.setY((int) ((background.getHeight())/10));

            EagleProjectile eagle = new EagleProjectile(new Image("file:./resources/images/eagle.png"), 10);
            pane.getChildren().add(eagle.getEagleView());

            enemies.add(addEnemy(true));
            enemies.add(addEnemy(false));
            pane.getChildren().add(enemies.get(0).getHeroView());
            pane.getChildren().add(enemies.get(1).getHeroView());
            enemies.get(0).walkRight(new Point2D(800, 0));
            enemies.get(1).walkLeft(new Point2D(800, 0));

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long time) {
                    if (eagle.getX() > 1600 || eagle.getX() < -790) {
                        eagle.disable();
                    }
                    TR.updateImageViewInScene(time);
                    eagle.updateImageViewInScene(time);
                    for (Enemy enemy : enemies) {
                        enemy.updateImageViewInScene(time);
                    }
                    System.out.println(time / (1000000000 * 10));
                }
            };

            timer.start();

            // summoning eagle with spacebar
            backgroundView.requestFocus();
            backgroundView.setOnKeyPressed(ev -> {
                if (ev.getCode() == KeyCode.SPACE) {
                    System.out.println("EAGLE!");
                    if (TR.getCurrentFrame().getY() == 0) {
                        eagle.enable(((int) TR.getCurrentFrame().getY()) == 0, (int) (TR.getHeroView().getX()),
                                ((int) ((background.getHeight())/10)));
                    } else {
                        eagle.enable(((int) TR.getCurrentFrame().getY()) == 0, (int) (TR.getHeroView().getX()),
                                ((int) ((background.getHeight())/10)));
                    }
                }
            });

            // commanding TR around the map
            backgroundView.setOnMouseClicked(mouseEvent -> {
                System.out.println("MARCHE!!!");
                if (mouseEvent.getX() < TR.getHeroView().getX() + (TR.getSpriteColumnSize() / 2)) {
                    TR.walkLeft(new Point2D(mouseEvent.getX() - (TR.getSpriteColumnSize() / 2), 0));
                } else {
                    TR.walkRight(new Point2D(mouseEvent.getX() - (TR.getSpriteColumnSize() / 2), 0));
                }
                System.out.println(mouseEvent.getX());
                System.out.println(mouseEvent.getY());
            });
        }

        public static void main(String[] args) {
            launch(args);
        }
}
