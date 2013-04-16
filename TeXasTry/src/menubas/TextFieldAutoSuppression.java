package menubas;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import briquesElementaires.Police;

public class TextFieldAutoSuppression extends JTextField implements MouseListener{

	private String nameDef;
	
	public TextFieldAutoSuppression(String str){
		super(str);
		this.nameDef = str;
		this.setFont(Police.segoe);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if(this.getText().equals(nameDef)){
			this.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	


}
