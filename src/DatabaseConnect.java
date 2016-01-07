import java.sql.*;
public class DatabaseConnect {
	
	private static Connection curConnect = null;
	private DatabaseConnect() {}
	
	public static Connection getConnection(){
		if(curConnect == null)
			try{
				Class.forName("com.mysql.jdbc.Driver");
				curConnect = DriverManager.getConnection("jdbc:mysql://10.10.1.70:3306/nselz","nselz","0359309");
			} 
			catch(SQLException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		return curConnect;
	}
	
}
