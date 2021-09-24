import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CompagnieGestionEquipage extends JFrame {

	private JPanel contentPane;
	private JTextField matriculefield;
	private JTextField nomfield;
	private JTextField prenomfield;
	private JTextField nbhvolfield;
	private JTable membrestable;
	JComboBox fonctionbox;
	JComboBox compagniebox;
	Connection cnx = null;
	PreparedStatement prepared=null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1=null;
	ResultSet resultat1 = null;
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
					CompagnieGestionEquipage frame = new CompagnieGestionEquipage();
					
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
	public CompagnieGestionEquipage() {
		super("Compagnie Aerienne - Gestion des Membres d'Equipage");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204,560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx=ConnexionMySql.ConnectionDB();
		cnx1=ConnexionMySql.ConnectionDB();
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			FenCompagnie obj = new FenCompagnie();
			obj.setLocationRelativeTo(null);
			obj.setVisible(true);
			dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 11, 46, 24);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(31, 46, 456, 464);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(153,255,255));
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAjouter.setBackground(new Color(153,255,255));
				btnAjouter.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnAjouter.setForeground(new Color(153,255,255));
				btnAjouter.setBackground(new Color(105,105,105));
			}
		});

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblerror.getText().equals("") && lblerror1.getText().equals("")){
					if(!matriculefield.getText().equals("") && !nomfield.getText().equals("") && !prenomfield.getText().equals("") && !nbhvolfield.getText().equals("")){
			 String sql = "insert into membre( num_membre , nom_membre , prenom_membre , fonction , nb_h_vol  , nom )  values (  ? ,? , ? , ? , ? , ?)";
			 
			 try {
				prepared=cnx.prepareStatement(sql);
                prepared.setString(1,matriculefield.getText().toString());
                prepared.setString(2,nomfield.getText().toString());
                prepared.setString(3,prenomfield.getText().toString());
                prepared.setString(4,fonctionbox.getSelectedItem().toString());
                prepared.setString(5,nbhvolfield.getText().toString());
                prepared.setString(6,compagniebox.getSelectedItem().toString());
                
                prepared.execute();
                JOptionPane.showMessageDialog(null,"Membre d'équipage ajouté)");
                updatetable();
                matriculefield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                nbhvolfield.setText("");
                		
			 }  catch (SQLException e1) {
				
				JOptionPane.showMessageDialog(null,"Numero du Membre deja existant ! Veuillez le changer");
			}}
			else{
						JOptionPane.showMessageDialog(null,"Remplissez le(s) champ(s) vide(s)");
			}}
				else{
				JOptionPane.showMessageDialog(null,"Exitence de Champ(s) invalide(s)");
			}
				
				}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnAjouter.setBounds(10, 422, 113, 31);
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(153,255,255));
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(new Color(153,255,255));
				btnSupprimer.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setForeground(new Color(153,255,255));
				btnSupprimer.setBackground(new Color(105,105,105));
			}
		});

		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int ligne = membrestable.getSelectedRow();
			if(ligne == -1){
				JOptionPane.showMessageDialog(null,"Selectionnez une ligne");
			}else{
			String id = membrestable.getModel().getValueAt(ligne,0).toString();
			
			
			String sql = " delete from membre where num_membre ="+id+" ";
			
			
			try {
				prepared=cnx.prepareStatement(sql);
			    prepared.execute();
			    JOptionPane.showMessageDialog(null,"Membre supprimé ");
			    updatetable();
			    matriculefield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                nbhvolfield.setText("");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,"Numero du Membre deja existant ! Veuillez le changer");
			}
			}}
		});
		btnSupprimer.setIcon(new ImageIcon(getClass().getResource("/icons8-supprimer-22.png")));
		btnSupprimer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnSupprimer.setBounds(152, 422, 140, 31);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(153,255,255));
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnModifier.setBackground(new Color(153,255,255));
				btnModifier.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnModifier.setForeground(new Color(153,255,255));
				btnModifier.setBackground(new Color(105,105,105));
			}
		});

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = membrestable.getSelectedRow();
				if(ligne == -1){
					JOptionPane.showMessageDialog(null,"Selectionnez une ligne");
				}else{
				String id = membrestable.getModel().getValueAt(ligne,0).toString();
				if(lblerror.getText().equals("") && lblerror1.getText().equals("")){
				String sql = " update membre set num_membre = ? , nom_membre = ? , prenom_membre = ? , fonction = ? , nb_h_vol = ? where num_membre ="+id+" ";
				
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,matriculefield.getText().toString());
					prepared.setString(2,nomfield.getText().toString());
					prepared.setString(3,prenomfield.getText().toString());
					prepared.setString(4,fonctionbox.getSelectedItem().toString());
					prepared.setString(5,nbhvolfield.getText().toString());
					
					prepared.execute();
					JOptionPane.showMessageDialog(null,"Membre mis à jour");
					updatetable();
					  matriculefield.setText("");
		                nomfield.setText("");
		                prenomfield.setText("");
		                nbhvolfield.setText("");
				} catch (SQLException e1) {
					//JOptionPane.showMessageDialog(null,"Numero du Membre deja existant ! Veuillez le changer");
				}
				
				
				}else{
					JOptionPane.showMessageDialog(null,"Exitence de Champ(s) invalide(s)");
				}
				
				}
			}
		});
		btnModifier.setIcon(new ImageIcon(getClass().getResource("/icons8-modifier-22.png")));
		btnModifier.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnModifier.setBounds(325, 422, 121, 31);
		panel.add(btnModifier);
		
		matriculefield = new JTextField();
		matriculefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if(Character.isLetter(ccc)){
					matriculefield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				}else{
					matriculefield.setEditable(true);
					lblerror.setText("");
				}
				int key=e.getKeyCode();
				if(key==10){
					nomfield.requestFocus();
				}
			}
		});
		matriculefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		matriculefield.setBounds(256, 38, 164, 22);
		panel.add(matriculefield);
		matriculefield.setColumns(10);
		
		nomfield = new JTextField();
		nomfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					prenomfield.requestFocus();
				}
				
			}
		});	
		nomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomfield.setColumns(10);
		nomfield.setBounds(256, 98, 164, 31);
		panel.add(nomfield);
		
		prenomfield = new JTextField();
		prenomfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==10){
					nbhvolfield.requestFocus();
				}
				
			}
		});	
		prenomfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		prenomfield.setColumns(10);
		prenomfield.setBounds(256, 169, 164, 31);
		panel.add(prenomfield);
		
		nbhvolfield = new JTextField();
		nbhvolfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if(Character.isLetter(ccc)){
					nbhvolfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				}else{
					nbhvolfield.setEditable(true);
					lblerror1.setText("");
				}
				int key=e.getKeyCode();
				if(key==10){
					fonctionbox.requestFocus();
				}
			}
		});
		nbhvolfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbhvolfield.setColumns(10);
		nbhvolfield.setBounds(256, 240, 164, 22);
		panel.add(nbhvolfield);
		
		 fonctionbox = new JComboBox();
		fonctionbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fonctionbox.setModel(new DefaultComboBoxModel(new String[] {"Pilote Comandant de bord", "Premier Co-Pilote", "Deuxieme Co-Pilote", "Steeward "}));
		fonctionbox.setBounds(256, 365, 164, 25);
		panel.add(fonctionbox);
		
		JLabel lblMatricule = new JLabel("Matricule :");
		lblMatricule.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblMatricule.setBounds(93, 40, 127, 14);
		panel.add(lblMatricule);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNom.setBounds(119, 100, 127, 14);
		panel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblPrenom.setBounds(93, 171, 127, 14);
		panel.add(lblPrenom);
		
		JLabel lblNombreDheureDe = new JLabel("Nombre d'heure de vols :");
		lblNombreDheureDe.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombreDheureDe.setBounds(32, 242, 175, 14);
		panel.add(lblNombreDheureDe);
		
		JLabel lblFonction = new JLabel("Fonction :");
		lblFonction.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblFonction.setBounds(103, 368, 127, 14);
		panel.add(lblFonction);
		
		JLabel lblNomDeLa = new JLabel("Nom de la Compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(63, 307, 144, 14);
		panel.add(lblNomDeLa);
		
		 compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		compagniebox.setBounds(256, 304, 164, 25);
		panel.add(compagniebox);
		
		 lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(227, 58, 165, 14);
		panel.add(lblerror);
		
		 lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(227, 258, 165, 14);
		panel.add(lblerror1);
		remplirnom();
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(536, 46, 618, 464);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 598, 442);
		panel_1.add(scrollPane_1);
		
		membrestable = new JTable();
		membrestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int ligne = membrestable.getSelectedRow();
			String id = membrestable.getModel().getValueAt(ligne,0).toString();
			
			String sql = "select * from membre where num_membre ="+id+" ";
			
			try {
				prepared=cnx.prepareStatement(sql);
		        resultat =prepared.executeQuery();
		        
		        if(resultat.next()){
		        	matriculefield.setText(resultat.getString("num_membre"));
		        	nomfield.setText(resultat.getString("nom_membre"));
		        	prenomfield.setText(resultat.getString("prenom_membre"));
		        	fonctionbox.setSelectedItem(resultat.getString("fonction"));
		        	nbhvolfield.setText(resultat.getString("nb_h_vol"));
		        }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		membrestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		membrestable.setBackground(new Color(153,255,255));
		membrestable.setForeground(Color.black);
		scrollPane_1.setViewportView(membrestable);
		
		JButton button = new JButton("Voir les membres d'\u00E9quipage");
		button.setBounds(717, 6, 256, 36);
		contentPane.add(button);
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
						String sql = " select * from membre where nom = '"+compagniebox.getSelectedItem().toString()+"'";
						
						try {
							prepared=cnx.prepareStatement(sql);
				            resultat=prepared.executeQuery();
				            membrestable.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				button.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
				button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
				label.setBounds(0, -52, 1200, 650);
				contentPane.add(label);
	}
	public void updatetable(){
		cnx=ConnexionMySql.ConnectionDB();
		String sql = " select * from membre where nom = '"+compagniebox.getSelectedItem().toString()+"'";
		
		try {
			prepared=cnx.prepareStatement(sql);
            resultat=prepared.executeQuery();
            membrestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
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
