package br.com.importarchive.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.importarchive.util.Util;





public abstract class DAO<T> {

	
    public void save(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoSqlServer();
            save(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    public List<T> listAll() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoSqlServer();
            return listAll(conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    public T searchById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoSqlServer();
            return searchById(id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
    
    public void delete(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoSqlServer();
            delete(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    public void update(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoSqlServer();
            update(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    public abstract Object update(T objeto, Connection conexao) throws SQLException;
    public abstract void delete(T objeto, Connection conexao) throws SQLException;
    public abstract T searchById(int id, Connection conexao) throws SQLException;
    public abstract List<T> listAll(Connection conexao) throws SQLException;
    public abstract void save(T objeto, Connection conexao) throws SQLException;
}
