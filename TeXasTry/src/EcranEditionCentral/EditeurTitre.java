package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class EditeurTitre extends Editeur implements KeyListener, FocusListener{

	private boolean selected = false;

	private int numeroHierarchie;

	private boolean numerote = true;


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

	private int[] coordHierarchie = {0,0,0,0,0};

	private JTextArea titreArea = new JTextArea();
//	private JTextArea typeArea = new JTextArea();
	private JTextArea numArea = new JTextArea();

	public EditeurTitre(int numeroHierarchie, boolean numerotation, String title){
		super(new BorderLayout());
		this.numeroHierarchie = numeroHierarchie;
		if(!numerotation){
			numerote = false;
			titreArea.setFont(fontTitreSansNumerotation[numeroHierarchie]);
		}
		else{
			titreArea.setFont(fontTitre[numeroHierarchie]);
			numArea.setFont(fontTitre[numeroHierarchie]);
		}
//		typeArea.setFont(Police.segoeItal);		
		titreArea.setText(title);
//		typeArea.setText(nomType[numeroHierarchie]);
		titreArea.setLineWrap(true);
		titreArea.setWrapStyleWord(true);
		titreArea.setOpaque(false);
//		typeArea.setOpaque(false);
		numArea.setOpaque(false);
		numArea.setEditable(false);
		titreArea.addKeyListener(this);
		titreArea.addFocusListener(this);
		this.add(numArea, BorderLayout.WEST);
		this.add(titreArea, BorderLayout.CENTER);
//		this.add(typeArea, BorderLayout.EAST);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(selected){
			this.setBackground(Couleur.bleuTexte);
		}
		else{
			this.setBackground(Couleur.white);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// Appui sur entré
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			ContenuEditable.addEditeurParagraphe(this);
			e.consume();
		}

		// Appui sur effacer caractère
		if(e.getKeyCode()==8){
			if(titreArea.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
			}
		}

		// Appui sur tabulation
		if(e.getKeyCode()==9){
			ContenuEditable.focusNext(this);
			e.consume();
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
		this.selected = true;
		if(titreArea.getText().equals("Votre titre ici")){
			titreArea.setText("");
		}
		ContenuEditable.refocus(this);
		this.repaint();
	}


	@Override
	public void focusLost(FocusEvent arg0) {

	}

	public void setSelected(boolean b) {
		this.selected = b;
		this.repaint();
		if(b){
			this.requestFocusInWindow();
		}
	}

	public boolean isSelected(){
		return selected;
	}

	public int getNumeroHierarchie() {
		return numeroHierarchie;
	}

	public void setCoordHierarchie(int[] coordHierarchie) {
		this.coordHierarchie = coordHierarchie;
	}
	

	public boolean isNumerote() {
		return numerote;
	}


	public void renumeroter() {
		if(numerote){
			numArea.setText("");
			for (int i = 0; i <= numeroHierarchie; i++) {
				numArea.setText(numArea.getText()+coordHierarchie[i]+".");
			}
			numArea.setText(numArea.getText()+" ");
		}
	}





}
