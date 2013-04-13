package menubas;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JPanel;

import menubas.menubastableau.MenuBasTableaux;



public class MenuBas extends JPanel{

	// Les JPanels

	// général
	private JPanel menuBas = new JPanel();

	// Menu Bas partie basse

	private MenuBasBase menuBasChoix = new MenuBasBase();
	private int indexOnglet = 0;

	// Menu Bas onglets

	private MenuBasTitres menuBasTitres = new MenuBasTitres();
	private MenuBasFigures menuBasFigures = new MenuBasFigures();
	private MenuBasTableaux menuBasTableaux;
	private MenuBasBlocs menuBasBlocs = new MenuBasBlocs();

	//Les boutons

	private BoutonMenuBas boutInserer = new BoutonMenuBas("Insérer");

	public MenuBas() {

		// Instanciation risquée
		try {
			menuBasTableaux= new MenuBasTableaux();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Gestion des onglets
		menuBasChoix.add("Titres", menuBasTitres);
		menuBasChoix.add("Mathématiques", new JPanel());
		menuBasChoix.add("Figures", menuBasFigures);
		menuBasChoix.add("Tableaux", menuBasTableaux);
		menuBasChoix.add("Blocs", menuBasBlocs);



		// Gestion du menu de base
		menuBas.setLayout(new BorderLayout());
		menuBas.add(boutInserer,BorderLayout.NORTH);
		menuBas.revalidate();

		boutInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!boutInserer.isCheck()){
					menuBas.removeAll();

					//FAUT IL OUI OU NON REINISTIALISER LE MENU LORS DE LA FERMETURE DU RUBAN ?
					menuBasTitres.reinistialisation();
					menuBasFigures.reinistialisation();

					menuBas.add(boutInserer,BorderLayout.NORTH);
					menuBas.add(menuBasChoix, BorderLayout.SOUTH);
					menuBas.revalidate();
					boutInserer.check();
				}
				else{
					menuBas.removeAll();
					menuBas.add(boutInserer,BorderLayout.NORTH);
					menuBas.revalidate();
					boutInserer.uncheck();
				}
			}
		});

	}


	public MenuBasBase getMenuBasChoix() {
		return menuBasChoix;
	}


	public JPanel getMenuBas() {
		return this.menuBas;
	}


}
