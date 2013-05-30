package EcranEditionCentral;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class EditeurParagraphe extends JTextArea implements KeyListener, FocusListener{

	public EditeurParagraphe(){
		super();
		this.setRows(1);
		this.setText("Votre texte ici...");
		this.setFont(Police.segoeBase);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==8){
			if(this.getText().equals("")){
				ContenuEditable.detruire(this);
				e.consume();
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
		if(this.getText().equals("Votre texte ici...")){
			this.setText("");
		}
		this.setBackground(Couleur.bleuClair.brighter());
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.setBackground(Couleur.white);
	}
	
	


}
