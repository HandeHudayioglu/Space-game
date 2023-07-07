package com.handehdy.space;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameMonitor extends JFrame {
	
	public GameMonitor (String title) throws HeadlessException {
		super(title);
	}
	
	public static void main(String [] args) {
		
		GameMonitor monitor = new GameMonitor("Space Game");
		
		monitor.setResizable(false);
		monitor.setFocusable(false);
		
		monitor.setSize(800,600);
		monitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game game = new Game();
		game.requestFocus();
		game.addKeyListener(game);
		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);
		
		monitor.add(game);
		
		monitor.setVisible(true);
		
		
	}

}
