package javagame.worlds;

import java.awt.Graphics;

import javagame.Handler;
import javagame.entities.EntityManager;
import javagame.entities.creatures.Goomba;
import javagame.entities.creatures.Player;
import javagame.entities.statics.Rock;
import javagame.tiles.Tile;
import javagame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	//Entities
	
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 168, 168));
		entityManager.addEntity(new Rock(handler, 1890, 128));
		entityManager.addEntity(new Rock(handler, 2550, 0));
		entityManager.addEntity(new Rock(handler, 3210, -128));
		entityManager.addEntity(new Goomba(handler, 3150, 200)); //Goomba behind Rock
		entityManager.addEntity(new Goomba(handler, 4260,940));
		entityManager.addEntity(new Goomba(handler, 4830, 150));
		entityManager.addEntity(new Goomba(handler, 165, 1050));
		entityManager.addEntity(new Goomba(handler, 420, 530));
		entityManager.addEntity(new Goomba(handler, 3500, 1660));
		entityManager.addEntity(new Goomba(handler, 800, 1820));
		
		
		loadWorld(path);
		
		entityManager.getBowser().setX(spawnX);
		entityManager.getBowser().setY(spawnY);
	}

	public void update(){
		entityManager.update();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.tileWidth);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.tileWidth + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.tileHeight);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.tileHeight + 1);
		
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int)(x * Tile.tileWidth - handler.getGameCamera().getxOffset()), 
						(int)(y * Tile.tileHeight - handler.getGameCamera().getyOffset()));
			}
		}
		
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
