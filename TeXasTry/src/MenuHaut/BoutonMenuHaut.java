package MenuHaut;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;



public class BoutonMenuHaut extends JButton{
	
	private String name;
	private Color grisClair = new Color(234,237,239),  grisFonce = new Color(200,201,202);
	private boolean check = false;

	public BoutonMenuHaut(String str){
		super(str);
		this.name = str;
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		this.setBackground(grisClair);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBorderPainted(false);
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
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(grisClair);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Dessin du texte en position centrée
		g.setColor(Color.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);
	}


}
