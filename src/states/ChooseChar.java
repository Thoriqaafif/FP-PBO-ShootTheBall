package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Window;
import ui.Button;
import ui.Click;

public class ChooseChar extends State {
	Button bappe,messi,ronaldo,back;
	
	public ChooseChar(Window window) {
		// TODO Auto-generated constructor stub
		super(window);
		ronaldo = new Button("Ronaldo",Window.WIDTH/4,Window.HEIGHT/2,new Click() {
			public void onClick() {
				Window.character=0;
				ronaldo.setString("SELECTED");
				messi.setString("Messi");
				bappe.setString("Mbappe");
			}
		},Assets.font30);
		messi = new Button("Messi",Window.WIDTH/2,Window.HEIGHT/2,new Click() {
			public void onClick() {
				Window.character=1;
				messi.setString("SELECTED");
				ronaldo.setString("Ronaldo");
				bappe.setString("Mbappe");
			}
		},Assets.font30);
		bappe = new Button("Mbappe",Window.WIDTH*3/4,Window.HEIGHT/2,new Click() {
			public void onClick() {
				Window.character=2;
				bappe.setString("SELECTED");
				messi.setString("Messi");
				ronaldo.setString("Ronaldo");
			}
		},Assets.font30);
		back = new Button("BACK", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
		
	}

	@Override
	public void update() {
	  ronaldo.update();
	  messi.update();
	  bappe.update();
	  back.update();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.ronaldoFrontBig, Window.WIDTH/4-100,Window.HEIGHT/2-300,null);	
		g.drawImage(Assets.messiFrontBig, Window.WIDTH/2-100,Window.HEIGHT/2-300,null);
		g.drawImage(Assets.mbappeFrontBig, Window.WIDTH*3/4-100,Window.HEIGHT/2-300,null);
		
		ronaldo.render(g);
		messi.render(g);
		bappe.render(g);
		back.render(g);
	}

}
