package Directeur;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Operateur.FenOperateur;
import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class DirecteurGestionOperateur extends JFrame {

	private JPanel contentPane;
	private JTable operateurstable;
	private JTextField matriculefield;
	private JTextField nomfield;
	private JTextField prenomfield;
	private JTextField exprfield;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	JLabel lblerror1;
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
					DirecteurGestionOperateur frame = new DirecteurGestionOperateur();

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
	public DirecteurGestionOperateur() {
		super("Directeur - Gestion des Operateurs");
		setResizable(false);
		setBounds(100, 100, 1203, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenDirecteur obj = new FenDirecteur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		label_1.setBounds(10, 11, 46, 29);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 63, 455, 457);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(153, 255, 255));
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAjouter.setBackground(new Color(153, 255, 255));
				btnAjouter.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnAjouter.setForeground(new Color(153, 255, 255));
				btnAjouter.setBackground(new Color(105, 105, 105));
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("")) {
					String sql = "insert into operateurusers (matricule , nom , prenom , nbexpr , username , password ) values (? , ? , ? , ? , ? , ? )";
					if (!matriculefield.getText().equals("") && !nomfield.getText().equals("")
							&& !prenomfield.getText().equals("") && !exprfield.getText().equals("")) {
						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, matriculefield.getText().toString());
							prepared.setString(2, nomfield.getText().toString());
							prepared.setString(3, prenomfield.getText().toString());
							prepared.setString(4, exprfield.getText().toString());
							String aaString = "none";
							prepared.setString(5, aaString);
							prepared.setString(6, aaString);
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Operateur ajouté");
							updatetable();
							matriculefield.setText("");
							nomfield.setText("");
							prenomfield.setText("");
							exprfield.setText("");

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Exitence de Champ(s) invalide(s)");
				}
			}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAjouter.setBounds(10, 393, 112, 28);
		panel.add(btnAjouter);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(153, 255, 255));
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(new Color(153, 255, 255));
				btnSupprimer.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setForeground(new Color(153, 255, 255));
				btnSupprimer.setBackground(new Color(105, 105, 105));
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = operateurstable.getSelectedRow();

				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = operateurstable.getModel().getValueAt(ligne, 0).toString();
					int a = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimé",
							"Suppression Operateur", JOptionPane.YES_NO_OPTION);
					String sql = "delete from operateurusers where matricule =" + id + " ";
					try {
						prepared = cnx.prepareStatement(sql);
						if (a == 0) {
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Operateur supprimé");
							updatetable();
							matriculefield.setText("");
							nomfield.setText("");
							prenomfield.setText("");
							exprfield.setText("");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
					}
				}
			}
		});
		btnSupprimer.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-supprimer-22.png")));
		btnSupprimer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnSupprimer.setBounds(143, 393, 150, 28);
		panel.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(153, 255, 255));
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnModifier.setBackground(new Color(153, 255, 255));
				btnModifier.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnModifier.setForeground(new Color(153, 255, 255));
				btnModifier.setBackground(new Color(105, 105, 105));
			}
		});
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("")) {
					int ligne = operateurstable.getSelectedRow();
					if (ligne == -1) {
						JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
					} else {
						String id = operateurstable.getModel().getValueAt(ligne, 0).toString();

						String sql = "update operateurusers set matricule = ? , nom = ? , prenom = ? , nbexpr =  ? where matricule = "
								+ id + " ";

						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, matriculefield.getText().toString());
							prepared.setString(2, nomfield.getText().toString());
							prepared.setString(3, prenomfield.getText().toString());
							prepared.setString(4, exprfield.getText().toString());
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Operateur mis à jour");
							updatetable();
							matriculefield.setText("");
							nomfield.setText("");
							prenomfield.setText("");
							exprfield.setText("");

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Exitence de Champ(s) invalide(s)");
				}
			}
		});
		btnModifier.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-modifier-22.png")));
		btnModifier.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnModifier.setBounds(317, 393, 128, 28);
		panel.add(btnModifier);

		matriculefield = new JTextField();
		matriculefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					matriculefield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					matriculefield.setEditable(true);
					lblerror.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					nomfield.requestFocus();
				}
			}
		});
		matriculefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		matriculefield.setBounds(227, 53, 189, 22);
		panel.add(matriculefield);
		matriculefield.setColumns(10);

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
		nomfield.setBounds(227, 123, 189, 31);
		panel.add(nomfield);

		prenomfield = new JTextField();
		prenomfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					exprfield.requestFocus();
				}
			}
		});
		prenomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		prenomfield.setColumns(10);
		prenomfield.setBounds(227, 196, 189, 31);
		panel.add(prenomfield);

		exprfield = new JTextField();
		exprfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					exprfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					exprfield.setEditable(true);
					lblerror1.setText("");
				}
				int key = e.getKeyCode();
				/*
				 * if(key==10){ fretfield.requestFocus(); }
				 */
			}
		});
		exprfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		exprfield.setColumns(10);
		exprfield.setBounds(227, 295, 189, 22);
		panel.add(exprfield);

		JLabel lblMatricule = new JLabel("Matricule :");
		lblMatricule.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblMatricule.setBounds(90, 56, 94, 14);
		panel.add(lblMatricule);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNom.setBounds(108, 126, 94, 14);
		panel.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPrenom.setBounds(108, 199, 94, 14);
		panel.add(lblPrenom);

		JLabel lblNombreDanneDexperience = new JLabel("Nombre d'ann\u00E9e d'experience :");
		lblNombreDanneDexperience.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNombreDanneDexperience.setBounds(10, 274, 245, 14);
		panel.add(lblNombreDanneDexperience);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(212, 75, 176, 14);
		panel.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(212, 317, 176, 14);
		panel.add(lblerror1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(509, 63, 663, 457);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 643, 401);
		panel_1.add(scrollPane);

		operateurstable = new JTable();
		operateurstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		operateurstable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = operateurstable.getSelectedRow();
				String id = operateurstable.getModel().getValueAt(ligne, 0).toString();

				String sql = " select matricule , nom , prenom ,nbexpr  from operateurusers where matricule =" + id
						+ " ";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if (resultat.next()) {
						matriculefield.setText(resultat.getString("matricule"));
						nomfield.setText(resultat.getString("nom"));
						prenomfield.setText(resultat.getString("prenom"));
						exprfield.setText(resultat.getString("nbexpr"));

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		operateurstable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(operateurstable);

		JButton btnVoirLesOperateurs = new JButton("Voir les Operateurs de Controle");
		btnVoirLesOperateurs.setBackground(new Color(153, 255, 255));
		btnVoirLesOperateurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesOperateurs.setBackground(new Color(153, 255, 255));
				btnVoirLesOperateurs.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesOperateurs.setForeground(new Color(153, 255, 255));
				btnVoirLesOperateurs.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesOperateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirLesOperateurs.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-eye-22.png")));
		btnVoirLesOperateurs.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirLesOperateurs.setBounds(188, 9, 279, 31);
		panel_1.add(btnVoirLesOperateurs);

		JButton btnConsulterLesTaches = new JButton("Consulter les taches de l'Operateur ");
		btnConsulterLesTaches.setBackground(new Color(153, 255, 255));
		btnConsulterLesTaches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnConsulterLesTaches.setBackground(new Color(153, 255, 255));
				btnConsulterLesTaches.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnConsulterLesTaches.setForeground(new Color(153, 255, 255));
				btnConsulterLesTaches.setBackground(new Color(105, 105, 105));
			}
		});
		btnConsulterLesTaches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenOperateur obj = new FenOperateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnConsulterLesTaches.setIcon(new ImageIcon(getClass().getResource("/Les Icones/consult.png")));
		btnConsulterLesTaches.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnConsulterLesTaches.setBounds(403, 11, 311, 35);
		contentPane.add(btnConsulterLesTaches);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -51, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		String sql = "Select matricule , nom , prenom , nbexpr from operateurusers";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			operateurstable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
