package application;

import java.io.File;
import java.util.Timer;

import javafx.event.EventHandler;
//importing libraries needed
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;


public class Menu{
	static Stage stage;
	static Scene sceneMenu, sceneInstruc, scenePick ,scenePlay, sceneAbt;
	private GraphicsContext gc;
	private Pane root, root2, root3, root4, root5;
	private Background gameBG, instrucBG, menuBG, pickBG1, pickBG2;
	private Canvas canvas, canvas2, canvas3, canvas4, canvas5;
	private Image buttonAOff, buttonAOn, buttonPOff, buttonPOn, buttonHOff, buttonHOn, buttonQOn, buttonQOff, bg, Instrucbg, Menubg;
	private Image buttonBOff, buttonBOn, buttonNOn, buttonNOff,pickbg1, pickbg2;
	private ImageView imageAVOff, imageAVOn,imagePVOff, imagePVOn, imageHVOff, imageHVOn, imageQVOn, imageQVOff, imageBVOff, imageBVOn, imageNVOff, imageNVOn;
	private Button buttonIns, buttonPlay, buttonAbt, buttonBack, buttonQuit, buttonNext, buttonBack1, buttonBack2, buttonBack3, buttonBack4, buttonNext1, buttonNext2;
	private ImageView ship1, ship2;
	private Line middleLine;
	private GameTimer gameTimer;
	
	public Menu(Stage stage, int WINDOW_WIDTH,int WINDOW_HEIGHT){
		// We set up the structure of the window. We'll use a canvas
		this.stage = stage;
		
		this.bg = new Image(getClass().getResourceAsStream("/Assets/GameBg.gif"));
	        BackgroundImage mainBg = new BackgroundImage(this.bg, BackgroundRepeat.NO_REPEAT,  
	                BackgroundRepeat.NO_REPEAT,  
	                BackgroundPosition.DEFAULT,  
	                BackgroundSize.DEFAULT);
	        
	    this.gameBG = new Background(mainBg);
	    
	    this.Menubg = new Image(getClass().getResourceAsStream("/Assets/MenuBg.gif"));
        BackgroundImage menuBg = new BackgroundImage(this.Menubg, BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                BackgroundSize.DEFAULT);
        
        this.menuBG = new Background(menuBg);
	    
	    this.Instrucbg = new Image(getClass().getResourceAsStream("/Assets/InstrucBg.gif"));
        BackgroundImage insBg = new BackgroundImage(this.Instrucbg, BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                BackgroundSize.DEFAULT);
        
        this.instrucBG = new Background(insBg);
        
        this.pickbg1 = new Image(getClass().getResourceAsStream("/Assets/pickBg_1.gif"));
        BackgroundImage pick1Bg = new BackgroundImage(this.pickbg1, BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                BackgroundSize.DEFAULT);
        
        this.pickBG1 = new Background(pick1Bg);
        
        this.pickbg2 = new Image(getClass().getResourceAsStream("/Assets/pickBg_2.gif"));
        BackgroundImage pick2Bg = new BackgroundImage(this.pickbg2, BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                BackgroundSize.DEFAULT);
        
        this.pickBG2 = new Background(pick2Bg);
		
		this.root = new Pane();
		this.canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.root.getChildren().add(this.canvas);
		this.root.setBackground(menuBG);
		
        //game instruction scene
		this.root2 = new Pane();
        this.canvas2 = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.root2.getChildren().addAll(canvas2);
        this.root2.setBackground(instrucBG);
        
        //game abt scene 
        this.root3 = new Pane();     
        this.canvas3 = new Canvas( WINDOW_WIDTH, WINDOW_HEIGHT );
        this.root3.getChildren().add(this.canvas3);
             
        this.root4 = new Pane();
        this.canvas4 = new Canvas( WINDOW_WIDTH, WINDOW_HEIGHT );
        this.root4.getChildren().add(this.canvas4);
        this.root4.setBackground(pickBG1);
        
        this.root5 = new Pane();
        this.canvas5 = new Canvas( WINDOW_WIDTH, WINDOW_HEIGHT );
        this.root5.getChildren().add(this.canvas5);
        this.root5.setBackground(gameBG);
        
        //buttons and images
        this.buttonAOff = new Image(getClass().getResourceAsStream("/Assets/AbtOff.png"));
        this.buttonAOn = new Image(getClass().getResourceAsStream("/Assets/AbtOn.png"));
        this.buttonPOff = new Image(getClass().getResourceAsStream("/Assets/StartOn.png"));
        this.buttonPOn = new Image(getClass().getResourceAsStream("/Assets/StartOff.png"));
        this.buttonHOff = new Image(getClass().getResourceAsStream("/Assets/HowOff.png"));
        this.buttonHOn = new Image(getClass().getResourceAsStream("/Assets/HowOn.png"));
        this.buttonQOff = new Image(getClass().getResourceAsStream("/Assets/QuitOff.png"));
        this.buttonQOn = new Image(getClass().getResourceAsStream("/Assets/QuitOn.png"));
        this.buttonBOff = new Image(getClass().getResourceAsStream("/Assets/BackOff.png"));
        this.buttonBOn = new Image(getClass().getResourceAsStream("/Assets/BackOn.png"));
        this.buttonNOff = new Image(getClass().getResourceAsStream("/Assets/NextOff.png"));
        this.buttonNOn = new Image(getClass().getResourceAsStream("/Assets/NextOn.png"));
		
		imageAVOff = new ImageView(buttonAOff);
		imageAVOn = new ImageView(buttonAOn);
		imageHVOff = new ImageView(buttonHOff);
		imageHVOn = new ImageView(buttonHOn);
		imagePVOff = new ImageView(buttonPOff);
		imagePVOn = new ImageView(buttonPOn);
		imageQVOff = new ImageView(buttonQOff);
		imageQVOn = new ImageView(buttonQOn);
		imageBVOff = new ImageView(buttonBOff);
		imageBVOn = new ImageView(buttonBOn);
		imageNVOff = new ImageView(buttonNOff);
		imageNVOn = new ImageView(buttonNOn);
		
		
      	this.buttonIns = new Button();
      	this.buttonPlay = new Button();
      	this.buttonAbt = new Button();
      	this.buttonBack1 = new Button();
      	this.buttonBack2 = new Button();
      	this.buttonBack3 = new Button();
      	this.buttonBack4 = new Button();
      	this.buttonQuit = new Button();
      	this.buttonNext1 = new Button();
      	this.buttonNext2 = new Button();
      	
      	buttonIns.setGraphic(imageHVOff);
      	buttonIns.setBackground(null);
		
      	buttonPlay.setGraphic(imagePVOff);
      	buttonPlay.setBackground(null);
		
      	buttonAbt.setGraphic(imageAVOff);
      	buttonAbt.setBackground(null);
      	
      	buttonQuit.setGraphic(imageQVOff);
      	buttonQuit.setBackground(null);
      	
      	buttonBack1.setGraphic(imageBVOff);
      	buttonBack2.setGraphic(imageBVOff);
      	buttonBack3.setGraphic(imageBVOff);
      	buttonBack4.setGraphic(imageBVOff);
      	buttonBack1.setBackground(null);
      	buttonBack2.setBackground(null);
      	buttonBack3.setBackground(null);
      	buttonBack4.setBackground(null);
      	
      	buttonNext1.setGraphic(imageNVOff);
      	buttonNext2.setGraphic(imageNVOff);
      	buttonNext1.setBackground(null);
      	buttonNext2.setBackground(null);

		//set the location of the buttons
		
		this.buttonAbt.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("About dev hover");
				buttonAbt.setGraphic(imageAVOn);
			}
		});
		
		this.buttonAbt.setOnMouseExited(e -> buttonAbt.setGraphic(imageAVOff));
		
		this.buttonPlay.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Play hover");
				buttonPlay.setGraphic(imagePVOn);
			}
		});
		
		this.buttonPlay.setOnMouseExited(e -> buttonPlay.setGraphic(imagePVOff));
		
		this.buttonIns.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Instructions hover");
				buttonIns.setGraphic(imageHVOn);
			}
		});
		
		this.buttonIns.setOnMouseExited(e -> buttonIns.setGraphic(imageHVOff));
		
		this.buttonQuit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Quit hover");
				buttonQuit.setGraphic(imageQVOn);
			}
		});
		
		this.buttonQuit.setOnMouseExited(e -> buttonQuit.setGraphic(imageQVOff));
		
		this.buttonBack1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Back hover");
				buttonBack1.setGraphic(imageBVOn);
			}
		});
		
		this.buttonBack1.setOnMouseExited(e -> buttonBack1.setGraphic(imageBVOff));
		
		this.buttonNext1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Next hover");
				buttonNext1.setGraphic(imageNVOn);
			}
		});
		
		this.buttonNext1.setOnMouseExited(e -> buttonNext1.setGraphic(imageNVOff));
		
		buttonIns.setOnAction(e -> stage.setScene(this.sceneInstruc));
		buttonPlay.setOnAction(e -> stage.setScene(this.scenePick));
		this.sceneAbt = Developers.getDevelopersScene(stage);
		buttonAbt.setOnAction(e -> stage.setScene(this.sceneAbt));
		buttonBack1.setOnAction(e -> stage.setScene(this.sceneMenu));
		buttonBack2.setOnAction(e -> stage.setScene(this.sceneMenu));
		buttonBack3.setOnAction(e -> stage.setScene(this.sceneMenu));
		buttonBack4.setOnAction(e -> stage.setScene(this.sceneMenu));
		buttonNext1.setOnAction(e -> root4.setBackground(pickBG2));
		buttonNext1.setOnAction(e -> stage.setScene(scenePlay));
		
		this.root.getChildren().addAll(buttonAbt, buttonPlay, buttonIns, buttonQuit);
		this.root2.getChildren().add(buttonBack1);
		this.root3.getChildren().add(buttonBack2);
		this.root4.getChildren().addAll(buttonBack3, buttonNext1);
		
		buttonIns.relocate(338, 250);
		buttonAbt.relocate(200, 250);
		buttonPlay.relocate(270,225);
		buttonQuit.relocate(270,280);
		buttonBack1.relocate(270,300);
		buttonBack2.relocate(270,300);
		buttonBack3.relocate(100,70);
		
		this.sceneMenu = new Scene(root);
		this.sceneInstruc = new Scene(root2);
		this.sceneAbt = new Scene(root3);
		this.scenePick = new Scene(root4);
		this.scenePlay = new Scene(root5);
		
		//ahhhhhhhhhhhhhhhhhhhhhhhhhhh
//		Image imageship1 = new Image("firstship.png");
//        Image imageship2 = new Image("secondship.png");
//
//        this.ship1 = new ImageView(imageship1);
//        this.ship1.setX(WINDOW_WIDTH / 2 - imageship1.getWidth() / 2);
//        this.ship1.setY(WINDOW_HEIGHT / 4 - imageship1.getHeight() / 2);
//
//        this.ship2 = new ImageView(imageship2);
//        this.ship2.setX(WINDOW_WIDTH / 2 - imageship2.getWidth() / 2);
//        this.ship2.setY(3 * WINDOW_HEIGHT / 4 - imageship2.getHeight() / 2);
//
//        this.middleLine = new Line(0, WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
//        this.middleLine.setStroke(Color.BLACK);
//        this.middleLine.setStrokeWidth(2);
//
//        var root5 = new javafx.scene.Group(canvas, middleLine, ship1, ship2);
//        this.scenePlay = new Scene(root5, WINDOW_WIDTH, WINDOW_HEIGHT);
   
      //ahhhhhhhhhhhhhhhhhhhhhhhhhhh	
	}
	
	public void setStage(Stage stage) {
		//add/append the buttons into the canvas and root of the scenes
		
		stage.setScene(sceneMenu);
		stage.show();
		
		//only start timer once main scene is instantiated
		stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
			if(newScene == this.scenePlay) {
				startGameTimer();
				
			}
		});
	}
	
	private void startGameTimer() {
	    // Only initialize once
	    if (this.gc == null) {
	        // Initialize the canvas and game timer when transitioning to the game scene
	        this.canvas = new Canvas(600, 400);  // Set your canvas size
	        this.gc = canvas.getGraphicsContext2D();

	        // Load images for the ships
	        Image imageship1 = new Image("firstship.png");
	        Image imageship2 = new Image("secondship.png");

	        this.ship1 = new ImageView(imageship1);
	        this.ship1.setX(600 / 2 - imageship1.getWidth() / 2);
	        this.ship1.setY(400 / 4 - imageship1.getHeight() / 2);

	        this.ship2 = new ImageView(imageship2);
	        this.ship2.setX(600 / 2 - imageship2.getWidth() / 2);
	        this.ship2.setY(3 * 400 / 4 - imageship2.getHeight() / 2);

	        // Create the scenePlay with the canvas and ship images
	        Group root = new Group(canvas, ship1, ship2);
	        this.scenePlay = new Scene(root, 600, 400);

	        // Create and start the game timer, passing the stage to GameTimer
	        gameTimer = new GameTimer(stage, this.scenePlay, this.canvas, this.ship1, this.ship2); // Pass stage here
	        gameTimer.start();
	    }

	    // Set the scene to scenePlay when starting the game (do this once, not repeatedly)
	    stage.setScene(scenePlay);
	}



}
