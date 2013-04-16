package menubas;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import briquesElementaires.Couleur;

public class RadioButtonReinit extends JRadioButton{

	private ImageIcon iconeCheckBox = new ImageIcon("checkBox2/checkbox.png");
	private ImageIcon iconeCheckBoxChecked = new ImageIcon("checkBox2/checkboxChecked.png");
	private ImageIcon iconeCheckBoxRollover = new ImageIcon("checkBox2/checkboxRollover.png");
	private ImageIcon iconeCheckBoxRolloverChecked = new ImageIcon("checkBox2/checkboxRolloverChecked.png");
	private ImageIcon iconeCheckBoxPressed = new ImageIcon("checkBox2/checkboxPressed.png");

	// Constructeur
	public RadioButtonReinit() {
		super();
		this.setSelected(true);
		this.setToolTipText("Si coch�, le ruban se r�inistialise lors de sa fermure (remise � z�ro des param�tres)");
		this.setVerticalTextPosition(AbstractButton.CENTER);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setIcon(iconeCheckBox);
		this.setSelectedIcon(iconeCheckBoxChecked);
		this.setRolloverIcon(iconeCheckBoxRollover);
		this.setPressedIcon(iconeCheckBoxPressed);
		this.setRolloverSelectedIcon(iconeCheckBoxRolloverChecked);
	}

}
