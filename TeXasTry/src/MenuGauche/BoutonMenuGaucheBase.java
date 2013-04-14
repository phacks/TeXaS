package MenuGauche;

import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import briquesElementaires.Couleur;
import briquesElementaires.Police;



public class BoutonMenuGaucheBase extends JButton implements MouseListener{
	private String name;
	private boolean check = false;
//	private Color grisClair = new Color(234,237,239),  grisFonce = new Color(200,201,202);
	private boolean mouseIn = false;


	public boolean isCheck() {
		return check;
	}

	public BoutonMenuGaucheBase(String str){
		super(str);
		this.name = str;
		this.setFont(Police.segoe);
		this.setBackground(Couleur.white);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBorderPainted(false);
		this.addMouseListener(this);
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
		GradientPaint gp = new GradientPaint(this.getWidth()-3, this.getHeight(), Couleur.grisClair , this.getWidth(), this.getHeight(), Couleur.grisFonce);
		Shape.setPaint(gp);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Dessin du texte en position centrée
		g.setColor(Couleur.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);

		if (check){
			g.setColor(Couleur.bleuClairMenuGauche);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());	
//			GradientPaint gpCheck = new GradientPaint(0, this.getHeight(), Couleur.bleuFonce, this.getWidth()/2, this.getHeight(), Couleur.bleuClair,true);
//			Shape.setPaint(gpCheck);
//			g.fillRect(0, 0, this.getWidth(),this.getHeight());
//			g.setColor(Couleur.bleuFonceBordure);
//			g.drawLine(0, 0, this.getWidth(), 0);
//			g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
			g.setColor(Couleur.white);
			g.drawString(this.name, x, y);
			//Dessin d'un triangle d'indicateur d'onglet
			int[] xPol = {this.getWidth(),this.getWidth(),this.getWidth()-(this.getHeight()*2/3-this.getHeight()*1/3)};
			int[] yPol = {this.getHeight()*2/3,this.getHeight()*1/3,this.getHeight()/2};
			g.setColor(Couleur.white);
			g.fillPolygon(xPol,yPol,3);			
		}
		
		if(mouseIn){
			g.setColor(Couleur.bleuFonceMenuGauche);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Couleur.white);
			g.drawString(this.name, x, y);
			if(check){
				//Dessin d'un triangle d'indicateur d'onglet
				int[] xPol = {this.getWidth(),this.getWidth(),this.getWidth()-(this.getHeight()*2/3-this.getHeight()*1/3)};
				int[] yPol = {this.getHeight()*2/3,this.getHeight()*1/3,this.getHeight()/2};
				g.setColor(Couleur.white);
				g.fillPolygon(xPol,yPol,3);	
			}
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.mouseIn = true;
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseIn = false;
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}      


}
