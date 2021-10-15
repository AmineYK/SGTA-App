package Operateur;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.toedter.calendar.JDateChooser;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class OperateurSuggestionDates extends JFrame {

	private JPanel contentPane;
	private JTextField heurefield;
	private JTable demandestable;
	JDateChooser date;
	JComboBox numpistebox;
	JComboBox compagniebox;
	JComboBox pisteoccupesbox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	Connection cnx2 = null;
	PreparedStatement prepared2 = null;
	ResultSet resultat2 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurSuggestionDates frame = new OperateurSuggestionDates();

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
	public OperateurSuggestionDates() {
		super("Operateur - Suggestion de Dates");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1203, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenOperateur obj = new FenOperateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 11, 36, 24);
		contentPane.add(label_1);
		// remplirpistes();

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(804, 45, 370, 465);
		contentPane.add(panel);

		JLabel label_6 = new JLabel("Date :");
		label_6.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_6.setBounds(32, 96, 68, 14);
		panel.add(label_6);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setBounds(127, 96, 151, 27);
		panel.add(dateChooser_1);

		JLabel label_7 = new JLabel("Heure :");
		label_7.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_7.setBounds(32, 171, 78, 14);
		panel.add(label_7);

		heurefield = new JTextField();
		heurefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heurefield.setColumns(10);
		heurefield.setBounds(127, 168, 131, 22);
		panel.add(heurefield);

		JLabel lblRemplirFicheDe = new JLabel("Remplir Fiche de Suggestions de Dates de vol ");
		lblRemplirFicheDe.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		lblRemplirFicheDe.setBounds(20, 26, 328, 26);
		panel.add(lblRemplirFicheDe);

		numpistebox = new JComboBox();
		numpistebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numpistebox.setBounds(190, 336, 158, 22);
		panel.add(numpistebox);
		remplirpistes();

		JLabel label_11 = new JLabel("Numero de la piste :");
		label_11.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_11.setBounds(37, 337, 160, 14);
		panel.add(label_11);

		JButton button = new JButton("Confirmer");
		button.setBackground(new Color(153, 255, 255));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(153, 255, 255));
				button.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153, 255, 255));
				button.setBackground(new Color(105, 105, 105));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " insert into suggestion (date_sugg , heure_sugg , num_piste , nom ) values ( ? , ? , ? , ? )";
				String dt = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

				try {
					if (!heurefield.getText().equals("")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, dt);
						prepared.setString(2, heurefield.getText().toString());
						prepared.setString(3, numpistebox.getSelectedItem().toString());
						prepared.setString(4, compagniebox.getSelectedItem().toString());

						prepared.execute();
						JOptionPane.showMessageDialog(null, "Date suggeré à la Compagnie");
						heurefield.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		button.setBounds(32, 423, 131, 31);
		panel.add(button);

		JButton button_1 = new JButton("Annuler");
		button_1.setBackground(new Color(153, 255, 255));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setBackground(new Color(153, 255, 255));
				button_1.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(new Color(153, 255, 255));
				button_1.setBackground(new Color(105, 105, 105));
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		button_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		button_1.setBounds(206, 423, 131, 31);
		panel.add(button_1);

		JLabel lblNomDeLa = new JLabel("Nom de la Compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		lblNomDeLa.setBounds(32, 251, 148, 14);
		panel.add(lblNomDeLa);

		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		compagniebox.setBounds(190, 249, 158, 22);
		panel.add(compagniebox);
		remplircompagnie();

		JLabel lblNon = new JLabel("NON");
		lblNon.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 22));
		lblNon.setBounds(960, 0, 142, 35);
		contentPane.add(lblNon);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 2, true));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(20, 45, 583, 465);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnVoirLesDemandes = new JButton("Voir les demandes de Pistes");
		btnVoirLesDemandes.setBackground(new Color(153, 255, 255));
		btnVoirLesDemandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesDemandes.setBackground(new Color(153, 255, 255));
				btnVoirLesDemandes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesDemandes.setForeground(new Color(153, 255, 255));
				btnVoirLesDemandes.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesDemandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirLesDemandes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLesDemandes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnVoirLesDemandes.setBounds(170, 5, 247, 32);
		panel_2.add(btnVoirLesDemandes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 563, 415);
		panel_2.add(scrollPane);

		demandestable = new JTable();
		demandestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = demandestable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = demandestable.getModel().getValueAt(ligne, 0).toString();

					String sql = "select * from demande where id_demande =" + id + " ";

					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();

						if (resultat.next()) {
							date.setDate(resultat.getDate("date_demande"));
							// compagniebox.setSelectedItem(resultat.getString("nom"));
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		demandestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		demandestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(demandestable);
		// suggererpiste();
		date = new JDateChooser();
		date.setDateFormatString("yyyy-MM-dd");
		date.setBounds(623, 174, 151, 27);
		contentPane.add(date);

		JButton btnVoirLesPistes = new JButton("Voir les pistes Occup\u00E9es");
		btnVoirLesPistes.setBackground(new Color(153, 255, 255));
		btnVoirLesPistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesPistes.setBackground(new Color(153, 255, 255));
				btnVoirLesPistes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesPistes.setForeground(new Color(153, 255, 255));
				btnVoirLesPistes.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesPistes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				remplirpistes_occup();

			}
		});
		btnVoirLesPistes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLesPistes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnVoirLesPistes.setBounds(586, 11, 226, 25);
		contentPane.add(btnVoirLesPistes);

		pisteoccupesbox = new JComboBox();
		pisteoccupesbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		pisteoccupesbox.setBounds(623, 45, 158, 22);
		contentPane.add(pisteoccupesbox);

		JButton btnConfirmerLesPistes = new JButton("Confirmer les pistes");
		btnConfirmerLesPistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmerLesPistes.setBackground(new Color(153, 255, 255));
				btnConfirmerLesPistes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnConfirmerLesPistes.setForeground(new Color(153, 255, 255));
				btnConfirmerLesPistes.setBackground(new Color(105, 105, 105));
			}
		});
		btnConfirmerLesPistes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Operateurpisteoccupé obj = new Operateurpisteoccupé();
				obj.setLocationRelativeTo(null);
				obj.setVisible(true);
			}
		});
		btnConfirmerLesPistes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnConfirmerLesPistes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnConfirmerLesPistes.setBackground(new Color(153, 255, 255));
		btnConfirmerLesPistes.setBounds(613, 462, 181, 25);
		contentPane.add(btnConfirmerLesPistes);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -54, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select id_demande , date_demande , heure_demande , duree_demande , type_action , nom , reguler  from demande";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			demandestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public void remplirpistes(){ cnx=ConnexionMySql.ConnectionDB(); String
	 * id="degagé"; String sql = " select * from piste where etat = '"+id+"'";
	 * 
	 * try { prepared=cnx.prepareStatement(sql); resultat=prepared.executeQuery();
	 * while(resultat.next()){ pistebox.addItem(resultat.getString("num_piste")); }
	 * } catch (SQLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } }
	 */
	public void remplirpistes() {
		cnx = ConnexionMySql.ConnectionDB();
		String id = "degagé";
		String sql = " select * from piste ";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				numpistebox.addItem(resultat.getString("num_piste"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void viderpistes() {
		numpistebox.removeAllItems();
	}

	public void remplirpistes_occup() {
		cnx = ConnexionMySql.ConnectionDB();
		pisteoccupesbox.removeAllItems();
		String dt = ((JTextField) date.getDateEditor().getUiComponent()).getText();
		String sql = "select distinct num_piste from ficheinfos where date_fiche = '" + dt + " ' ";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				pisteoccupesbox.addItem(resultat.getString("num_piste"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public void remplirpistes1(){ cnx=ConnexionMySql.ConnectionDB(); String
	 * id="degagé"; String sql = " select * from piste ";
	 * 
	 * try { prepared=cnx.prepareStatement(sql); resultat=prepared.executeQuery();
	 * while(resultat.next()){
	 * pistelibrebox.addItem(resultat.getString("num_piste")); } } catch
	 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
	 * } }
	 */
	public void remplircompagnie() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = "select * from compagnieusers";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				compagniebox.addItem(resultat.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
