package nvidia.in;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminDashboard extends JFrame {
    private JTextField accountIdField;
    private JTextField planBillField;
    private JTextField dueFineField;
    private JTextField stateTaxField;
    private JTextField totalAmountField;
    private JButton calculateTotalButton;
    private JButton generateBillButton;
    private JPanel contentPanel;

    public AdminDashboard() {
        // Set up the JFrame
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 600);
        setResizable(true);

        // Add system window controls (minimize, maximize, close)
        setUndecorated(false);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the left sidebar (dark gray panel)
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(213, getHeight()));

        // Add sidebar menu items with exact styling
        JButton billGeneratorButton = createSidebarButton("Bill Generator", new Color(68, 68, 68));
        JButton accountDetailsButton = createSidebarButton("Account Details", new Color(51, 51, 51));
        JButton serviceRequestsButton = createSidebarButton("Service Requests", new Color(51, 51, 51));
        JButton profileButton = createSidebarButton("Profile", new Color(51, 51, 51));

        // Add spacing between buttons
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        sidebarPanel.add(billGeneratorButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        sidebarPanel.add(accountDetailsButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        sidebarPanel.add(serviceRequestsButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        sidebarPanel.add(profileButton);

        // Create content panel with light gray background
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(230, 230, 230));
        contentPanel.setLayout(null); // Using absolute positioning for exact matching

        // Add the sidebar and content panels to the main panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Set the content pane
        setContentPane(mainPanel);

        // Center the frame on screen
        setLocationRelativeTo(null);

        // Add action listeners for sidebar buttons
        billGeneratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBillGenerator();
            }
        });

        accountDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAccountDetails();
            }
        });

        serviceRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showServiceRequests();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfile();
            }
        });

        // Show the default view
        showBillGenerator();
    }

    private JButton createSidebarButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(213, 54));
        button.setMinimumSize(new Dimension(213, 54));
        button.setPreferredSize(new Dimension(213, 54));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    private void showBillGenerator() {
        contentPanel.removeAll();
        contentPanel.setLayout(null);

        // Account ID
        JLabel accountIdLabel = new JLabel("Account ID:");
        accountIdLabel.setBounds(360, 245, 120, 30);
        accountIdLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        accountIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(accountIdLabel);

        accountIdField = new JTextField();
        accountIdField.setBounds(490, 245, 200, 25);
        contentPanel.add(accountIdField);

        // Plan Bill
        JLabel planBillLabel = new JLabel("Plan Bill (Monthly):");
        planBillLabel.setBounds(360, 280, 120, 30);
        planBillLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        planBillLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(planBillLabel);

        planBillField = new JTextField("0.00");
        planBillField.setBounds(490, 280, 200, 25);
        contentPanel.add(planBillField);

        // Due Fine
        JLabel dueFineLabel = new JLabel("Due Fine ($):");
        dueFineLabel.setBounds(360, 315, 120, 30);
        dueFineLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dueFineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(dueFineLabel);

        dueFineField = new JTextField("0.00");
        dueFineField.setBounds(490, 315, 200, 25);
        contentPanel.add(dueFineField);

        // State Tax
        JLabel stateTaxLabel = new JLabel("State Tax :");
        stateTaxLabel.setBounds(360, 350, 120, 30);
        stateTaxLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stateTaxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(stateTaxLabel);

        stateTaxField = new JTextField("18.00");
        stateTaxField.setBounds(490, 350, 200, 25);
        contentPanel.add(stateTaxField);

        // Total Amount
        JLabel totalAmountLabel = new JLabel("Total Amount ($):");
        totalAmountLabel.setBounds(360, 385, 120, 30);
        totalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        totalAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(totalAmountLabel);

        totalAmountField = new JTextField("0.00");
        totalAmountField.setBounds(490, 385, 200, 25);
        contentPanel.add(totalAmountField);

        // Add buttons with exact styling
        calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.setBounds(400, 450, 120, 30);
        calculateTotalButton.setBackground(new Color(240, 240, 240));
        calculateTotalButton.setFocusPainted(false);
        contentPanel.add(calculateTotalButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(550, 450, 120, 30);
        generateBillButton.setBackground(new Color(240, 240, 240));
        generateBillButton.setFocusPainted(false);
        contentPanel.add(generateBillButton);

        // Add action listeners
        calculateTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAccountDetails() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        JTextArea accountDetailsArea = new JTextArea();
        accountDetailsArea.setEditable(false);
        accountDetailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        accountDetailsArea.setText(getAccountDetails());

        JScrollPane scrollPane = new JScrollPane(accountDetailsArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private String getAccountDetails() {
        StringBuilder details = new StringBuilder();
        ConnectionJDBC connection = new ConnectionJDBC();
        String query = "SELECT * FROM broadband_plans";
        try {
            ResultSet rs = connection.s.executeQuery(query);
            while (rs.next()) {
                details.append("Account ID: ").append(rs.getString("accountNo")).append("\n");
                details.append("Plan Type: ").append(rs.getString("planType")).append("\n");
                details.append("Plan Price: ").append(rs.getDouble("planPrice")).append("\n");
                details.append("Plan Data: ").append(rs.getString("planData")).append("\n");
                details.append("Speed: ").append(rs.getString("speed")).append("\n");
                details.append("Plan Duration: ").append(rs.getString("planDuration")).append("\n");
                details.append("Mobile Number: ").append(rs.getLong("mobileNumber")).append("\n");
                details.append("====================================\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details.toString();
    }

    private void showServiceRequests() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        JTextArea serviceRequestsArea = new JTextArea();
        serviceRequestsArea.setEditable(false);
        serviceRequestsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        serviceRequestsArea.setText(getServiceRequests());

        JScrollPane scrollPane = new JScrollPane(serviceRequestsArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private String getServiceRequests() {
        StringBuilder requests = new StringBuilder();
        ConnectionJDBC connection = new ConnectionJDBC();
        String query = "SELECT * FROM service_request";
        try {
            ResultSet rs = connection.s.executeQuery(query);
            while (rs.next()) {
                requests.append("Request ID: ").append(rs.getInt("request_id")).append("\n");
                requests.append("User: ").append(rs.getString("user")).append("\n");
                requests.append("Type: ").append(rs.getString("type")).append("\n");
                requests.append("Status: ").append(rs.getString("status")).append("\n");
                requests.append("Date: ").append(rs.getDate("date")).append("\n");
                requests.append("====================================\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests.toString();
    }

    private void showProfile() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        JTextArea profileArea = new JTextArea();
        profileArea.setEditable(false);
        profileArea.setFont(new Font("Arial", Font.PLAIN, 14));
        profileArea.setText(getProfileDetails());

        JScrollPane scrollPane = new JScrollPane(profileArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private String getProfileDetails() {
        StringBuilder profile = new StringBuilder();
        profile.append("Admin Profile\n");
        profile.append("Name: Admin\n");
        profile.append("Role: Administrator\n");
        profile.append("Email: admin@nvidia.com\n");
        profile.append("====================================\n");
        return profile.toString();
    }

    private void calculateTotal() {
        try {
            double planBill = Double.parseDouble(planBillField.getText());
            double dueFine = Double.parseDouble(dueFineField.getText());
            double stateTax = Double.parseDouble(stateTaxField.getText());

            double total = planBill + dueFine + stateTax;
            totalAmountField.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateBill() {
        String accountId = accountIdField.getText();
        if (accountId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Account ID.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Bill generated successfully for Account ID: " + accountId,
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Set the look and feel to system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
}