//package nvidia.in;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.*;
//
//public class UserDashboard extends JFrame {
//	static long phoneNo = SignInGUI.phoneNo;
//	static String planType;
//	static double planPrice;
//	static String planData;
//	static String speed;
//	static String planDuration;
//	static String accountNumber;
//	static String status = "UNKNOWN";
//
//	public UserDashboard() throws SQLException {
//		setUpFrame();
//		JPanel headerF = createHeader();
//		add(headerF, BorderLayout.NORTH);
//
//		JPanel mainPanel = new JPanel(new BorderLayout());
//		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//		// Create promotional panel and add to the left side
//		JPanel leftPanel = createPromotionalPanel();
//		mainPanel.add(leftPanel, BorderLayout.WEST);
//
//		JPanel rightPanel = createAccountDetailPanel();
//		mainPanel.add(rightPanel, BorderLayout.CENTER);
//
//		add(mainPanel, BorderLayout.CENTER);
//		addFrame();
//	}
//
//	private void setUpFrame() {
//		setSize(1920, 1080);
//		setTitle("Nvidia-Fibernet");
//		setLayout(new BorderLayout());
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//	}
//
//	private void addFrame() {
//		setVisible(true);
//	}
//
//	private JButton createStyledButton(String text) {
//		JButton button = new JButton(text);
//		button.setFont(new Font("Arial", Font.PLAIN, 14));
//		button.setForeground(new Color(51, 51, 51));
//		button.setBorderPainted(false);
//		button.setFocusable(false);
//		button.setContentAreaFilled(false);
//		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				button.setFont(new Font("Arial", Font.BOLD, 14));
//				button.setForeground(new Color(255, 51, 85));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				button.setFont(new Font("Arial", Font.PLAIN, 14));
//				button.setForeground(new Color(51, 51, 51));
//			}
//		});
//
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				switch (text) {
//				case "Pay Bills":
//					SwingUtilities.invokeLater(new Runnable() {
//						public void run() {
////	                           try 
////	                           {
////	                               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////	                           } 
////	                           catch (Exception ex)
////	                           {
////	                               ex.printStackTrace();
////	                           }
//							new PaymentGUI();
//						}
//					});
//					break;
//
//				case "Service Requests":
//					if (UserDashboard.status == "INACTIVE") {
//						JOptionPane.showMessageDialog(UserDashboard.this,
//								"Inactive Plan. Service Request Cannot Be Raised", "Failed",
//								JOptionPane.INFORMATION_MESSAGE);
//					} else {
////	                       try 
////	                       {
////	                           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////	                       } 
////	                       catch (Exception ex) 
////	                       {
////	                           ex.printStackTrace();
////	                       }
////	                       new ServiceRequest();
//					}
//					break;
//
//				case "New Connection":
////	                   try 
////	                   {
////	                       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////	                   }
////	                   catch (ClassNotFoundException | InstantiationException |
////	                           IllegalAccessException | UnsupportedLookAndFeelException ex) 
////	                   {
////	                       ex.printStackTrace();
////	                   }
//					new BuyConnection();
//					break;
//
//				case "FAQ":
//					JOptionPane.showMessageDialog(button, "FAQ Section Coming Soon....");
//					break;
//				}
//
//			}
//		});
//		return button;
//	}
//
//	public JPanel createHeader() {
//		JPanel headerPanel = new JPanel();
//		headerPanel.setLayout(new BorderLayout());
//		headerPanel.setBackground(Color.white);
//		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//
//		JLabel titleLable = new JLabel("Nvidia Fibernet");
//		titleLable.setForeground(new Color(255, 51, 85));
//		titleLable.setFont(new Font("Arial", Font.BOLD, 24));
//
//		JPanel navMenu = new JPanel();
//		navMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
//		navMenu.setBackground(Color.white);
//
//		String[] menuItems = { "Pay Bills", "Service Requests", "New Connection", "FAQ" };
//
//		for (String item : menuItems) {
//			JButton meanuButton = createStyledButton(item);
//			navMenu.add(meanuButton);
//		}
//
//		JPanel dropDownPanel = new JPanel();
//		dropDownPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		dropDownPanel.setBackground(Color.white);
//
//		JButton dropDownButton = new JButton("Option");
//		dropDownButton.setBackground(Color.white);
//		dropDownButton.setForeground(new Color(51, 51, 51));
//		dropDownButton.setFont(new Font("Arial", Font.PLAIN, 14));
//		dropDownButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		dropDownButton.setFocusable(false);
//
//		JPopupMenu menus = new JPopupMenu();
//		JMenuItem profileOption = new JMenuItem("Profile");
//		JMenuItem logoutOption = new JMenuItem("Logout");
//
//		profileOption.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Profile Section Coming Soon", "Update patch",
//						JOptionPane.INFORMATION_MESSAGE);
//			}
//		});
//
////		profileOption.addActionListener(new ActionListener() {
////		    @Override
////		    public void actionPerformed(ActionEvent e) {
////		        try {
////		            // Create a dialog to show the profile
////		            JDialog profileDialog = new JDialog(UserDashboard.this, "User Profile", true);
////		            profileDialog.setLayout(new BorderLayout());
////		            profileDialog.add(createProfilePanel(), BorderLayout.CENTER);
////		            profileDialog.setSize(500, 400);
////		            profileDialog.setLocationRelativeTo(UserDashboard.this);
////		            profileDialog.setVisible(true);
////		        } catch (SQLException ex) {
////		            JOptionPane.showMessageDialog(UserDashboard.this, 
////		                    "Error loading profile: " + ex.getMessage(),
////		                    "Error", JOptionPane.ERROR_MESSAGE);
////		            ex.printStackTrace();
////		        }
////		    }
////		});
//
//		logoutOption.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout", "Success",
//						JOptionPane.YES_NO_OPTION);
//
//				if (choice == JOptionPane.YES_OPTION) {
//					new HomeGUI();
//					dispose();
//				}
//			}
//		});
//
//		menus.add(profileOption);
//		menus.add(logoutOption);
//
//		dropDownButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				menus.show(dropDownButton, 0, dropDownButton.getHeight());
//
//			}
//		});
//
//		headerPanel.add(titleLable, BorderLayout.WEST);
//		dropDownPanel.add(dropDownButton);
//		headerPanel.add(navMenu, BorderLayout.CENTER);
//		headerPanel.add(dropDownPanel, BorderLayout.EAST);
//
//		return headerPanel;
//	}
//
//	private boolean getStatus() throws SQLException {
//		ConnectionJDBC connection = new ConnectionJDBC();
//		String query = "select * from broadband_plans where mobileNumber = '" + phoneNo + "';";
//		ResultSet rs = connection.s.executeQuery(query);
//
//		if (rs.next()) {
//			planType = rs.getString("planType");
//			planPrice = rs.getDouble("planPrice");
//			planData = rs.getString("planData");
//			speed = rs.getString("speed");
//			accountNumber = rs.getString("accountNo");
//			planDuration = rs.getString("planDuration");
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private JPanel createAccountDetailPanel() throws SQLException {
//		JPanel detailPanel = new JPanel();
//		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
//		detailPanel.setBackground(Color.white);
//		detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//		if (getStatus()) {
//			status = "ACTIVE";
//		} else {
//			planType = "NA";
//			speed = "NA";
//			planData = "NA";
//			status = "INACTIVE";
//		}
//
//		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		statusPanel.setBackground(Color.white);
//
//		JLabel statuLabel = new JLabel(UserDashboard.status);
//		statuLabel.setBackground(Color.white);
//		statuLabel.setFont(new Font("Arial", Font.BOLD, 12));
//		statuLabel.setForeground(new Color(40, 167, 69));
//		statusPanel.add(statuLabel);
//
//		JPanel planPanel = new JPanel();
//		planPanel.setLayout(new BoxLayout(planPanel, BoxLayout.Y_AXIS));
//		planPanel.setBackground(Color.white);
//
//		JLabel planName = new JLabel(UserDashboard.planType);
//		planName.setFont(new Font("Arial", Font.BOLD, 18));
//		planPanel.add(planName);
//
//		JPanel duePanel = new JPanel();
//		duePanel.setLayout(new BoxLayout(duePanel, BoxLayout.Y_AXIS));
//		duePanel.setBackground(Color.white);
//
//		JLabel dueDate = new JLabel("27th Feb 2025");
//		dueDate.setFont(new Font("Arial", Font.BOLD, 14));
//		duePanel.add(dueDate);
//
//		JLabel dueDateLabel = new JLabel("Due Date");
//		dueDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
//		duePanel.add(dueDateLabel);
//
//		JPanel usagePanel = new JPanel();
//		usagePanel.setLayout(new BoxLayout(usagePanel, BoxLayout.Y_AXIS));
//		usagePanel.setBackground(Color.white);
//
//		JLabel usageLabel = new JLabel("45.6 GB of 150GB");
//		usageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//		usagePanel.add(usageLabel);
//
//		JPanel upgradePanel = new JPanel();
//		upgradePanel.setBackground(new Color(255, 240, 240));
//		upgradePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//		JLabel upgradeLabel = new JLabel("Upgrade To Enjoy Cost Higher speeds & Great Offers");
//		upgradeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//		upgradePanel.add(upgradeLabel);
//
//		JButton upgradeButton = new JButton("Upgrade");
//		upgradeButton.setBackground(Color.white);
//		upgradeButton.setFocusable(false);
//		upgradePanel.add(upgradeButton);
//
//		detailPanel.add(statusPanel);
//		detailPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//		detailPanel.add(planPanel);
//		detailPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//		detailPanel.add(duePanel);
//		detailPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//		detailPanel.add(usagePanel);
//		detailPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//		detailPanel.add(upgradePanel);
//
//		upgradeButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(upgradeButton, "Upgrade Section Coming Soon....");
//
//			}
//		});
//
//		return detailPanel;
//
//	}
//
//	private JPanel createPromotionalPanel() {
//		JPanel promotionalPanel = new JPanel(new BorderLayout());
//		promotionalPanel.setBackground(Color.WHITE);
//		promotionalPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//		// Create gradient panel with custom painting
//		JPanel gradientPanel = new JPanel() {
//			@Override
//			protected void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				Graphics2D g2d = (Graphics2D) g;
//				GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 100, 150), getWidth(), getHeight(),
//						new Color(255, 150, 200));
//				g2d.setPaint(gradient);
//				g2d.fillRect(0, 0, getWidth(), getHeight());
//			}
//		};
//
//		// Set preferred size for the gradient panel
//		gradientPanel.setPreferredSize(new Dimension(250, 300));
//		gradientPanel.setLayout(new GridBagLayout());
//
//		// Create and style promotional text
//		JLabel promoText = new JLabel("<html><div style='text-align: center;'>"
//				+ "Pay your Nvidia Fibernet<br/>bill via CRED UPI and Get<br/>"
//				+ "<span style='font-size: 24px;'>up to Rs. 250*</span><br/>" + "Cashback</div></html>");
//		promoText.setHorizontalAlignment(JLabel.CENTER);
//		promoText.setFont(new Font("Arial", Font.BOLD, 18));
//		promoText.setForeground(Color.WHITE);
//
//		// Add text to gradient panel
//		gradientPanel.add(promoText);
//
//		// Add gradient panel to the promotional panel
//		promotionalPanel.add(gradientPanel, BorderLayout.CENTER);
//
//		return promotionalPanel;
//	}
//
////	private JPanel createProfilePanel() throws SQLException {
////	    JPanel profilePanel = new JPanel();
////	    profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
////	    profilePanel.setBackground(Color.white);
////	    profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
////	    
////	    // Get user details from database
////	    ConnectionJDBC connection = new ConnectionJDBC();
////	    String query = "SELECT * FROM users WHERE mobileNumber = '" + phoneNo + "';";
////	    ResultSet rs = connection.s.executeQuery(query);
////	    
////	    // User information panel
////	    JPanel userInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
////	    userInfoPanel.setBackground(Color.white);
////	    
////	    if (rs.next()) {
////	        // Add user details to panel
////	        addProfileField(userInfoPanel, "Name:", rs.getString("name"));
////	        addProfileField(userInfoPanel, "Phone:", String.valueOf(phoneNo));
////	        addProfileField(userInfoPanel, "Email:", rs.getString("email"));
////	        addProfileField(userInfoPanel, "Address:", rs.getString("address"));
////	        
////	        // Add account information if available
////	        if (status.equals("ACTIVE")) {
////	            addProfileField(userInfoPanel, "Plan:", planType);
////	            addProfileField(userInfoPanel, "Account Number:", accountNumber);
////	            addProfileField(userInfoPanel, "Plan Data:", planData);
////	            addProfileField(userInfoPanel, "Speed:", speed);
////	        }
////	    } else {
////	        // If user details not found
////	        JLabel noDataLabel = new JLabel("No profile data available");
////	        noDataLabel.setFont(new Font("Arial", Font.BOLD, 16));
////	        userInfoPanel.add(noDataLabel);
////	    }
////	    
////	    // Add header
////	    JLabel headerLabel = new JLabel("User Profile");
////	    headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
////	    headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
////	    
////	    // Add edit button
////	    JButton editButton = new JButton("Edit Profile");
////	    editButton.setAlignmentX(Component.LEFT_ALIGNMENT);
////	    editButton.addActionListener(e -> {
////	        JOptionPane.showMessageDialog(this, "Edit Profile feature coming soon!", 
////	                "Information", JOptionPane.INFORMATION_MESSAGE);
////	    });
////	    
////	    // Add components to the profile panel
////	    profilePanel.add(headerLabel);
////	    profilePanel.add(Box.createRigidArea(new Dimension(0, 20)));
////	    profilePanel.add(userInfoPanel);
////	    profilePanel.add(Box.createRigidArea(new Dimension(0, 20)));
////	    profilePanel.add(editButton);
////	    
////	    return profilePanel;
////	}
////
////	// Helper method to add field to profile panel
////	private void addProfileField(JPanel panel, String label, String value) {
////	    JLabel labelComp = new JLabel(label);
////	    labelComp.setFont(new Font("Arial", Font.BOLD, 14));
////	    
////	    JLabel valueComp = new JLabel(value != null ? value : "N/A");
////	    valueComp.setFont(new Font("Arial", Font.PLAIN, 14));
////	    
////	    panel.add(labelComp);
////	    panel.add(valueComp);
////	}
////	
//
//	public static void main(String[] args) {
//		try {
//			new UserDashboard();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}





package nvidia.in;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class UserDashboard extends JFrame
{
	static  long phoneNo=SignInGUI.phoneNo;
	static  String planType;
	static  double planPrice;
	static 	String planData;
	static 	String speed;
	static  String planDuration;
	static  String accountNumber;
	static String status = "UNKNOWN";	
	
	public UserDashboard() throws SQLException
	{
	    setUpFrame();
	    JPanel headerF = createHeader();
	    add(headerF,BorderLayout.NORTH);
	    
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    
	    // Create promotional panel and add to the left side
	    JPanel leftPanel = createPromotionalPanel();
	    mainPanel.add(leftPanel, BorderLayout.WEST);
	    
	    JPanel rightPanel = createAccountDetailPanel();
	    mainPanel.add(rightPanel, BorderLayout.CENTER);
	    
	    add(mainPanel, BorderLayout.CENTER);
	    addFrame();
	}
	
	
	private void setUpFrame()
	{
		setSize(1920,1080);
		setTitle("Nvidia-Fibernet");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addFrame()
	{
		setVisible(true);
	}
	
	private JButton createStyledButton(String text)
	{
		JButton button = new JButton(text);
		button.setFont(new Font("Arial",Font.PLAIN,14));
		button.setForeground(new Color(51,51,51));
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				button.setFont(new Font("Arial",Font.BOLD,14));
				button.setForeground(new Color(255,51,85));
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				button.setFont(new Font("Arial",Font.PLAIN,14));
				button.setForeground(new Color(51,51,51));
			}
		});
		
		button.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (text) 
				{
	               case "Pay Bills":
	                   SwingUtilities.invokeLater(new Runnable() 
	                   {
	                       public void run() {
	                           new PaymentGUI();
	                       }
	                   });
	                break;
	                   
	               case "Service Requests":
	                   if (UserDashboard.status.equals("INACTIVE")) {
	                       JOptionPane.showMessageDialog(UserDashboard.this,
	                               "Please purchase a connection first before raising a service request.",
	                               "No Active Connection",
	                               JOptionPane.WARNING_MESSAGE);
	                   } else {
	                       SwingUtilities.invokeLater(() -> {
	                           NvidiaBroadbandServiceRequest form = new NvidiaBroadbandServiceRequest();
	                           form.setVisible(true);
	                       });
	                   }
	               break;
	                   
	               case "New Connection":
	                   new BuyConnection();
	               break;
	               
	               case "FAQ":
	                   JOptionPane.showMessageDialog(button, "FAQ Section Coming Soon....");
	               break;
	           }

				
			}
		});
		return button;
	}
	
	public JPanel createHeader()
	{
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setBackground(Color.white);
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		
		JLabel titleLable = new JLabel("Nvidia Fibernet");
		titleLable.setForeground(new Color(255,51,85));
		titleLable.setFont(new Font("Arial",Font.BOLD,24));
		
		JPanel navMenu = new JPanel();
		navMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		navMenu.setBackground(Color.white);
		
		String[] menuItems = {"Pay Bills","Service Requests","New Connection","FAQ"};
		
		for(String item : menuItems)
		{
			JButton meanuButton = createStyledButton(item);
			navMenu.add(meanuButton);
		}
		
		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dropDownPanel.setBackground(Color.white);
		
		JButton dropDownButton = new JButton("Option");
		dropDownButton.setBackground(Color.white);	
		dropDownButton.setForeground(new Color(51,51,51));
		dropDownButton.setFont(new Font("Arial",Font.PLAIN,14));
		dropDownButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dropDownButton.setFocusable(false);
		
		JPopupMenu menus = new JPopupMenu();
		JMenuItem profileOption = new JMenuItem("Profile");
		JMenuItem logoutOption = new JMenuItem("Logout");
		
		
		
		profileOption.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null,"Profile Section Coming Soon","Update patch",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		logoutOption.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout","Success",JOptionPane.YES_NO_OPTION);
				
				if(choice == JOptionPane.YES_OPTION)
				{
					new HomeGUI();
					dispose();
				}
			}
		});
		
		menus.add(profileOption);
		menus.add(logoutOption);
		
		dropDownButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				menus.show(dropDownButton,0,dropDownButton.getHeight());
				
			}
		});
		
		headerPanel.add(titleLable,BorderLayout.WEST);
		dropDownPanel.add(dropDownButton);
		headerPanel.add(navMenu,BorderLayout.CENTER);
		headerPanel.add(dropDownPanel,BorderLayout.EAST);
		
		return headerPanel;
	}
	
	private boolean getStatus() throws SQLException
	{
		ConnectionJDBC connection = new ConnectionJDBC();
		String query = "select * from broadband_plans where mobileNumber = '"+phoneNo+"';";
		ResultSet rs = connection.s.executeQuery(query);
		
		if(rs.next())
		{
			planType = rs.getString("planType");
			planPrice = rs.getDouble("planPrice");
			planData = rs.getString("planData");
			speed = rs.getString("speed");
			accountNumber = rs.getString("accountNo");
			planDuration = rs.getString("planDuration");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private JPanel createAccountDetailPanel() throws SQLException
	{
		JPanel detailPanel = new JPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
		detailPanel.setBackground(Color.white);
		detailPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		if(getStatus())
		{
			status = "ACTIVE";
		}
		else
		{
			planType= "NA";
			speed = "NA" ;
 			planData = "NA";
 			status = "INACTIVE";
		}
		
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBackground(Color.white);
		
		JLabel statuLabel = new JLabel(UserDashboard.status);
		statuLabel.setBackground(Color.white);
		statuLabel.setFont(new Font("Arial",Font.BOLD,12));
		statuLabel.setForeground(new Color(40,167,69));
		statusPanel.add(statuLabel);
		
		
		JPanel planPanel = new JPanel();
		planPanel.setLayout(new BoxLayout(planPanel, BoxLayout.Y_AXIS));
		planPanel.setBackground(Color.white);
		
		JLabel planName = new JLabel(UserDashboard.planType);
		planName.setFont(new Font("Arial", Font.BOLD, 18));
		planPanel.add(planName);
		
		
		JPanel duePanel = new JPanel();
		duePanel.setLayout(new BoxLayout(duePanel, BoxLayout.Y_AXIS));
		duePanel.setBackground(Color.white);
		
		JLabel dueDate = new JLabel("27th Feb 2025");
		dueDate.setFont(new Font("Arial", Font.BOLD, 14));
		duePanel.add(dueDate);
		
		JLabel dueDateLabel = new JLabel("Due Date");
		dueDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
		duePanel.add(dueDateLabel);
		
		JPanel usagePanel = new JPanel();
		usagePanel.setLayout(new BoxLayout(usagePanel, BoxLayout.Y_AXIS));
		usagePanel.setBackground(Color.white);
		
		JLabel usageLabel = new JLabel("45.6 GB of 150GB");
		usageLabel.setFont(new Font("Arial",Font.PLAIN,12));
		usagePanel.add(usageLabel);
		
		JPanel upgradePanel  = new JPanel();
		upgradePanel.setBackground(new Color(255,240,240));
		upgradePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel upgradeLabel = new JLabel("Upgrade To Enjoy Cost Higher speeds & Great Offers");
		upgradeLabel.setFont(new Font("Arial",Font.PLAIN,12));
		upgradePanel.add(upgradeLabel);
		
		JButton upgradeButton = new JButton("Upgrade ");
		upgradeButton.setBackground(Color.white);
		upgradeButton.setFocusable(false);
		upgradePanel.add(upgradeButton);
		
		detailPanel.add(statusPanel);
		detailPanel.add(Box.createRigidArea(new Dimension(0,10)));
		detailPanel.add(planPanel);
		detailPanel.add(Box.createRigidArea(new Dimension(0,20)));
		detailPanel.add(duePanel);
		detailPanel.add(Box.createRigidArea(new Dimension(0,10)));
		detailPanel.add(usagePanel);
		detailPanel.add(Box.createRigidArea(new Dimension(0,20)));
		detailPanel.add(upgradePanel);
		
		return detailPanel;
		
	}
	
	
	
	
	private JPanel createPromotionalPanel() {
	    JPanel promotionalPanel = new JPanel(new BorderLayout());
	    promotionalPanel.setBackground(Color.WHITE);
	    promotionalPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    // Create gradient panel with custom painting
	    JPanel gradientPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;
	            GradientPaint gradient = new GradientPaint(
	                    0, 0, new Color(255, 100, 150),
	                    getWidth(), getHeight(), new Color(255, 150, 200)
	            );
	            g2d.setPaint(gradient);
	            g2d.fillRect(0, 0, getWidth(), getHeight());
	        }
	    };
	    
	    // Set preferred size for the gradient panel
	    gradientPanel.setPreferredSize(new Dimension(250, 300));
	    gradientPanel.setLayout(new GridBagLayout());
	    
	    // Create and style promotional text
	    JLabel promoText = new JLabel("<html><div style='text-align: center;'>" +
	            "Pay your Nvidia Fibernet<br/>bill via CRED UPI and Get<br/>" +
	            "<span style='font-size: 24px;'>up to Rs. 250*</span><br/>" +
	            "Cashback</div></html>");
	    promoText.setHorizontalAlignment(JLabel.CENTER);
	    promoText.setFont(new Font("Arial", Font.BOLD, 18));
	    promoText.setForeground(Color.WHITE);
	    
	    // Add text to gradient panel
	    gradientPanel.add(promoText);
	    
	    // Add gradient panel to the promotional panel
	    promotionalPanel.add(gradientPanel, BorderLayout.CENTER);
	    
	    return promotionalPanel;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) 
	{
		try {
			new UserDashboard();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}