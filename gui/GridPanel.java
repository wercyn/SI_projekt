package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import com.dszi.decision_tree.TreeAlgorithm;
import com.dszi.geneticAlgorithm.geneticAlgorithm;
import com.dszi.neuro.NeuroAlgorithm;
import com.dszi.randomizer.RandomGroundGenerator;
import com.dszi.stateSpace.AAlgorithm;
import com.dszi.support.Constants;
import com.dszi.tractor.TractorMovement;
import com.dszi.utils.Point;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

	SingleCell cellPanel[][] = new SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	List<Point> aPointsList = new ArrayList<Point>();
	List<Point> treePointsList = new ArrayList<Point>();
	List<Point> geneticPointsList = new ArrayList<Point>();
	List<Point> neuroPointsList = new ArrayList<Point>();

	public GridPanel() {
		initializeGridView();
		initializeGeneratedData();
	}

	private void initializeGridView() {
		Border border = null;
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		for (int row = 0; row < Constants.gridSizeX; row++) {
			for (int col = 0; col < Constants.gridSizeY; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				cellPanel[row][col] = new SingleCell();
				border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
				cellPanel[row][col].setBorder(border);
				add(cellPanel[row][col], gbc);
			}
		}
	}
	
	public void initializeGeneratedData() {
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				int genIrrigation = RandomGroundGenerator.generateIrrigation();
				int genSoilDesctruction = RandomGroundGenerator.generateSoilDesctruction();
				int genNumberOfPests = RandomGroundGenerator.generateNumberOfPests();
				
				cellPanel[i][j].setIrrigation(genIrrigation);
				cellPanel[i][j].setSoilDestruction(genSoilDesctruction);
				cellPanel[i][j].setNumberofPests(genNumberOfPests);
				cellPanel[i][j].refreshGroundParameters();
				
				cellPanel[i][j].add(new JLabel("N : " + Integer.toString(genIrrigation))); // Nawodnienie
				cellPanel[i][j].add(new JLabel("ZZ : " + Integer.toString(genSoilDesctruction))); // Zanieszczyszczenie ziemi
				cellPanel[i][j].add(new JLabel("IS : " + Integer.toString(genNumberOfPests))); // Iloœæ szkodników
			}
		}
	}
	
	public void refresh() {
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				if(
				cellPanel[i][j].getIrrigation()==100 &&
				cellPanel[i][j].getSoilDestruction()==0 &&
				cellPanel[i][j].getNumberOfPests()==0
				) {
				
				cellPanel[i][j].removeAll();
				cellPanel[i][j].repaint();
					
				cellPanel[i][j].setBackground(new Color(255,255,0));
				
				cellPanel[i][j].add(new JLabel("N : " + Integer.toString(cellPanel[i][j].getIrrigation()))); // Nawodnienie
				cellPanel[i][j].add(new JLabel("ZZ : " + Integer.toString(cellPanel[i][j].getSoilDestruction()))); // Zanieszczyszczenie ziemi
				cellPanel[i][j].add(new JLabel("IS : " + Integer.toString(cellPanel[i][j].getNumberOfPests()))); // Iloœæ szkodników
			
				cellPanel[i][j].validate();
				}
				else {
				
				cellPanel[i][j].removeAll();
				cellPanel[i][j].repaint();
					
				cellPanel[i][j].add(new JLabel("N : " + Integer.toString(cellPanel[i][j].getIrrigation()))); // Nawodnienie
				cellPanel[i][j].add(new JLabel("ZZ : " + Integer.toString(cellPanel[i][j].getSoilDestruction()))); // Zanieszczyszczenie ziemi
				cellPanel[i][j].add(new JLabel("IS : " + Integer.toString(cellPanel[i][j].getNumberOfPests()))); // Iloœæ szkodników
			
				cellPanel[i][j].validate();	
				}
			}
		}
		
	}
	
	public void clearGridView() {
		for (int row = 0; row < Constants.gridSizeX; row++) 
			for (int col = 0; col < Constants.gridSizeY; col++) {
				cellPanel[row][col].setClearCellHere();
				cellPanel[row][col].validate();
			}
	}

	
}