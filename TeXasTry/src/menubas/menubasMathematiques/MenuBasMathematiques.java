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

import menubas.MenuBasDesignOngletsInterneGaucheUI;
import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class MenuBasMathematiques extends JTabbedPane implements ChangeListener, Runnable{

	// Element de design du jtabbedpan
	private MenuBasDesignOngletsInterneGaucheUI menuBlocsDesign;

	// Les onglets
	private PanelMathematiquesAvecOnglets panelOngletsAlphabet ;
	private PanelMathematiquesAvecOnglets panelOngletsOperateurs;
	
	public MenuBasMathematiques(){
		super(JTabbedPane.LEFT);
		menuBlocsDesign = new MenuBasDesignOngletsInterneGaucheUI();
		this.setUI(menuBlocsDesign);
		this.addChangeListener(this);
		
		this.add("Favoris", new PanelMathematiquesFavoris());
		
		this.add("Structure", new PanelMathematiqueSansOngletGrand("Structure"));
		
		String[] ongletsAlphabet = {"LettresGrecques","PoliceMathematiques"};
		panelOngletsAlphabet = new PanelMathematiquesAvecOnglets(ongletsAlphabet);
		this.add("Alphabets", panelOngletsAlphabet);
		
		this.add("Accentuation", new PanelMathematiquesSansOnglet("Accents"));
		
		this.add("Parenthèses", new PanelMathematiqueSansOngletGrand("Parentheses"));
		
		this.add("Flèches", new PanelMathematiquesSansOnglet("Fleches"));
		
		this.add("Fonctions", new PanelMathematiquesSansOnglet("Fonctions"));
		
		String[] ongletsOperateurs = {"Operateur2","Operateur1","Operateur3","Operateur4","Divers"};
		panelOngletsOperateurs = new PanelMathematiquesAvecOnglets(ongletsOperateurs);
		this.add("Opérateurs", panelOngletsOperateurs);
	}
	
	public void reinistialisation(){
		this.setSelectedIndex(0);
		panelOngletsAlphabet.setSelectedIndex(0);
		panelOngletsOperateurs.setSelectedIndex(0);
		
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
			float positionYprevious = previousTab*(menuBlocsDesign.getMaxHeight()+10)+2;
			float positionYnew = newTab*(menuBlocsDesign.getMaxHeight()+10)+2;

			float t = (float) step / animationLongueur;
			float u = 1 - t;
			int positionY = (int)(u*u*u*positionYprevious+3*u*u*t*positionYprevious+3*u*t*t*positionYnew+t*t*t*positionYnew);
			
			g2.fillRect(0, positionY, menuBlocsDesign.getMaxWidth()+21, menuBlocsDesign.getMaxHeight()+10);

		}
	}

}
