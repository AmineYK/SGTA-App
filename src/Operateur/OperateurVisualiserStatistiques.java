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

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import Welcome.ConnexionMySql;

public class OperateurVisualiserStatistiques extends JFrame {

	private JPanel contentPane;
	private JTextField categoriefield;
	private JTextField atterirfield;
	private JTextField decolerfield;
	private JTextField voleffectuefield;
	private JTextField compagniesfield;
	private JTextField pistesfield;
	JComboBox compagniebox;
	JComboBox categoriebox;
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
					OperateurVisualiserStatistiques frame = new OperateurVisualiserStatistiques();

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
	public OperateurVisualiserStatistiques() {
		super("Operateur - Visualiser Statistiques");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204, 560);
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
		label_1.setBounds(10, 11, 46, 24);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 55, 393, 455);
		contentPane.add(panel);
		panel.setLayout(null);

		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		compagniebox.setBounds(186, 18, 178, 22);
		panel.add(compagniebox);

		JLabel lblNomDeLa = new JLabel("Nom de la Compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(10, 21, 166, 14);
		panel.add(lblNomDeLa);

		JButton btnVoirNombresDaeronefs = new JButton("Voir nombres d'Aeronefs  par Categorie :");
		btnVoirNombresDaeronefs.setBackground(new Color(153, 255, 255));
		btnVoirNombresDaeronefs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombresDaeronefs.setBackground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombresDaeronefs.setForeground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombresDaeronefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString = categoriebox.getSelectedItem().toString();
				String id = compagniebox.getSelectedItem().toString();
				String sql = " select count(*) from aeronef where categorie = '" + idString + "' and nom = '" + id
						+ "'";
				int cpt = 0;
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String string = String.valueOf(cpt);
					categoriefield.setText(string);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombresDaeronefs.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombresDaeronefs.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombresDaeronefs.setBounds(17, 70, 347, 26);
		panel.add(btnVoirNombresDaeronefs);

		categoriebox = new JComboBox();
		categoriebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		categoriebox.setModel(new DefaultComboBoxModel(
				new String[] { "De Sport et de Loisirs ", "Commerciaux", "A Services divers", "A Usage militaire" }));
		categoriebox.setBounds(186, 104, 178, 22);
		panel.add(categoriebox);
		remplirnom();

		JLabel lblCategorie = new JLabel("Categorie :");
		lblCategorie.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblCategorie.setBounds(51, 107, 90, 14);
		panel.add(lblCategorie);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombre.setBounds(51, 148, 90, 14);
		panel.add(lblNombre);

		categoriefield = new JTextField();
		categoriefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		categoriefield.setBounds(186, 145, 178, 22);
		panel.add(categoriefield);
		categoriefield.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(10, 54, 373, 5);
		panel.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(10, 179, 373, 5);
		panel.add(panel_2);

		JButton btnVoirNombresDaeronefs_1 = new JButton("Voir nombres d'Aeronefs  Atteris  :");
		btnVoirNombresDaeronefs_1.setBackground(new Color(153, 255, 255));
		btnVoirNombresDaeronefs_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombresDaeronefs_1.setBackground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs_1.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombresDaeronefs_1.setForeground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs_1.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombresDaeronefs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "Atterissage";
				String sql = "select count(*) from vol where nom = '" + compagniebox.getSelectedItem().toString()
						+ "' and action = '" + id + "'";
				int cpt = 0;
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String t = String.valueOf(cpt);
					atterirfield.setText(t);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnVoirNombresDaeronefs_1.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombresDaeronefs_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombresDaeronefs_1.setBounds(17, 195, 347, 26);
		panel.add(btnVoirNombresDaeronefs_1);

		JLabel label_2 = new JLabel("Nombre :");
		label_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_2.setBounds(51, 234, 90, 14);
		panel.add(label_2);

		atterirfield = new JTextField();
		atterirfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		atterirfield.setColumns(10);
		atterirfield.setBounds(186, 229, 178, 22);
		panel.add(atterirfield);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(10, 263, 373, 5);
		panel.add(panel_3);

		JButton btnVoirNombresDaeronefs_2 = new JButton("Voir nombres d'Aeronefs  Decol\u00E9s  :");
		btnVoirNombresDaeronefs_2.setBackground(new Color(153, 255, 255));
		btnVoirNombresDaeronefs_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombresDaeronefs_2.setBackground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs_2.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombresDaeronefs_2.setForeground(new Color(153, 255, 255));
				btnVoirNombresDaeronefs_2.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombresDaeronefs_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "Decolage";
				String sql = "select count(*) from vol where nom = '" + compagniebox.getSelectedItem().toString()
						+ "' and action = '" + id + "'";
				int cpt = 0;
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String t = String.valueOf(cpt);
					decolerfield.setText(t);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombresDaeronefs_2.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombresDaeronefs_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombresDaeronefs_2.setBounds(17, 277, 347, 26);
		panel.add(btnVoirNombresDaeronefs_2);

		JLabel label_3 = new JLabel("Nombre :");
		label_3.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_3.setBounds(51, 324, 90, 14);
		panel.add(label_3);

		decolerfield = new JTextField();
		decolerfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		decolerfield.setColumns(10);
		decolerfield.setBounds(186, 320, 178, 22);
		panel.add(decolerfield);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(10, 349, 373, 5);
		panel.add(panel_4);

		JButton btnVoirNombreDe = new JButton("Voir nombre de Vols effectu\u00E9s :");
		btnVoirNombreDe.setBackground(new Color(153, 255, 255));
		btnVoirNombreDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombreDe.setBackground(new Color(153, 255, 255));
				btnVoirNombreDe.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombreDe.setForeground(new Color(153, 255, 255));
				btnVoirNombreDe.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombreDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " select count(*) from vol where nom='" + compagniebox.getSelectedItem().toString() + "'";
				int cpt = 0;

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String string = String.valueOf(cpt);
					voleffectuefield.setText(string);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombreDe.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombreDe.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombreDe.setBounds(17, 365, 347, 26);
		panel.add(btnVoirNombreDe);

		JLabel label_4 = new JLabel("Nombre :");
		label_4.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(51, 417, 90, 14);
		panel.add(label_4);

		voleffectuefield = new JTextField();
		voleffectuefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		voleffectuefield.setColumns(10);
		voleffectuefield.setBounds(186, 414, 178, 22);
		panel.add(voleffectuefield);

		JButton btnVoirNombreDe_1 = new JButton("Voir nombre de Compagnies Aeriennes en interaction avec l'Aeroport :");
		btnVoirNombreDe_1.setBackground(new Color(153, 255, 255));
		btnVoirNombreDe_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombreDe_1.setBackground(new Color(153, 255, 255));
				btnVoirNombreDe_1.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombreDe_1.setForeground(new Color(153, 255, 255));
				btnVoirNombreDe_1.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombreDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " select count(*) from compagnieusers";
				int cpt = 0;

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String string = String.valueOf(cpt);
					compagniesfield.setText(string);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombreDe_1.setBounds(444, 487, 493, 26);
		contentPane.add(btnVoirNombreDe_1);
		btnVoirNombreDe_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombreDe_1.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));

		compagniesfield = new JTextField();
		compagniesfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		compagniesfield.setBounds(1029, 490, 97, 20);
		contentPane.add(compagniesfield);
		compagniesfield.setColumns(10);

		JButton btnVoirNombreDe_2 = new JButton("Voir nombre de Pistes :");
		btnVoirNombreDe_2.setBackground(new Color(153, 255, 255));
		btnVoirNombreDe_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombreDe_2.setBackground(new Color(153, 255, 255));
				btnVoirNombreDe_2.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombreDe_2.setForeground(new Color(153, 255, 255));
				btnVoirNombreDe_2.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombreDe_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " select count(*) from piste";
				int cpt = 0;

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}
					String string = String.valueOf(cpt);
					pistesfield.setText(string);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombreDe_2.setBounds(116, 12, 279, 26);
		contentPane.add(btnVoirNombreDe_2);
		btnVoirNombreDe_2.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombreDe_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));

		pistesfield = new JTextField();
		pistesfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		pistesfield.setBounds(444, 13, 97, 20);
		contentPane.add(pistesfield);
		pistesfield.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.RED, Color.BLUE));
		panel_5.setBounds(428, 53, 757, 423);
		contentPane.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		JButton btnVoirLeGraphe = new JButton("Voir le graphe des pistes  utilis\u00E9es");
		btnVoirLeGraphe.setBackground(new Color(153, 255, 255));
		btnVoirLeGraphe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLeGraphe.setBackground(new Color(153, 255, 255));
				btnVoirLeGraphe.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLeGraphe.setForeground(new Color(153, 255, 255));
				btnVoirLeGraphe.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLeGraphe.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLeGraphe.setBounds(594, 11, 418, 31);
		contentPane.add(btnVoirLeGraphe);
		btnVoirLeGraphe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select numpiste , nbpiste from nombre_piste";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					JDBCCategoryDataset dataset = new JDBCCategoryDataset(cnx, sql);
					JFreeChart chart = ChartFactory.createBarChart("Courbe des Pistes", "numero de la piste",
							"Son utilisation", dataset, PlotOrientation.VERTICAL, false, true, true);
					// BarRenderer renderer=null;
					// chart.setBackgroundPaint(new Color(153,255,255));
					// chart.getTitle().setBackgroundPaint(Color.lightGray);

					CategoryPlot plot = chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.green);
					// renderer=new BarRenderer();
					ChartFrame pp = new ChartFrame("mon graphe", chart);
					pp.setVisible(false);
					pp.setLocationRelativeTo(contentPane);
					ChartPanel panel = new ChartPanel(chart);
					panel_5.removeAll();
					panel_5.add(panel);
					panel_5.updateUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirLeGraphe.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 14));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}

	public void remplirnom() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from compagnieusers";

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
