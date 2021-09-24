
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnexionMySql {
  Connection conn = null ;
  public static Connection ConnectionDB(){
	 
	  try {
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionta","root","");
		return conn ;
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
		return null ;
	}
  }
}
