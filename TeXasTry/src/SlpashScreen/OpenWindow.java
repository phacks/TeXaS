package SlpashScreen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JWindow;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class OpenWindow extends JWindow {

	private static JLabel labelSouth;
	
	// >>>>>>>>>> Getters and Setters
	public static void setText(String string){
		labelSouth.setText(string);
	}
	
	// >>>>>>>>>>> Fin getters and setters
	
	public OpenWindow(){
		super();
		this.setSize(256, 300);
		this.setLocationRelativeTo(null);
		

		labelSouth = new JLabel("Initialisation...");
		labelSouth.setFont(Police.segoeItal);
		labelSouth.setOpaque(false);
		
		PanelIcone panelIcone = new PanelIcone();
		
		JPanelDef conteneur = new JPanelDef(new BorderLayout());
		conteneur.setBackground(Couleur.vertPomme);
		conteneur.add(panelIcone, BorderLayout.CENTER);
		conteneur.add(labelSouth, BorderLayout.SOUTH);
	
		
		this.setContentPane(conteneur);

		this.setVisible(true);
	}



	public class PanelIcone extends JPanelDef{
		public PanelIcone(){
			super();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Couleur.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			try {
				Image img = ImageIO.read(new File("TeXaS2.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}
}

