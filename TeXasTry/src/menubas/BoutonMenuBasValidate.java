package menubas;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoutonMenuBasValidate extends BoutonMenuBas implements MouseListener{

	private String name;
	private boolean survol;
	private Color grisClair = new Color(238,238,238),  grisFonce = new Color(200,201,202);
	private Color bleuCiel = new Color(128,204,232);
	private boolean pressed;
	

	public BoutonMenuBasValidate(String str) {
		super(str);
		this.name = str;
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		Graphics2D  Shape = (Graphics2D) g;
		Shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Choix de la couleur du background (obligatoire, sinon bug)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		
		
		// Dessin du texte en position centrée
		g.setColor(Color.black);
		FontMetrics fm = g.getFontMetrics();
		int x = (this.getWidth() - fm.stringWidth(this.name)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.name, x, y);

		g.setColor(Color.BLACK);
		g.drawRoundRect(x-10, this.getHeight()/3, fm.stringWidth(name)+20, this.getHeight()/3, 5, 5);
		
		// Si le bouton est survolé
		if (this.survol){
			g.setColor(bleuCiel);
			g.drawRoundRect(x-13, this.getHeight()/3-3, fm.stringWidth(name)+26, this.getHeight()/3+6, 5, 5);
			
		}
		
		if (this.pressed){
			g.setColor(bleuCiel);
			g.fillRoundRect(x-30, this.getHeight()/3-17, fm.stringWidth(name)+60, this.getHeight()/3+34, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(x-10, this.getHeight()/3, fm.stringWidth(name)+20, this.getHeight()/3, 5, 5);
			g.drawString(this.name, x, y);
		}



	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.survol = true;
		this.repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.survol = false;
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pressed=true;
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.pressed=false;
		this.repaint();
	}
}      


