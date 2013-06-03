import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GestionEcran.EcranCentral;
import MenuGauche.MenuGauche;
import SlpashScreen.OpenWindow;



public class Fenetre extends JFrame {

	//Instanciation des objets JPanel
	JPanel container = new JPanel();
	JPanel centre = new JPanel();

	// Les ecrans
	EcranCentral ecranCentral = new EcranCentral();
	MenuGauche menuGauchePan = new MenuGauche();

	public Fenetre(){   
		this.setTitle("TeXaS");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("TeXaS2.png").getImage());;



		//Definition de sa couleur de fond
		centre.setBackground(Color.WHITE); 

		// Gestion menu haut et bas

		container.setLayout(new BorderLayout());

		OpenWindow.setText("Ajout du menu gauche");
		container.add(menuGauchePan, BorderLayout.WEST);

		centre.setLayout(new BorderLayout());

		OpenWindow.setText("Ajout de l'éditeur central");
		centre.add(ecranCentral, BorderLayout.CENTER);

		container.add(centre, BorderLayout.CENTER);

		// Validation de la fenetre
		this.setContentPane(container); 
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}       


}