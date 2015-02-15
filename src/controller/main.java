package controller;
import javax.swing.JFrame;

import view.MainBoard;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainBoard board = new MainBoard();
		board.setVisible(true);
		board.setSize(600, 600);
		board.setResizable(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
