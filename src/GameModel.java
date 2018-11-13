import java.util.ArrayList;

import javafx.scene.image.Image;

public class GameModel {
	private Mario mario = new Mario(new Image("resources/mario.png"), 4, 4, 196, 80, 40, 40, 0, 675, 1, 0, 40, false);
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();
	

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	public ArrayList<Coin> getCoins() {
		return coins;
	}

}
