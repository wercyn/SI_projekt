package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import gui.ClassNotFoundException;
import gui.IllegalAccessException;
import gui.InstantiationException;
import gui.Override;
import gui.Runnable;

import support.Constants;
import tractor.Tractor;


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


	public MainView() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException e2) {
				} catch ( IllegalAccessException e3) {
				} catch ( UnsupportedLookAndFeelException e4) {
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

				frame.add(refresh, "cell 0 9, wrap");
				frame.add(gridPanel, "east");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	

	private void initializeListeners() {
		
		
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.clearGridView();
				gridPanel.refresh();
			}
		});
	}
}