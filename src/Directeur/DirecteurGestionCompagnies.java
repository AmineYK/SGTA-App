package Directeur;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class DirecteurGestionCompagnies extends JFrame {

	private JPanel contentPane;
	private JTable compagniestable;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DirecteurGestionCompagnies frame = new DirecteurGestionCompagnies();

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
	public DirecteurGestionCompagnies() {
		super("Directeur - Gestion des Compagnies Aeriennes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1211, 560);
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
		label_1.setBounds(10, 11, 46, 24);
		contentPane.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(149, 46, 940, 464);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnVoirLesCompagnies = new JButton("Voir les Compagnies aeriennes ");
		btnVoirLesCompagnies.setBackground(new Color(153, 255, 255));
		btnVoirLesCompagnies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesCompagnies.setBackground(new Color(153, 255, 255));
				btnVoirLesCompagnies.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesCompagnies.setForeground(new Color(153, 255, 255));
				btnVoirLesCompagnies.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesCompagnies.setToolTipText("");
		btnVoirLesCompagnies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "Select nom,site,email,numerotel,pays from compagnieusers";

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					compagniestable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirLesCompagnies.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-eye-22.png")));
		btnVoirLesCompagnies.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirLesCompagnies.setBounds(327, 4, 282, 34);
		panel_1.add(btnVoirLesCompagnies);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 920, 413);
		panel_1.add(scrollPane);

		compagniestable = new JTable();
		compagniestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		compagniestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = compagniestable.getSelectedRow();
				String id = compagniestable.getModel().getValueAt(ligne, 0).toString();

				String sql = " select nom,site,email,numerotel,pays from compagnie where  id_compagnie = " + id + " ";

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();

					if (resultat.next()) {
						// nomfield.setText(resultat.getString("nom"));
						// sitefield.setText(resultat.getString("site"));
						// emailfield.setText(resultat.getString("email"));
						// numtelfield.setText(resultat.getString("numtel"));
						// paysfield.setText(resultat.getString("pays"));
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		compagniestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(compagniestable);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -52, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		String sql = "Select nom,site,email,numerotel,pays from compagnie";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			compagniestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
