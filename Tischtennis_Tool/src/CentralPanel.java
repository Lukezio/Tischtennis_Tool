import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CentralPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel easternPanel;
	private JPanel centralPanel;

	private JTextField firstName;
	private JTextField name;
	private JTextField birthdayTextField;
	private JTextField address;
	private JTextField city;
	private JTextField genderTextField;
	private JLabel ageGroup;

	private JLabel year;
	private JButton addParticipant;
	private JButton showParticipants;
	private JButton createPoolPlans;

	boolean nameFilled = false;
	boolean firstNameFilled = false;
	boolean birthdayFilled = false;
	boolean genderFilled = false;
	boolean cityFilled = false;
	
	ParticipantViewer participantViewer;

	String birthday;
	private String gender;
	static int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	private boolean canAddParticipant = false;

	public CentralPanel() {
		centralPanel = new JPanel();
		easternPanel = new JPanel();
		setupEasternPanel();
		setupCentralPanel();
		this.setLayout(new BorderLayout());
		this.add(centralPanel, BorderLayout.CENTER);
		this.add(easternPanel, BorderLayout.EAST);
	}

	private void setupCentralPanel() {
		firstName = new JTextField();
		firstName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				firstNameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				firstNameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				firstNameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

		});
		name = new JTextField();
		name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				nameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				nameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				nameFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

		});
		city = new JTextField();
		city.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				cityFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				cityFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				cityFilled = !firstName.getText().isEmpty();
				enableAddButton();
			}

		});
		birthdayTextField = new JTextField();
		birthdayTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						checkBirthdaySyntax();
						enableAddButton();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						checkBirthdaySyntax();
						enableAddButton();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						checkBirthdaySyntax();
						enableAddButton();
					}

				});
		address = new JTextField();
		genderTextField = new JTextField();
		genderTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						checkGenderSyntax();
						enableAddButton();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						checkGenderSyntax();
						enableAddButton();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						checkGenderSyntax();
						enableAddButton();
					}

				});
		ageGroup = new JLabel();
		centralPanel.setLayout(new GridLayout(7, 2));
		centralPanel.add(new JLabel("Vorname: "));
		centralPanel.add(firstName);
		centralPanel.add(new JLabel("Nachname: "));
		centralPanel.add(name);
		centralPanel.add(new JLabel("Geburtsdatum: "));
		centralPanel.add(birthdayTextField);
		centralPanel.add(new JLabel("Ortsentscheid: "));
		centralPanel.add(city);
		centralPanel.add(new JLabel("Adresse: "));
		centralPanel.add(address);
		centralPanel.add(new JLabel("Geschlecht: "));
		centralPanel.add(genderTextField);
		centralPanel.add(new JLabel("Altersklasse: "));
		centralPanel.add(ageGroup);
	}

	private void setupEasternPanel() {
		year = new JLabel(String.valueOf(currentYear));
		createPoolPlans = new JButton("Poolpläne");
		createPoolPlans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(SouthernPanel.getFemaleCombinationCategories());
				System.out.println(SouthernPanel.getMaleCombinationCategories());
				new PoolPlanWindow(8);
			}
		});
		showParticipants = new JButton("Teilnehmerliste");
		showParticipants.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ParticipantViewer.isAlreadyOpened) {
					participantViewer = new ParticipantViewer();					
				}
			}
		});
		addParticipant = new JButton("Teilnehmer hinzufügen");
		addParticipant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!DatabaseHandler.exists(firstName.getText(),
							name.getText(), birthday)) {
						DatabaseHandler.insert(firstName.getText(),
								name.getText(), city.getText(),
								address.getText(), birthday, gender);
						clearTextFields();
						SouthernPanel.updateNumbers();
						if (ParticipantViewer.isAlreadyOpened) {
							participantViewer.getWesternParticipantList().getComboBox().setSelectedIndex(0);
						}
					} else {
						System.out.println("Gibts schon");
						// TODO: Popup
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		easternPanel.setMinimumSize(new Dimension(250, 200));
		easternPanel.setMaximumSize(new Dimension(250, 200));
		easternPanel.setPreferredSize(new Dimension(250, 200));
		easternPanel.setLayout(new GridLayout(4, 1));
		JPanel p = new JPanel();
		easternPanel.add(p);
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("<html>Jahr des <br> Bezirksentscheids: </html>"));
		p.add(year);
		easternPanel.add(addParticipant);
		easternPanel.add(showParticipants);
		easternPanel.add(createPoolPlans);
		addParticipant.setEnabled(canAddParticipant);
	}

	private void enableAddButton() {
		if (birthdayFilled && nameFilled && firstNameFilled && genderFilled
				&& cityFilled) {
			addParticipant.setEnabled(true);
		} else {
			addParticipant.setEnabled(false);
		}
	}

	private void checkBirthdaySyntax() {
		String date = birthdayTextField.getText();
		int[] dateIntegers = new int[3];
		int lastPosition = 0;
		int counter = 0;
		for (int i = lastPosition; i < date.length(); i++) {
			if (date.substring(i, i + 1).equalsIgnoreCase(".")) {

				try {
					dateIntegers[counter] = Integer.parseInt(date.substring(
							lastPosition, i));
					System.out.println(dateIntegers[counter]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				lastPosition = i + 1;
				counter++;
			}
			if (i == date.length() - 1 && counter == 2) {
				try {
					dateIntegers[counter] = Integer.parseInt(date.substring(
							lastPosition, i + 1));
					System.out.println(dateIntegers[counter]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (dateIntegers[0] > 0 && dateIntegers[0] <= 31) {
			if (dateIntegers[1] > 0 && dateIntegers[1] <= 12) {
				if (dateIntegers[2] < currentYear
						&& dateIntegers[2] >= currentYear - 13) {
					birthday = dateIntegers[2] + "-" + dateIntegers[1] + "-"
							+ dateIntegers[0];
					birthdayFilled = true;
					updateAgeGroup();
					return;
				}
			}
		}
		birthdayFilled = false;
		updateAgeGroup();
	}

	// TODO:
	private void checkGenderSyntax() {
		gender = this.genderTextField.getText();
		if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("männlich")) {
			genderFilled = true;
			gender = "männlich";
			updateAgeGroup();
		} else if (gender.equalsIgnoreCase("w")
				|| gender.equalsIgnoreCase("weiblich")) {
			gender = "weiblich";
			genderFilled = true;
			updateAgeGroup();
		} else {
			genderFilled = false;
			updateAgeGroup();
		}
	}

	private void clearTextFields() {
		firstName.setText("");
		name.setText("");
		genderTextField.setText("");
		city.setText("");
		birthdayTextField.setText("");
		address.setText("");
		nameFilled = false;
		firstNameFilled = false;
		birthdayFilled = false;
		genderFilled = false;
		cityFilled = false;
		enableAddButton();
		updateAgeGroup();
	}

	private void updateAgeGroup() {
		if (genderFilled && birthdayFilled) {
			int year = getAgeCategory(Integer.parseInt(birthday.substring(0, 4)));
			ageGroup.setText(gender.substring(0, 1) + "U" + year);
		} else {
			ageGroup.setText("");
		}
	}
	
	public static int getAgeCategory(int year) {
		int cur;
		int diff = currentYear - 1 - year;
		if (diff <= 8) {
			cur = 8;
		} else if (diff > 10) {
			cur = 12;
		} else {
			cur = 10;
		}
		return cur;
	}

}
