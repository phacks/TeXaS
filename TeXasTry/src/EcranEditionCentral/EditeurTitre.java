package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private boolean masque = false;

	private int numeroHierarchie;

	private boolean numerote = true;


	private Font[] fontTitre = {
			Police.segoePartie, Police.segoeChapitre, Police.segoeSection, Police.segoeSousSection, Police.segoeSousSousSection
	};

	private int[] coordHierarchie = {0,0,0,0,0};

	private JTextArea decalageArea = new JTextArea();
	private JTextArea titreArea = new JTextArea();
	private JTextArea numArea = new JTextArea();

	private BouttonMasquerParagraphe boutonAffMasq;

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


		partieCentrale.setOpaque(false);

		boutonAffMasq = new BouttonMasquerParagraphe(numeroHierarchie);
		boutonAffMasq.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(boutonAffMasq.isMasque()){
					masquer(false);
				}
				else{
					masquer(true);
				}
			}

		});

		if(!numerote){
			numArea.setForeground(Couleur.white);
		}
		if(numeroHierarchie<2){
			if(numeroHierarchie==0){
				numArea.setText("Partie "+coordHierarchie[0]);
			}
			else{
				numArea.setText("Chapitre "+ coordHierarchie[0]);
			}
			partieCentrale.add(numArea, BorderLayout.NORTH);
			partieCentrale.add(titreArea, BorderLayout.SOUTH);
			partieGauche.add(boutonAffMasq, BorderLayout.CENTER);
			partieGauche.add(decalageArea, BorderLayout.WEST);
		}
		else{
			for (int i = 1; i <= numeroHierarchie; i++) {
				numArea.setText(numArea.getText()+coordHierarchie[i]+".");
			}
			partieGauche.add(numArea, BorderLayout.EAST);
			partieCentrale.add(titreArea, BorderLayout.SOUTH);
			partieGauche.add(boutonAffMasq, BorderLayout.CENTER);
			partieGauche.add(decalageArea, BorderLayout.WEST);
		}

		this.add(partieGauche,BorderLayout.WEST);
		this.add(partieCentrale,BorderLayout.CENTER);
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
			if(masque){
				ContenuEditable.masquer(this, false);
			}
			ContenuEditable.masquer(this, false);
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

		// Appui sur tabulation ou flecheBas
		if(e.getKeyCode()==9 || e.getKeyCode()==40){
			ContenuEditable.focusNext(this);
			e.consume();
		}

		//Appui sur PageUp
		if(e.getKeyCode()==33 || e.getKeyCode()==38){
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
		titreArea.setCaretPosition(titreArea.getDocument().getLength());
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
		for (int i = 0; i < coordHierarchie.length; i++) {
			this.coordHierarchie[i] = coordHierarchie[i];
		}
	}

	public int[] getCoordHierarchie() {
		return this.coordHierarchie;
	}



	public boolean isNumerote() {
		return numerote;
	}


	public void renumeroter() {
		numArea.setText("");
		if(numeroHierarchie<2){
			if(numeroHierarchie==1){
				numArea.setText("Chapitre "+coordHierarchie[1]);
			}
			else{
				numArea.setText("Partie "+coordHierarchie[0]);
			}
		}
		else{
			for (int j = 1; j <= numeroHierarchie; j++) {
				numArea.setText(numArea.getText()+coordHierarchie[j]+".");
			}
		}
		if(!numerote){
			if(numeroHierarchie<2){
				if(numeroHierarchie==1){
					numArea.setForeground(Couleur.gray.brighter());
					numArea.setText("Chapitre");
				}
				else{
					numArea.setForeground(Couleur.gray.brighter());
					numArea.setText("Partie");
				}
			}
		}
	}


	public String getText(){
		return titreArea.getText();
	}

	private void masquer(boolean b) {
		ContenuEditable.masquer(this, b);
	}

	public void setBoutonMasque(boolean b){
		this.boutonAffMasq.setMasque(b);
	}

	public boolean isMasque() {
		return masque;
	}

	public void setMasque(boolean masque) {
		this.masque = masque;
	}

}
