package javagame.states;

import java.awt.Graphics;

import javagame.Handler;
import javagame.entities.creatures.Goomba;
import javagame.entities.creatures.Player;
import javagame.entities.statics.Rock;
import javagame.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}
	public void update() {
		world.update();
	}

	public void render(Graphics g) {
		world.render(g);
		
	}
	
}
