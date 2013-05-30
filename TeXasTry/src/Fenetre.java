import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import GestionEcran.EcranCentral;
import MenuGauche.MenuGauche;
import briquesElementaires.JPanelDef;

public class Fenetre extends JFrame {

	//Instanciation des objets globaux
	JPanelDef container = new JPanelDef();
	MenuGauche menuGauchePan = new MenuGauche();
	EcranCentral containerCentre = new EcranCentral();


	public Fenetre(){             
		// Mise en forme générale
		this.setTitle("TeXaS");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("TeXaS2.png").getImage());;

		// Mise en place des Panels
		container.setLayout(new BorderLayout());
		container.add(menuGauchePan, BorderLayout.WEST);
		container.add(containerCentre,BorderLayout.CENTER);
		
		// Validation de la fenetre
		
		this.setContentPane(container); 
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}   

}