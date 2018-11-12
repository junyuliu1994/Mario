import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MarioGameView extends Application implements Observer{
	
	Image background = new Image("resources/start_background.png");
	Font font = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),13);
	int curr = 1;

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	Canvas canvas = new Canvas(856, 550);
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	Pane pane = new Pane();
    	pane.getChildren().add(canvas);

    	// set event handler
        Scene scene = new Scene(pane);
		StartMenu(scene, gc);


		stage.setScene(scene);

        stage.setTitle("Mario Game");
        stage.setResizable(false);

     
        stage.show();
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void reDrawStart(GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		gc.drawImage(background, 0,0);

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
		gc.fillText("About",350, 410);
		gc.setFill(Color.BLACK);
		if(curr == 1){
			gc.fillText("New Game",350, 350);
		}else if(curr == 2){
			gc.fillText("Load Game",348, 380);
		}else{
			gc.fillText("About" ,350, 410);
		}
	}

	private void StartMenu(Scene scene, GraphicsContext gc){
		reDrawStart(gc);
		scene.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.UP){
				if(curr > 1){
					curr --;
					reDrawStart(gc);
				}
			}else if(event.getCode() == KeyCode.DOWN){
				if(curr < 3){
					curr ++;
					reDrawStart(gc);
				}
				// set up entry
			}else if(event.getCode() == KeyCode.ENTER){
				if(curr == 1){
					MainGame(scene, gc);
				}else if(curr == 2){
					LoadGame(scene, gc);
				}else{
					aboutThisGame(scene, gc);
				}
			}
		});
	}

	private void MainGame(Scene scene,GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		gc.fillText("WORKING ON IT", 300,250);
		// TODO: do something
		goBackToMainMenu(scene,gc);
	}

	private void LoadGame(Scene scene, GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		gc.fillText("WORKING ON IT", 300,250);
		// TODO: do something
		goBackToMainMenu(scene,gc);
	}

	private void aboutThisGame(Scene scene, GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		gc.fillText("WORKING ON IT", 300,250);
		// TODO: do something
		goBackToMainMenu(scene,gc);
	}

	private void goBackToMainMenu(Scene scene,GraphicsContext gc){
		scene.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ESCAPE){
				StartMenu(scene,gc);
			}
		});
	}

}
