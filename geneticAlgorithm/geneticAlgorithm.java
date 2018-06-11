package com.dszi.geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

import com.dszi.gui.SingleCell;
import com.dszi.stateSpace.AAlgorithm;
import com.dszi.support.Constants;
import com.dszi.utils.Point;

public class geneticAlgorithm {

	SingleCell cellPanel[][] = new SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	// SingleCell cellPanel2[][] = new
	// SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	boolean[][] visited = new boolean[Constants.gridSizeX + 1][Constants.gridSizeY + 1];
	List<Point> pointsList = new ArrayList<Point>();

	int positionX;
	int positionY;
	int waterLevel;
	int averageWaterLevel = 0;
	int pesticideLevel;
	int averagePesticideLevel = 0;
	int fertilizerLevel;
	int averageFertilizerLevel = 0;

	public geneticAlgorithm(SingleCell[][] cellPanel) {
		this.cellPanel = cellPanel;
		// copyPanel();
		refuseBadCells();
	}

	/*
	 * public void copyPanel() { for (int i = 0; i < Constants.gridSizeX; i++) { for
	 * (int j = 0; j < Constants.gridSizeY; j++) { cellPanel2[i][j] =
	 * cellPanel[i][j]; cellPanel2[i][j] = cellPanel[i][j]; cellPanel2[i][j] =
	 * cellPanel[i][j]; } } refuseBadCells(); }
	 */

	public void refuseBadCells() {
		calculateAverages();
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				if (i != 0 && j != 0) {
					waterLevel = cellPanel[i][j].getIrrigation();
					fertilizerLevel = cellPanel[i][j].getSoilDestruction();
					pesticideLevel = cellPanel[i][j].getNumberOfPests();
					if (waterLevel < (averageWaterLevel + 30) && fertilizerLevel > 2 * averageFertilizerLevel) {
						visited[i][j] = true;
					}
					if (waterLevel < (averageWaterLevel + 30) && pesticideLevel > 2 * averagePesticideLevel) {
						visited[i][j] = true;
					}
					if (pesticideLevel > 2 * averagePesticideLevel && fertilizerLevel > 2 * averageFertilizerLevel) {
						visited[i][j] = true;
					}
				} else {
					continue;
				}
			}
		}
		refuseTheBests();
	}

	public void calculateAverages() {
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				averageWaterLevel += cellPanel[i][j].getIrrigation();
				averageFertilizerLevel += cellPanel[i][j].getSoilDestruction();
				averagePesticideLevel += cellPanel[i][j].getNumberOfPests();
			}
		}
		averageWaterLevel = averageWaterLevel / (Constants.gridSizeX * Constants.gridSizeY);
		averageFertilizerLevel = averageFertilizerLevel / (Constants.gridSizeX * Constants.gridSizeY);
		averagePesticideLevel = averagePesticideLevel / (Constants.gridSizeX * Constants.gridSizeY);
	}

	public void refuseTheBests() {
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				waterLevel = cellPanel[i][j].getIrrigation();
				fertilizerLevel = cellPanel[i][j].getSoilDestruction();
				pesticideLevel = cellPanel[i][j].getNumberOfPests();
				if (waterLevel > 95) {
					visited[i][j] = true;
				}
				if (fertilizerLevel < 5) {
					visited[i][j] = true;
				}
				if (pesticideLevel < 5) {
					visited[i][j] = true;
				}
			}
		}
		runGenetic();
	}

	public void runGenetic() {
		AAlgorithm dfs = new AAlgorithm(cellPanel, visited);
		pointsList = dfs.getPointsList();
	}

	public List<Point> getPointsList() {
		return pointsList;
	}

}
