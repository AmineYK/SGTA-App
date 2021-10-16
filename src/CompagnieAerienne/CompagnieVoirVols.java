package CompagnieAerienne;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class CompagnieVoirVols extends JFrame {

	private JPanel contentPane;
	private JTable volstable;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					CompagnieVoirVols frame = new CompagnieVoirVols();

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
	public CompagnieVoirVols() {
		super("Compagnie Aerienne - Gestion des Vols : Voir les Vols");
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

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompagnieGestionvols obj = new CompagnieGestionvols();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		label_1.setBounds(10, 11, 46, 34);
		contentPane.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 47, 959, 463);
		contentPane.add(scrollPane);

		volstable = new JTable();
		volstable.setBackground(new Color(153, 255, 255));
		volstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		scrollPane.setViewportView(volstable);
		updatetable();

		JButton button = new JButton("Supprimer");
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
				int ligne = volstable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = volstable.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from vol where num_vol =" + id + " ";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.execute();
						updatetable();
						JOptionPane.showMessageDialog(null, "Vol Supprimé");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-supprimer-22.png")));
		button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		button.setBounds(1023, 271, 161, 31);
		contentPane.add(button);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = "select * from connecter";
		String nom = null;
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			if (resultat.next()) {
				nom = resultat.getString("nom_compagnie");
			}
			cnx1 = ConnexionMySql.ConnectionDB();
			String sql1 = "select * from vol where nom='" + nom + "'";
			prepared1 = cnx1.prepareStatement(sql1);
			resultat1 = prepared1.executeQuery();
			volstable.setModel(DbUtils.resultSetToTableModel(resultat1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
