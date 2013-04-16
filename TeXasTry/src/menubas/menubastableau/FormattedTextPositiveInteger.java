package menubas.menubastableau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import briquesElementaires.Couleur;
import briquesElementaires.Police;

public class FormattedTextPositiveInteger extends JFormattedTextField implements KeyListener, MouseListener{

	public FormattedTextPositiveInteger(NumberFormat integerInstance) {
		super(integerInstance);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(Police.segoe);
		this.setBorder(BorderFactory.createLineBorder(Couleur.lightgray));
		this.addKeyListener(this);
		this.addMouseListener(this);
	}

	public void keyReleased(KeyEvent event) {
		if(!(event.getKeyCode()==10) && !isNumericPos(event.getKeyChar()))
			this.setText(this.getText().replace(String.valueOf(event.getKeyChar()),""));
	}


	public void keyPressed(KeyEvent event) {}

	public void keyTyped(KeyEvent event) {}

	//Retourne true si le paramètre est numérique, false dans le cas contraire
	private boolean isNumericPos(char carac){
		try {
			return (Integer.parseInt(String.valueOf(carac))>=0);
		}
		catch (NumberFormatException e) {
			return false;
		}
	}


	public void up() {
		int nb = 0;
		try {
			nb = new Integer(this.getText());
		} catch (Exception e) {
			nb=0;
		}
		nb++;
		String str = new String();
		str += nb;
		this.setText(str);
	}

	public void down() {
		int nb = 0;
		try {
			nb = new Integer(this.getText());
		} catch (Exception e) {
			nb=1;
		}
		if (nb>1){
			nb--;
			String str = new String();
			str += nb;
			this.setText(str);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
