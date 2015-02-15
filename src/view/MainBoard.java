package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import controller.Control;

public class MainBoard extends JFrame{
	private JTextArea area = new JTextArea(20,120);
	private JFileChooser dialog = new JFileChooser(System.getProperty("user"));
	private String currentFile = "Untitled";
	private boolean changed = false;
	private Control controller = new Control();
	private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			changed = true;
			Save.setEnabled(true);
			SaveAs.setEnabled(true);
		}
	};
	
	public MainBoard(){
		area.setFont(new Font("Monospaced",Font.PLAIN,12));
		JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scroll,BorderLayout.CENTER);
		
		JMenuBar JMB = new JMenuBar();
		setJMenuBar(JMB);
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMB.add(file); JMB.add(edit);
		file.add(Open);file.add(Save);
		file.add(Quit);file.add(SaveAs);
		
		for(int i=0; i<4; i++)
			file.getItem(i).setIcon(null);
		
		edit.add(Cut);edit.add(Copy);edit.add(Paste);

		edit.getItem(0).setText("Cut out");
		edit.getItem(1).setText("Copy");
		edit.getItem(2).setText("Paste");
		JToolBar tool = new JToolBar();
		add(tool,BorderLayout.NORTH);
		tool.add(Open);tool.add(Save);
		tool.addSeparator();
		
		JButton cut = tool.add(Cut), cop = tool.add(Copy),pas = tool.add(Paste);
		
		cut.setText(null); cut.setIcon(new ImageIcon(System.getProperty("user.dir")+"/image/cut.gif"));
		cop.setText(null); cop.setIcon(new ImageIcon(System.getProperty("user.dir")+"/image/copy.gif"));
		pas.setText(null); pas.setIcon(new ImageIcon(System.getProperty("user.dir")+"/image/paste.gif"));
		
		Save.setEnabled(false);
		SaveAs.setEnabled(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		area.addKeyListener(k1);
		setTitle(currentFile);
		setVisible(true);
	}
	
	Action Open = new AbstractAction("Open", new ImageIcon("/Users/jiaqichen/Documents/workspace/TextEditor/image/open.gif")) {
		public void actionPerformed(ActionEvent e) {
			if(changed){
			controller.saveOld(area, dialog.getSelectedFile().getAbsolutePath());
			currentFile = dialog.getSelectedFile().getAbsolutePath();
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
			}
			if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				controller.readInFile(area,dialog.getSelectedFile().getAbsolutePath());
				currentFile = dialog.getSelectedFile().getAbsolutePath();
				setTitle(currentFile);
				changed = false;
			}
			SaveAs.setEnabled(true);
		}
	};
	
	Action Save = new AbstractAction("Save", new ImageIcon("/Users/jiaqichen/Documents/workspace/TextEditor/image/save.gif")) {
		public void actionPerformed(ActionEvent e) {
			if(!currentFile.equals("Untitled")){
				controller.saveFile(area,currentFile);
				setTitle(currentFile);
				changed = false;
				Save.setEnabled(false);
			}
			else
				controller.saveFileAs(area,dialog);
		}
	};
	
	Action SaveAs = new AbstractAction("Save as...") {
		public void actionPerformed(ActionEvent e) {
			if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
			controller.saveFileAs(area,dialog);
			currentFile = dialog.getSelectedFile().getAbsolutePath();
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
			}
		}
	};
	
	Action Quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent e) {
			if(changed) {
			controller.saveOld(area,currentFile);
			currentFile = dialog.getSelectedFile().getAbsolutePath();
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
		}
			System.exit(0);
		}
	};
	
	ActionMap m = area.getActionMap();
	Action Cut = m.get(DefaultEditorKit.cutAction);
	Action Copy = m.get(DefaultEditorKit.copyAction);
	Action Paste = m.get(DefaultEditorKit.pasteAction);
	
//	private void saveFileAs() {
//		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
//			controller.saveFile(area,dialog.getSelectedFile().getAbsolutePath());
//			currentFile = dialog.getSelectedFile().getAbsolutePath();
//			setTitle(currentFile);
//			changed = false;
//			Save.setEnabled(false);
//		}
//	}
	
//	private void saveOld() {
//		if(changed) {
//			if(JOptionPane.showConfirmDialog(this, "Would you like to save "+ currentFile +" ?","Save",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
//				controller.saveFile(area,currentFile);
//			currentFile = dialog.getSelectedFile().getAbsolutePath();
//			setTitle(currentFile);
//			changed = false;
//			Save.setEnabled(false);
//		}
//	}
}
