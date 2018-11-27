import java.lang.reflect.Array;
import java.util.ArrayList;

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

	public Image getMarioImage() {
		return gameModel.getMarioImage();
	}

	public void setMarioImage(Image marioImage) {
		gameModel.setMarioImage(marioImage);;
	}

	public Image getMarioConvertImage() {
		return gameModel.getMarioConvertImage();
	}

	public void setMarioConvertImage(Image marioConvertImage) {
		gameModel.setMarioConvertImage(marioConvertImage);
	}

	public Image getBlocks() {
		return gameModel.getBlocks();
	}

	public void setBlocks(Image blocks) {
		gameModel.setBlocks(blocks);;
	}

	public Background getBackground() {
		return gameModel.getBackground();
	}

	public boolean isOutOfControl() {
	    return gameModel.OUT_OF_CONTROL;
    }

	public Mario getMario() {
		return gameModel.getMario();
	}

	public void setMario(Mario mario) {
		gameModel.setMario(mario);
	}

	public ArrayList<Brick> getBricks() {
		return gameModel.getBricks();
	}

	public ArrayList<Coin> getCoins() {
		return gameModel.getCoins();
	}
	
	public ArrayList<Goomba> getGoombas(){
		return gameModel.getGoombas();
	}

	public ArrayList<Mushroom> getMushrooms() {
		return gameModel.getMushrooms();
	}

	public ArrayList<BlackCircle> getBlackCircles() { return gameModel.getBlackCircles(); }

	public ArrayList<Firework> getFireworks() { return gameModel.getFireworks(); }

	public void setMushrooms(ArrayList<Mushroom> mushrooms) {
		gameModel.setMushrooms(mushrooms);
	}

	public Image getItemImage() {
		return gameModel.getItemImage();
	}

	public void setItemImage(Image itemImage) {
		gameModel.setItemImage(itemImage);
	}





}