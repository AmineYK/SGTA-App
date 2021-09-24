import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompagnieVoirSuggestion extends JFrame {

	private JPanel contentPane;
	private JTable suggtable;
	JComboBox  compagniebox;
	Connection cnx=null;
	PreparedStatement prepared=null;
	ResultSet resultat = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					CompagnieVoirSuggestion frame = new CompagnieVoirSuggestion();
					
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
	public CompagnieVoirSuggestion() {
		super("Compagnie Aerienne - Voir reponse : Suggestion de Dates");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1203,560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompagnieLesDeuxFrames obj = new CompagnieLesDeuxFrames();
				obj.setLocationRelativeTo(null);
				obj.setVisible(true);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 11, 46, 24);
		contentPane.add(label_1);
		
		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		compagniebox.setBounds(43, 134, 209, 25);
		contentPane.add(compagniebox);
		
		JButton btnVoirSuggestionDe = new JButton("Voir Suggestion de Dates ");
		btnVoirSuggestionDe.setBackground(new Color(153,255,255));
		btnVoirSuggestionDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirSuggestionDe.setBackground(new Color(153,255,255));
				btnVoirSuggestionDe.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirSuggestionDe.setForeground(new Color(153,255,255));
				btnVoirSuggestionDe.setBackground(new Color(105,105,105));
			}
		});
		btnVoirSuggestionDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			upadatetable();
			}
		});
		btnVoirSuggestionDe.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirSuggestionDe.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirSuggestionDe.setBounds(20, 224, 232, 31);
		contentPane.add(btnVoirSuggestionDe);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(302, 39, 668, 459);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 648, 437);
		panel.add(scrollPane);
		
		suggtable = new JTable();
		suggtable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		suggtable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(suggtable);
		remplirnom();
		
		JLabel label_2 = new JLabel("Nom de la compagnie :");
		label_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(49, 107, 157, 14);
		contentPane.add(label_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}
	public void upadatetable(){
		cnx=ConnexionMySql.ConnectionDB();
		String id=compagniebox.getSelectedItem().toString();
		String sql= " select * from suggestion where nom = '"+id+"'";
		
		try {
			prepared=cnx.prepareStatement(sql);
			resultat=prepared.executeQuery();
			suggtable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void remplircompagnie(){
		cnx=ConnexionMySql.ConnectionDB();
		String sql= "select * from compagnie";
		
		try {
			prepared=cnx.prepareStatement(sql);
			resultat=prepared.executeQuery();
			while(resultat.next()){
				compagniebox.addItem(resultat.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public void remplirnom(){
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from connecter" ;
		
		try {
			prepared=cnx.prepareStatement(sql);
			resultat=prepared.executeQuery();
			while(resultat.next()){
				compagniebox.addItem(resultat.getString("nom_compagnie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
