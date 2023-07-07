package com.handehdy.space;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fire {
	
	private int x;
	private int y;
	public Fire(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
public class Game extends JPanel implements KeyListener,ActionListener {
	
	Timer timer = new Timer(5,this);
	
	private int elapsedTime = 0;
	private int spentFire = 0;
	private BufferedImage image;
	
	private ArrayList<Fire> fires = new ArrayList<Fire> ();
	
	private int fireY = 1;
	
	private int ballX = 0;
	
	private int ball_X = 2;
	
	private int spaceShipX = 0;
	
	private int spaceUnitX = 20;
	
	//Topla ateşin çarpışıp çarpışmadığını kontrol ediyoruz
	public boolean check() {
		
		for(Fire fire : fires) {
			
			if (new Rectangle(fire.getX(), fire.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))) {
				
				return true;
			}
			
		}
		return false;
		
	}

	public Game() {
		
		try {
			image = ImageIO.read(new FileImageInputStream(new File("uz.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.BLACK);
		
		timer.start();
		
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		elapsedTime+=5;
		
		g.setColor(Color.BLUE);
		
		g.fillOval(ballX, 0, 20, 20);
		
		g.drawImage(image, spaceShipX, 420, image.getWidth() /10, image.getHeight()/10, this);
		
		for ( Fire fire:fires) {
			
			if(fire.getY()<0) {
				fires.remove(fire);
			}
		}
		
		g.setColor(Color.RED);
		
		for(Fire fire: fires) {
			
			g.fillRect(fire.getX(),fire.getY(), 10, 20);
		}
		
		if(check()) {
			
			timer.stop();
			String message = "You won! \n" + "Spent fire: " + spentFire + "\nElapsed time: " + elapsedTime/1000.0 + " second";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
			
		}
	}
	
	

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for( Fire fire: fires) {
			fire.setY(fire.getY()-fireY);
		}
		
		ballX += ball_X ;
		
		if(ballX >= 750) {
			ball_X = -ball_X;
		}
		
		if(ballX <= 0) {
			ball_X = -ball_X;
		}
		
		repaint();
			}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		//Klavyede sola basıldığında
		if ( c==KeyEvent.VK_LEFT) {
			
			if(spaceShipX <= 0) {
				
				spaceShipX = 0;
						
			}  
			else {
				
				spaceShipX -= spaceUnitX;
			}
			
		}
		//Klavyede sağa basıldığında
		else if( c== KeyEvent.VK_RIGHT) {
			
			if(spaceShipX >=700) {
				
				spaceShipX = 700;
			}
			else {
				spaceShipX += spaceUnitX;
			}
			
		}
		else if (c == KeyEvent.VK_CONTROL) {
			
			fires.add(new Fire(spaceShipX+ 40,360));
			
			spentFire++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
