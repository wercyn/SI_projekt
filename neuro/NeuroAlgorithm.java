package com.dszi.neuro;

import java.util.ArrayList;
import java.util.List;

import com.dszi.gui.SingleCell;
import com.dszi.support.Constants;
import com.dszi.tractor.Tractor;
import com.dszi.utils.Point;
import com.dszi.utils.RandomUtils;
import com.dszi.neuro.GradientMethod;
import com.dszi.neuro.AssociationModel;

public class NeuroAlgorithm {

	Tractor tractor = new Tractor();
	SingleCell cellPanel[][] = new SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	List<Point> pointsList = new ArrayList<Point>();
	GradientMethod GM = new GradientMethod();
	AssociationModel AM = new AssociationModel();

	boolean[][] visited = new boolean[Constants.gridSizeX][Constants.gridSizeY];

	boolean shortage_flag = false, stop_flag = false;

	int positionX = 0;
	int positionY = 0;
	int waterLevel;
	int pesticideLevel;
	int fertilizerLevel;

	public NeuroAlgorithm(SingleCell[][] cellPanel) {
		stop_flag = true;
		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				if (cellPanel[i][j].getIrrigation() != 100 || cellPanel[i][j].getSoilDestruction() != 0
						|| cellPanel[i][j].getNumberOfPests() != 0)
					stop_flag = false;
			}
		}

		if (stop_flag == true)
			System.out.println("Algorytm zakoñczy³ dzia³anie - wszystkie pola w porz¹dku!");
		else {
			this.cellPanel = cellPanel;

			initializeTractor();
			if (isNotOutOfBounds(positionX, positionY) == true)
				logic(0, 0, cellPanel);
			else
				System.out.println("--NA--isNotOutOfBounds--values out of bound.");

			while (hasSupplies() == true && shortage_flag == false && stop_flag == false) {
				logic(positionX, positionY, cellPanel);

				stop_flag = true;
				for (int i = 0; i < Constants.gridSizeX; i++) {
					for (int j = 0; j < Constants.gridSizeY; j++) {
						if (cellPanel[i][j].getIrrigation() != 100 || cellPanel[i][j].getSoilDestruction() != 0
								|| cellPanel[i][j].getNumberOfPests() != 0)
							stop_flag = false;
					}
				}
			}
		}
	}

	public void logic(int positionX, int positionY, SingleCell[][] cellpanel) {
		int current_x = positionX, current_y = positionY, target_x, target_y;
		Point TargetPoint;

		TargetPoint = GM.GradientMethodOutput(positionX, positionY, cellpanel, visited);

		target_x = TargetPoint.getX();
		target_y = TargetPoint.getY();

		continuewithoutCalculations(current_x, current_y);

		while (current_x != target_x) {
			if (target_x > current_x)
				current_x++;
			else
				current_x--;
			continuewithoutCalculations(current_x, current_y);
		}

		while (current_y != target_y) {
			if (target_y > current_y)
				current_y++;
			else
				current_y--;
			continuewithoutCalculations(current_x, current_y);
		}

		calculateTractorResources(current_x, current_y);

	}

	private void continuewithoutCalculations(int x, int y) {
		positionX = x;
		positionY = y;
		// visited[x][y] = true;

		pointsList.add(new Point(x, y));
	}

	private void calculateTractorResources(int x, int y) {
		positionX = x;
		positionY = y;
		if ((waterLevel - cellPanel[x][y].getIrrigation()) >= 0
				&& (pesticideLevel - cellPanel[x][y].getNumberOfPests()) >= 0
				&& (fertilizerLevel - cellPanel[x][y].getSoilDestruction()) >= 0) {
			waterLevel = waterLevel - (100 - cellPanel[x][y].getIrrigation());
			pesticideLevel = pesticideLevel - cellPanel[x][y].getNumberOfPests();
			fertilizerLevel = fertilizerLevel - cellPanel[x][y].getSoilDestruction();
			visited[x][y] = true;

			pointsList.add(new Point(x, y));

			updateCell(x, y);
			showResources();
		}
		if ((waterLevel - cellPanel[x][y].getIrrigation()) < 0
				|| (pesticideLevel - cellPanel[x][y].getNumberOfPests()) < 0
				|| (fertilizerLevel - cellPanel[x][y].getSoilDestruction()) < 0) {
			double[] proposedComponent = new double[201]; // from current waterLevel-100 to current waterLevel+100
			double[] idealComponent = new double[201]; // same here

			for (int i = 0; i < 201; i++) {
				if (RandomUtils.getRandomNumber() <= 50)
					proposedComponent[i] = -1;
				else
					proposedComponent[i] = 1;
			}

			// Water's ideal component calculations:
			int position = 0, match = waterLevel - cellPanel[x][y].getIrrigation();
			
			if (match < 0 && match > -102) {
				match = -1 * match; // how much we lacked to water the last tile.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] WaterUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorWaterLevel += match;
			}
			if (match >= 0 && match < 102) {
				match = -1 * match; // how much spare water we had.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] WaterUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorWaterLevel -= match;
			}

			// Fertiliser's ideal component calculations:

			match = fertilizerLevel - cellPanel[x][y].getSoilDestruction();
			if (match < 0 && match > -102) {
				match = -1 * match; // how much we lacked to fertilise the last tile.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] FertiliserUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorFertilizerLevel += match;
			}

			if (match >= 0 && match < 102) {
				match = -1 * match; // how much spare fertiliser we had.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] FertiliserUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorFertilizerLevel -= match;
			}

			// Pesticide's ideal component calculations:

			match = pesticideLevel - cellPanel[x][y].getNumberOfPests();
			if (match < 0 && match > -102) {
				match = -1 * match; // how much we lacked to water the last tile.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] PesticideUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorPesticideLevel += match;
			}
			if (match >= 0 && match < 102) {
				match = -1 * match; // how much spare water we had.
				position = 101 + match;

				idealComponent[position] = 1;
				for (int i = 0; i < 201; i++) {
					if (i != position)
						idealComponent[i] = -1;
				}
				//double[] PesticideUpgrade = AM.AssocMod(idealComponent, proposedComponent);

				Constants.tractorPesticideLevel -= match;
			}

			shortage_flag = true;
		}
		;
	}

	private void updateCell(int x, int y) {
		cellPanel[x][y].setIrrigation(100);
		cellPanel[x][y].setNumberofPests(0);
		cellPanel[x][y].setSoilDestruction(0);
		cellPanel[x][y].refreshGroundParameters();
	}

	private boolean hasSupplies() {
		if (waterLevel < 0 || pesticideLevel < 0 || fertilizerLevel < 0)
			return false;
		return true;
	}

	private void initializeTractor() {
		this.positionX = tractor.getTractorPostionX();
		this.positionY = tractor.getTractorPositionY();
		this.waterLevel = tractor.getWaterLevel();
		this.pesticideLevel = tractor.getPesticideLevel();
		this.fertilizerLevel = tractor.getFertilizerLevel();
	}

	private void showResources() {
		System.out.println("-----------------------");
		System.out.println("Sprawdzanie stanu pierwszej pozycji ");
		System.out.println("Aktualna pozycja : " + positionX + " " + positionY);
		System.out.println("Pozosta³e zasoby traktora : ");
		System.out.println("Woda " + waterLevel);
		System.out.println("Pestycydy " + pesticideLevel);
		System.out.println("Nawóz " + fertilizerLevel);
	}

	private boolean isNotOutOfBounds(int x, int y) {
		if (x >= 0 && y >= 0 && x < Constants.gridSizeX && y < Constants.gridSizeY)
			return true;
		return false;
	}

	public List<Point> getPointsList() {
		return pointsList;
	}
}
