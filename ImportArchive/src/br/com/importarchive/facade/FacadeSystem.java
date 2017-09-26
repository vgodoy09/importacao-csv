package br.com.importarchive.facade;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.importarchive.dao.DAOIssue;

public class FacadeSystem implements Facade{
	private DAOIssue dao = new DAOIssue();

	@Override
	public void insertIssue(String query) throws ClassNotFoundException, SQLException {
		dao.insertIssue(query);
	}

	@Override
	public void insertIssueNew(String messageBody, String registryDate, String email, PreparedStatement ps)
			throws ClassNotFoundException, SQLException {
			dao.insertIssueNew(messageBody, registryDate, email, ps);
	}

}
