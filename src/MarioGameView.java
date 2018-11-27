import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class MarioGameView extends Application implements Observer{
	private GameModel gameModel= new GameModel();
	private GameController gameController = new GameController(gameModel);
	Canvas canvasForMario = gameModel.getCanvasForMario();
	GraphicsContext gcForMario = gameModel.getGcForMario();
	Image background = new Image("resources/start_background.png");
	Font font = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),13);
	int curr = 1;

	public static void main(String[] args) {
		launch(args);
	}


	public void start(Stage primaryStage) {

		Group root = new Group();
		root.getChildren().add(canvasForMario);
		Scene scene = new Scene(root);
		StartMenu(scene, gcForMario);
		primaryStage.getIcons().add(new Image("resources/icon.png"));
		primaryStage.setResizable(false);
		//initGame(scene);
		primaryStage.setTitle("Mario Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	private void initContent() {
		gcForMario.drawImage(gameController.getBackground().getImage(), // the image to be drawn or null.
				gameController.getBackground().getOffset_x(), // the source rectangle's X coordinate position.
				gameController.getBackground().getOffset_y(), // the source rectangle's Y coordinate position.
				gameController.getBackground().getWidth(), // the source rectangle's width.
				gameController.getBackground().getHeight(), // the source rectangle's height.
				gameController.getBackground().getX(), // the destination rectangle's X coordinate position.
				gameController.getBackground().getY(), // the destination rectangle's Y coordinate position.
				gameController.getBackground().getWidth(), // the destination rectangle's width.
				gameController.getBackground().getHeight()); // the destination rectangle's height.


		gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
				gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
				gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the source rectangle's width.
				gameController.getMario().getHeight(), // the source rectangle's height.
				gameController.getMario().getX(), // the destination rectangle's X coordinate position.
				gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the destination rectangle's width.
				gameController.getMario().getWidth()); // the destination rectangle's height.

		for (int i = 0; i < LevelData.LEVEL1.length; i++) {
			String line = LevelData.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						break;
					case '1':
						Brick brick = new Brick(gameController.getBlocks(), 40, 40, j*40, i*40);
						gameController.getBricks().add(brick);
						int index = gameController.getBricks().size() -1;
						gcForMario.drawImage(gameController.getBricks().get(index).getImage(), // the image to be drawn or null.
								gameController.getBricks().get(index).getOffset_x(), // the source rectangle's X coordinate position.
								gameController.getBricks().get(index).getOffset_y(), // the source rectangle's Y coordinate position.
								gameController.getBricks().get(index).getWidth(), // the source rectangle's width.
								gameController.getBricks().get(index).getHeight(), // the source rectangle's height.
								gameController.getBricks().get(index).getX(), // the destination rectangle's X coordinate position.
								gameController.getBricks().get(index).getY(), // the destination rectangle's Y coordinate position.
								gameController.getBricks().get(index).getWidth(), // the destination rectangle's width.
								gameController.getBricks().get(index).getHeight()); // the destination rectangle's height.
						break;
					case '2':
						Wall wall = new Wall(gameModel.getBlocks(),40,40,j*40,i*40);
//                        Coin coin = new Coin( 3, 3, 946, 40, 40,40,j*40,i*40);

//                   

//                        System.out.println("file:coin's location:"+j*40+" ,"+i*40);
						gameController.getBricks().add(wall);

                   
						gcForMario.drawImage(wall.getImage(),
								wall.getOffset_x(), wall.getOffset_y(),
								wall.getWidth(), wall.getHeight(),
								wall.getX(),wall.getY(), wall.getWidth(), wall.getHeight()
						);
						break;
                    case '3':
                        QuestionBrick questionBrick = new QuestionBrick(gameModel.getBlocks(),40,40,j*40,i*40);
//                        Coin coin = new Coin( 3, 3, 946, 40, 40,40,j*40,i*40);
//                        System.out.println("file:coin's location:"+j*40+" ,"+i*40);
                        gameController.getBricks().add(questionBrick);
//
                        gcForMario.drawImage(questionBrick.getImage(),
								questionBrick.getOffset_x(), questionBrick.getOffset_y(),
								questionBrick.getWidth(), questionBrick.getHeight(),
								questionBrick.getX(),questionBrick.getY(), questionBrick.getWidth(), questionBrick.getHeight()
                        );
                        break;
                    case '4':
                    	Goomba goomba = new Goomba(0,40,j*40, i*40);
                    	gameController.getGoombas().add(goomba);
                        //System.out.println("file:goomba's location:"+ j*40+" ,"+i*40);
                    	
                    	int index2 = gameController.getGoombas().size() -1;
                    	
                    	/*
                    	System.out.print(gameController.getGoombas().get(index2).getOffset_x() + " " + 
    							gameController.getGoombas().get(index2).getOffset_y() + " " + 	gameController.getGoombas().get(index2).getWidth() 
    							+ " " + gameController.getGoombas().get(index2).getHeight() );
                    	System.out.println(gameController.getGoombas().get(index2).getX() + " " +  gameController.getGoombas().get(index2).getX());
                    	*/
                    	gcForMario.drawImage(gameController.getGoombas().get(index2).getImage(), // the image to be drawn or null.
    							gameController.getGoombas().get(index2).getOffset_x(), // the source rectangle's X coordinate position.
    							gameController.getGoombas().get(index2).getOffset_y(), // the source rectangle's Y coordinate position.
    							gameController.getGoombas().get(index2).getWidth(), // the source rectangle's width.
    							gameController.getGoombas().get(index2).getHeight(), // the source rectangle's height.
    							gameController.getGoombas().get(index2).getX(), // the destination rectangle's X coordinate position.
    							gameController.getGoombas().get(index2).getY(), // the destination rectangle's Y coordinate position.
    							gameController.getGoombas().get(index2).getWidth(), // the destination rectangle's width.
    							gameController.getGoombas().get(index2).getHeight());
                    case '5':
                        Coin coin = new Coin(gameModel.getBlocks(), j * 40, i * 40);
                        gameModel.getCoins().add(coin);
                        gcForMario.drawImage(coin.getImage(), coin.getOffset_x(), coin.getOffset_y(), coin.getWidth(),
                                coin.getHeight(), coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight());
                        break;
                    default:

                   
                  
    			}
    		}
    	}  
    }


	@Override
	public void update(Observable o, Object arg1) {
		GameModel model = (GameModel)o;
		gcForMario.clearRect(0, 0, 1000, 480);
		reDrawExceptionMario();
		reDrawMario();
	}

	private void reDrawMario() {
		gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
				gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
				gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the source rectangle's width.
				gameController.getMario().getHeight(), // the source rectangle's height.
				gameController.getMario().getX(), // the destination rectangle's X coordinate position.
				gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the destination rectangle's width.
				gameController.getMario().getHeight()); // the destination rectangle's height.

	}

	private void reDrawExceptionMario() {
		gcForMario.drawImage(gameController.getBackground().getImage(), // the image to be drawn or null.
				gameController.getBackground().getOffset_x(), // the source rectangle's X coordinate position.
				gameController.getBackground().getOffset_y(), // the source rectangle's Y coordinate position.
				gameController.getBackground().getWidth(), // the source rectangle's width.
				gameController.getBackground().getHeight(), // the source rectangle's height.
				gameController.getBackground().getX(), // the destination rectangle's X coordinate position.
				gameController.getBackground().getY(), // the destination rectangle's Y coordinate position.
				gameController.getBackground().getWidth(), // the destination rectangle's width.
				gameController.getBackground().getHeight()); // the destination rectangle's height.

        // draw bricks
		for (int i = 0; i < gameController.getBricks().size(); i++) {
		    if (gameController.getBricks().get(i) == null){
		        continue;
            }
			gcForMario.drawImage(gameController.getBricks().get(i).getImage(), // the image to be drawn or null.
					gameController.getBricks().get(i).getOffset_x(), // the source rectangle's X coordinate position.
					gameController.getBricks().get(i).getOffset_y(), // the source rectangle's Y coordinate position.
					gameController.getBricks().get(i).getWidth(), // the source rectangle's width.
					gameController.getBricks().get(i).getHeight(), // the source rectangle's height.
					gameController.getBricks().get(i).getX(), // the destination rectangle's X coordinate position.
					gameController.getBricks().get(i).getY(), // the destination rectangle's Y coordinate position.
					gameController.getBricks().get(i).getWidth(), // the destination rectangle's width.
					gameController.getBricks().get(i).getHeight()); // the destination rectangle's height.
		}

        // draw coins
		for (Coin coin : gameController.getCoins()) {
		    if (coin == null) continue;
			gcForMario.drawImage(coin.getImage(),
					coin.getOffset_x(), coin.getOffset_y(),
					coin.getWidth(), coin.getHeight(),
					coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight()
			);
		}

        // draw mushrooms
        for (Mushroom mushroom : gameController.getMushrooms()) {
            if (mushroom != null) {
                gcForMario.drawImage(mushroom.getImage(),
                        mushroom.getOffset_x(), mushroom.getOffset_y(),
                        mushroom.getWidth(), mushroom.getHeight(),
                        mushroom.getX(), mushroom.getY(), mushroom.getWidth(), mushroom.getHeight()
                );
            }

        }

        // draw goombas
		for(Goomba goomba: gameController.getGoombas()) {
			gcForMario.drawImage( goomba.getImage(), goomba.getOffset_x(), goomba.getOffset_y(),
					goomba.getWidth(), goomba.getHeight(),
					goomba.getX(), goomba.getY(), goomba.getWidth(), goomba.getHeight());
		}

		// draw fireworks
        for (Firework firework: gameController.getFireworks()) {
            gcForMario.drawImage( firework.getImage(), firework.getOffsetX(), firework.getOffsetY(),
                    firework.getWidth(), firework.getHeight(),
                    firework.getX(), firework.getY(), 40, 40);
        }

		// draw scores

        // draw black circles
        for (BlackCircle blackCircle: gameController.getBlackCircles()) {
            gcForMario.setLineWidth(21);
            gcForMario.setFill(Color.BLACK);
            if (!blackCircle.isFill()) {
                gcForMario.strokeOval(blackCircle.getX(), blackCircle.getY(),
                        blackCircle.getWidth(), blackCircle.getHeight());
            } else {
                gcForMario.fillOval(blackCircle.getX(), blackCircle.getY(),
                        blackCircle.getWidth(), blackCircle.getHeight());
            }
        }

        // draw information
        for (Information information: gameController.getInformations()) {
            gcForMario.setFill(information.getColor());
            gcForMario.setFont(information.getFont());
            gcForMario.fillText(information.getText(), information.getX(), information.getY());
        }

	}
	
	public void initGame(Scene scene){
		gameModel.start();
		gameModel.addObserver(this);


		scene.setOnKeyPressed(event -> {
		    if (!gameController.isOutOfControl()) {
                if (event.getCode().toString().equals("D")) {
                    gameController.setStart(true);
                    gameController.getMario().setSpeed(2);
                    gameController.getMario().setRight(true);

                } else if (event.getCode().toString().equals("A")) {
                    gameController.setStart(true);
                    gameController.getMario().setSpeed(-2);
                    gameController.getMario().setLeft(true);
                } else if (event.getCode().toString().equals("W")) {
                    gameController.setStart(true);
                    gameController.getMario().setJump(true);
                }
            }
		});

		scene.setOnKeyReleased(event -> {
            if (!gameController.isOutOfControl()) {
                if (gameController.getMario().getMarioAnimation() != null) {
                    if (event.getCode().toString().equals("D")) {
                        gameController.getMario().setRight(false);
                        gameController.getMario().setSpeed(0);
                    }

                    if (event.getCode().toString().equals("A")) {
                        gameController.getMario().setLeft(false);
                        gameController.getMario().setSpeed(0);
                    }
                }
            }
		});

		initContent();
	}

	private void MainGame(Scene scene,GraphicsContext gc){
		gc.clearRect(0,0,856,550);
		initGame(scene);
		//goBackToMainMenu(scene,gc);
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

	public void reDrawStart(GraphicsContext gc){
		gc.clearRect(0,0,1000,480);
		gc.drawImage(background, 0,0);

		// load main GUI
		Image title = new Image("resources/title.png");
		gc.drawImage(title,275,40);
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.fillText("\uD83C\uDD2F8102 NINJIGOKU",520,275);
		//gc.drawImage(i, 640, 360);

		Font newfont = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),20);
		gc.setFont(newfont);
		gc.fillText("New Game",410, 330);
		gc.fillText("Load Game",408, 360);
		gc.fillText("About",410, 390);
		gc.setFill(Color.BLACK);
		if(curr == 1){
			gc.fillText("New Game",410, 330);
		}else if(curr == 2){
			gc.fillText("Load Game",408, 360);
		}else{
			gc.fillText("About" ,410, 390);
		}
	}

	private void StartMenu(Scene scene, GraphicsContext gc){
		reDrawStart(gc);
		scene.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.UP|| event.getCode() == KeyCode.W){
				if(curr > 1){
					curr --;
					reDrawStart(gc);
				}
			}else if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
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

}