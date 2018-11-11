import java.util.ArrayList;

import javafx.scene.image.Image;

public class GameModel {
	private Mario mario = new Mario(new Image("file:mario.png"), 4, 4, 80, 32, 16, 15, 640, 480, 0);
	private ArrayList<Coin> coins= new ArrayList<>();

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}
	
	public ArrayList<Coin> getCoins() {
		return coins;
	}

}
