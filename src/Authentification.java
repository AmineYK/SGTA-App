import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.sql.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class Authentification extends JFrame {

	private JPanel contentPane;
	private JTextField usernamedirecteurfield;
	private JTextField usernameoperateurfield;
	private JTextField usernamecompagniefield;
	private JPasswordField passworddirecteurfield;
	private JPasswordField passwordcompagnieField;
	private JPasswordField passwordoperateurField;
	JComboBox nombox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	Connection cnx2 = null;
	PreparedStatement prepared2 = null;
	ResultSet resultat2 = null;
	Connection cnx3 = null;
	PreparedStatement prepared3 = null;
	ResultSet resultat3 = null;
	Connection cnx4 = null;
	PreparedStatement prepared4 = null;
	ResultSet resultat4 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Authentification frame = new Authentification();
										frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Authentification() {
		super("Authentification");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1203,560);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(this.getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());

		contentPane.setLayout(null);
		cnx=ConnexionMySql.ConnectionDB();
		cnx4=ConnexionMySql.ConnectionDB();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBackground(new Color(216, 191, 216));
		panel_1.setBounds(806, 0, 4, 521);
		contentPane.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(new Color(216, 191, 216));
		panel.setBounds(380, 0, 4, 521);
		contentPane.add(panel);
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.setBackground(new Color(153,255,255));
		btnSinscrire.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSinscrire.setBackground(new Color(153,255,255));
				btnSinscrire.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				btnSinscrire.setForeground(new Color(153,255,255));
				btnSinscrire.setBackground(new Color(105,105,105));
			}
		});
		btnSinscrire.setIcon(new ImageIcon(getClass().getResource("/icons8-document-25.png")));
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             InscriptionOperateur obj = new InscriptionOperateur() ;
             obj.setVisible(true);
             obj.setLocationRelativeTo(contentPane);
             
             
			}
		});
		btnSinscrire.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnSinscrire.setBounds(419, 88, 143, 35);
		contentPane.add(btnSinscrire);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setBackground(new Color(153,255,255));
		btnSeConnecter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSeConnecter.setBackground(new Color(153,255,255));
				btnSeConnecter.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				btnSeConnecter.setForeground(new Color(153,255,255));
				btnSeConnecter.setBackground(new Color(105,105,105));
			}
		});
		btnSeConnecter.setIcon(new ImageIcon(getClass().getResource("/icons8-connexion-22 (1).png")));
		btnSeConnecter.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernamedirecteurfield.getText().equals("amine") && passworddirecteurfield.getText().equals("ayk")){
					JOptionPane.showMessageDialog(null,"Connexion Reussis");
					FenDirecteur obj = new FenDirecteur();
					obj.setVisible(true);
					dispose();
					obj.setLocationRelativeTo(null);
				}else{
					JOptionPane.showMessageDialog(null,"Utilisateur Introuvable ou Mot De passe Incorrecte");
					usernamedirecteurfield.setText("");
					passworddirecteurfield.setText("");
				}
			}
		});
		btnSeConnecter.setBounds(94, 88, 169, 35);
		contentPane.add(btnSeConnecter);
		
		JLabel lblDirecteurGenerale = new JLabel("                   Directeur Generale");
		lblDirecteurGenerale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDirecteurGenerale.setBackground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {
				lblDirecteurGenerale.setBackground(Color.WHITE);
			}
		});
		lblDirecteurGenerale.setForeground(Color.WHITE);
		lblDirecteurGenerale.setBackground(Color.ORANGE);
		lblDirecteurGenerale.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 14));
		lblDirecteurGenerale.setBounds(10, 11, 360, 42);
		contentPane.add(lblDirecteurGenerale);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(Color.WHITE);
			}
			public void mouseExited(MouseEvent e){
				panel_2.setBackground(Color.BLACK);
			}
		});
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(10, 11, 360, 42);
		contentPane.add(panel_2);
		
		JLabel lblOperateurDeControle = new JLabel("                    Operateur De Controle");
		lblOperateurDeControle.setBounds(394, 11, 402, 42);
		contentPane.add(lblOperateurDeControle);
		lblOperateurDeControle.setForeground(Color.WHITE);
		lblOperateurDeControle.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 14));
		lblOperateurDeControle.setBackground(Color.GREEN);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(394, 11, 402, 42);
		contentPane.add(panel_3);
		
		JButton button = new JButton("Se connecter");
		button.setBackground(new Color(153,255,255));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(153,255,255));
				button.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153,255,255));
				button.setBackground(new Color(105,105,105));
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/icons8-connexion-22 (1).png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 String sql = "select * from operateurusers";
			 String sql1="truncate connecterr";
			 String er = null;
			 int a =0;
		
			 try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
				
				while(resultat.next()){
					if(resultat.getString("username").equals(usernameoperateurfield.getText().toString())  &&  resultat.getString("password").equals(passwordoperateurField.getText().toString()) )
					{
						
						cnx1=ConnexionMySql.ConnectionDB();
						prepared1=cnx1.prepareStatement(sql1);
						prepared1.execute();
						
						String sql2="select * from operateurusers where username = '"+usernameoperateurfield.getText().toString()+"'";
						cnx2=ConnexionMySql.ConnectionDB();
						prepared2=cnx2.prepareStatement(sql2);
						resultat2=prepared2.executeQuery();
						if(resultat2.next()){
						     er =resultat2.getString("matricule");
						}
						
						String sql3="insert into connecterr ( num_operateur ) values ( ? )";
						cnx3=ConnexionMySql.ConnectionDB();
						prepared3=cnx3.prepareStatement(sql3);
						prepared3.setString(1,er);
						prepared3.execute();
						
						a=1;
						
						
						JOptionPane.showMessageDialog(null,"Connexion Reussis");
						FenOperateur obj = new FenOperateur();
						obj.setVisible(true);
						dispose();
						obj.setLocationRelativeTo(null);
					}
					
				}
				if(a==0){
					JOptionPane.showMessageDialog(null,"Username ou mot de passe incorrecte ");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 
			}
		});
		button.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button.setBounds(597, 88, 169, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("S'inscrire");
		button_1.setBackground(new Color(153,255,255));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setBackground(new Color(153,255,255));
				button_1.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(new Color(153,255,255));
				button_1.setBackground(new Color(105,105,105));
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource("/icons8-document-25.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscriptionCompagnie obj = new InscriptionCompagnie();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
				//dispose();
			}
		});
		button_1.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button_1.setBounds(837, 88, 143, 35);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Se connecter");
		button_2.setBackground(new Color(153,255,255));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setBackground(new Color(153,255,255));
				button_2.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				button_2.setForeground(new Color(153,255,255));
				button_2.setBackground(new Color(105,105,105));
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("/icons8-connexion-22 (1).png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql5="select * from listenoir where nom ='"+nombox.getSelectedItem().toString()+"'";
				try {
					prepared4=cnx4.prepareStatement(sql5);
					resultat4=prepared4.executeQuery();
					if(resultat4.next()){
						JOptionPane.showMessageDialog(null,"Vous etes sur la liste Noir !! Veuillez contcter la direction de l'aeroport pour plus de details");
					     usernamecompagniefield.setText("");
					     passwordcompagnieField.setText("");
					}else{
						
					
				 
				
				
				
				
				
				
				
				
				String sql = "select * from compagnieusers";
				String sql1="truncate connecter";
				
				
			
				 int a =0;
				 try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
					
					while(resultat.next()){
						if(resultat.getString("username").equals(usernamecompagniefield.getText().toString())  &&  resultat.getString("password").equals(passwordcompagnieField.getText().toString()) && resultat.getString("nom").equals(nombox.getSelectedItem().toString()) )
						{
							
							
							

							JOptionPane.showMessageDialog(null,"Connexion Reussis");
							
							a=1;
									
							cnx1=ConnexionMySql.ConnectionDB();
							prepared1=cnx1.prepareStatement(sql1);
							prepared1.execute();
							
							
							
							/*cnx2=ConnexionMySql.ConnectionDB();
							String sql2="select * from compagnieusers where username ='"+resultat.getString("username")+"'";
							prepared2=cnx2.prepareStatement(sql2);
							resultat2=prepared2.executeQuery();
							String nom = resultat2.getString("nom");*/
							
							
							
							cnx3=ConnexionMySql.ConnectionDB();
							String sql3= "insert into connecter (nom_compagnie , username , password ) values ( ? , ? , ? )";
							prepared3=cnx3.prepareStatement(sql3);
							prepared3.setString(1,nombox.getSelectedItem().toString());
							prepared3.setString(2,resultat.getString("username"));
							prepared3.setString(3,resultat.getString("password"));
							prepared3.execute();

							FenCompagnie obj = new FenCompagnie();
							obj.setVisible(true);
							dispose();
							setLocationRelativeTo(null);
							
						}
					}
					if(a==0){
						JOptionPane.showMessageDialog(null,"Username ou mot de passe incorrecte ");
				}
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				 
				}}catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button_2.setBounds(1018, 88, 161, 35);
		contentPane.add(button_2);
		
		JLabel lblCompagnieAerienne = new JLabel("                       Compagnie Aerienne");
		lblCompagnieAerienne.setForeground(Color.WHITE);
		lblCompagnieAerienne.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 14));
		lblCompagnieAerienne.setBackground(Color.GREEN);
		lblCompagnieAerienne.setBounds(806, 11, 402, 42);
		contentPane.add(lblCompagnieAerienne);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.BLACK);
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(820, 11, 365, 42);
		contentPane.add(panel_4);
		
		usernamedirecteurfield = new JTextField();
		usernamedirecteurfield.setHorizontalAlignment(SwingConstants.CENTER);
		usernamedirecteurfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					passworddirecteurfield.requestFocus();
				}
			}
		});
		usernamedirecteurfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		usernamedirecteurfield.setBounds(153, 200, 155, 31);
		contentPane.add(usernamedirecteurfield);
		usernamedirecteurfield.setColumns(10);
		
		usernameoperateurfield = new JTextField();
		usernameoperateurfield.setHorizontalAlignment(SwingConstants.CENTER);
		usernameoperateurfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					passwordoperateurField.requestFocus();
				}
			}
		});
		usernameoperateurfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		usernameoperateurfield.setColumns(10);
		usernameoperateurfield.setBounds(553, 200, 169, 31);
		contentPane.add(usernameoperateurfield);
		
		usernamecompagniefield = new JTextField();
		usernamecompagniefield.setHorizontalAlignment(SwingConstants.CENTER);
		usernamecompagniefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					passwordcompagnieField.requestFocus();
				}
			}
		});
		usernamecompagniefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		usernamecompagniefield.setColumns(10);
		usernamecompagniefield.setBounds(973, 200, 155, 31);
		contentPane.add(usernamecompagniefield);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblUsername.setBounds(34, 210, 93, 14);
		contentPane.add(lblUsername);
		
		JLabel label_1 = new JLabel("Username :");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		label_1.setBounds(419, 210, 93, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Username :");
		label_2.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		label_2.setBounds(857, 206, 93, 14);
		contentPane.add(label_2);
		
		passworddirecteurfield = new JPasswordField();
		passworddirecteurfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		passworddirecteurfield.setEchoChar('+');
		passworddirecteurfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					btnSeConnecter.doClick();
				}
			}
		});
		passworddirecteurfield.setBounds(153, 260, 155, 25);
		contentPane.add(passworddirecteurfield);
		
		passwordcompagnieField = new JPasswordField();
		passwordcompagnieField.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		passwordcompagnieField.setEchoChar('+');
		passwordcompagnieField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					button_2.doClick();
				}
			}
		});
		passwordcompagnieField.setBounds(973, 249, 155, 25);
		contentPane.add(passwordcompagnieField);
		
		passwordoperateurField = new JPasswordField();
		passwordoperateurField.setEchoChar('+');
		passwordoperateurField.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		passwordoperateurField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					button.doClick();
				}
			}
		});
		passwordoperateurField.setBounds(553, 255, 169, 25);
		contentPane.add(passwordoperateurField);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPassword.setBounds(34, 267, 93, 14);
		contentPane.add(lblPassword);
		
		JLabel label_3 = new JLabel("Password :");
		label_3.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		label_3.setBounds(419, 260, 93, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Password :");
		label_4.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(857, 255, 93, 14);
		contentPane.add(label_4);
		
		JLabel lblNomDeLa = new JLabel("Nom de la Compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(820, 325, 178, 14);
		contentPane.add(lblNomDeLa);
		
		 nombox = new JComboBox();
		 nombox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		nombox.setBounds(1010, 321, 177, 23);
		contentPane.add(nombox);
		remplirnom();
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Kristen ITC", Font.BOLD | Font.ITALIC, 13));
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}
	public void remplirnom(){
		cnx2=ConnexionMySql.ConnectionDB();
		String sqll="select * from compagnieusers";
		
		try {
			prepared2=cnx2.prepareStatement(sqll);
			resultat2=prepared2.executeQuery();
			while(resultat2.next()){
				nombox.addItem(resultat2.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
