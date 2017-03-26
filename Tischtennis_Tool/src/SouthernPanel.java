import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SouthernPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static JLabel fu8;
	private static JLabel mu8;
	private static JLabel fu10;
	private static JLabel mu10;
	private static JLabel fu12;
	private static JLabel mu12;
	
	private String[] combinationCategories = {"U8, U10, U12", "U8 + U10, U12", "U8, U10 + U12", "U8 + U10 + U12"};
	private static JComboBox<String> femaleCombinations;
	private static JComboBox<String> maleCombinations;
	
	public SouthernPanel() {
		fu8 = new JLabel();
		mu8 = new JLabel();
		fu10 = new JLabel();
		mu10 = new JLabel();
		fu12 = new JLabel();
		mu12= new JLabel();
		femaleCombinations = new JComboBox<String>(combinationCategories);
		maleCombinations = new JComboBox<String>(combinationCategories);
		this.setLayout(new GridLayout(2, 8));
		this.add(new JLabel("wU8: "));
		this.add(fu8);
		this.add(new JLabel("wU10: "));
		this.add(fu10);
		this.add(new JLabel("wU12: "));
		this.add(fu12);
		this.add(new JLabel("Konstellationen Mädchen:"));
		this.add(femaleCombinations);
		this.add(new JLabel("mU8: "));
		this.add(mu8);
		this.add(new JLabel("mU10: "));
		this.add(mu10);
		this.add(new JLabel("mU12: "));
		this.add(mu12);
		this.add(new JLabel("Konstellationen Mädchen:"));
		this.add(maleCombinations);
		updateNumbers();
	}
	
	public static void updateNumbers() {
		int[] numbers;
		try {
			numbers = DatabaseHandler.getAllNumbers();
		} catch (SQLException e) {
			numbers = new int[6];
		}
		fu8.setText(String.valueOf(numbers[0]));
		mu8.setText(String.valueOf(numbers[1]));
		fu10.setText(String.valueOf(numbers[2]));
		mu10.setText(String.valueOf(numbers[3]));
		fu12.setText(String.valueOf(numbers[4]));
		mu12.setText(String.valueOf(numbers[5]));
	}

	public static String getFemaleCombinationCategories() {
		return String.valueOf(femaleCombinations.getSelectedItem());
	}
	
	public static String getMaleCombinationCategories() {
		return String.valueOf(maleCombinations.getSelectedItem());
	}
	
}
