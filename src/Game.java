import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable{
	ArrayList<Enemy> enemies = new ArrayList<>();
	int level;
	Player player = new Player();
	int score;
}
