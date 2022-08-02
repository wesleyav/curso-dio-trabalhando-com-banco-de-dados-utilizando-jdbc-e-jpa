package jdbcbasico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

//	Consulta
	public List<Aluno> list() {
		List<Aluno> alunos = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement prepareStatement = conn.prepareStatement("SELECT * FROM aluno");
			ResultSet executeQuery = prepareStatement.executeQuery();

			while (executeQuery.next()) {
				Aluno aluno = new Aluno(
						executeQuery.getInt("id"), 
						executeQuery.getString("nome"),
						executeQuery.getInt("idade"), 
						executeQuery.getString("estado"));
				alunos.add(aluno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alunos;
	}
	
//	Consulta com filtro
	public Aluno getById(int id) {
		Aluno aluno = new Aluno();

		try (Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "SELECT * FROM aluno WHERE id = ?";
			
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			
			ResultSet resultSet = prepareStatement.executeQuery();

			if(resultSet.next()) {
				aluno.setId(resultSet.getInt("id"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setIdade(resultSet.getInt("idade"));
				aluno.setEstado(resultSet.getString("estado"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aluno;
	}

//	Inserção
	public void create (Aluno aluno) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "INSERT INTO aluno (nome, idade, estado) VALUES (?, ?, ?)";
			
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			
			prepareStatement.setString(1 ,aluno.getNome());
			prepareStatement.setInt(2 ,aluno.getIdade());
			prepareStatement.setString(3 ,aluno.getEstado());
			
			int rowsAffected = prepareStatement.executeUpdate();
			System.out.println("Inserção bem sucedida!. Foi adicionada " + rowsAffected + " linha");
		
		} catch (Exception e) {
			System.out.println("Inserção falhou!");
			e.printStackTrace();
		}
	}
	
//	Delete
	public void delete (int id) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "DELETE FROM aluno WHERE id = ?";
			
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			
			prepareStatement.setInt(1, id);
			
			int rowsAffected = prepareStatement.executeUpdate();
			System.out.println("Remoção bem sucedida!. Foi removida " + rowsAffected + " linha");
		
		} catch (Exception e) {
			System.out.println("Remoção falhou!");
			e.printStackTrace();
		}
	}

//	Update
	public void update (Aluno aluno) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";
			
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			
			prepareStatement.setString(1, aluno.getNome());
			prepareStatement.setInt(2, aluno.getIdade());
			prepareStatement.setString(3, aluno.getEstado());
			prepareStatement.setInt(4, aluno.getId());
			
			int rowsAffected = prepareStatement.executeUpdate();
			System.out.println("Atualização bem sucedida!. Foi atualizada " + rowsAffected + " linha");
		
		} catch (Exception e) {
			System.out.println("Atualização falhou!");
			e.printStackTrace();
		}
	}


}
