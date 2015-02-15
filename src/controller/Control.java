package controller;

import java.awt.Toolkit;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Control implements Operations{

	public void saveFile(JTextArea area,String fileName) {
		try {
			if(!fileName.contains(".txt"))
				fileName+=".txt";
			FileWriter w = new FileWriter(fileName);
			area.write(w);
			w.close();
		}
		catch(IOException e) {
		}
	}
	public void readInFile(JTextArea area,String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			area.read(r,null);
			r.close();
		}
		catch(IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(area,"Editor can't find the file called "+fileName);
		}
	}
	public void saveOld(JTextArea area,String fileName, boolean changed){
		if(changed) {
			if(JOptionPane.showConfirmDialog(area, "Would you like to save "+ fileName +" ?","Save",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
				this.saveFile(area,fileName);
		}
	}
	public void saveFileAs(JTextArea area,JFileChooser dialog){
			this.saveFile(area,dialog.getSelectedFile().getAbsolutePath());
	}
}
