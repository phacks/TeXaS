package menubas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import briquesElementaires.Police;



public class TextAligned extends JLabel {

	public TextAligned(String string, boolean b){
		super(string+" ");
		this.setFont(Police.segoe);
		if(b){
			this.setHorizontalAlignment(JLabel.CENTER);
		}
		this.setVerticalAlignment(JLabel.CENTER);
	}

}
