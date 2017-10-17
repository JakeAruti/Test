package javagame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javagame.Handler;
import javagame.entities.Entity;
import javagame.gfx.Animation;
import javagame.gfx.Assets;

public class Player extends Creature{
	
	//Animations
	private Animation animDN, animLT, animRT, animUP;
	private BufferedImage lastDirection = Assets.bowserDn;
	
	//Class
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 118, 134); //Original dimensions: w=59, h=67
		
		bounds.x = 16;
		bounds.y = 66;
		bounds.width = 104;
		bounds.height = 80;
		//Animations
		animDN = new Animation(333, Assets.bowserDN);
		animLT = new Animation(333, Assets.bowserLT);
		animRT = new Animation(333, Assets.bowserRT);
		animUP = new Animation(333, Assets.bowserUP);
	}

	@Override
	public void update() {
		//Animations
		animDN.update();
		animLT.update();
		animRT.update();
		animUP.update();
		//Move
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//checkCollection();
	}
	
	@Override
	public void disappear(){
		System.out.println("DIDED");
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
	
//	private void disappear() {
//		// TODO Auto-generated method stub
//		
//	}

	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), 
				(int)(y - handler.getGameCamera().getyOffset()), 118, 134, null);
		g.setColor(Color.red);            //Scaling factors here ^ and ^
		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		g.setColor(Color.blue);            //Scaling factors here ^ and ^
		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		
		if(xMove < 0){
			lastDirection = Assets.bowserLt; 
			return animLT.getCurrentFrame();
		}else if(xMove > 0){
			lastDirection = Assets.bowserRt;
			return animRT.getCurrentFrame();
		}else if(yMove < 0){
			lastDirection = Assets.bowserUp;
			return animUP.getCurrentFrame();
		}else if(yMove > 0){
			lastDirection = Assets.bowserDn;
			return animDN.getCurrentFrame();
		}else{
			return lastDirection;
		}
	}
	
}
