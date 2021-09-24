import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionCompagnie extends JFrame {
	private JPanel contentPane;
	private JTextField nomField;
	private JTextField sitefield;
	private JTextField emailfield;
	private JTextField numtelfield;
	private JTextField usernamefield;
	private JTextField passwordfield;
	JButton btnSinscrire;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx4 = null;
	PreparedStatement prepared4 = null;
	ResultSet resultat4 = null;
	private JTextField paysfield;
	JLabel lblerror;
	Character ccc;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					InscriptionCompagnie frame = new InscriptionCompagnie();
					
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
	public InscriptionCompagnie() {
		super("Inscription d'une Compagnie Aerienne");
		setResizable(false);
		setBounds(100, 100, 439,533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		cnx=ConnexionMySql.ConnectionDB();
		contentPane.setLayout(null);
		
		sitefield = new JTextField();
		sitefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					emailfield.requestFocus();
				}
			}
		});
		sitefield.setText("www.monsite.com");
		sitefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		sitefield.setColumns(10);
		sitefield.setBounds(164, 110, 167, 22);
		contentPane.add(sitefield);
		
		JLabel lblsite = new JLabel("Site :");
		lblsite.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblsite.setBounds(98, 114, 86, 14);
		contentPane.add(lblsite);
		
		usernamefield = new JTextField();
		usernamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					passwordfield.requestFocus();
				}
				
			}
		});
		usernamefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		usernamefield.setColumns(10);
		usernamefield.setBackground(Color.WHITE);
		usernamefield.setBounds(164, 310, 167, 22);
		contentPane.add(usernamefield);
		
		passwordfield = new JTextField();
		passwordfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnSinscrire.doClick();
			}
		});
		passwordfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		passwordfield.setColumns(10);
		passwordfield.setBackground(Color.WHITE);
		passwordfield.setBounds(164, 364, 167, 22);
		contentPane.add(passwordfield);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblUsername.setBounds(63, 314, 86, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPassword.setBounds(63, 368, 86, 14);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("S'inscrire");
		
		btnNewButton.setBounds(0, 0, 0, 0);
		btnNewButton.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(21, 29, 381, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblnom = new JLabel("Nom :");
		panel.add(lblnom);
		lblnom.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblnom.setBounds(86, 22, 109, 14);
		
		JLabel lblnumtel = new JLabel("Num\u00E9ro de Telephone :");
		panel.add(lblnumtel);
		lblnumtel.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblnumtel.setBounds(10, 237, 244, 14);
		
				nomField = new JTextField();
				nomField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						int key=e.getKeyCode();
						if(key==10){
							sitefield.requestFocus();
						}
					}
				});
				nomField.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
				panel.add(nomField);
				nomField.setBounds(145, 18, 167, 22);
				nomField.setColumns(10);
				
				JLabel lblPays = new JLabel("Pays :");
				lblPays.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
				lblPays.setBounds(48, 187, 86, 14);
				panel.add(lblPays);
				
				paysfield = new JTextField();
				paysfield.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						int key=e.getKeyCode();
						if(key==10){
							numtelfield.requestFocus();
						}
					}
				});
				paysfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
				paysfield.setColumns(10);
				paysfield.setBackground(Color.WHITE);
				paysfield.setBounds(145, 183, 167, 22);
				panel.add(paysfield);
				
				emailfield = new JTextField();
				emailfield.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						int key=e.getKeyCode();
						if(key==10){
							paysfield.requestFocus();
						}
					}
				});
				emailfield.setText("monemail@gmail.com");
				emailfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
				emailfield.setBounds(145, 132, 167, 22);
				panel.add(emailfield);
				emailfield.setBackground(Color.WHITE);
				emailfield.setColumns(10);
				
				JLabel lblemail = new JLabel("Email :");
				lblemail.setBounds(60, 136, 86, 14);
				panel.add(lblemail);
				lblemail.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
				
				numtelfield = new JTextField();
				numtelfield.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						ccc = e.getKeyChar();
						if(Character.isLetter(ccc)){
							numtelfield.setEditable(false);
							lblerror.setText("Que des chiffres SVP");
						}else{
							numtelfield.setEditable(true);
							lblerror.setText("");
						}
						int key=e.getKeyCode();
						if(key==10){
							usernamefield.requestFocus();
						}
					}
				});
				numtelfield.setFont(new Font("Lucida FaX", Font.BOLD | Font.ITALIC, 12));
				numtelfield.setBounds(197, 233, 140, 22);
				panel.add(numtelfield);
				numtelfield.setColumns(10);
				
				 lblerror = new JLabel("");
				lblerror.setForeground(Color.RED);
				lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
				lblerror.setBounds(159, 252, 176, 14);
				panel.add(lblerror);
				
				 btnSinscrire = new JButton("S'inscrire");
				btnSinscrire.setBackground(new Color(153,255,255));
				btnSinscrire.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						btnSinscrire.setBackground(new Color(153,255,255));
						btnSinscrire.setForeground(Color.black);//
					}
					public void mouseEntered(MouseEvent e) {
						btnSinscrire.setForeground(new Color(153,255,255));
						btnSinscrire.setBackground(new Color(105,105,105));
					}
				});
				btnSinscrire.setIcon(new ImageIcon(getClass().getResource("/icons8-document-25.png")));
				btnSinscrire.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(lblerror.getText().equals("")){
						 String sql = "insert into compagnieusers values (? , ? , ? , ? , ? , ? , ? )";
						 
						 try {
							 if(!nomField.getText().equals("") && !sitefield.getText().equals("") && !emailfield.getText().equals("")  && !numtelfield.getText().equals("") && !usernamefield.getText().equals("") && !passwordfield.getText().equals(""))
							 {
							prepared=cnx.prepareStatement(sql);
							prepared.setString(1,nomField.getText().toString());
							prepared.setString(2,sitefield.getText().toString());
							prepared.setString(3,emailfield.getText().toString());
							prepared.setString(4,numtelfield.getText().toString());
							prepared.setString(5,usernamefield.getText().toString());
							prepared.setString(6,passwordfield.getText().toString());
							prepared.setString(7,paysfield.getText().toString());
							
							prepared.execute();
							JOptionPane.showMessageDialog(null,"Compagnie Inserée");
							String sql2="insert into nbcompagnie (nom , compteur ) values ( ? , ? )";
							cnx4=ConnexionMySql.ConnectionDB();
							prepared4=cnx4.prepareStatement(sql2);
							prepared4.setString(1,nomField.getText().toString());
							String gg = String.valueOf(0);
							prepared4.setString(2,gg);
							prepared4.execute();
							nomField.setText("");
							sitefield.setText("");
							emailfield.setText("");
							numtelfield.setText("");
							usernamefield.setText("");
							passwordfield.setText("");
							paysfield.setText("");
							Authentification obj = new Authentification();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							dispose();
							 }
							 else{
								 JOptionPane.showMessageDialog(null,"Remplissez Le(s) champ(s) ");
							 }
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null,"Username deja existant ! veuillez le changer");
						}
					}else{
						JOptionPane.showMessageDialog(null,"Existance de champ(s) invalide(s)");
					}
						}
				});
				btnSinscrire.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 14));
				btnSinscrire.setBounds(135, 439, 157, 36);
				contentPane.add(btnSinscrire);		
		
	}

}
