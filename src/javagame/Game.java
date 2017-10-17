package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import display.Display;
import javagame.gfx.Assets;
import javagame.gfx.GameCamera;
import javagame.input.KeyManager;
import javagame.states.GameState;
import javagame.states.MenuState;
import javagame.states.State;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	private String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
	}
	
	private void update(){
		keyManager.update();
		if(State.getState() != null)
			State.getState().update();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear here
		g.clearRect(0, 0, width, height);
		g.setColor(Color.CYAN);
		g.fillRect(-50, -50, width + 50, height + 50);

		if(State.getState() != null)
			State.getState().render(g);
		
		//g.fillRect(0, 0, width, height);
		//draw here
		
		//g.drawImage(Assets.dirt, 64, 0, null);
		
		
		
		//end draw
		bs.show();
		g.dispose();
	}
	
	public void run(){
		init();
		
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				updates++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
