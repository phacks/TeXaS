package MenuGauche;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import briquesElementaires.Couleur;



public class BoutonMenuGaucheBase extends JButton{
	private String name;
	private boolean check = false;
	private Color grisClair = new Color(234,237,239),  grisFonce = new Color(200,201,202);


	public boolean isCheck() {
		return check;
	}

	public BoutonMenuGaucheBase(String str){
		super(str);
		this.name = str;
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		this.setBackground(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBorderPainted(false);
	}

	public void check() {
		this.check = true;
	}

	public void uncheck() {
		this.check = false;
	}

	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		GradientPaint gp = new GradientPaint(this.getWidth()-5, this.getHeight(), grisClair , this.getWidth(), this.getHeight(), grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Dessin du texte en position centrée
		g.setColor(Color.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);


		if (check){
			GradientPaint gpCheck = new GradientPaint(0, this.getHeight(), Couleur.vertFonce, this.getWidth()/2, this.getHeight(), Couleur.vertClair,true);
			Shape.setPaint(gpCheck);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());
			g.setColor(Couleur.vertFonceBordure);
			g.drawLine(0, 0, this.getWidth(), 0);
			g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
			g.setColor(Color.WHITE);
			g.drawString(this.name, x, y);
			//Dessin d'un triangle d'indicateur d'onglet
			int[] xPol = {this.getWidth(),this.getWidth(),this.getWidth()-(this.getHeight()*2/3-this.getHeight()*1/3)};
			int[] yPol = {this.getHeight()*2/3,this.getHeight()*1/3,this.getHeight()/2};
			g.setColor(Color.WHITE);
			g.fillPolygon(xPol,yPol,3);			

		}
	}      


}
