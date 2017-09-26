package br.com.importarchive.teste;

import java.sql.SQLException;

import br.com.importarchive.dao.DAO;
import br.com.importarchive.dao.DAOIssue;
import br.com.importarchive.model.Issue;

public class TesteConection {

	public static void main(String[] args) {
		DAO<Issue> dao = new DAOIssue();
		try {
			Issue issue = dao.searchById(5948587);
			System.out.println("id " + issue == null ? "" : issue.getId() + " emailFrom " + issue == null ? "" : issue.getEmailFrom());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
