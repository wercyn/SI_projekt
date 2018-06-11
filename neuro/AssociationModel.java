package com.dszi.neuro;

public class AssociationModel {

	public class Data {
		private double[] data;

		public Data(double[] arg) {
			data = arg;
		}

		public void Alter(int arg1, double arg2) {
			if (arg1 < 0 || arg1 > 99)
				System.out.println("ERROR! Index out of bounds!");
			else {
				if (arg2 != -1 && arg2 != 1)
					System.out.println("ERROR! Attempted to alter the value to something that wasn't 1 or -1!");
				else
					data[arg1] = arg2;
			}
		}

		public double Get(int arg1) {
			return data[arg1];
		}

		public void InputData(double[] arguments) {
			if (arguments.length != 100)
				System.out.println("ERROR! Attempted to input a table that didn't have 25 elements!");
			else {
				boolean flag = true;
				for (int i = 0; i < 25; i++)
					if (arguments[i] != 1 && arguments[i] != -1) {
						System.out.println("ERROR! Value of arguments[" + i + "] is not 1 or -1!");
						flag = false;
					}
				if (flag == true)
					for (int i = 0; i < 25; i++)
						Alter(i, arguments[i]);
			}
		}

		public void SGNthedata(double[] arg1) {
			double[] output = new double[arg1.length];

			for (int i = 0; i < arg1.length; i++) {
				if (arg1[i] >= 0)
					output[i] = 1;
				else if (arg1[i] < 0)
					output[i] = -1;
			}
			data = output;
		}
	}

	public double[] AssocMod(double[] idealComponent, double[] proposedComponent) {		
		boolean continues = false;
		// Ideals:
		double[] DataToZ0 = new double[idealComponent.length];
		DataToZ0 = idealComponent;

		Data Z0 = new Data(DataToZ0);

		// Candidates:
		double[] DataToZ1 = new double[proposedComponent.length];
		DataToZ1 = proposedComponent;

		Data Z1 = new Data(DataToZ1);

		// Logic:
		double test1 = 1, test2 = 201, testresult;
		testresult = test1 / test2;

		double[][] W = new double[201][201];

		for (int i = 0; i < 201; i++) {
			for (int j = 0; j < 201; j++) {
				W[i][j] = (testresult * (Z0.Get(i) * Z0.Get(j))) + (testresult * (Z1.Get(i) * Z1.Get(j)));
			}
		}

		double[] resultz0 = new double[201];
		double[] resultz1 = new double[201];
		double tempold = 0, tempnew = 0;

		while (continues) {
			for (int i = 0; i < 201; i++) {
				for (int j = 0; j < 201; j++) {
					if (j == 200) {
						tempold = tempnew;
						tempnew = W[i][j] * Z0.Get(j);
						tempnew = tempold + tempnew;
						resultz0[i] = tempnew;
						tempnew = 0;
						tempold = 0;
					} else {
						tempold = tempnew;
	
						tempnew = W[i][j] * Z0.Get(j);
						tempnew = tempnew + tempold;
					}
				}
			}

			for (int i = 0; i < 201; i++) {
				for (int j = 0; j < 201; j++) {
					if (j == 200) {
						tempold = tempnew;
						tempnew = W[i][j] * Z1.Get(j);
						tempnew = tempold + tempnew;
						resultz1[i] = tempnew;
						tempnew = 0;
						tempold = 0;
					} else {
						tempold = tempnew;
						tempnew = W[i][j] * Z1.Get(j);
						tempnew = tempnew + tempold;
					}
				}
			}

			Data Resultz0 = new Data(resultz0);
			Resultz0.SGNthedata(resultz0);

			Data Resultz1 = new Data(resultz1);
			Resultz1.SGNthedata(resultz1);

			continues = false;
			for (int i = 0; i < 201; i++) {
				for (int j = 0; j < 201; j++) {
					if (resultz0[i] != idealComponent[i])
						continues = true;
				}
			}
		}
		continues = false;
		return resultz0;
	}
}