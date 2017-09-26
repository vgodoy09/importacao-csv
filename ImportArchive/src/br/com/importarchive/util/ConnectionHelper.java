package br.com.importarchive.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHelper {

	private static Connection conexao = null;
	private static PreparedStatement ps = null;
	
	private ConnectionHelper() {
		criaConexao();
	}
	
	public static void init(String issueQuery) {
		getPreparedStatement(issueQuery);
	}

	private static Connection criaConexao() {
		try {
			conexao = Util.criaConexaoSqlServerOficial();
		} catch(Exception e) {
			try {
				conexao.close();
				conexao = null;
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
		return conexao;
	}
	
	public static final PreparedStatement getPreparedStatement(String query) {
		Connection conn = criaConexao();
		PreparedStatement prepareStatement = criaPreparedStatement(query, conn);
		return prepareStatement;
	}

	private static PreparedStatement criaPreparedStatement(String query, Connection conn) {

		if(ps != null) {
			return ps;
		}
		
		try {
			ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			
			throw new RuntimeException(e);
		}
		return ps;
	}

	public static void close() {
		if(conexao != null) {
			try {
				conexao.close();
				conexao = null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		if(ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}