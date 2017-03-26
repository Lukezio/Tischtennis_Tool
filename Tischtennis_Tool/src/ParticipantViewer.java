import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ParticipantViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	public static boolean isAlreadyOpened = false;

	List<JPanel> participantList = new ArrayList<JPanel>();
	private JPanel listViewContainer = new JPanel();
	private WesternParticipantList westernParticipantListPanel = new WesternParticipantList(
			this);

	List<Person> list;

	public ParticipantViewer() {
		super("Teilnehmer");
		isAlreadyOpened = true;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				isAlreadyOpened = false;
				e.getWindow().dispose();
			}
		});
		JScrollPane jsp = new JScrollPane(listViewContainer);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(westernParticipantListPanel, BorderLayout.NORTH);
		this.add(jsp, BorderLayout.CENTER);
		this.pack();
		updateParticipantList();
		updateScrollbar();
		this.setSize(800, 500);
		this.setVisible(true);
	}

	public void updateScrollbar() {
		listViewContainer.removeAll();
		int size = participantList.size();
		GridLayout layout;
		if (size > 7) {
			layout = new GridLayout(size, 1);
		} else {
			layout = new GridLayout(7, 1);
		}

		listViewContainer.setLayout(layout);
		for (JPanel m : participantList) {
			listViewContainer.add(m);
		}
		listViewContainer.repaint();
		listViewContainer.revalidate();
	}

	private void sortFirstName() {
		for (int cnt = 0; cnt < 2; cnt++) {
			Collections.sort(list, new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					return p1.getFirstName().toLowerCase()
							.compareTo(p2.getFirstName().toLowerCase());
				}
			});
		}
	}

	private void sortName() {
		for (int cnt = 0; cnt < 2; cnt++) {
			Collections.sort(list, new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					return p1.getName().toLowerCase()
							.compareTo(p2.getName().toLowerCase());
				}
			});
		}
	}

	private void sortCity() {
		for (int cnt = 0; cnt < 2; cnt++) {
			Collections.sort(list, new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					return p1.getCity().toLowerCase()
							.compareTo(p2.getCity().toLowerCase());
				}
			});
		}
	}

	private void sortBirthday() {
		for (int cnt = 0; cnt < 2; cnt++) {
			Collections.sort(list, new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					String s1 = p1.getBirthday().substring(6)
							+ p1.getBirthday().substring(3, 5)
							+ p1.getBirthday().substring(0, 2);
					String s2 = p2.getBirthday().substring(6)
							+ p2.getBirthday().substring(3, 5)
							+ p2.getBirthday().substring(0, 2);
					return Integer.valueOf(s1) - Integer.valueOf(s2);
				}
			});

		}
	}

	private void sortGender() {
		for (int cnt = 0; cnt < 2; cnt++) {
			Collections.sort(list, new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					return p1.getGender().toLowerCase()
							.compareTo(p2.getGender().toLowerCase());
				}
			});
		}
	}

	public void updateParticipantList() {
		try {
			list = DatabaseHandler.getAllParticipants();
		} catch (SQLException e) {
			list = null;
		}
		mapPersonIntoLabel();
		updateScrollbar();
	}

	public void mapPersonIntoLabel() {
		participantList.clear();
		participantList.add(UserInfoLabel.getColumns(this));
		for (Person p : list) {
			participantList.add(new UserInfoLabel(p,
					participantList.size() % 2 == 0, this));
		}
	}

	public void sortAscending(int sortID) {
		switch (sortID) {
		case 0:
			updateParticipantList();
			break;

		case 1:
			sortFirstName();
			break;

		case 2:
			sortName();
			break;

		case 3:
			sortCity();
			break;

		case 4:
			sortBirthday();
			break;

		default:
			sortGender();
			break;
		}
	}

	public void sortDescending(int sortID) {
		if (sortID != 0) {
			sortAscending(sortID);
			Collections.reverse(list);
		}
	}
	
	public WesternParticipantList getWesternParticipantList() {
		return westernParticipantListPanel;
	}

}
