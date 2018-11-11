import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MarioGameView extends Application implements Observer{
	
	
	Image i = new Image("file:marioRight2Lvl0.png", false);
	Image backgroud = new Image("resources/background.png");
	Font font = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),13);
	int curr = 1;

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	Canvas canvas = new Canvas(856, 550);
    	GraphicsContext gc = canvas.getGraphicsContext2D();
		startMenu(gc);
    	Pane pane = new Pane();
    	pane.getChildren().add(canvas);
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
        	 if (event.getCode() == KeyCode.RIGHT) {
        		 new AnimationTimer() {
        			 public void handle(long currentNanoTime) {
		        		i = new Image("file:marioRight3Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720);
		        	    gc.drawImage(i, 640, 360);

		        	    i = new Image("file:marioRight0Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720);
		        	    gc.drawImage(i, 640, 360);

		        	    i = new Image("file:marioRight1Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720);
		        	    gc.drawImage(i, 640, 360);
		        	    System.out.println("QWE");
        			 }
        		 }.start();

        	 } else if (event.getCode() == KeyCode.LEFT) {

        	 }
        });

//        scene.setOnKeyReleased(event -> {
//        	i = new Image("file:marioRight2Lvl0.png", false);
//    		gc.clearRect(0, 0, 1280, 720);
//    	    gc.drawImage(i, 640, 360);
//        });

		scene.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.UP){
				if(curr > 1){
					curr --;
					startMenu(gc);
				}
			}else if(event.getCode() == KeyCode.DOWN){
				if(curr < 3){
					curr ++;
					startMenu(gc);
				}
			}
		});
        stage.setScene(scene);
        stage.setTitle("Mario Game");

        AnimationTimer timer = new AnimationTimer() {
        	public void handle(long now) {

        	}
        };
        timer.start();
     
        stage.show();
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void startMenu(GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		gc.drawImage(backgroud, 0,0);

		// load main GUI
		Image title = new Image("resources/title.png");
		gc.drawImage(title,200,50);
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.fillText("\uD83C\uDD2F8102 NINJIGOKU",445,285);
		//gc.drawImage(i, 640, 360);

		Font newfont = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),20);
		gc.setFont(newfont);
		gc.fillText("New Game",350, 350);
		gc.fillText("Load Game",348, 380);
		gc.fillText("Setting",350, 410);
		gc.setFill(Color.BLACK);
		if(curr == 1){
			gc.fillText("New Game",350, 350);
		}else if(curr == 2){
			gc.fillText("Load Game",348, 380);
		}else{
			gc.fillText("Setting",350, 410);
		}
	}

}
