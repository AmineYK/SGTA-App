import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DirecteurAjouterListeNoir extends JFrame {

	private JPanel contentPane;
	Connection cnx = null;
	Connection cnx1 = null;
	PreparedStatement prepared = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat = null;
	ResultSet resultat1 = null;
	String merde ;
	private JTable table;
	private JTextField nomfield;
	private JTextField paysfield;
	private JTextField idfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DirecteurAjouterListeNoir frame = new DirecteurAjouterListeNoir();
					
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
	public DirecteurAjouterListeNoir() {
		super("Directeur - Ajouter à la Liste Noir");
		setResizable(false);
		setBounds(100, 100, 736, 533);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 732, 504);
		contentPane.add(panel);
		
		
		

		
		JButton btnConfirmer = new JButton("Annuler ");
		btnConfirmer.setBackground(new Color(153,255,255));
		btnConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmer.setBackground(new Color(153,255,255));
				btnConfirmer.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnConfirmer.setForeground(new Color(153,255,255));
				btnConfirmer.setBackground(new Color(105,105,105));
			}
		});
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnConfirmer.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		btnConfirmer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnConfirmer.setBounds(526, 452, 141, 29);
		panel.add(btnConfirmer);
		
		JButton button = new JButton("Confirmer ");
		
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String sql = " insert into listenoir (id_compagnie ,nom ,pays ) values ( ? , ? , ? )";
			if(!idfield.getText().equals("") && !nomfield.getText().equals("") && !paysfield.getText().equals("")){
			try {
				int a =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment placer cette Compagnie en Liste Noire ?!","Ajout en Liste Noire",JOptionPane.YES_NO_OPTION);
				prepared=cnx.prepareStatement(sql);
				prepared.setString(1,idfield.getText().toString());
				prepared.setString(2, nomfield.getText().toString());
				prepared.setString(3,paysfield.getText().toString());
				if(a==0){prepared.execute();
				JOptionPane.showMessageDialog(null,"Compagnie Aerienne ajoutée a la liste noir");
				
				/*cnx1=ConnexionMySql.ConnectionDB();
				String sql1="delete from compagnieusers where nom= '"+nomfield.getText().toString()+"'";
				prepared1=cnx1.prepareStatement(sql1);
				prepared1.execute();*/
				dispose();
			}} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Compagnie deja sur la liste noire");
			}}else{
				JOptionPane.showMessageDialog(null,"Remplissez les champs");
			}
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		button.setBounds(285, 452, 141, 29);
		panel.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 11, 503, 424);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(153, 255, 255));
		table.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		scrollPane.setViewportView(table);
		updatetable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int ligne  = table.getSelectedRow();
			String id = table.getModel().getValueAt(ligne,0).toString();
			
			
			String sql = " select * from compagnieusers where  nom = '"+id +"'"; 
			
			try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
				
				if(resultat.next()){
					nomfield.setText(resultat.getString("nom"));
					idfield.setText(resultat.getString("numerotel"));
					paysfield.setText(resultat.getString("pays"));
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		JLabel lblNomDeLa = new JLabel("Nom de la compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(10, 88, 171, 12);
		panel.add(lblNomDeLa);
		
		nomfield = new JTextField();
		nomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomfield.setBounds(10, 111, 188, 22);
		panel.add(nomfield);
		nomfield.setColumns(10);
		
		JLabel lblPaysDorigine = new JLabel("Pays d'origine :");
		lblPaysDorigine.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPaysDorigine.setBounds(10, 183, 171, 22);
		panel.add(lblPaysDorigine);
		
		paysfield = new JTextField();
		paysfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		paysfield.setColumns(10);
		paysfield.setBounds(10, 216, 188, 22);
		panel.add(paysfield);
		
		idfield = new JTextField();
		idfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		idfield.setColumns(10);
		idfield.setBounds(10, 345, 188, 22);
		panel.add(idfield);
		
		JLabel lblNumeroDeLa = new JLabel("Numero de la compagnie :");
		lblNumeroDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNumeroDeLa.setBounds(10, 312, 199, 22);
		panel.add(lblNumeroDeLa);
	}
	public void updatetable(){
		String sql = "Select nom,site,email,numerotel,pays from compagnieusers";
		
		try {
			prepared=cnx.prepareStatement(sql);
            resultat=prepared.executeQuery();
          table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			}
}
