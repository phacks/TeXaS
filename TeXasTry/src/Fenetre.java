import java.awt.BorderLayout;
import java.awt.Color; 

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menubas.MenuBas;


import MenuGauche.MenuGauche;
import MenuHaut.BoutonMenuHaut;
import MenuHaut.MenuHaut;

public class Fenetre extends JFrame {
	
	//Instanciation des objets JPanel
	JPanel container = new JPanel();
	JPanel centre = new JPanel();

	MenuGauche menuGauchePan = new MenuGauche();
	MenuHaut menuHautPan = new MenuHaut();
	MenuBas menuBasPan = new MenuBas();


	public Fenetre(){             
		this.setTitle("TeXaS");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("TeXaS2.png").getImage());;



		//Définition de sa couleur de fond
		centre.setBackground(Color.WHITE); 

		// Gestion menu haut et bas

		container.setLayout(new BorderLayout());

		container.add(menuGauchePan.getMenuGauche(), BorderLayout.WEST);

		centre.setLayout(new BorderLayout());

		centre.add(menuHautPan.getMenuHaut(),BorderLayout.NORTH);

		centre.add(menuBasPan.getMenuBas(),BorderLayout.SOUTH);

		container.add(centre, BorderLayout.CENTER);


		this.setContentPane(container); 
		this.pack();
		this.setVisible(true);

	}       


}