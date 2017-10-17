package javagame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javagame.Handler;
import javagame.entities.Entity;
import javagame.gfx.Animation;
import javagame.gfx.Assets;

public class Goomba extends Creature{
	
	int collected = 0;
	
	private Animation animDN;

	public Goomba(Handler handler, float x, float y) {
		super(handler, x, y, 57, 66); //Original dimensions: w=19, h=22
		
		bounds.x = 2;
		bounds.y = 10;
		bounds.width = 53;
		bounds.height = 56;

		animDN = new Animation(333, Assets.goombaID);
	}
	
//	@Override
//	public boolean checkEntityCollision(float xOffset, float yOffset){
//		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
//			if(e.equals(this))
//				continue;
//			
//			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
//				if(e.getCollisionBounds(0f, 0f).intersects(entities.bowser.getCollisionBounds())){
//					setActive(false);
//					disappear();
//				}
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public void update() {

		animDN.update();

		checkCollection();
	}
	
	private void checkCollection(){
		Rectangle bowserCollision = getCollisionBounds(0, 0);
		Rectangle collectionRect = new Rectangle();
		int collectionRange = 115;
		collectionRect.width = collectionRange;
		collectionRect.height = collectionRange;
		collectionRect.x = bowserCollision.x - 5;
		collectionRect.y = bowserCollision.y - 20;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(collectionRect)){
				touched();
				return;
			}
		}
	}
	
	@Override
	public void disappear(){
		System.out.println("collected");
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(animDN.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()), 
				(int)(y - handler.getGameCamera().getyOffset()), 57, 66, null);
		g.setColor(Color.red);            //Scaling factors here ^ and ^
		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}                                     

	

}
