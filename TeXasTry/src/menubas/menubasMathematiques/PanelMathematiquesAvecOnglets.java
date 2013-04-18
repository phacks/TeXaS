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

import briquesElementaires.Couleur;

public class PanelMathematiquesAvecOnglets extends JTabbedPane implements ChangeListener, Runnable{

	// Element de design du jtabbedpan
	private MenuBasDesignOngletsInterneHautUI menuBlocsDesign;
	private String[] nomOngletsOperateurs =  {"Opérateurs 1", "Opérateurs 2", "Opérateurs 3", "Opérateurs 4", "Divers"};
	private String[] nomOngletsAlphabet =  {"Lettres Grecques", "Polices Mathématiques"};

	public PanelMathematiquesAvecOnglets(String[] ongletsOperateurs) {
		super(JTabbedPane.TOP);
		menuBlocsDesign = new MenuBasDesignOngletsInterneHautUI();
		this.setUI(menuBlocsDesign);
		this.addChangeListener(this);
		for (int i = 0; i < ongletsOperateurs.length; i++) {
			if(ongletsOperateurs[0].equals("Operateur2")){
				this.add(nomOngletsOperateurs[i], new PanelMathematiquesSansOnglet(ongletsOperateurs[i]));
			}
			else{
				if(ongletsOperateurs[0].equals("LettresGrecques")){
					this.add(nomOngletsAlphabet[i], new PanelMathematiquesSansOnglet(ongletsOperateurs[i]));
				}
				else{
					this.add((i+1)+"", new PanelMathematiqueSansOngletGrand(ongletsOperateurs[i]));
				}
			}
		}
	}

	private int step;
	private BufferedImage buf = null;
	private int previousTab = -1;
	private int newTab = -1;
	private int animationLongueur = 15;

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
			float positionXprevious = previousTab*(menuBlocsDesign.getMaxWidth()+20);
			float positionXnew = newTab*(menuBlocsDesign.getMaxWidth()+20);

			float t = (float) step / animationLongueur;
			float u = 1 - t;
			int positionX = (int)(u*u*u*positionXprevious+3*u*u*t*positionXprevious+3*u*t*t*positionXnew+t*t*t*positionXnew);

			g2.fillRect(positionX, 0, menuBlocsDesign.getMaxWidth()+21, menuBlocsDesign.getMaxHeight()+11);

		}
	}

}
