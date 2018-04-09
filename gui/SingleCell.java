package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import firstorderlogic.Properties;
import logikaklauzul.LogicProperties;

import gui.Override;

public class SingleCell extends JPanel {

	private static final long serialVersionUID = 5121081628171676849L;
	private Color defaultBackground;
	
	int irrigation, soilDestruction, numberOfPests;

	public SingleCell() {
		initializeMouseListener();
	}

	private void initializeMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				defaultBackground = getBackground();
				setBackground(new Color(100, 220, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultBackground);
			}
		});
	}
	
	public void refreshGroundParameters() {
		String toolTip = "<html>Nawodnienie : " + getIrrigation() + "% | " + 
						 "Zniszczenie gleby : " + getSoilDestruction() + "% | " +
						 "Liczba szkodników : " + getNumberOfPests() + " <br>";
		
		toolTip = toolTip + 
				 "Czy wymaga podlania: " + Properties.NeedsWatering(this) + "<br>" +
				 "Czy wymaga nawo¿enia: " + Properties.NeedsFertilising(this) + "<br>" +
				 "Czy ma insekty: " + Properties.SomePestsPresent(this) + "<br>" + "</html>";
		
		setToolTipText(toolTip);
		
		
	}
	
	public int getIrrigation() {
		return irrigation;
	}

	public void setIrrigation(int irrigation) {
		this.irrigation = irrigation;
	}

	public int getSoilDestruction() {
		return soilDestruction;
	}

	public void setSoilDestruction(int soilDestruction) {
		this.soilDestruction = soilDestruction;
	}

	public int getNumberOfPests() {
		return numberOfPests;
	}

	public void setNumberofPests(int numberofPests) {
		this.numberOfPests = numberofPests;
	}

	public void setTractorPositionHere() {
		setBackground(new Color(150, 25, 100));
	}

	public void setTractorPositionNotHere() {
		setBackground(Color.BLUE);
	}
	
	public void setTractorPositionWhenLeaving() {
		if( getIrrigation()==100 &&
			getSoilDestruction()==0 &&
			getNumberOfPests()==0
		  ) {
			removeAll();
			repaint();
					
			setBackground(new Color(255,255,0));
				
			add(new JLabel("N : " + Integer.toString(getIrrigation()))); // Nawodnienie
			add(new JLabel("ZZ : " + Integer.toString(getSoilDestruction()))); // Zanieszczyszczenie ziemi
			add(new JLabel("IS : " + Integer.toString(getNumberOfPests()))); // Iloœæ szkodników
			
			validate();
			}
		   else {
			removeAll();
			repaint();
			
			setBackground(new Color(70,25,5));
			
			add(new JLabel("N : " + Integer.toString(getIrrigation()))); // Nawodnienie
			add(new JLabel("ZZ : " + Integer.toString(getSoilDestruction()))); // Zanieszczyszczenie ziemi
			add(new JLabel("IS : " + Integer.toString(getNumberOfPests()))); // Iloœæ szkodników
			
			validate();	
			}
	}
	
	public void setClearCellHere() {
		setBackground(defaultBackground);
		validate();
	}
	
	public void setAlgoritmPositionHere() {
		setBackground(new Color(120, 165, 100));
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(65, 65);
	}
}