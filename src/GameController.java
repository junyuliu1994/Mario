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
	
	public ArrayList<Monster> getMonsters(){
		return gameModel.getMonsters();
	}

	public ArrayList<Mushroom> getMushrooms() {
		return gameModel.getMushrooms();
	}

	public void setMushrooms(ArrayList<Mushroom> mushrooms) {
		gameModel.setMushrooms(mushrooms);
	}

	public Image getItemImage() {
		return gameModel.getItemImage();
	}

	public void setItemImage(Image itemImage) {
		gameModel.setItemImage(itemImage);
	}

	public Image getWxzImage() {
		return gameModel.getWxzImage();
	}

	public void setWxzImage(Image wxzImage) {
		gameModel.setWxzImage(wxzImage);
	}

	public Image getWxzConvertImage() {
		return gameModel.getWxzConvertImage();
	}

	public void setWxzConvertImage(Image wxzConvertImage) {
		gameModel.setWxzConvertImage(wxzConvertImage);
	}

	public ArrayList<Bullet> getBullets() {
		return gameModel.getBullets();
	}

	public ArrayList<BlackCircle> getBlackCircles() { return gameModel.getBlackCircles(); }

	public ArrayList<Firework> getFireworks() { return gameModel.getFireworks(); }

	public ArrayList<Information> getInformations() { return gameModel.getInformations(); }

	public void setBullets(ArrayList<Bullet> bullets) {
		gameModel.setBullets(bullets);
	}

	public int getScore() {
		return gameModel.getScore();
	}

	public void setScore(int score) {
		gameModel.setScore(score);
	}
	public void restoreModel(GameModel model, GraphicsContext gc, Canvas canvas){
		gameModel = model;
		gameModel.restore(gc, canvas);
	}
}