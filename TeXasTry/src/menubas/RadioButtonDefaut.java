package menubas;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import briquesElementaires.Police;

public class RadioButtonDefaut extends JRadioButton {

	// Le design des checkbox
	private ImageIcon iconeCheckBox = new ImageIcon("checkBox2/checkbox.png");
	private ImageIcon iconeCheckBoxChecked = new ImageIcon("checkBox2/checkboxChecked.png");
	private ImageIcon iconeCheckBoxRollover = new ImageIcon("checkBox2/checkboxRollover.png");
	private ImageIcon iconeCheckBoxRolloverChecked = new ImageIcon("checkBox2/checkboxRolloverChecked.png");
	private ImageIcon iconeCheckBoxPressed = new ImageIcon("checkBox2/checkboxPressed.png");

	// Constructeur
	public RadioButtonDefaut(String string, boolean b) {
		super(string,b);
		this.setBackground(Color.WHITE);
		this.setFont(Police.segoe);
		this.setVerticalTextPosition(AbstractButton.CENTER);
		this.setIcon(iconeCheckBox);
		this.setSelectedIcon(iconeCheckBoxChecked);
		this.setRolloverIcon(iconeCheckBoxRollover);
		this.setPressedIcon(iconeCheckBoxPressed);
		this.setRolloverSelectedIcon(iconeCheckBoxRolloverChecked);
		
	}

	public RadioButtonDefaut(String string){
		this(string, false);
	}




}
