package com.dszi.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import com.dszi.support.Constants;
import com.dszi.tractor.Tractor;

public class MainView {

	GridPanel gridPanel = new GridPanel();
	static Tractor tractor = new Tractor();

	JLabel tractorStateLabel = new JLabel("-- Stan pocz¹tkowy traktora -- ");
	JLabel waterLevelLabel = new JLabel("Poziom wody : ");
	public static JLabel waterLevel = new JLabel(Integer.toString(tractor.getWaterLevel()));
	JLabel pesticideLevelLabel = new JLabel("Poziom pestycydów : ");
	public static JLabel pesticideLevel = new JLabel(Integer.toString(tractor.getPesticideLevel()));
	JLabel fertilizerLevelLabel = new JLabel("Poziom nawozu : ");
	public static JLabel fertilizerLevel = new JLabel(Integer.toString(tractor.getFertilizerLevel()));

	JButton generujGenetic = new JButton("Selekcja");
	JButton moveGeneticTractor = new JButton("Go");
	JButton generujA = new JButton("Generuj A*");
	JButton moveATractor = new JButton("Go");
	JButton generujTree = new JButton("Generuj drzewo");
	JButton moveTreeTractor = new JButton("Go");
	JButton generujNeuro = new JButton("Generuj neuro");
	JButton moveNeuroTractor = new JButton("Go");
	JButton refresh = new JButton("Odswiez");

	public MainView() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException e2) {
				} catch (IllegalAccessException e3) {
				} catch (UnsupportedLookAndFeelException e4) {
				}

				initializeListeners();

				JFrame frame = new JFrame("iTractor");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new MigLayout());

				frame.add(tractorStateLabel, "cell 0 0");
				frame.add(waterLevelLabel, "cell 0 1");
				frame.add(waterLevel, "w 80!, cell 0 1");
				frame.add(pesticideLevelLabel, "cell 0 2");
				frame.add(pesticideLevel, "cell 0 2");
				frame.add(fertilizerLevelLabel, "cell 0 3");
				frame.add(fertilizerLevel, "cell 0 3");

				frame.add(generujGenetic, "cell 0 5, wrap");
				frame.add(moveGeneticTractor, "cell 1 5, wrap");
				frame.add(generujA, "cell 0 6, wrap");
				frame.add(moveATractor, "cell 1 6, wrap");
				frame.add(generujTree, "cell 0 7, wrap");
				frame.add(moveTreeTractor, "cell 1 7, wrap");
				frame.add(generujNeuro, "cell 0 8, wrap");
				frame.add(moveNeuroTractor, "cell 1 8, wrap");
				frame.add(refresh, "cell 0 9, wrap");
				frame.add(gridPanel, "east");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void initializeListeners() {
		generujA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.generateAAPath();
			}
		});
		moveATractor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.startATractor();
				waterLevel.setText(Integer.toString(Constants.tractorWaterLevel));
				pesticideLevel.setText(Integer.toString(Constants.tractorPesticideLevel));
				fertilizerLevel.setText(Integer.toString(Constants.tractorFertilizerLevel));
			}
		});

		generujGenetic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.Genetic();
			}
		});
		moveGeneticTractor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.startGeneticTractor();
			}
		});

		generujTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.generateTree();
			}
		});

		generujNeuro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// gridPanel.clearGridView();
				gridPanel.generateNeuro();
			}
		});

		moveTreeTractor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.startTreeTractor();
			}
		});
		moveNeuroTractor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// gridPanel.clearGridView();
				gridPanel.startNeuroTractor();
				waterLevel.setText(Integer.toString(Constants.tractorWaterLevel));
				pesticideLevel.setText(Integer.toString(Constants.tractorPesticideLevel));
				fertilizerLevel.setText(Integer.toString(Constants.tractorFertilizerLevel));
			}
		});
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.refresh();
			}
		});
	}
}