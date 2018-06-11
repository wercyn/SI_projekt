package com.dszi.neuro;

import com.dszi.utils.Point;
import com.dszi.support.Constants;
import com.dszi.gui.SingleCell;

public class GradientMethod {
	public class Polynomial {
		// Czterowymiarowa funkcja (wielomian 4tego stopnia) wagi, okreœlona dla ka¿dego
		// elementu gridu.
		// Argumenty:
		// D - dystans od teraŸniejszego punktu do celu algorytmu,
		// N - nawodnienie danego pola,
		// Z - zniszczenie ziemii w danym polu,
		// I - liczba insektów w danym polu.
		// Potêgi wspó³czynników w polach coeff id¹ od n do 1.
		public double[] coeffD;
		public double[] coeffN;
		public double[] coeffZ;
		public double[] coeffI;

		public double freecoeff; // wyraz wolny wielomianu.

		public Polynomial(double[] D, double[] N, double[] Z, double[] I, double fc) {
			coeffD = new double[D.length];
			coeffN = new double[N.length];
			coeffZ = new double[Z.length];
			coeffI = new double[I.length];

			coeffD = D;
			coeffN = N;
			coeffZ = Z;
			coeffI = I;

			freecoeff = fc;
		}

		public double get_coeff(char which, int at) {
			double result = Double.MAX_VALUE;
			if (which == 'D' || which == 'd')
				result = coeffD[at];
			if (which == 'N' || which == 'n')
				result = coeffN[at];
			if (which == 'Z' || which == 'z')
				result = coeffZ[at];
			if (which == 'I' || which == 'i')
				result = coeffI[at];
			if (which == 'F' || which == 'f')
				result = freecoeff;
			if (result == Double.MAX_VALUE)
				System.out.println("--GM--Getcoeff--FAILED");
			return result;
		}

		public double get_value(double[] values) // ma strukturê (D,N,Z,I) - lokalna dla ka¿dego pola.
		{
			double result = 0;

			for (int j = 0; j < 4; j++) {
				if (j == 0)
					for (int i = 0; i < 5; i++)
					
						result = result - get_coeff('D', i) * Math.pow(values[j], (5 - i));
						
					
				if (j == 1)
					for (int i = 0; i < 5; i++)
						result = result - get_coeff('N', i) * Math.pow(values[j], (5 - i));
						
				if (j == 2)
					for (int i = 0; i < 5; i++)
						result = result + get_coeff('Z', i) * Math.pow(values[j], (5 - i));
				
				if (j == 3)
					for (int i = 0; i < 5; i++)
						result = result + get_coeff('I', i) * Math.pow(values[j], (5 - i));
				
			}
			
			result = result + freecoeff;
			
			return result;
		}
	}

	public double d_at(int tractor_x, int tractor_y, int coord_x, int coord_y) {
		double result = 0;

		result = Math.sqrt(Math.pow((tractor_x - coord_x), 2) + Math.pow((tractor_y - coord_y), 2));

		return result;
	}

	public Point GradientMethodOutput(int tractor_x, int tractor_y, SingleCell CellPanel[][], boolean[][] visited) {
		Point result = new Point();
		int x, y, x_max = 0, y_max = 0;
		double[][] value = new double[10][10];
		double maxvalue = -1 * Double.MAX_VALUE;

		double[] FcoeffD = new double[] { 0, 0, 0, 0, 0.71 };
		double[] FcoeffN = new double[] { 0, 0, 0, 0, 1 };
		double[] FcoeffZ = new double[] { 0, 0, 0, 0, 1 };
		double[] FcoeffI = new double[] { 0, 0, 0, 0, 1 };
		double Ffc = 5;

		Polynomial F = new Polynomial(FcoeffD, FcoeffN, FcoeffZ, FcoeffI, Ffc);

		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				x = i;
				y = j;

				value[x][y] = F
						.get_value(new double[] { d_at(tractor_x, tractor_y, x, y), CellPanel[x][y].getIrrigation(),
								CellPanel[x][y].getSoilDestruction(), CellPanel[x][y].getNumberOfPests() });
				if (CellPanel[x][y].getIrrigation() == 100 && CellPanel[x][y].getSoilDestruction() == 0
						&& CellPanel[x][y].getNumberOfPests() == 0 && visited[x][y] == true)
					value[x][y] = -1000000.0;
			}
		}
		System.out.println(value[0][0]);

		for (int i = 0; i < Constants.gridSizeX; i++) {
			for (int j = 0; j < Constants.gridSizeY; j++) {
				x = i;
				y = j;

				if (value[x][y] > maxvalue) {
					maxvalue = value[x][y];
					x_max = x;
					y_max = y;
				}
			}
		}
		result = new Point(x_max, y_max);
		return result;
	}
}
