package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class EditeurTitre extends JPanelDef implements KeyListener, FocusListener{

	private String[] nomType = {
			"Partie", "Chapitre", "Section", "Sous-section", "Sous-sous-section"
	};
	
	private Font[] fontTitre = {
			Police.segoePartie, Police.segoeChapitre, Police.segoeSection, Police.segoeSousSection, Police.segoeSousSousSection
	};

	private Font[] fontTitreSansNumerotation = {
			Police.segoePartieItal, Police.segoeChapitreItal, Police.segoeSectionItal, Police.segoeSousSectionItal, 
			Police.segoeSousSousSectionItal
	};

	private JTextArea titreArea = new JTextArea();
	private JTextArea typeArea = new JTextArea();
	

	public EditeurTitre(int numeroHierarchie, boolean numerotation, String title){
		super(new BorderLayout());

		if(!numerotation){
			titreArea.setFont(fontTitreSansNumerotation[numeroHierarchie]);
		}
		else{
			titreArea.setFont(fontTitre[numeroHierarchie]);
		}
		
		typeArea.setFont(Police.segoeItal);

		
		titreArea.setText(title);
		typeArea.setText(nomType[numeroHierarchie]);
		


		titreArea.setLineWrap(true);
		titreArea.setWrapStyleWord(true);
		
		titreArea.addKeyListener(this);
		
		titreArea.addFocusListener(this);
		
		this.add(titreArea, BorderLayout.CENTER);
		this.add(typeArea, BorderLayout.EAST);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			ContenuEditable.addEditeurParagraphe(this);
			e.consume();
		}
		if(e.getKeyCode()==8){
			if(titreArea.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void focusGained(FocusEvent arg0) {
		if(titreArea.getText().equals("Votre titre ici")){
			titreArea.setText("");
		}
		titreArea.setBackground(Couleur.bleuClair.brighter());
		typeArea.setBackground(Couleur.bleuClair.brighter());
		this.setBackground(Couleur.bleuClair.brighter());
	}


	@Override
	public void focusLost(FocusEvent arg0) {
		titreArea.setBackground(Couleur.white);
		typeArea.setBackground(Couleur.white);
		this.setBackground(Couleur.white);
		
	}





}
