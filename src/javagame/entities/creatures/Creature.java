package javagame.entities.creatures;

import javagame.Handler;
import javagame.entities.Entity;
import javagame.tiles.Tile;

public abstract class Creature extends Entity{
	
	public static final float defaultSpeed = 8.0f;
	public static final int defaultCreatureWidth = 64,
			defaultCreatureHeight = 64;
	
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		
		super(handler, x, y, width, height);
		speed = defaultSpeed;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		if(!checkEntityCollision(xMove, 0f)){
			moveX();
		}
		if(!checkEntityCollision(0f, yMove)){
			moveY();
		}
	}
	
	public void moveX(){
		if(xMove > 0){
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.tileWidth;
			
			if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.tileHeight) && 
					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.tileHeight)){
				x += xMove;
			}else{
				x = tx * Tile.tileWidth - bounds.x - bounds.width - 1;
			}
		}else if(xMove < 0){
			int tx = (int) (x + xMove + bounds.x) / Tile.tileWidth;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.tileHeight) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight) ){
				x += xMove;
			}else{
				x = tx * Tile.tileWidth - bounds.x + Tile.tileWidth;
			}
		}
	}
	
	public void moveY(){
		if(yMove < 0){//Up
			int ty = (int) (y + yMove + bounds.y) / Tile.tileHeight;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.tileWidth, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
				y += yMove;
			}else{
				y = ty * Tile.tileHeight + Tile.tileHeight - bounds.y;
			}
		}else if(yMove > 0){//Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.tileHeight;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.tileWidth, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
				y += yMove;
			}else{
				y = ty * Tile.tileHeight - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//Getters and Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public static float getDefaultspeed() {
		return defaultSpeed;
	}

	public void disappear() {
		
	}
	
	
}
