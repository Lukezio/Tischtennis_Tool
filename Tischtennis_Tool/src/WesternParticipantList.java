import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class WesternParticipantList extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int COMPONENT_HEIGHT = 15;

	private ParticipantViewer pv;
	
	JComboBox<String> box;
	private String[] sortCategories = {"-", "Vorname", "Nachname", "Ortsentscheid", "Geburtstag", "Geschlecht"};
	private JRadioButton ascending;
	private JRadioButton descending;
	
	public WesternParticipantList(ParticipantViewer pv) {
		this.pv = pv;
		box = new JComboBox<String>(sortCategories);
		this.setMinimumSize(new Dimension(100, COMPONENT_HEIGHT * 2));
		this.setMaximumSize(new Dimension(100, COMPONENT_HEIGHT * 2));
		this.setPreferredSize(new Dimension(100, COMPONENT_HEIGHT * 2));
		this.setLayout(new GridLayout(1, 4));
		this.add(new JLabel("Sortieren nach"));
		this.add(box);
		box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ascending.isSelected()) {
					pv.sortAscending(box.getSelectedIndex());
				} else {
					pv.sortDescending(box.getSelectedIndex());
				}
				
				pv.mapPersonIntoLabel();
				pv.updateScrollbar();
			}
		});
		ascending = new JRadioButton("Aufsteigend");
		descending = new JRadioButton("Absteigend");
		ActionListener al = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(ascending)) {
					pv.sortAscending(box.getSelectedIndex());
				} else {
					pv.sortDescending(box.getSelectedIndex());
				}
				pv.mapPersonIntoLabel();
				pv.updateScrollbar();
			}
		};
		this.add(ascending);
		this.add(descending);
		ButtonGroup bg = new ButtonGroup();
		bg.add(ascending);
		bg.add(descending);
		ascending.setSelected(true);
		ascending.addActionListener(al);
		descending.addActionListener(al);
	}

	public ParticipantViewer getParticipantViewer() {
		return pv;
	}
	
	public JComboBox<String> getComboBox() {
		return box;
	}

}
