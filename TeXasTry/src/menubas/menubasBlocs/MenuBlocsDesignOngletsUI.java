package menubas.menubasBlocs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.plaf.metal.MetalTabbedPaneUI;

import briquesElementaires.Couleur;
import briquesElementaires.Police;


public class MenuBlocsDesignOngletsUI extends MetalTabbedPaneUI{

	int maxWidth;
	public int getMaxWidth() {
		return maxWidth;
	}


	public int getMaxHeight() {
		return maxHeight;
	}



	int maxHeight;
	Font segoe = new Font("Segoe UI Light", Font.PLAIN, 15);
	Font segoeBold = new Font("Segoe UI Light", Font.BOLD,15);


	public MenuBlocsDesignOngletsUI(){
		super();
	}


	protected int calculateTabWidth(int tabPlacement, int tabIndex,FontMetrics metrics) {
		maxWidth = Math.max(maxWidth, super.calculateTabWidth(tabPlacement, tabIndex, metrics));
		return maxWidth+20;
	}

	protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		int vHeight = fontHeight;
		if (vHeight % 2 > 0)
		{
			vHeight += 1;
		}
		maxHeight = vHeight;
		return vHeight+10;
	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{

	}

	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		Graphics2D surface = (Graphics2D) g;
		surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isSelected){
			surface.setColor(Couleur.bleuClairMenuGauche);
		}
		else{
			surface.setColor(Couleur.white);
		}

		surface.fillRect(x-5, y, w+5, h);

		surface.setColor(Couleur.grisFonce);
		surface.drawLine(x+w-1, y, x+w-1, y+h);
	}

	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		// Do nothing
	}


	protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {

		int th = tabPane.getBounds().height;

		g.setColor(Couleur.white);
		g.fillRect(0, 0, this.maxTabWidth+4, tabPane.getBounds().height);

		g.setColor(Couleur.grisFonce);
		g.drawLine(this.maxTabWidth+1, 0, this.maxTabWidth+1, th);

		super.paintTabArea(g, tabPlacement, selectedIndex);

	}

	public void overrideContentBorderInsetsOfUI(){
		this.contentBorderInsets.top = 0;
		this.contentBorderInsets.left = 0;
		this.contentBorderInsets.right = 0;
		this.contentBorderInsets.bottom = 0;    
		highlight = Color.LIGHT_GRAY;
		lightHighlight = Color.LIGHT_GRAY;
		shadow = Color.LIGHT_GRAY;
		darkShadow = Color.LIGHT_GRAY;
		focus = Color.LIGHT_GRAY;
	}




	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,int selectedIndex, int x, int y, int w, int h) {
		
	}



	protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected)
	{	
		if(isSelected){
			g.setColor(Couleur.white);
		}
		else{
			g.setColor(Couleur.black);
		}
		g.setFont(Police.segoe);
		g.drawString(title, 10, (tabIndex+1)*this.maxTabHeight-textRect.height/2+textRect.height/5);
	}


}
