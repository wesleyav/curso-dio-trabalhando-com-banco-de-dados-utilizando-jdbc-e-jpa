package jdbcbasico;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {

	public static void main(String[] args) {

		String driver = "mysql";
		String dataBaseAddress = "localhost";
		String dataBaseName = "digital_innovation_one";
		String user = "root";
		String password = "root";

		StringBuilder sb = new StringBuilder("jdbc:")
				.append(driver).append("://")
				.append(dataBaseAddress).append("/")
				.append(dataBaseName);

		String connectionUrl = sb.toString();

		try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
			System.out.println("Conexão realizada com Sucesso!");
		} catch (Exception e) {
			System.out.println("Falha na conexão!");
			e.printStackTrace();
		}
	}

}
