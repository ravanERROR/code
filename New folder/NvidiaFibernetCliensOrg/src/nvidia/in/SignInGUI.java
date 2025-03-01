package nvidia.in;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SignInGUI extends JFrame {

	private JTextField userNameField, mobileNumberField;
	private JButton signInButton, goBackButton;
	private JCheckBox termsBox;
	private static final Color SIGNIN_BUTTON_COLOR = new Color(0, 200, 0);
	private static final Color GOBACK_BUTTON_COLOR = new Color(0, 0, 200);
	private static final Font ARIAL_BOLD_24 = new Font("Arial", Font.BOLD, 24);
	private static final Font ARIAL_PLAIN_14 = new Font("Arial", Font.PLAIN, 14);
	private static final Font ARIAL_BOLD_15 = new Font("Arial", Font.BOLD, 15);

	private static final Color SIGNUP_BUTTON_COLOR_BORDER = new Color(220, 20, 60);
	private static final Color SIGNIN_BUTTON_COLOR_BORDER = new Color(30, 144, 255);
	
	static long phoneNo = 0;

	/**
	 * @throws HeadlessException
	 */
	public SignInGUI() throws HeadlessException {

		createLoginWindow();

		initalizeComponents();

		addComponents();

		setupListner();

	}

	private void createLoginWindow() {

		setSize(1366, 768);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		setContentPane(setBackgroundImage());

		setVisible(true);
	}

	private void addComponents() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		// Add title
		JLabel titleLabel = new JLabel("Sign-In", SwingConstants.CENTER);
		titleLabel.setFont(ARIAL_BOLD_24);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(Box.createVerticalStrut(260));
		contentPanel.add(Box.createVerticalGlue());
		contentPanel.add(titleLabel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Form Textfields and Lables
		JPanel formPanel = new JPanel();

		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

		formPanel.setOpaque(false);

		formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		Supporter.createFormRow("Username: ", userNameField, formPanel, Color.WHITE, ARIAL_BOLD_15);

		Supporter.createFormRow("Mobile Number: ", mobileNumberField, formPanel, Color.WHITE, ARIAL_BOLD_15);

		contentPanel.add(formPanel);

		JPanel checkbox = new JPanel();

		checkbox.add(termsBox);

		checkbox.setOpaque(false);

		contentPanel.add(checkbox);

		contentPanel.add(Box.createVerticalStrut(20));

		// for Button Layout
		JPanel buttonPanal = new JPanel();

		buttonPanal.setLayout(new BoxLayout(buttonPanal, BoxLayout.X_AXIS));

		buttonPanal.setOpaque(false);

		buttonPanal.add(signInButton);

		buttonPanal.add(Box.createHorizontalStrut(20));

		buttonPanal.add(goBackButton);

		contentPanel.add(buttonPanal);

		add(contentPanel);
	}

	private void initalizeComponents() {

		userNameField = Supporter.createStyledTextField(20, ARIAL_PLAIN_14);

		mobileNumberField = Supporter.createStyledTextField(20, ARIAL_PLAIN_14);

		signInButton = Supporter.createButtonStyle("Sign In", Color.WHITE, Color.BLACK, ARIAL_BOLD_15,
				SIGNIN_BUTTON_COLOR_BORDER);

		goBackButton = Supporter.createButtonStyle("Go Back", Color.WHITE, Color.BLACK, ARIAL_BOLD_15,
				SIGNUP_BUTTON_COLOR_BORDER);

		termsBox = Supporter.createCheckboxStyle("I agree to the terms and conditions", Color.WHITE, ARIAL_BOLD_15);
	}

	private  void handleSignin() {

		if (!termsBox.isSelected()) {
			Supporter.showErrorMessage("Agree terms and Conditions");
			return;
		}

		String name = userNameField.getText().strip();

		 String no = mobileNumberField.getText().strip();
	

		if (!no.isEmpty()) {
			try {
				phoneNo = Long.parseLong(no);
			} catch (NumberFormatException e) {
				Supporter.showErrorMessage("Mobile number should contain only digits.");
				return;
			}
		}

		if (name.isEmpty() || no.isEmpty()) {
			Supporter.showErrorMessage("Please fill all required fields.");
			return;
		}

		String query = "SELECT * FROM users WHERE name='" + name + "' AND mobilenumber=" + phoneNo;
//	    String query = "SELECT * FROM users WHERE name='" + name + "' AND mobilenumber=" + mobile + " AND city='" + dest + "'";

		ConnectionJDBC connection = new ConnectionJDBC();
		try {
			ResultSet rs = connection.s.executeQuery(query);

			if (rs.next()) {
				Supporter.showSuccessMessage("Wellcome Back " + name);
				UserDashboard user=new UserDashboard();

			} else {
				Supporter.showErrorMessage("invalid Credentioal");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		finally {
//			try {
//				connection.c.close();
//				System.out.println("dbclode");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	void setupListner() {

		signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				handleSignin();
			}
		});

		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new HomeGUI();
			}
		});
	}
	
	
	

	
	static JPanel setBackgroundImage() {

		return new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				ImageIcon img = new ImageIcon(
						"D:\\coding with pavan\\Qspiders\\java\\NvidiaFibernetCliensOrg\\src\\icons\\usersignNvidia.jpg");

				Image icon = img.getImage();

				double windoWidth = getWidth();

				double windoHight = getHeight();

				double iconWidth = icon.getWidth(this);

				double iconHight = icon.getHeight(this);

				double scale = (Math.max(windoWidth / iconWidth, windoHight / iconHight));

				int scaledWidth = ((int) (iconWidth * scale));

				int scaledHight = ((int) (iconHight * scale));

				int p = ((int) (windoWidth - scaledWidth) / 2);

				int q = ((int) (windoHight - scaledHight) / 2);

				g.drawImage(icon, p, q, scaledWidth, scaledHight, this);

				g.setColor(new Color(0, 0, 0, 100));

				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				new SignInGUI();

			}
		});
	}

}
