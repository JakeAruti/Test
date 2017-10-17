package javagame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import javagame.Handler;
import javagame.entities.creatures.Player;

public class EntityManager {
	
	private Handler handler;
	//private Goomba goomba;
	private Player bowser;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b){
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player){
		bowser = player;
		this.handler = handler;
		entities = new ArrayList<Entity>();
		addEntity(bowser);
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.update();
			if(!e.isActive())
				entities.remove(e);
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//Getters and Setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getBowser() {
		return bowser;
	}

	public void setBowser(Player bowser) {
		this.bowser = bowser;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
