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

	private int nbEntreePresse = 1;
	private boolean selected;
	private int positionCarret;
	
	private JTextArea textArea = new JTextArea();

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
		if(e.getKeyCode()==8){
			if(textArea.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
			}
		}
		if(e.getKeyCode()==10){
			if(textArea.getCaretPosition()==textArea.getText().length()){
				if(nbEntreePresse == 1){
					nbEntreePresse++;
					positionCarret = textArea.getCaretPosition();
				}
				else{
					if(textArea.getCaretPosition()==positionCarret+1){
					nbEntreePresse=1;
					ContenuEditable.addEditeurParagraphe(this);
					e.consume();
					}
					else{
						nbEntreePresse=2;
						positionCarret = textArea.getCaretPosition();
					}
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

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


}
