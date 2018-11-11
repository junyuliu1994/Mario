import java.util.ArrayList;

public class GameController {
	GameModel gameModel;
	
	public GameController(GameModel gameModel){
		this.gameModel = gameModel;
	}
	
	public Mario getMario() {
		return gameModel.getMario();
	}

	public void setMario(Mario mario) {
		gameModel.setMario(mario);
	}
	
	public ArrayList<Coin> getCoins() {
		return gameModel.getCoins();
	}


}
