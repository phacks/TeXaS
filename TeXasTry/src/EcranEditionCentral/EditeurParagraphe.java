package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import briquesElementaires.Couleur;
import briquesElementaires.Police;


//Classe générant les éditeurs de paragraphe
public class EditeurParagraphe extends Editeur implements KeyListener, FocusListener{

	private int numeroHierarchie = 1;

	private boolean selected;

	private JTextArea textArea = new JTextArea();

	private JTextArea decalageArea = new JTextArea();

	private int previousCarretPosition;


	// >>>>>>>>>>> Getters and setters
	public boolean isSelected(){
		return selected;
	}

	public String getText() {
		return textArea.getText();
	}

	private void setText(String string) {
		textArea.setText(string);

	}

	public int getNumeroHierarchie() {
		return numeroHierarchie;
	}

	public void setNumeroHierarchie(int numeroHierarchie) {
		this.numeroHierarchie = numeroHierarchie;
	}

	public void setCarretPosition(int i) {
		textArea.setCaretPosition(i);
	}

	// >>>>>>>>>>> Fin Getters and setters

	public EditeurParagraphe(){
		super(new BorderLayout());
		textArea.setRows(1);
		textArea.setText("Votre texte ici...");
		textArea.setFont(Police.segoeBase);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.addKeyListener(this);
		textArea.addFocusListener(this);
		textArea.setOpaque(false);
		this.add(textArea, BorderLayout.CENTER);
		decalageArea.setOpaque(true);
		decalageArea.setEditable(false);
		decalageArea.setForeground(Couleur.white);
		decalageArea.setFocusable(false);
		decalageArea.setText("coucou");
		this.add(decalageArea,BorderLayout.WEST);
	}

	public EditeurParagraphe(String textEditeur) {
		this();
		textArea.setText(textEditeur);
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
	public  void keyPressed(KeyEvent e) {
		// Appui sur efface
		if(e.getKeyCode()==8){
			if(textArea.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
			}
			else{
				if(textArea.getCaretPosition()==0){
					int indiceComp = ContenuEditable.getListeContenu().indexOf(this);
					if(indiceComp>0 && ContenuEditable.getListeContenu().get(indiceComp-1).getClass().toString().contains("EditeurParagraphe")){
						EditeurParagraphe tmp = (EditeurParagraphe) ContenuEditable.getListeContenu().get(indiceComp-1);
						String textPrecedent = tmp.getText();
						ContenuEditable.getListeContenu().remove(indiceComp-1);
						ContenuEditable.revalider();
						textArea.setText(textPrecedent + textArea.getText());
						textArea.setCaretPosition(textPrecedent.length());
						ContenuEditable.refocus(this);
						e.consume();
					}
				}
			}
		}

		// Appui sur entrée
		if(e.getKeyCode()==10){
			String beforeCarret = textArea.getText().substring(0, textArea.getCaretPosition());
			String afterCarret = textArea.getText().substring(textArea.getCaretPosition(),textArea.getDocument().getLength());
			textArea.setText(beforeCarret);
			ContenuEditable.addEditeurParagraphe(this, afterCarret);
			e.consume();
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


		//Appui sur flecheBas / flecheHaut - gestion avec le relachement de la touche
		if(e.getKeyCode()==40 || e.getKeyCode()==38){
			previousCarretPosition = textArea.getCaretPosition();
		}



	}

	@Override
	public  void keyReleased(KeyEvent e) {
		// Appui sur flecheBas
		if(e.getKeyCode()==40 && previousCarretPosition == textArea.getCaretPosition()){
			ContenuEditable.focusNext(this);
			e.consume();
		}

		// Appui sur flecheHaut
		if(e.getKeyCode()==38 && previousCarretPosition == textArea.getCaretPosition()){
			ContenuEditable.focusPrevious(this);
			e.consume();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		this.selected = true;

		if(this.getText().equals("Votre texte ici...")){
			this.setText("");
		}
		ContenuEditable.refocus(this);
		this.repaint();
	}



	@Override
	public void focusLost(FocusEvent e) {
		this.previousCarretPosition=-1;
	}

	public void setSelected(boolean b) {
		this.selected = b;
		this.repaint();
		if(b){
			textArea.requestFocusInWindow();
			textArea.grabFocus();
		}
	}


	public void reindenter(){
		decalageArea.setText("");
		for (int i = -1; i < numeroHierarchie; i++) {
			decalageArea.setText(decalageArea.getText()+"coucou");
		}
	}
}
