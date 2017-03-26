import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static String title = "mini-Meisterschafts Tool";
	private SouthernPanel southernPanel;
	private CentralPanel centralPanel;
	
	public Window() {
		super(title);
		southernPanel = new SouthernPanel();
		centralPanel = new CentralPanel();
		this.setSize(800, 400);
		setupGUI();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupMenuBar();
		setVisible(true);
	}
	
	private void setupMenuBar() {
		JMenu menus[] = { new JMenu("Optionen")};
		JMenuItem optionItems[] = { new JMenuItem("Neuer Bezirksentscheid"), new JMenuItem("Bezirksentscheid laden") };
		for (int i = 0; i < optionItems.length; i++) {
			menus[0].add(optionItems[i]);
			optionItems[i].addActionListener(new OptionItemListener());
		}
		JMenuBar menuBar = new JMenuBar();
		for (int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
		}
		setJMenuBar(menuBar);
	}

	private void setupGUI() {
		this.setLayout(new BorderLayout());
		setupCentralPanel();
		setupSouthernPanel();
	}

	private void setupCentralPanel() {
		this.add(centralPanel, BorderLayout.CENTER);
	}

	private void setupSouthernPanel() {
		this.add(southernPanel, BorderLayout.SOUTH);
		
	}

	
}
