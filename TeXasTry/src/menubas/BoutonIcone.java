package menubas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import briquesElementaires.Couleur;

public class BoutonIcone extends JButton {

	private String name;
	
	public BoutonIcone(String string) {
		super();
		this.name=string;
		this.setBorder(null);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Couleur.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	
		try {
			g.drawImage(ImageIO.read(new File("icone"+this.name+".png")), this.getWidth()/2-this.getHeight()/2, 0, this.getHeight(),this.getHeight(),this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
