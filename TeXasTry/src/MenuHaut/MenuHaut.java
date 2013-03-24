package MenuHaut;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.Border;


public class MenuHaut extends JPanel {

	// Les JPanels

	private JPanel menuHaut = new JPanel();

	private JPanel menuHautBase = new JPanel();

	private JPanel menuHautFormattage = new JPanel();

	// Les boutons

	// de base
	private BoutonMenuHaut boutMiseEnForme = new BoutonMenuHaut("Mise en Forme");

	// du menu Formattage
	private BoutonMenuFormattage boutGras = new BoutonMenuFormattage("Gras");
	private BoutonMenuFormattage boutItalique = new BoutonMenuFormattage("Italique");
	private BoutonMenuFormattage boutSouligne = new BoutonMenuFormattage("Souligne");
	private BoutonMenuFormattage boutCouleur = new BoutonMenuFormattage("Couleur");
	private BoutonMenuFormattage boutPoliceUp = new BoutonMenuFormattage("PoliceUp");
	private BoutonMenuFormattage boutPoliceDown = new BoutonMenuFormattage("PoliceDown");
	private BoutonMenuFormattage boutEffacerMeF = new BoutonMenuFormattage("EffacerMiseEnForme");

	private BoutonMenuFormattage[] mesBoutonsFormatage = {
			boutGras,boutItalique,boutSouligne,boutCouleur,boutPoliceUp,boutPoliceDown,boutEffacerMeF};

	public MenuHaut() {
		menuHaut.setLayout(new BorderLayout());

		menuHautBase.add(boutMiseEnForme);

		menuHaut.add(menuHautBase, BorderLayout.SOUTH);
		menuHaut.revalidate();

		// Menu de mise en forme
		for (int i = 0; i < mesBoutonsFormatage.length; i++) {
			menuHautFormattage.add(mesBoutonsFormatage[i]);
		}

		boutMiseEnForme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!boutMiseEnForme.isCheck()){
					boutMiseEnForme.check();
					menuHaut.removeAll();
					menuHaut.add(menuHautFormattage, BorderLayout.NORTH);
					menuHaut.add(menuHautBase, BorderLayout.SOUTH);
					menuHaut.revalidate();
				}
				else {
					boutMiseEnForme.uncheck();
					menuHaut.removeAll();
					menuHaut.add(menuHautBase, BorderLayout.SOUTH);
					menuHaut.revalidate();
					}
			}
		});
	}

	public Component getMenuHaut() {
		return this.menuHaut;
	}



}
