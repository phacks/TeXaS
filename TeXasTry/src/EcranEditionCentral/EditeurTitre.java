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
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class EditeurTitre extends Editeur implements KeyListener, FocusListener{

	private boolean selected = false;

	private int numeroHierarchie;

	private boolean numerote = true;


	private Font[] fontTitre = {
			Police.segoePartie, Police.segoeChapitre, Police.segoeSection, Police.segoeSousSection, Police.segoeSousSousSection
	};

	private int[] coordHierarchie = {0,0,0,0,0};

	private JTextArea decalageArea = new JTextArea();
	private JTextArea titreArea = new JTextArea();
	private JTextArea numArea = new JTextArea();

	private JPanelDef partieGauche = new JPanelDef(new BorderLayout());
	private JPanelDef partieCentrale = new JPanelDef(new BorderLayout());

	public EditeurTitre(int numeroHierarchie, boolean numerotation, String title){
		super(new BorderLayout());
		this.numeroHierarchie = numeroHierarchie;
		if(!numerotation){
			numerote = false;
		}
		titreArea.setFont(fontTitre[numeroHierarchie]);
		numArea.setFont(fontTitre[numeroHierarchie]);

		if(!title.equals("Votre titre ici")){
			titreArea.setText(title);
		}
		titreArea.setLineWrap(true);
		titreArea.setWrapStyleWord(true);
		titreArea.setOpaque(false);
		numArea.setOpaque(false);
		numArea.setEditable(false);
		numArea.setFocusable(false);
		numArea.setForeground(Couleur.gray.brighter());

		decalageArea.setEditable(false);
		decalageArea.setForeground(Couleur.white);
		decalageArea.setBackground(Couleur.white);
		decalageArea.setFocusable(false);
		for (int i = 0; i < numeroHierarchie; i++) {
			decalageArea.setText(decalageArea.getText()+"coucou");
		}

		titreArea.addKeyListener(this);
		titreArea.addFocusListener(this);
		this.renumeroter();

		partieCentrale.setOpaque(false);
		this.add(partieGauche,BorderLayout.WEST);
		this.add(partieCentrale,BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(selected){
			if(!numerote){
				numArea.setForeground(Couleur.bleuTexte);
			}
			this.setBackground(Couleur.bleuTexte);
		}
		else{
			if(!numerote){
				numArea.setForeground(Couleur.white);
			}
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

		//Appui sur PageUp
		if(e.getKeyCode()==33){
			ContenuEditable.focusPrevious(this);
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
			titreArea.requestFocusInWindow();
			titreArea.grabFocus();
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
		partieGauche.add(numArea, BorderLayout.EAST);
		partieGauche.add(decalageArea,BorderLayout.WEST);
		partieGauche.setOpaque(false);
		if(!numerote){
			numArea.setForeground(Couleur.white);
		}
		numArea.setText("");
		switch (numeroHierarchie) {
		case 0:
			numArea.setText("Partie "+coordHierarchie[0]);
			partieCentrale.removeAll();
			partieCentrale.add(numArea, BorderLayout.NORTH);
			partieCentrale.add(titreArea, BorderLayout.CENTER);
			partieCentrale.revalidate();
			break;
		case 1:
			numArea.setText("Chapitre "+coordHierarchie[1]);
			partieCentrale.removeAll();
			partieCentrale.add(numArea, BorderLayout.NORTH);
			partieCentrale.add(titreArea, BorderLayout.CENTER);
			partieCentrale.revalidate();
			break;
		default:
			for (int i = 1; i <= numeroHierarchie; i++) {
				numArea.setText(numArea.getText()+coordHierarchie[i]+".");
			}
			numArea.setText(numArea.getText()+" ");
			partieCentrale.removeAll();
			partieCentrale.add(titreArea, BorderLayout.CENTER);
			partieCentrale.revalidate();
			partieGauche.removeAll();
			partieGauche.add(decalageArea, BorderLayout.WEST);
			partieGauche.add(numArea, BorderLayout.EAST);
			partieGauche.revalidate();
			break;
		}
	}





}
