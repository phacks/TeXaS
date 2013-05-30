import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menubas.MenuBas;
import MenuGauche.MenuGauche;
import MenuHaut.MenuHaut;
import formule.BoutonsAction;
import formule.Formule;



public class Fenetre extends JFrame {
	
	//Instanciation des objets JPanel
	JPanel container = new JPanel();
	JPanel centre = new JPanel();
	
	// Ajouts de Nico
	private JPanel partieCentrale = new JPanel();
	private JPanel formuleContainer = new JPanel();
	private JPanel bottom = new JPanel();	
	private BoutonsAction boutonsAction = new BoutonsAction();
	private Formule formule = new Formule(boutonsAction, formuleContainer,1, null);

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

		container.add(menuGauchePan, BorderLayout.WEST);

		centre.setLayout(new BorderLayout());

		centre.add(menuHautPan.getMenuHaut(),BorderLayout.NORTH);

		centre.add(menuBasPan,BorderLayout.SOUTH);

		container.add(centre, BorderLayout.CENTER);

		// Ajout de Nico pour tester les formules
		
		partieCentrale.setBackground(Color.WHITE);
		formuleContainer.setBackground(Color.WHITE);
		bottom.setBackground(Color.WHITE);
		
		centre.add(partieCentrale, BorderLayout.CENTER);
		
		partieCentrale.setLayout(new BorderLayout());
		
		partieCentrale.add(formuleContainer, BorderLayout.CENTER);
		partieCentrale.add(bottom, BorderLayout.SOUTH);
		
		formuleContainer.setPreferredSize(new Dimension(600,450));
		formuleContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		formuleContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		
		bottom.add(boutonsAction.getBoutonFraction());
		bottom.add(boutonsAction.getBoutonIndice());
		bottom.add(boutonsAction.getBoutonAddImage());

		
		
		this.setContentPane(container); 
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);

	}       


}