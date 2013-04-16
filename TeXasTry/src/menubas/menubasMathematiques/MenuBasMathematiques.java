package menubas.menubasMathematiques;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import menubas.menubasBlocs.MenuBlocsDesignOngletsUI;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class MenuBasMathematiques extends JTabbedPane implements ChangeListener, Runnable{

	// Element de design du jtabbedpan
	private MenuBlocsDesignOngletsUI menuBlocsDesign;

	// Les onglets
//	private MenuBasBlocCode menuBasBlocCode = new MenuBasBlocCode();
//	private MenuBasBlocCitation menuBasBlocCitation= new MenuBasBlocCitation();
//	private MenuBasBlocMaths menuBasBlocMaths = new MenuBasBlocMaths();
//	private MenuBasBlocNoteBasPage menuBasBlocNoteBasPage = new MenuBasBlocNoteBasPage();
	
	public MenuBasMathematiques(){
		super(JTabbedPane.LEFT);
		menuBlocsDesign = new MenuBlocsDesignOngletsUI();
		this.setUI(menuBlocsDesign);
		this.addChangeListener(this);
		this.add("Symboles", new JPanelDef());
		this.add("Structure", new JPanelDef());
		this.add("Accentuation", new JPanelDef());
		this.add("Parenthèses", new JPanelDef());
	}

	private int step;
	private BufferedImage buf = null;
	private int previousTab = -1;
	private int newTab = -1;
	private int animationLongueur = 20;

	public void stateChanged(ChangeEvent evt) {
		new Thread(this).start();
	}


	public void run() {
		step = 0;

		newTab = this.getSelectedIndex();

		// save the previous tab
		if(previousTab != -1) {
			Component comp = this.getComponentAt(previousTab);
			buf = new BufferedImage(comp.getWidth(),comp.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
			comp.paint(buf.getGraphics());
		}

		for(int i=0; i<=animationLongueur; i++) {
			step = i;
			repaint();
			try {
				Thread.sleep(15);
			} catch (Exception ex) {
				System.out.println("ex: " + ex);
			}
		}
		step = -1;
		previousTab = this.getSelectedIndex();
		repaint();
	}


	public void paintChildren(Graphics g) {
		super.paintChildren(g);
		float alpha = 1.0f;

		if(step != -1) {
			Rectangle size = this.getComponentAt(0).getBounds();
			Graphics2D g2 = (Graphics2D)g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha-0.05f*step)); 
			g2.drawImage(buf,(int)size.getX(),(int)size.getY(),null); 

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); 
			g2.setColor(Couleur.bleuClairMenuGauche);
			float positionYprevious = previousTab*(menuBlocsDesign.getMaxHeight()+10)+2;
			float positionYnew = newTab*(menuBlocsDesign.getMaxHeight()+10)+2;

			float t = (float) step / animationLongueur;
			float u = 1 - t;
			int positionY = (int)(u*u*u*positionYprevious+3*u*u*t*positionYprevious+3*u*t*t*positionYnew+t*t*t*positionYnew);
			
			g2.fillRect(0, positionY, menuBlocsDesign.getMaxWidth()+21, menuBlocsDesign.getMaxHeight()+10);

		}
	}

}
