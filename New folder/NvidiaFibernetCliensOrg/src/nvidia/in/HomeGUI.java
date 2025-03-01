package nvidia.in;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HomeGUI extends JFrame {

	JButton existingcustomer, newconnection, adminsignin;

	public HomeGUI() {

//		winIcon();

		createWindow();

		addButtons();

		addComponents();
	}

	public void createWindow() {

		setSize(1366, 1366);

		setContentPane(Supporter.setBackgroundImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		setVisible(true);
	}

	public void addComponents() {

		JPanel mpanal = new JPanel();

		mpanal.setOpaque(false);

		mpanal.setLayout(new BoxLayout(mpanal, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Welcome To Nvidia Fibernet");

		title.setFont(new Font("Arial", Font.BOLD, 24));

		title.setForeground(Color.white);

		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		mpanal.add(Box.createVerticalStrut(200));

		mpanal.add(title);

		mpanal.add(Box.createVerticalStrut(30));

		mpanal.add(existingcustomer);

		mpanal.add(Box.createVerticalStrut(10));

		mpanal.add(newconnection);

		mpanal.add(Box.createVerticalStrut(10));

		mpanal.add(adminsignin);

		add(mpanal);
	}

	public void addButtons() {
		existingcustomer = createStyleButton("existing customer", new Color(30, 144, 255));
		existingcustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
		existingcustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int yes = Supporter.showYesNo("are you Exixting user");

				if (yes == JOptionPane.YES_OPTION) {

					new SignInGUI();
				}

				if (yes == JOptionPane.NO_OPTION) {

					new SignUpGUI();
				}

			}
		});

		existingcustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				existingcustomer.setBackground(new Color(30, 190, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				existingcustomer.setBackground(new Color(30, 144, 255));
			}
		});

		newconnection = createStyleButton("new connection", new Color(220, 20, 60));
		newconnection.setAlignmentX(Component.CENTER_ALIGNMENT);

		newconnection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Supporter.showYesNo("are you New user");

				if (no == JOptionPane.YES_OPTION) {

					new SignUpGUI();
				}

				if (no == JOptionPane.NO_OPTION) {

					new SignInGUI();
				}

			}
		});

		newconnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				newconnection.setBackground(new Color(220, 100, 60));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				newconnection.setBackground(new Color(220, 20, 60));
			}
		});

		adminsignin = createStyleButton("admin sign-in", new Color(50, 205, 50));
		adminsignin.setAlignmentX(Component.CENTER_ALIGNMENT);

		adminsignin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				adminsignin.setBackground(new Color(50, 250, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				adminsignin.setBackground(new Color(50, 205, 50));
			}
		});
		
		
		adminsignin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Supporter.showYesNo("are you Admin");

				if (no == JOptionPane.YES_OPTION) {

					new AdminSignInGUI();
				}

				if (no == JOptionPane.NO_OPTION) {

					new HomeGUI();
				}

			}
		});
	}

	private JButton createStyleButton(String text, Color bgcolor) {

		JButton button = new JButton(text);

		button.setFont(new Font("Arial", Font.BOLD, 15));

		button.setForeground(Color.WHITE);

		button.setBackground(bgcolor);

		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2),
				BorderFactory.createEmptyBorder(12, 25, 12, 25)));

		button.setPreferredSize(new Dimension(300, 50));

		button.setMaximumSize(new Dimension(300, 50));

		button.setFocusPainted(false);

		return button;
	}

	
	 void winIcon() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {

					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (UnsupportedLookAndFeelException e) {

					e.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HomeGUI();
				

			}
		});
	}
}