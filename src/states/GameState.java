package states;

import java.awt.Graphics;

import game.Level;
import main.Window;

public class GameState extends State{
	
	private Level level;
	
	public GameState(Window window) {
		super(window);
	}
	
	@Override
	public void update() {
		level.update();
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
	}
	
	public void setLevel(Level level){
		this.level = level;
	}
	
}
