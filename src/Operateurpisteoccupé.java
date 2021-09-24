import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.awt.Font;

public class Operateurpisteoccupé extends JFrame {

	private JPanel contentPane;
	private JTable table;
	PreparedStatement prepared=null;ResultSet resultat = null;
	Connection cnx=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Operateurpisteoccupé frame = new Operateurpisteoccupé();
					
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
	public Operateurpisteoccupé() {
		super("Operateur - Pistes Occupées");
		setBounds(100, 100,728,466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 692, 405);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		table.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(table);
		updatetable();
	}
	public void updatetable(){
		cnx=ConnexionMySql.ConnectionDB();
		String sql = "select * from ficheinfos";
		
		try {
			prepared=cnx.prepareStatement(sql);
		    resultat=prepared.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
