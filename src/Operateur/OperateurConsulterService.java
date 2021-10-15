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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class OperateurConsulterService extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
					OperateurConsulterService frame = new OperateurConsulterService();

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
	public OperateurConsulterService() {
		super("Consulter les services Supplementaires");
		setResizable(false);
		setBounds(100, 100, 752, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenOperateur obj = new FenOperateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label.setBounds(10, 8, 46, 33);
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 716, 468);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		table.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(table);

		JButton btnVoir = new JButton("Voir les Services ");
		btnVoir.setBackground(new Color(153, 255, 255));
		btnVoir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoir.setBackground(new Color(153, 255, 255));
				btnVoir.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoir.setForeground(new Color(153, 255, 255));
				btnVoir.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoir.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoir.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnVoir.setBounds(243, 10, 214, 31);
		contentPane.add(btnVoir);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = "select * from service";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
