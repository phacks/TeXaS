package menubas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;



public class BoutonMenuBas extends JButton{
	
	private String name;
	private Color grisClair = new Color(234,237,239),  grisFonce = new Color(200,201,202);
	private boolean check = false;

	public BoutonMenuBas(String str){
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

		// Choix de la couleur du background (obligatoire, sinon bug)
		GradientPaint gp = new GradientPaint(0, this.getHeight()-5, grisClair ,0, this.getHeight(), grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Dessin du texte en position centrée
		g.setColor(Color.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);
	}


}
