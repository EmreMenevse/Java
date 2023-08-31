import java.sql.*;
import java.util.*;

public class Baglanti {

	public static void main(String[] args) {

		Connection connection = null;
		CallableStatement callableStatement = null;
		
		Scanner girdi = new Scanner(System.in);
		
//		System.out.println("Uye id: ");
//		int id = girdi.nextInt();
		System.out.println("Uye Ad: ");
		String ad = girdi.nextLine();	
		System.out.println("Uye Soyad: ");
		String soyad = girdi.nextLine();
		System.out.println("Uye Meslek: ");
		String meslek = girdi.nextLine();
		System.out.println("Uye Cinsiyet: ");
		String cinsiyet = girdi.nextLine();
		
		
		
		

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "Emre");

			// Parametreleri içeren prosedür çağrısı
			String procedureCall = "{call system.pr_uyeekle(?, ?, ?, ?)}";
			callableStatement = connection.prepareCall(procedureCall);

			// Parametreleri atama
//			callableStatement.setInt(1, id);
			callableStatement.setString(1, ad);
			callableStatement.setString(2, soyad);
			callableStatement.setString(3, meslek);
			callableStatement.setString(4, cinsiyet);

			// Prosedürü çağırma
			callableStatement.execute();
			System.out.println("Uye Eklendi");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
}