package br.com.importarchive.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	
	public static Connection criaConexaoPostgresql()
            throws ClassNotFoundException, SQLException {
            // Definir o driver de conexão.
            Class.forName("org.postgresql.Driver");
            // Cria uma conexão.
            String url = "jdbc:postgresql://127.0.0.1:5432/church";
            Connection conexao = null;
            if(conexao == null){
                conexao = DriverManager.getConnection(url, "postgres", "x8pdkv19");
            }
            return conexao;
    }
	
	public static Connection criaConexaoMySql()
            throws ClassNotFoundException, SQLException {
            // Definir o driver de conexão.
            Class.forName("com.mysql.jdbc.Driver");
            // Cria uma conexão.
            String url = "jdbc:mysql://127.0.0.1:3306/church";
//            String url = "jdbc:mysql://mysql117150-chuchmissionary.jelasticlw.com.br/church";
            Connection conexao = null;
            if(conexao == null){
                conexao = DriverManager.getConnection(url, "root", "x8pdkv19");
//                conexao = DriverManager.getConnection(url, "root", "lolbQ5FAdm");
            }
            return conexao;
    }
	
	public static Connection criaConexaoSqlServer()
			throws ClassNotFoundException, SQLException {
		// Definir o driver de conexão.
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Cria uma conexão.
		String url = "jdbc:sqlserver://10.21.0.25;;databaseName=Attendance";
//            String url = "jdbc:mysql://mysql117150-chuchmissionary.jelasticlw.com.br/church";
		Connection conexao = null;
		if(conexao == null) {
			conexao = DriverManager.getConnection(url, "attendancesysuser", "br34d14a69");
//                conexao = DriverManager.getConnection(url, "root", "lolbQ5FAdm");
		}
		return conexao;
	}
	
	public static Connection criaConexaoSqlServerOficial()
			throws ClassNotFoundException, SQLException {
		// Definir o driver de conexão.
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Cria uma conexão.
		String url = "jdbc:sqlserver://10.21.0.200;;databaseName=Attendance";
//            String url = "jdbc:mysql://mysql117150-chuchmissionary.jelasticlw.com.br/church";
		Connection conexao = null;
		if(conexao == null) {
			conexao = DriverManager.getConnection(url, "attendancesysuser", "br34d14a69");
//                conexao = DriverManager.getConnection(url, "root", "lolbQ5FAdm");
		}
		return conexao;
	}
}
			