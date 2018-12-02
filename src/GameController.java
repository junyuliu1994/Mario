import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {
	GameModel gameModel;

	public boolean isStart() {
		return gameModel.isStart();
	}

	public void setStart(boolean start) {
		gameModel.setStart(start);
	}
	public GameController(GameModel gameModel){
		this.gameModel = gameModel;
	}

	public Image getBlocks() {
		return gameModel.getBlocks();
	}

	public Background getBackground() {
		return gameModel.getBackground();
	}

	public Mario getMario() {
		return gameModel.getMario();
	}

	public ArrayList<Brick> getBricks() {
		return gameModel.getBricks();
	}

	public ArrayList<Coin> getCoins() {
		return gameModel.getCoins();
	}
	
	public ArrayList<Monster> getMonsters(){
		return gameModel.getMonsters();
	}

	public ArrayList<Mushroom> getMushrooms() {
		return gameModel.getMushrooms();
	}

	public Image getWxzImage() {
		return gameModel.getWxzImage();
	}

	public Image getWxzConvertImage() {
		return gameModel.getWxzConvertImage();
	}

	public ArrayList<Bullet> getBullets() {
		return gameModel.getBullets();
	}

	public ArrayList<BlackCircle> getBlackCircles() { return gameModel.getBlackCircles(); }

	public ArrayList<Firework> getFireworks() { return gameModel.getFireworks(); }

	public ArrayList<Information> getInformations() { return gameModel.getInformations(); }

	public void restoreModel(GameModel model, GraphicsContext gc, Canvas canvas){
		gameModel = model;
		gameModel.restore(gc, canvas);
	}
}