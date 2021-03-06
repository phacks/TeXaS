package menubas;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import briquesElementaires.Couleur;
import briquesElementaires.Police;



public class BoutonMenuBas extends JButton{
	
	private String name;
	private boolean check = false;

	public BoutonMenuBas(String str){
		super(str);
		this.name = str;
		this.setFont(Police.segoe);
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
		GradientPaint gp = new GradientPaint(0, this.getHeight()-5, Couleur.grisClair ,0, this.getHeight(), Couleur.grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Dessin du texte en position centr�e
		g.setColor(Couleur.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);
	}


}
