package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameTimer extends AnimationTimer {

    private Scene scene;
    private Stage stage;
    private Canvas canvas = new Canvas(600, 400);
    private GraphicsContext gc;
    private Ship ship1;
    private Ship ship2;
    private double windowWidth;
    private double windowHeight;
    private Menu menu2;
    
    
    
    private Set<KeyCode> activeKeys = new HashSet<>();
    private List<Shot> shots = new ArrayList<>();

    // Cooldown ng mga ships
    private long lastShotTimeShip1 = 0;
    private long lastShotTimeShip2 = 0;

    // Cooldown ng mga shots for normal and special
    private static final long COOLDOWN_TIME = 200_000_000;
    private static final long COOLDOWN_TIME_SPECIAL = 1_000_000_000; // 0.5 seconds

    public GameTimer(Stage stage, Scene scene, Canvas canvas, ImageView ship1ImageView, ImageView ship2ImageView) {
    	this.stage = stage;
        this.scene = scene;
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
        
        if (this.canvas == null || this.gc == null) {
            System.err.println("Canvas or GraphicsContext is null");
            return;
        }
        // Initialize ships with movement speed and damage
        this.ship1 = new Ship(ship1ImageView, 5, 3); // Default speed 10, damage 3
        this.ship2 = new Ship(ship2ImageView, 7, 3); // Default speed 10, damage 3
        
        this.windowWidth = this.canvas.getWidth();
        this.windowHeight = this.canvas.getHeight();

        setInitialPositions();
        handleKeyPressEvent();
    }

    private void setInitialPositions() {
        // Initial positions ng images
        ship1.setPosition(windowWidth / 2 - ship1.getImageView().getImage().getWidth() / 2,
                          windowHeight / 4 - ship1.getImageView().getImage().getHeight() / 2);

        ship2.setPosition(windowWidth / 2 - ship2.getImageView().getImage().getWidth() / 2,
                          3 * windowHeight / 4 - ship2.getImageView().getImage().getHeight() / 2);
    }

    private void handleKeyPressEvent() {
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode())); // Track pressed keys
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode())); // Remove released keys
    }

    private void moveShip(Ship ship, KeyCode up, KeyCode down, KeyCode left, KeyCode right, boolean isTopHalf) {
        double newX = ship.getImageView().getX();
        double newY = ship.getImageView().getY();
        double moveAmount = ship.getMovementSpeed();

        if (activeKeys.contains(up) && newY - moveAmount >= (isTopHalf ? 0 : windowHeight / 2)) 
            newY -= moveAmount;
        if (activeKeys.contains(down) && newY + moveAmount + ship.getImageView().getImage().getHeight() <= (isTopHalf ? windowHeight / 2 : windowHeight)) 
            newY += moveAmount;

        if (activeKeys.contains(left) && newX - moveAmount >= 0) 
            newX -= moveAmount;
        if (activeKeys.contains(right) && newX + moveAmount + ship.getImageView().getImage().getWidth() <= windowWidth) 
            newX += moveAmount;

        ship.setPosition(newX, newY);
    }

    boolean checkCollisionWithImage(Shot shot, ImageView targetImageView) {
        double targetMinX = targetImageView.getX();
        double targetMinY = targetImageView.getY();
        double targetWidth = targetImageView.getImage().getWidth();
        double targetHeight = targetImageView.getImage().getHeight();

        // itong shrink factor feel ko sobrang useful kapag iimplement yung slow down sa movement 
        double hitboxShrinkFactor = 0.6;
        double hitboxWidth = targetWidth * hitboxShrinkFactor;
        double hitboxHeight = targetHeight * hitboxShrinkFactor;

        double hitboxMinX = targetMinX + (targetWidth - hitboxWidth) / 2;
        double hitboxMinY = targetMinY + (targetHeight - hitboxHeight) / 2;
        double hitboxMaxX = hitboxMinX + hitboxWidth;
        double hitboxMaxY = hitboxMinY + hitboxHeight;

        // pangcheck if within sa bounds yung bala
        return shot.posX >= hitboxMinX && shot.posX <= hitboxMaxX &&
               shot.posY >= hitboxMinY && shot.posY <= hitboxMaxY;
    }


    boolean checkCollisionBetweenShots(Shot shot1, Shot shot2) {
        double dx = shot1.posX - shot2.posX;
        double dy = shot1.posY - shot2.posY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < Shot.SIZE;
    }

    @Override
    public void handle(long now) {
    	if (gc == null) {
            System.err.println("GraphicsContext is null during drawing");
            return;
        }
    	gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, windowWidth, windowHeight);

        gc.drawImage(ship1.getImageView().getImage(), ship1.getImageView().getX(), ship1.getImageView().getY());
        gc.drawImage(ship2.getImageView().getImage(), ship2.getImageView().getX(), ship2.getImageView().getY());
        
        drawHealthBar(gc, ship1.health, 10, 10, windowWidth - 20, 20); // Top health bar
        drawHealthBar(gc, ship2.health, 10, windowHeight - 30, windowWidth - 20, 20); // Bottom health bar

        List<Shot> shotsToRemove = new ArrayList<>();

        for (Shot shot : shots) {
            shot.update();
            shot.draw(gc);
            if (shot.playerId.equals("Ship1")) {
                if (checkCollisionWithImage(shot, ship2.getImageView())) {
                    shotsToRemove.add(shot);
                    ship2.health -= shot.damage;
                }
            } else if (shot.playerId.equals("Ship2")) {
                if (checkCollisionWithImage(shot, ship1.getImageView())) {
                    shotsToRemove.add(shot);
                    ship1.health -= shot.damage;
                }
            }
        }

        // Check collisions between shots
        for (int i = 0; i < shots.size(); i++) {
            for (int j = i + 1; j < shots.size(); j++) {
                Shot shot1 = shots.get(i);
                Shot shot2 = shots.get(j);

                if (!shot1.playerId.equals(shot2.playerId) && checkCollisionBetweenShots(shot1, shot2)) {
                    shotsToRemove.add(shot1);
                    shotsToRemove.add(shot2);
                }
            }
        }

        // Remove all shots that collided
        shots.removeAll(shotsToRemove);

        // Paggalaw ng ships
        moveShip(ship1, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, true);
        moveShip(ship2, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, false);

        // shooting cooldowns, may cooldowns depending sa bala
        long currentTime = System.nanoTime(); 

        //Ship 1 Shooting
        if (activeKeys.contains(KeyCode.NUMPAD0) && currentTime - lastShotTimeShip1 >= COOLDOWN_TIME) {
            // Use center of ship1 to calculate shot's starting position
            int startX = (int) (ship1.getX() + ship1.getImage().getWidth() / 2);
            int startY = (int) (ship1.getY() + ship1.getImage().getHeight() / 2);
            shots.add(new Shot(startX, startY, 12, "normal", "Ship1"));
            lastShotTimeShip1 = currentTime; // Update last shot time for Ship1
        } else if (activeKeys.contains(KeyCode.ENTER) && currentTime - lastShotTimeShip1 >= COOLDOWN_TIME_SPECIAL) {
            // Use center of ship1 to calculate shot's starting position
            int startX = (int) (ship1.getX() + ship1.getImage().getWidth() / 2);
            int startY = (int) (ship1.getY() + ship1.getImage().getHeight() / 2);
            shots.add(new Shot(startX, startY, 5, "special", "Ship1"));
            lastShotTimeShip1 = currentTime; // Update last shot time for Ship1
        }

        //Ship 2 Shooting
        if (activeKeys.contains(KeyCode.Q) && currentTime - lastShotTimeShip2 >= COOLDOWN_TIME) {
            int startX = (int) (ship2.getX() + ship2.getImage().getWidth() / 2);
            int startY = (int) (ship2.getY() + ship2.getImage().getHeight() / 2);
            shots.add(new Shot(startX, startY, -12, "normal", "Ship2"));
            lastShotTimeShip2 = currentTime; // Update last shot time for Ship2
        } else if (activeKeys.contains(KeyCode.E) && currentTime - lastShotTimeShip2 >= COOLDOWN_TIME_SPECIAL) {
            int startX = (int) (ship2.getX() + ship2.getImage().getWidth() / 2);
            int startY = (int) (ship2.getY() + ship2.getImage().getHeight() / 2);
            shots.add(new Shot(startX, startY, -5, "special", "Ship2"));
            lastShotTimeShip2 = currentTime; // Update last shot time for Ship2
        }
        
        if(ship1.health <=0 ) {
        	stop();
        	winningScene("Winner: Ship 2");
        }
        
        if(ship2.health <=0 ) {
        	stop();
        	winningScene("Winner: Ship 1");
        }
    }
    
    private void drawHealthBar(GraphicsContext gc, int health, double x, double y, double width, double height) {
        // Draw the background bar
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);

        // Calculate health percentage and draw the health bar
        double healthPercentage = Math.max(0, health) / 100.0;
        gc.setFill(healthPercentage > 0.5 ? Color.GREEN : healthPercentage > 0.2 ? Color.ORANGE : Color.RED);
        gc.fillRect(x, y, width * healthPercentage, height);

        // Draw health text
        gc.setFill(Color.WHITE);
        gc.fillText("Health: " + health + "/100", x + 10, y + height - 5);
    }

    // Nested Shot class inside GameTimer
    public class Shot {
        int posX, posY, speed;
        static final int SIZE = 20;
        Image shotImage;
        String type;
        int damage;
        String playerId;  // mahalaga lang player id para maidentify kanino galing bullet and for collision checking
        double angle;  // para lang maayos rotate ng image since yung isa pababa, and yung other pataas

        public Shot(int posX, int posY, int speed, String type, String playerId) {
            this.posX = posX;
            this.posY = posY;
            this.speed = speed;
            this.type = type;
            this.playerId = playerId; 

            if (type.equals("normal")) {
                this.shotImage = new Image(getClass().getResource("/Assets/BasicBullet.png").toExternalForm());  // Replace with actual image file name
                this.damage = 3;
            } else if (type.equals("special")) {
                this.shotImage = new Image(getClass().getResource("/Assets/SpecialBullet.png").toExternalForm());  // Replace with actual image file name
                this.damage = 10;
            }

            this.angle = 0;
        }

        public void update() {
            posY += speed;

            if (speed > 0) {
                angle = 0;
            } else if (speed < 0) {
                angle = 180;
            }
        }


        public void draw(GraphicsContext gc) {
            gc.save();
            gc.translate(posX, posY);
            gc.rotate(angle);
            gc.drawImage(shotImage, -SIZE / 2, -SIZE / 2, SIZE, SIZE);
            gc.restore();
        }
    }
    
    public void winningScene(String winnerMessage) {
    	Menu menu = new Menu(this.stage, 600, 400);
        StackPane winningScene = new StackPane();
        String contentGameOver = "GAME OVER!";
        String contentWinner = winnerMessage;

        // Text node for "GAME OVER!"
        Text animatedTextGameOver = new Text();
        animatedTextGameOver.setStyle("-fx-font-size: 100px; -fx-fill: white;");
        animatedTextGameOver.setFont(Font.font("Showcard Gothic", 20));
        StackPane.setAlignment(animatedTextGameOver, Pos.TOP_CENTER);
        StackPane.setMargin(animatedTextGameOver, new Insets(100, 0, 0, 0));

        // Text node for "Winner: Player"
        Text animatedTextWinner = new Text();
        animatedTextWinner.setStyle("-fx-font-size: 50px; -fx-fill: white;");
        animatedTextWinner.setFont(Font.font("Showcard Gothic", 20));
        StackPane.setAlignment(animatedTextWinner, Pos.TOP_CENTER);
        StackPane.setMargin(animatedTextWinner, new Insets(200, 0, 0, 0)); 

        // BG
        Image bgImage = new Image(getClass().getResource("winning_scene_bg.gif").toExternalForm());
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1280);
        bgImageView.setFitHeight(720);
        bgImageView.setPreserveRatio(false);
        
        Button goBack = new Button("Go Back to Main Menu");
	    goBack.setPrefWidth(200);
	    goBack.setPrefHeight(50);
	    goBack.setStyle("-fx-background-color: white; -fx-text-fill: #3498db; -fx-font-size: 16px; -fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
	    StackPane.setAlignment(goBack, Pos.BOTTOM_LEFT);
	    StackPane.setMargin(goBack, new Insets(20, 0, 20, 20));
	    goBack.setOnAction(e -> menu.setStage(this.stage));


        // Timeline for "GAME OVER!"
        Timeline timelineGameOver = new Timeline();
        final StringBuilder displayedTextGameOver = new StringBuilder();
        for (int i = 0; i < contentGameOver.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(100 * (index + 1)),
                e -> {
                    displayedTextGameOver.append(contentGameOver.charAt(index));
                    animatedTextGameOver.setText(displayedTextGameOver.toString());
                }
            );
            timelineGameOver.getKeyFrames().add(keyFrame);
        }

        Timeline timelineWinner = new Timeline();
        final StringBuilder displayedTextWinner = new StringBuilder();
        for (int i = 0; i < contentWinner.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(100 * (index + 1)),
                e -> {
                    displayedTextWinner.append(contentWinner.charAt(index));
                    animatedTextWinner.setText(displayedTextWinner.toString());
                }
            );
            timelineWinner.getKeyFrames().add(keyFrame);
        }

        timelineGameOver.setOnFinished(e -> timelineWinner.play());
        timelineGameOver.play();

        winningScene.getChildren().addAll(bgImageView, animatedTextGameOver, animatedTextWinner, goBack);
        Scene scene = new Scene(winningScene, 600, 400);
        stage.setScene(scene);
        this.stage.show();
    }
}
