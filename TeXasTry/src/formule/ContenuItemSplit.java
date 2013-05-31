package formule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class ContenuItemSplit extends JPanel{

	private BoutonsAction ba;
	private JPanel panelHaut = new JPanel();
	private Formule splitHaut;
	private JPanel panelBas = new JPanel();
	private Formule splitBas;
	private Item itemParent;
	private PanelLigneHorizontale split = new PanelLigneHorizontale();
	private ItemLayeredPane layeredPane;
	private String famille = ""; 
	private String type = "";
	private Formule[] arraySplit;

	public ContenuItemSplit(ItemLayeredPane layeredPane, String type){


		this.setBackground(Color.WHITE);
		this.famille = type.split("-")[0];
		this.type = type.split("-")[1];
		
		this.layeredPane = layeredPane;

		this.setBounds(5, 2, this.layeredPane.getPreferredSize().width - 10, this.layeredPane.getPreferredSize().height-3);
		this.ba = ba;

		if (famille.equals("split")){

			if (this.type.equals("indice")){
				
				panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
				
				this.splitBas = new Formule(this.panelBas,this.layeredPane.getDepth() +1, this.layeredPane);

				this.arraySplit = new Formule[1];
				arraySplit[0] = splitBas;
				
				this.setLayout(new BorderLayout());
				
				this.add(panelBas, BorderLayout.SOUTH);
				
				

			}

			if (this.type.equals("exposant")){
				
				panelHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
				
				this.splitHaut = new Formule(this.panelHaut,this.layeredPane.getDepth() +1, this.layeredPane);
				
				this.arraySplit = new Formule[1];
				arraySplit[0] = splitHaut;
				
				this.setLayout(new BorderLayout());
				
				this.add(panelHaut, BorderLayout.NORTH);
			

			}

			if (this.type.equals("indiceexposant")){
				
				panelHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
				panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));


				this.splitHaut = new Formule(this.panelHaut,this.layeredPane.getDepth() +1, this.layeredPane);

				Formule[] arraySplitHaut = new Formule[1];
				arraySplitHaut[0] = splitHaut;

				this.splitBas = new Formule(this.panelBas,this.layeredPane.getDepth() +1, this.layeredPane);

				Formule[] arraySplitBas = new Formule[1];
				arraySplitBas[0] = splitBas;


				this.setLayout(new BorderLayout());
				
				this.add(panelHaut, BorderLayout.NORTH);
				this.add(panelBas, BorderLayout.SOUTH);


				this.arraySplit = new Formule[2];
				arraySplit[0] = splitHaut;
				arraySplit[1] = splitBas;
				

			}

			if (this.type.equals("fraction")){
				
				split.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, 5));

				panelHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
				panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));


				this.splitHaut = new Formule(this.panelHaut,this.layeredPane.getDepth() +1, this.layeredPane);

				Formule[] arraySplitHaut = new Formule[1];
				arraySplitHaut[0] = splitHaut;

				this.splitBas = new Formule(this.panelBas,this.layeredPane.getDepth() +1, this.layeredPane);

				Formule[] arraySplitBas = new Formule[1];
				arraySplitBas[0] = splitBas;


				this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

				this.panelHaut.setAlignmentX((float) 0.5);
				this.panelBas.setAlignmentX((float) 0.5);

				this.add(panelHaut);
				this.add(split);
				this.add(panelBas);

				this.arraySplit = new Formule[2];
				arraySplit[0] = splitHaut;
				arraySplit[1] = splitBas;
				

			}

			AdapteWidthItem adapteWidthItem = new AdapteWidthItem(this.arraySplit, this.layeredPane);
			

		}





	}

	public void redefinirApparence(){

		this.setBounds(5, 2, this.layeredPane.getPreferredSize().width - 10, this.layeredPane.getPreferredSize().height-3);
		//		panelHaut.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
		//	    panelBas.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
		//	    split.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, 5));

	}

	public String getText(){
		return "";
	}

	public String getType(){
		return this.type;
	}

	public Formule[] getArraySplit(){
		return this.arraySplit;
	}
}
