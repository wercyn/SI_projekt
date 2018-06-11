package com.dszi.tractor;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.dszi.gui.SingleCell;
import com.dszi.support.Constants;
import com.dszi.utils.Point;

public class TractorMovement extends TimerTask {

	SingleCell cellPanel[][] = new SingleCell[Constants.gridSizeX][Constants.gridSizeY];
	List<Point> pointsList = new ArrayList<Point>();

	int listCount = 0;

	public TractorMovement(SingleCell cellPanel[][], List<Point> pointsList) {
		this.cellPanel = cellPanel;
		this.pointsList = pointsList;
	}

	public void run() {
		setTractorPosition();
	}

	private void setTractorPosition() {
		if (pointsList.size() > listCount) {
			int x = pointsList.get(listCount).getX();
			int y = pointsList.get(listCount).getY();

			cellPanel[x][y].setAlgoritmPositionHere();
			cellPanel[x][y].removeAll();

			if (listCount > 0) {
				cellPanel[pointsList.get(listCount - 1).getX()][pointsList.get(listCount - 1).getY()]
						.setTractorPositionWhenLeaving();
			}
		}
		listCount++;
	}
}
