import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


public class PasswordWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPasswordField passwordField;
	private JButton enter;

	public PasswordWindow() {
		super("Bitte Passwort eingeben");
		passwordField = new JPasswordField();
		enter = new JButton("OK");
		enter.addActionListener(this);
		this.setSize(600, 75);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 3));
		this.add(new JLabel("Bitte das Passwort eingeben:"));
		this.add(passwordField);
		this.add(enter);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tryToLogin(String.valueOf(passwordField.getPassword()));
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tryToLogin(String.valueOf(passwordField.getPassword()));
	}

	private void tryToLogin(String password) {
		if (DatabaseHandler.tryToConnect(password)) {
			new Window();
			this.dispose();
		} else {
			System.out.println("Hier info fenster öffnen");
		}
	}

}
