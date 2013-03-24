package menubas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class RadioButtonMenuBas extends JRadioButton {

	// Le design des checkbox
	Font segoe = new Font("Segoe UI Light", Font.PLAIN, 15);
	
	private ImageIcon iconeCheckBox = new ImageIcon("checkbox.png");
	private ImageIcon iconeCheckBoxChecked = new ImageIcon("checkboxChecked.png");
	private ImageIcon iconeCheckBoxRollover = new ImageIcon("checkboxRollover.png");
	private ImageIcon iconeCheckBoxRolloverChecked = new ImageIcon("checkboxRolloverChecked.png");
	private ImageIcon iconeCheckBoxPressed = new ImageIcon("checkboxPressed.png");

	// Constructeur
	public RadioButtonMenuBas(String string, boolean b) {
		super(string,b);
		this.setBackground(Color.WHITE);
		this.setIcon(iconeCheckBox);
		this.setFont(segoe);
		this.setSelectedIcon(iconeCheckBoxChecked);
		this.setRolloverIcon(iconeCheckBoxRollover);
		this.setPressedIcon(iconeCheckBoxPressed);
		this.setRolloverSelectedIcon(iconeCheckBoxRolloverChecked);
	}

	public RadioButtonMenuBas(String string){
		this(string, false);
	}




}
