package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Contato;

public class ContatoDAO {

	public void save(Contato contato) {

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.execute();
			System.out.println("Contato salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> findContatos(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rst = null;
		String sql = "SELECT * FROM contatos";
		List<Contato> contatos = new ArrayList<Contato>();

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rst = pstm.executeQuery();

			while (rst.next()) {
				Contato contato = new Contato();
				contato.setId(rst.getInt("id"));
				contato.setNome(rst.getString("nome"));
				contato.setIdade(rst.getInt("idade"));
				contato.setDataCadastro(rst.getDate("dataCadastro"));
				contatos.add(contato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rst != null) {
					rst.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	public void updateContato(Contato contato){
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			pstm.setInt(4, contato.getId());
            pstm.execute();
            System.out.println("Contato atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id){
		Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM contatos WHERE id =?";

		try {
			conn  = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
			System.out.println("Contato deletado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

