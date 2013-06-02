package formule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menubas.menubasMathematiques.BoutonMenuMathematiqueSymbole;

/**
 * ActionListener qui permet de faire communiquer les boutons du ruban avec la formule en cours d'�dition
 * @author nicolasgoutay
 *
 */
public class ActionListenerFormule implements ActionListener{
	
	static Formule formuleEnCours; 
	private String type;
	
	public ActionListenerFormule(String type){
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if (this.type.equals("symbole")){
			formuleEnCours.addImage(((BoutonMenuMathematiqueSymbole) arg0.getSource()).getFichierBouton());
		}
		
		if (this.type.equals("structure")){
			formuleEnCours.addStructure(((BoutonMenuMathematiqueSymbole) arg0.getSource()).getFichierBouton().getName().split("\\.")[0]);
		}
		
		
		
	}

	public void setFormuleEnCours(Formule formule) {
		this.formuleEnCours = formule;
		
	}

}
