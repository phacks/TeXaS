package formule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menubas.menubasMathematiques.BoutonMenuMathematiqueSymbole;

public class ActionListenerFormule implements ActionListener{
	
	static Formule formuleEnCours; 
	private String type;
	
	public ActionListenerFormule(String type){
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (this.type.equals("symbole")){
			System.out.println(((BoutonMenuMathematiqueSymbole) arg0.getSource()).getFichierBouton().getName());
		}
		
		formuleEnCours.addImage(((BoutonMenuMathematiqueSymbole) arg0.getSource()).getFichierBouton());
		
	}

	public void setFormuleEnCours(Formule formule) {
		this.formuleEnCours = formule;
		
	}

}
