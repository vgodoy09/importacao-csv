package br.com.importarchive.facade;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Facade {
	public void insertIssue(String query) throws ClassNotFoundException, SQLException;
	void insertIssueNew(String messageBody, String registryDate, String email, PreparedStatement ps) throws ClassNotFoundException, SQLException;
}
