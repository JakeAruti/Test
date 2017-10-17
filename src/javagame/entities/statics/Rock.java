package javagame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import javagame.Handler;
import javagame.gfx.Assets;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, 660, 660);
		bounds.x = 8;
		bounds.y = 296;
		bounds.width = 644;
		bounds.height = 350;
	}

	@Override
	public void update(){
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bigRock1, (int)(x - handler.getGameCamera().getxOffset()), 
				(int)(y - handler.getGameCamera().getyOffset()), 660, 660, null);
		g.setColor(Color.red);            //Scaling factors here ^ and ^
		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	@Override
	public void disappear() {
		// TODO Auto-generated method stub
		
	}

}
