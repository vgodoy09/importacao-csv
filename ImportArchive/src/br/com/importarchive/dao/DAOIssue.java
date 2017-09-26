package br.com.importarchive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.importarchive.model.Issue;
import br.com.importarchive.util.Util;



public class DAOIssue extends DAO<Issue>{

	/**
     * Este metodo preencher Issue do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Issue preencher(ResultSet resultado)
    throws SQLException {
        Issue issue = new Issue();
        issue.setId(resultado.getInt("id"));
        issue.setEmailFrom(resultado.getString("emailFrom"));
//        Issue.setNome(resultado.getString("name"));
//        Issue.setImageLogoAvailable(resultado.getString("imageLogoAvailable"));
//        Issue.setImageLogoUnavailable(resultado.getString("imageLogoUnavailable"));
//        Issue.setLink(resultado.getString("link"));
//        Issue.setVisible(resultado.getShort("visible") == 1 ? true : false);
//        Issue.setCheck(Issue.getVisible() ? "checked" : "");
//        Issue.setMessageUserActivation(resultado.getString("messageUserActivation"));
        return issue;
    }
    
    private PreparedStatement setSql(PreparedStatement sql, Issue obj) throws SQLException{
//    	sql.setString(1, obj.getNome());
//    	sql.setString(2, obj.getImageLogoAvailable());
//    	sql.setString(3, obj.getImageLogoUnavailable());
//    	sql.setString(4, obj.getLink());
//    	sql.setBoolean(5, obj.getVisible());
//    	sql.setString(6, obj.getMessageUserActivation());
//    	sql.setString(7, obj.getNamebutton());
    	return sql;
    }

	@Override
	public Object update(Issue objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update application set name=?, imageLogoAvailable=?, "
				+ "imageLogoUnavailable=?, link=?, visible=?, messageUserActivation=?, namebutton=? where id =?");
		sql = setSql(sql, objeto);
		return sql.executeUpdate();
	}
	

	@Override
	public void delete(Issue objeto, Connection conexao)throws SQLException {
	}

	@Override
	public Issue searchById(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select id, emailFrom from Attendance..Issue a where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Issue issue = null;
        if(resultado.next()){
            issue = preencher(resultado);
        }
		return issue;
	}

	@Override
	public List<Issue> listAll(Connection conexao) throws SQLException {
		return null;
	}
	
	@Override
	public void save(Issue objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into application (name,imageLogoAvailable,imageLogoUnavailable,"
				+ "link,visible,messageUserActivation,namebutton) values (?, ?, ?, ?, ?, ?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
	}
	
	public void insertIssue(String query) throws ClassNotFoundException, SQLException {
		Connection conexao = null;
		
		try {
			conexao = Util.criaConexaoSqlServer();
			insertIssue(query, conexao);
		} finally {
			try {
				conexao.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public void insertIssue(String query, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement(query);
		sql.executeUpdate();
		sql.close();
	}
	
	public void insertIssueNew(String messageBody, String registryDate, String email, PreparedStatement ps) throws SQLException {
		ps.setString(1, email);
		ps.setString(2, registryDate);
		ps.setString(3, messageBody);
		ps.executeUpdate();
	}
	
	

}
