package Operateur;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.Authentification;
import Welcome.ConnexionMySql;

public class InscriptionOperateur extends JFrame {

	private JPanel contentPane;
	private JTextField nomfield;
	private JTextField prenomfield;
	private JTextField nbanneeexprfield;
	private JTextField usernamefield;
	private JTextField passwordfield;
	JButton btnSinscrire;
	Connection cnx = null;

	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	Connection cnx4 = null;
	PreparedStatement prepared4 = null;
	ResultSet resultat4 = null;
	private JTextField matriculeField;
	JLabel lblerror;
	JLabel lblerror1;
	Character ccc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					InscriptionOperateur frame = new InscriptionOperateur();

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
	public InscriptionOperateur() {
		super("Inscription Operateur De Controle Aerien");
		setResizable(false);
		setBounds(100, 100, 439, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		cnx4 = ConnexionMySql.ConnectionDB();

		nomfield = new JTextField();
		nomfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();
				if (key == 10) {
					prenomfield.requestFocus();
				}
			}
		});
		nomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomfield.setColumns(10);
		nomfield.setBounds(164, 110, 167, 22);
		contentPane.add(nomfield);

		prenomfield = new JTextField();
		prenomfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					nbanneeexprfield.requestFocus();
				}
			}
		});
		prenomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		prenomfield.setBackground(Color.WHITE);
		prenomfield.setColumns(10);
		prenomfield.setBounds(164, 181, 167, 22);
		contentPane.add(prenomfield);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNom.setBounds(98, 114, 86, 14);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPrenom.setBounds(78, 185, 86, 14);
		contentPane.add(lblPrenom);

		usernamefield = new JTextField();
		usernamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
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
				int key = e.getKeyCode();
				if (key == 10) {
					btnSinscrire.doClick();
				}

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

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(21, 30, 381, 371);
		contentPane.add(panel);
		panel.setLayout(null);

		matriculeField = new JTextField();
		matriculeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					matriculeField.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					matriculeField.setEditable(true);
					lblerror.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					nomfield.requestFocus();
				}
			}
		});
		matriculeField.setBounds(145, 30, 177, 22);
		matriculeField.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		matriculeField.setColumns(10);
		panel.add(matriculeField);

		JLabel lblMatricule = new JLabel("Matricule :");
		lblMatricule.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblMatricule.setBounds(29, 32, 106, 14);
		panel.add(lblMatricule);

		JLabel lblNombreDannesDexperience = new JLabel("Nombre d'ann\u00E9es d'Experience :");
		lblNombreDannesDexperience.setBounds(10, 223, 244, 14);
		panel.add(lblNombreDannesDexperience);
		lblNombreDannesDexperience.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));

		nbanneeexprfield = new JTextField();
		nbanneeexprfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbanneeexprfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					nbanneeexprfield.setEditable(true);
					lblerror1.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					usernamefield.requestFocus();
				}
			}
		});
		nbanneeexprfield.setBounds(264, 220, 86, 22);
		panel.add(nbanneeexprfield);
		nbanneeexprfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbanneeexprfield.setColumns(10);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(174, 244, 176, 14);
		panel.add(lblerror1);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(124, 48, 176, 14);
		panel.add(lblerror);

		btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.setBackground(new Color(153, 255, 255));
		btnSinscrire.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSinscrire.setBackground(new Color(153, 255, 255));
				btnSinscrire.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnSinscrire.setForeground(new Color(153, 255, 255));
				btnSinscrire.setBackground(new Color(105, 105, 105));
			}
		});
		btnSinscrire.setIcon(new ImageIcon(getClass().getResource("/icons8-document-25.png")));
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("")) {
					if (!nomfield.getText().equals("") && !prenomfield.getText().equals("")
							&& !nbanneeexprfield.getText().equals("") && !usernamefield.getText().equals("")
							&& !passwordfield.getText().equals("")) {
						String sql2 = "select * from operateurusers where nom = '" + nomfield.getText().toString()
								+ "' and prenom = '" + prenomfield.getText().toString() + "'";
						try {
							prepared4 = cnx4.prepareStatement(sql2);
							resultat4 = prepared4.executeQuery();
							if (!resultat4.next()) {
								JOptionPane.showMessageDialog(null,
										"Votre nom n'est pas sur la liste des Operateurs ! veuillez vous presenter à la direction !!! ");
							} else {
								matriculeField.setText(resultat4.getString("matricule"));
								String sql = "update operateurusers set username= ? , password = ? where nom ='"
										+ nomfield.getText().toString() + "' and prenom = '"
										+ prenomfield.getText().toString() + "'";
								String er = null;
								try {

									prepared = cnx.prepareStatement(sql);

									prepared.setString(1, usernamefield.getText().toString());
									prepared.setString(2, passwordfield.getText().toString());

									prepared.execute();
									JOptionPane.showMessageDialog(null, "Operateur Inseré");

									cnx1 = ConnexionMySql.ConnectionDB();
									String sql1 = "insert into nboperateur (num_operateur , compteur ) values ( ? , ? ) ";

									prepared1 = cnx1.prepareStatement(sql1);
									prepared1.setString(1, matriculeField.getText().toString());
									String s = String.valueOf(0);
									prepared1.setString(2, s);
									prepared1.execute();

									/*
									 * String
									 * sql4="insert into nboperateur ( num_operateur , compteur ) values ( ? , ? )";
									 * cnx4=ConnexionMySql.ConnectionDB(); prepared4=cnx4.prepareStatement(sql4);
									 * prepared4.setString(1,matriculeField.getText().toString()); String ze =
									 * String.valueOf(0); prepared4.setString(2,ze); prepared4.execute();
									 */

									matriculeField.setText("");
									nomfield.setText("");
									prenomfield.setText("");
									nbanneeexprfield.setText("");
									usernamefield.setText("");
									passwordfield.setText("");
									dispose();
									Authentification obj = new Authentification();
									obj.setVisible(true);
									obj.setLocationRelativeTo(null);

								} catch (SQLException e1) {

									e1.printStackTrace();
								}

							}
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(null,
									"Numero ou username deja existant ! veuillez le changer");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Remplissez Le(s) champ(s) ");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Exitence de Champ(s) invalide(s)");
				}

			}
		});
		btnSinscrire.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		btnSinscrire.setBounds(145, 439, 141, 32);
		contentPane.add(btnSinscrire);
	}
}
