package menubas;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class TextFieldAutoSuppression extends JTextField implements MouseListener{

	private String nameDef;

	public TextFieldAutoSuppression(String str){
		super(str);
		this.nameDef = str;
		this.setFont(Police.segoe);
		this.setBorder(BorderFactory.createLineBorder(Couleur.lightgray));
		this.setHorizontalAlignment(JTextField.CENTER);
		this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if(this.getText().equals(nameDef)){
			this.setText("");
		}
		this.setBorder(BorderFactory.createLineBorder(Couleur.bleuClairMenuGauche));
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setBorder(BorderFactory.createLineBorder(Couleur.bleuMedianMenuGauche));
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBorder(BorderFactory.createLineBorder(Couleur.lightgray));
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
