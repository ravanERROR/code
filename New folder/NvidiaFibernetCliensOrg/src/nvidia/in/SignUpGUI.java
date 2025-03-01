package nvidia.in;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class SignUpGUI extends JFrame {
	private JTextField userNameField, mobileNumberField;
	private JButton signInButton, signUpButton;
	private JComboBox<String> city;

	static ConnectionJDBC connection = new ConnectionJDBC();

	private JCheckBox termsBox;
	private static final Color SIGNUP_BUTTON_COLOR_BORDER = new Color(220, 20, 60);
	private static final Color SIGNIN_BUTTON_COLOR_BORDER = new Color(30, 144, 255);
	private static final Font ARIAL_BOLD_24 = new Font("Arial", Font.BOLD, 24);
	private static final Font ARIAL_PLAIN_14 = new Font("Arial", Font.PLAIN, 14);
	private static final Font ARIAL_BOLD_15 = new Font("Arial", Font.BOLD, 15);

	public SignUpGUI() throws HeadlessException {

		createSignupWindow();
		
		initalizeComponents();
		
		addComponents();
		
		actionButtonListnears();
	}

	private void createSignupWindow() {
		setSize(1366, 768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(Supporter.setBackgroundImage());
		setVisible(true);
	}

	private void addComponents() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		// Heading
		JLabel titleLabel = new JLabel("User Registration", SwingConstants.CENTER);
		titleLabel.setFont(ARIAL_BOLD_24);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(Box.createVerticalStrut(260));
		contentPanel.add(titleLabel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Form Panel
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setOpaque(false);
		formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		Supporter.createFormRow("Username: ", userNameField, formPanel, Color.WHITE, ARIAL_BOLD_15);
		Supporter.createFormRow("Mobile Number: ", mobileNumberField, formPanel, Color.WHITE, ARIAL_BOLD_15);
//		Supporter.createFormRow("City: ", termsBox, formPanel, Color.WHITE, ARIAL_BOLD_15);

		formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		contentPanel.add(formPanel);

		contentPanel.add(city);

		JPanel checkbox = new JPanel();

		checkbox.add(termsBox);

		checkbox.setOpaque(false);

		contentPanel.add(checkbox);

		contentPanel.add(Box.createVerticalStrut(20));

		// Button Panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
		buttonPanel.setOpaque(false);
		buttonPanel.add(signUpButton);
		buttonPanel.add(signInButton);

		contentPanel.add(buttonPanel);
		contentPanel.add(Box.createVerticalGlue());

		add(contentPanel);
	}

	private void initalizeComponents() {
		userNameField = Supporter.createStyledTextField(20, ARIAL_PLAIN_14);

		mobileNumberField = Supporter.createStyledTextField(20, ARIAL_PLAIN_14);

		signInButton = Supporter.createButtonStyle("Sign In", Color.WHITE, Color.BLACK, ARIAL_BOLD_15,
				SIGNIN_BUTTON_COLOR_BORDER);

		signUpButton = Supporter.createButtonStyle("Sign Up", Color.WHITE, Color.BLACK, ARIAL_BOLD_15,
				SIGNUP_BUTTON_COLOR_BORDER);

		termsBox = Supporter.createCheckboxStyle("I agree to the terms and conditions", Color.WHITE, ARIAL_BOLD_15);

		String[] combo = { "Select City", "Austin", "Texas", "Raleigh", "Nort carolina", "Kansas City", "Missouri", "New York City",
				"Brooklyn", "Manhattan"};

		city = new JComboBox<String>(combo);
		
		Supporter.createStyleCombobox(city);

	}

	public void handlSignUp() {

		String name = userNameField.getText().trim();

		String mob = mobileNumberField.getText().trim();

		String dest = (String) city.getSelectedItem();

		long mobile = 0;

		if (!mob.isEmpty()) {
			try {
				mobile = Long.parseLong(mob);
			} catch (NumberFormatException e) {
				Supporter.showErrorMessage("Mobile number should contain only digits.");
				return;
			}
		}

		if (!termsBox.isSelected()) {
			Supporter.showErrorMessage("Agree to the terms and conditions.");
			return;
		}

		if (name.isEmpty() || mob.isEmpty() || dest.equals("Select City")) {
			Supporter.showErrorMessage("Please fill all required fields.");
			return;
		}

		String query = "SELECT * FROM users WHERE mobilenumber=" + mobile;

		try {
			ResultSet rs = connection.s.executeQuery(query);

			if (rs.next()) {
				Supporter.showSuccessMessage("Number is Already used  or Account Already Present try Another");
			} else {
				String insert = "INSERT INTO users (name, city, mobilenumber) VALUES (?, ?, ?)";
				PreparedStatement conn = connection.c.prepareStatement(insert);
				conn.setString(1, name);
				conn.setString(2, dest);
				conn.setLong(3, mobile);
				conn.executeUpdate();

				ResultSet rsCheck = connection.s.executeQuery(query);

				if (rsCheck.next()) {
					Supporter.showSuccessMessage("Account Created Successfully!");
					dispose();
					UserDashboard user=new UserDashboard();
				} else {
					Supporter.showErrorMessage("Failed to create account. Please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				if (connection.c != null) {
//					connection.c.close();
//					System.out.println("Database connection closed.");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	private void actionButtonListnears() {
		signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handlSignUp();

			}
		});

		signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignInGUI();

			}
		});
	}
	
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				new SignUpGUI();

			}
		});
	}
}