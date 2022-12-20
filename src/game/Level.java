package game;

import java.awt.Graphics;

import java.awt.Image;
import java.util.ArrayList;

import gfx.Assets;
import input.KeyBoard;
import main.Window;
import states.LevelSelectorState;
import states.State;
import ui.Button;
import ui.Click;

public class Level {
	
	public static final int TILESIZE = 48;
	
	private int[][] maze;
	
	private int[][] copy;
	
	private int playerRow, playerCol;
	
	private Image texture;
	
	private int xOffset, yOffset;
	
	private long time, lastTime;
	
	private final int DELAY = 150;
	
	private Button restart, undo, back;
	
	private boolean solved;
	
	private int plaStartRow, plaStartCol;
	
	private LevelSelectorState levelSelectorState;
	
	public static int ID = 0;
	
	private int id;
	
	private static int GROUND=0;
	private static int WALL=1;
	private static int BALL=2;
	private static int GOALOFF=3;
	private static int GOALON=4;
    
    //private int level;
    
    private ArrayList<Integer> moveHistory = new ArrayList<Integer>();
    private static int MOVEUP=1;
    private static int MOVEDOWN=2;
    private static int MOVERIGHT=3;
    private static int MOVELEFT=4;
    private static int PUSHUP=5;
    private static int PUSHDOWN=6;
    private static int PUSHRIGHT=7;
    private static int PUSHLEFT=8;
    
    //private int totalGoal=0;
    private ArrayList<Integer> goalCount = new ArrayList<Integer>();
    	
	public Level(int[][] maze, int player_row, int player_col, LevelSelectorState levelSelectorState){
		this.levelSelectorState = levelSelectorState;
		this.maze = maze;
		ID ++;
		id = ID;
		copy = new int[maze.length][maze[0].length];
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++)
				copy[row][col] = maze[row][col];
		plaStartRow = player_row;
		plaStartCol = player_col;
		this.playerRow = player_row;
		this.playerCol = player_col;
		if(ID == 1)
			solved = true;
		else
			solved = false;
		xOffset = (Window.WIDTH - maze[0].length*TILESIZE)/2;
		yOffset = (Window.HEIGHT - maze.length*TILESIZE)/2;
		if(Window.character==0) {
			texture = Assets.ronaldoFront;
		}else if (Window.character==1){
			texture = Assets.messiFront;
		}else if (Window.character==2){
			texture = Assets.mbappeFront;
		}
		restart = new Button("RESTART",  100, Window.HEIGHT/20, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}},
				Assets.font30);
		undo = new Button("UNDO",  100, Window.HEIGHT/20+50, new Click(){

			@Override
			public void onClick() {
				undo();
				
			}},
				Assets.font30);
		back = new Button("BACK", Window.WIDTH - 100, Window.HEIGHT/20, new Click(){

			@Override
			public void onClick() {
				State.currentState = levelSelectorState;
				
			}},
				Assets.font30);
		
		time = 0;
		lastTime = System.currentTimeMillis();
		}
	}
	
	public void undo() {
		if(!moveHistory.isEmpty()) {
			int lastMove = moveHistory.get(moveHistory.size()-1);
			if(lastMove==MOVEUP) playerRow++;
			else if(lastMove==MOVEDOWN) playerRow--;
			else if(lastMove==MOVERIGHT) playerCol--;
			else if(lastMove==MOVELEFT) playerCol++;
			else if(lastMove==PUSHUP) {
				if(maze[playerRow-1][playerCol]==GOALON)
					maze[playerRow-1][playerCol]=GOALOFF;
				else if(maze[playerRow-1][playerCol]==BALL)
					maze[playerRow-1][playerCol]=GROUND;
				if(maze[playerRow][playerCol]==GOALOFF)
					maze[playerRow][playerCol]=GOALON;
				else if(maze[playerRow][playerCol]==GROUND)
					maze[playerRow][playerCol]=BALL;
				playerRow++;
			}
			else if(lastMove==PUSHDOWN) {
				if(maze[playerRow+1][playerCol]==GOALON)
					maze[playerRow+1][playerCol]=GOALOFF;
				else if(maze[playerRow+1][playerCol]==BALL)
					maze[playerRow+1][playerCol]=GROUND;
				if(maze[playerRow][playerCol]==GOALOFF)
					maze[playerRow][playerCol]=GOALON;
				else if(maze[playerRow][playerCol]==GROUND)
					maze[playerRow][playerCol]=BALL;
				playerRow--;
			}
			else if(lastMove==PUSHRIGHT) {
				if(maze[playerRow][playerCol+1]==GOALON)
					maze[playerRow][playerCol+1]=GOALOFF;
				else if(maze[playerRow][playerCol+1]==BALL)
					maze[playerRow][playerCol+1]=GROUND;
				if(maze[playerRow][playerCol]==GOALOFF)
					maze[playerRow][playerCol]=GOALON;
				else if(maze[playerRow][playerCol]==GROUND)
					maze[playerRow][playerCol]=BALL;
				playerCol--;
			}
			else if(lastMove==PUSHLEFT) {
				if(maze[playerRow][playerCol-1]==GOALON)
					maze[playerRow][playerCol-1]=GOALOFF;
				else if(maze[playerRow][playerCol-1]==BALL)
					maze[playerRow][playerCol-1]=GROUND;
				if(maze[playerRow][playerCol]==GOALOFF)
					maze[playerRow][playerCol]=GOALON;
				else if(maze[playerRow][playerCol]==GROUND)
					maze[playerRow][playerCol]=BALL;
				playerCol++;
			}
			moveHistory.remove(moveHistory.size()-1);
			goalCount.remove(goalCount.size()-1);
		}
	}
	
	private void reset(){
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				maze[row][col] = copy[row][col];
		
		moveHistory.clear();
		goalCount.clear();
		goalCount.add(0);
		playerRow = plaStartRow;
		playerCol = plaStartCol;
		if(Window.character==0) {
		texture = Assets.ronaldoFront;
		}else if (Window.character==1){
			texture = Assets.messiFront;
			}else if (Window.character==2){
				texture = Assets.mbappeFront;
				}
	}
	
	
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(KeyBoard.UP && time > DELAY){
			move(-1, 0);
			if(Window.character==0) {
				texture = Assets.ronaldoBack;
			}else if (Window.character==1){
				texture = Assets.messiBack;
			}else if (Window.character==2){
				texture = Assets.mbappeBack;
			}
		}
		else if(KeyBoard.LEFT && time > DELAY){
			move(0, -1);
			if(Window.character==0) {
				texture = Assets.ronaldoLeft;
			}else if (Window.character==1){
				texture = Assets.messiLeft;
			}else if (Window.character==2){
				texture = Assets.mbappeLeft;
			}
		}
		else if(KeyBoard.DOWN && time > DELAY){
			move(1, 0);
			if(Window.character==0) {
				texture = Assets.ronaldoFront;
			}else if (Window.character==1){
				texture = Assets.messiFront;
			}else if (Window.character==2){
				texture = Assets.mbappeFront;
			}
		}
		else if(KeyBoard.RIGHT && time > DELAY){
			move(0, 1);
			if(Window.character==0) {
				texture = Assets.ronaldoRight;
			}else if (Window.character==1){
				texture = Assets.messiRight;
			}else if (Window.character==2){
				texture = Assets.mbappeRight;
			}
		}
		
		restart.update();
		undo.update();
		back.update();
		// check answer
		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		levelSelectorState.getLevels()[id].setSolved(true);
		State.currentState = levelSelectorState;
		
	}
	
	private void move(int row, int col){
		int adj1 = maze[playerRow+row][playerCol+col];
		int adj2=-1;
		if(playerRow+2*row>=0 && playerRow+2*row<maze.length && playerCol+2*col>=0 && playerCol+2*col<maze[0].length)
			adj2 = maze[playerRow+2*row][playerCol+2*col];
		int lastCount=0;
		if(!goalCount.isEmpty())
			lastCount = goalCount.get(goalCount.size()-1);
		if(adj1==GROUND || adj1==GOALOFF) {
			playerRow+=row;
			playerCol+=col;
			if(row==1) moveHistory.add(MOVEDOWN);
			else if(row==-1) moveHistory.add(MOVEUP);
			else if(col==1) moveHistory.add(MOVERIGHT);
			else if(col==-1) moveHistory.add(MOVELEFT);
			goalCount.add(lastCount);
		}
		else if(adj1==GOALON && (adj2==GROUND || adj2==GOALOFF)) {
			maze[playerRow+row][playerCol+col]=GOALOFF;
			if(adj2==GROUND) {
				maze[playerRow+2*row][playerCol+2*col]=BALL;
				goalCount.add(lastCount-1);
			}
			else{
				maze[playerRow+2*row][playerCol+2*col]=GOALON;
				goalCount.add(lastCount);
			}
			playerRow+=row;
			playerCol+=col;
			if(row==1) moveHistory.add(PUSHDOWN);
			else if(row==-1) moveHistory.add(PUSHUP);
			else if(col==1) moveHistory.add(PUSHRIGHT);
			else if(col==-1) moveHistory.add(PUSHLEFT);
		}
		else if(adj1==BALL && (adj2==GROUND || adj2==GOALOFF)) {
			maze[playerRow+row][playerCol+col]=GROUND;
			if(adj2==GROUND) {
				maze[playerRow+2*row][playerCol+2*col]=BALL;
				goalCount.add(lastCount);
			}
			else {
				maze[playerRow+2*row][playerCol+2*col]=GOALON;
				goalCount.add(lastCount+1);
			}
			playerRow+=row;
			playerCol+=col;
			if(row==1) moveHistory.add(PUSHDOWN);
			else if(row==-1) moveHistory.add(PUSHUP);
			else if(col==1) moveHistory.add(PUSHRIGHT);
			else if(col==-1) moveHistory.add(PUSHLEFT);
		}
		time = 0;
	}
	
	public void render(Graphics g){
		
		restart.render(g);
		undo.render(g);
		back.render(g);
		
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				g.drawImage(Assets.floor, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(Assets.cone,xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(Assets.ball, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(Assets.gawang, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(Assets.Goal, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 5)
					g.drawImage(Assets.red, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 6)
					g.drawImage(Assets.wall, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 7)
					g.drawImage(Assets.blue, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 8)
					g.drawImage(Assets.floor3, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 9)
					g.drawImage(Assets.black, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				
			}
		}
	// 1 = wall orang
	//2= box bola
	// 3 = titik gawang
		//4 = goal
		//6= wall
		//5 = red
		//7 = blue
		//8 = ground
		//9 = black
		g.drawImage(texture, xOffset + playerCol*TILESIZE, yOffset + playerRow*TILESIZE, null);
		
		
	}
	
	public boolean isSolved(){return solved;}
	public void setSolved(boolean bool){solved = bool;}
	public void setTexture() {
		if(true) {
			texture = Assets.mbappeFront;
		}
	}
}