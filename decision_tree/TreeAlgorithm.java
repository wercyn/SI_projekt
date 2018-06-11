package com.dszi.decision_tree;

import java.util.ArrayList;
import java.util.List;

import com.dszi.gui.SingleCell;
import com.dszi.support.Constants;
import com.dszi.tractor.Tractor;
import com.dszi.utils.Point;

public class TreeAlgorithm {

	Tractor tractor = new Tractor();
	SingleCell cellPanel[][] = new SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	List<Point> pointsList = new ArrayList<Point>();

	boolean[][] visited = new boolean[Constants.gridSizeX + 1][Constants.gridSizeY + 1];
	boolean hasToFinish = false;

	int positionX;
	int positionY;
	int waterLevel;
	int pesticideLevel;
	int fertilizerLevel;

	public TreeAlgorithm(SingleCell[][] cellPanel) {
		this.cellPanel = cellPanel;

		initializeTractor();
		dfs(5, 5);
	}

	public void dfs(int x, int y) {
		if (hasSupplies()) {
			calculateTractorResources(x, y);
			dfsChecker(x, y);
		}
	}

	private void dfsChecker(int x, int y) {
		int oldX = x;
		int oldY = y;
		int newX = x;
		int newY = y;

		if (isNotOutOfBounds(x + 1, y)) {
			if (!visited[x + 1][y]) {
				oldX = x + 1;
				oldY = y;
			}
		}
		if (isNotOutOfBounds(x, y + 1)) {
			if (!visited[x][y + 1]) {
				newX = x;
				newY = y + 1;
				if (repairMostDamaged(oldX, oldY, newX, newY)) {
					oldX = newX;
					oldY = newY;
				}
			}
		}
		if (isNotOutOfBounds(x - 1, y)) {
			if (!visited[x - 1][y]) {
				newX = x - 1;
				newY = y;
				if (repairMostDamaged(oldX, oldY, newX, newY)) {
					oldX = newX;
					oldY = newY;
				}
			}
		}
		if (isNotOutOfBounds(x, y - 1)) {
			if (!visited[x][y - 1]) {
				newX = x;
				newY = y - 1;
				if (repairMostDamaged(oldX, oldY, newX, newY)) {
					oldX = newX;
					oldY = newY;
				}
			}
		}
		if (!visited[oldX][oldY]) {
			dfs(oldX, oldY);
		}
	}

	int Failure = 0;

	private boolean repairMostDamaged(int oldX, int oldY, int newX, int newY) {
		int chooseOld = 0;
		int chooseNew = 0;

		if (cellPanel[newX][newY].getIrrigation() < 20) {
			chooseNew = 10;

			if (cellPanel[newX][newY].getIrrigation() > cellPanel[oldX][oldY].getIrrigation()) {
				Failure++;
				if (Failure >= 5) {
					chooseOld = 20;
					System.out.println("Zmieniam decyzje");
				}
			}
		} else {
			if (cellPanel[newX][newY].getSoilDestruction() >= 70) {
				chooseNew = 10;

				if (cellPanel[newX][newY].getSoilDestruction() < cellPanel[oldX][oldY].getSoilDestruction()) {
					Failure++;
					if (Failure >= 5) {
						chooseOld = 20;
						System.out.println("Zmieniam decyzje");
					}
				}

			} else {
				if (cellPanel[newX][newY].getNumberOfPests() > 10) {
					chooseNew = 10;

					if (cellPanel[newX][newY].getNumberOfPests() < cellPanel[oldX][oldY].getNumberOfPests()) {
						Failure++;
						if (Failure >= 5) {
							chooseOld = 20;
							System.out.println("Zmieniam decyzje");
						}
					}
				}
			}
		}

		if (chooseOld >= chooseNew)
			return false;
		else
			return true;
	}

	private void calculateTractorResources(int x, int y) {
		positionX = x;
		positionY = y;
		waterLevel = waterLevel - cellPanel[x][y].getIrrigation();
		pesticideLevel = pesticideLevel - cellPanel[x][y].getNumberOfPests();
		fertilizerLevel = fertilizerLevel - cellPanel[x][y].getSoilDestruction();
		visited[x][y] = true;

		pointsList.add(new Point(x, y));

		updateCell(x, y);
		showResources();
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
		System.out.println("Porazki " + Failure);
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
