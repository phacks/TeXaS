package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import briquesElementaires.Couleur;
import briquesElementaires.Police;



public class EditeurParagraphe extends Editeur implements KeyListener, FocusListener{

	private int numeroHierarchie = 1;

	//	private int nbEntreePresse = 1;
	private boolean selected;
	private int positionCarret;

	private JTextArea textArea = new JTextArea();
	private JTextArea decalageArea = new JTextArea();

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
		if(e.getKeyCode()==8){
			if(textArea.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
			}
		}
		if(e.getKeyCode()==10){
			ContenuEditable.addEditeurParagraphe(this);
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
	}

	@Override
	public  void keyReleased(KeyEvent e) {

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

	}

	public void setSelected(boolean b) {
		this.selected = b;
		this.repaint();
		if(b){
			textArea.requestFocusInWindow();
			textArea.grabFocus();
		}
	}

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

	public void reindenter(){
		decalageArea.setText("");
		for (int i = -1; i < numeroHierarchie; i++) {
			decalageArea.setText(decalageArea.getText()+"coucou");
		}
	}


}
