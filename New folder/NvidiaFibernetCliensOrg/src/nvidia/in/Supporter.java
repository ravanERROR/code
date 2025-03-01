package nvidia.in;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Supporter {

	static JPanel setBackgroundImage() {

		return new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				ImageIcon img = new ImageIcon(
						"D:\\coding with pavan\\Qspiders\\java\\NvidiaFibernetCliensOrg\\src\\icons\\logo.jpg");

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

	static JButton createButtonStyle(String name, Color bgcolor, Color foreGroundColor, Font font, Color bordercolor) {
		JButton button = new JButton(name);
		button.setBackground(bgcolor);
		button.setForeground(foreGroundColor);
		button.setFont(font);
		button.setFocusPainted(false);

		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(bordercolor, 2), // Using
																											// bordercolor
																											// here
				BorderFactory.createEmptyBorder(12, 25, 12, 25)));

		// Add hover effect
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(bgcolor.darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(bgcolor);
			}
		});

		return button;
	}

	static JTextField createStyledTextField(int columns, Font font) {

		JTextField field = new JTextField(columns);

		field.setBackground(new Color(255, 255, 255));

		field.setForeground(new Color(33, 33, 33));

		field.setFont(font);

		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		return field;
	}

	static JCheckBox createCheckboxStyle(String text, Color forGroundColor, Font font) {
		JCheckBox termsBox = new JCheckBox(text);
		termsBox.setForeground(forGroundColor);
		termsBox.setFont(font);
		termsBox.setOpaque(false);
		termsBox.setFocusPainted(false);
		return termsBox;
	}

	static void createFormRow(String labelText, Component component, JPanel formPanel, Color forground, Font font) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setOpaque(false);

		JLabel label = new JLabel(labelText);
		label.setForeground(forground);
		label.setFont(font);
		label.setPreferredSize(new Dimension(120, 25));

		rowPanel.add(label);
		rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		rowPanel.add(component);

		formPanel.add(rowPanel);
		formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	static void createStyleCombobox(JComboBox<String> dest) {

		dest.setFont(new Font("Arial", Font.PLAIN, 19));
		dest.setBackground(Color.WHITE);
		dest.setForeground(Color.BLACK);
		dest.setPreferredSize(new Dimension(20, 25));
	}

	static void showSuccessMessage(String msg) {

		JOptionPane.showConfirmDialog(null, msg, "Susess", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	static void showErrorMessage(String msg) {

		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	static void showConfermation(String msg) {

		JOptionPane.showMessageDialog(null, msg, "CONFORMATION", JOptionPane.QUESTION_MESSAGE);
	}
	
	static int showYesNo(String msg) {

		return JOptionPane.showConfirmDialog(null, msg, "conforn", JOptionPane.YES_NO_OPTION,

				JOptionPane.QUESTION_MESSAGE);

	}

	
	
	
	
}
