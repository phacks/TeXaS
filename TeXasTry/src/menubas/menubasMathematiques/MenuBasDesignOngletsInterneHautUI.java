package menubas.menubasMathematiques;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthConstants;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.synth.SynthStyle;

import briquesElementaires.Couleur;
import briquesElementaires.Police;




public class MenuBasDesignOngletsInterneHautUI extends BasicTabbedPaneUI {

	private int maxWidth = 0;
	private int maxHeight = 0;

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	Font segoe = new Font("Segoe UI Light", Font.PLAIN, 15);
	Font segoeBold = new Font("Segoe UI Light", Font.BOLD,15);


	public MenuBasDesignOngletsInterneHautUI(){
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

		Area bordure = new Area();
		bordure.add(new Area(new Rectangle2D.Double(x,y,w,h)));
		if (!isSelected){
			surface.setColor(Color.white);
			surface.fill(bordure);
			surface.setColor(Couleur.lightgray);
			surface.drawLine(x, y+h-1, x+w, y+h-1);
		}
		else{
			surface.setColor(Couleur.bleuClairMenuGauche);
			surface.fill(bordure);	
		}
	}

	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		// Do nothing
	}


	protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
		Graphics2D shape = (Graphics2D) g;
		shape.setColor(Couleur.white);
		shape.fillRect(0, 0, tabPane.getBounds().width, tabPane.getBounds().height);
		shape.setColor(Couleur.lightgray);
		shape.drawLine(0, this.maxHeight+11, tabPane.getBounds().width, this.maxHeight+11);
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




	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,int selectedIndex, int x, int y, int w, int h) {

	}



	protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected){
		if (isSelected) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}

		Font tabFont = Police.segoe;

		g.setFont(tabFont);
		g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
	}





}