package GestionEcran;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Listener pour les boutons du menu gauche, pour le switch entre les écrans du logiciel (principal, nouveau document, etc.)
 * @author Kilian
 *
 */
public class boutonMenuGaucheListener implements ActionListener {

	private static EcranCentral ecranCentral;
	private static boolean[] etatBouton;

	/** Gestion indicateur des boutons
	 * 
	 */
	private static boolean monProjetCheck= false;

	public boutonMenuGaucheListener(EcranCentral ecranCentral){
		boutonMenuGaucheListener.ecranCentral  = ecranCentral;
		etatBouton = new boolean[6];
	}

	public boutonMenuGaucheListener(){
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().toString().contains("Mon Projet")){
			changeEtat(0);
		}
		if(e.getSource().toString().contains("Préférences")){
			changeEtat(1);
		}
		if(e.getSource().toString().contains("Personnaliser")){
			changeEtat(2);
		}
		if(e.getSource().toString().contains("Editer le code LaTeX")){
			changeEtat(3);
		}
		if(e.getSource().toString().contains("Exporter en PDF")){
			changeEtat(4);
		}
		if(e.getSource().toString().contains("Aide")){
			changeEtat(5);
		}
	}

	public void changeEtat(int indicateurBouton){
		if(etatBouton[indicateurBouton]){
			EcranCentral.containerCentreLayout.show(ecranCentral, EcranCentral.listeDesContenus[0]);
			etatBouton[indicateurBouton]=false;
		}
		else{
			EcranCentral.containerCentreLayout.show(ecranCentral, EcranCentral.listeDesContenus[indicateurBouton+1]);
			etatBouton[indicateurBouton]=true;
		}
		for (int i = 0; i < etatBouton.length; i++) {
			etatBouton[i] = (i==indicateurBouton) ? etatBouton[i] : false; 
		}
	}

}
