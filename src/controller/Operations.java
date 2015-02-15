package controller;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public interface Operations {
	public void saveFile(JTextArea area,String fileName);
	public void readInFile(JTextArea area,String fileName);
	public void saveFileAs(JTextArea area,JFileChooser dialog);
	public void saveOld(JTextArea area, String filename);
}
