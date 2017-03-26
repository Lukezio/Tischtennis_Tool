import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfoLabel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GridLayout layout;
	private JLabel firstName;
	private JLabel name;
	private JLabel city;
	private JLabel address;
	private JLabel birthday;
	private JLabel gender;

	private Person person;
	private static ParticipantListSelectionListener plsl;

	public UserInfoLabel(Person person, boolean color, ParticipantViewer pv) {
		this.person = person;
		plsl = new ParticipantListSelectionListener(pv);
		Color backgroundColor;
		if (color) {
			backgroundColor = new Color(170, 170, 170);
		} else {
			backgroundColor = new Color(200, 200, 200);
		}
		Color textColor;
		if (person.getGender().substring(0, 1).equalsIgnoreCase("w")) {
			textColor = Color.RED;
		} else {
			textColor = Color.BLUE;
		}
		layout = new GridLayout(1, 6);
		firstName = new JLabel(person.getFirstName());
		firstName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		firstName.setOpaque(true);
		firstName.setBackground(backgroundColor);
		firstName.setForeground(textColor);
		name = new JLabel(person.getName());
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name.setOpaque(true);
		name.setBackground(backgroundColor);
		name.setForeground(textColor);
		city = new JLabel(person.getCity());
		city.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		city.setOpaque(true);
		city.setForeground(textColor);
		city.setBackground(backgroundColor);
		address = new JLabel(person.getAddress());
		address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		address.setOpaque(true);
		address.setBackground(backgroundColor);
		address.setForeground(textColor);
		birthday = new JLabel(person.getBirthday());
		birthday.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		birthday.setOpaque(true);
		birthday.setBackground(backgroundColor);
		birthday.setForeground(textColor);
		gender = new JLabel(person.getGender());
		gender.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gender.setOpaque(true);
		gender.setBackground(backgroundColor);
		gender.setForeground(textColor);
		this.setLayout(layout);
		this.add(firstName);
		this.add(name);
		this.add(city);
		this.add(address);
		this.add(birthday);
		this.add(gender);
		this.setPreferredSize(new Dimension(pv.getWidth(), 35));
		this.addMouseListener(plsl);
	}

	public Person getPerson() {
		return person;
	}

	public static JPanel getColumns(ParticipantViewer pv) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(pv.getWidth(), 35));
		p.setLayout(new GridLayout(1, 6));
		
		p.add(new JLabel("Vorname"));
		p.add(new JLabel("Nachname"));
		p.add(new JLabel("Ortsentscheid"));
		p.add(new JLabel("Adresse"));
		p.add(new JLabel("Geburtstag"));
		p.add(new JLabel("Geschlecht"));
		return p;
	}

}
