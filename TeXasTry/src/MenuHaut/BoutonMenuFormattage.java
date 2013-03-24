package MenuHaut;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class BoutonMenuFormattage extends JButton {

	private String name;
	private boolean check;
	private Image icone;
	private Color grisClair = new Color(234,237,239);

	public BoutonMenuFormattage(String name){
		super();
		this.setPreferredSize(new Dimension(40,40));
		this.setBorderPainted(false);
		try {
			icone = ImageIO.read(new File("PoliceUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(1,7));
		this.setBackground(Color.WHITE);
	}

	public void check() {
		this.check = true;
	}

	public void uncheck() {
		this.check = false;
	}

	public boolean isCheck(){
		return check;
	}


	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(grisClair );
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.drawImage(icone, 0, 0, this.getWidth(), this.getHeight(), this);	
	}
}
