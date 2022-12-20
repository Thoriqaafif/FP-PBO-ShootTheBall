package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Level;

public class Assets {
	
	public static Image ronaldoLeft, ronaldoBack, ronaldoFront,ronaldoRight, ronaldoFrontBig;
	public static Image messiLeft, messiBack, messiFront,messiRight, messiFrontBig;
	public static Image mbappeLeft,mbappeBack, mbappeFront,mbappeRight, mbappeFrontBig;
	
	public static Image floor, floor2, wall,cone, Goal, ball, gawang, outline, outline2,red,blue,floor3,black;
	
	public static Font font48;
	public static Font font30;
	public static Font font22;
	
	public static void init()
	{
		ronaldoLeft = loadImage("/player/Ronaldo/r_left.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		ronaldoBack = loadImage("/player/Ronaldo/r_back.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		ronaldoFront = loadImage("/player/Ronaldo/r_front.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		ronaldoRight = loadImage("/player/Ronaldo/r_right.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		ronaldoFrontBig = loadImage("/player/Ronaldo/r_front.png").getScaledInstance(4*Level.TILESIZE, 4*Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		messiLeft = loadImage("/player/messi/left.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		messiBack = loadImage("/player/messi/back.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		messiFront = loadImage("/player/messi/front.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		messiRight = loadImage("/player/messi/right.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		messiFrontBig = loadImage("/player/messi/front.png").getScaledInstance(4*Level.TILESIZE, 4*Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		mbappeLeft = loadImage("/player/mbappe/m_left.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		mbappeBack = loadImage("/player/mbappe/m_back.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		mbappeFront = loadImage("/player/mbappe/m_front.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		mbappeRight = loadImage("/player/mbappe/m_right.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		mbappeFrontBig = loadImage("/player/mbappe/m_front.png").getScaledInstance(4*Level.TILESIZE, 4*Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/blocks/grass.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/blocks/ground.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		cone = loadImage("/blocks/cone.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		Goal = loadImage("/blocks/GOAL.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		ball = loadImage("/blocks/ball.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		gawang = loadImage("/blocks/gawang.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/blocks/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/blocks/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		red = loadImage("/blocks/red.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall = loadImage("/blocks/grass.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		blue = loadImage("/blocks/blue.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor3 = loadImage("/blocks/ground.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		black = loadImage("/blocks/black.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		font48 = loadFont("res/fonts/square.ttf", 48);
		font22 = loadFont("res/fonts/square.ttf", 22);
		font30 = loadFont("res/fonts/square.ttf", 30);
		
	}
	
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
