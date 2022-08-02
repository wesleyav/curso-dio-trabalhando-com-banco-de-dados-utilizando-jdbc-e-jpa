package jdbcbasico;

import java.util.List;

public class QueriesExecution {

	public static void main(String[] args) {

		AlunoDAO alunoDAO = new AlunoDAO();

		List<Aluno> alunos = alunoDAO.list();
		System.out.println("Consulta");
		alunos.stream().forEach(System.out::println);

		System.out.println("------------------");

		Aluno alunoParaConsulta = alunoDAO.getById(3);
		System.out.println("Consulta com filtro");
		System.out.println(alunoParaConsulta);

		System.out.println("------------------");

		Aluno alunoParaInsercao = new Aluno("Matheus", 43, "SP");
//		System.out.println("Inserção");
//		alunoDAO.create(alunoParaInsercao);

		System.out.println("------------------");

		alunoDAO.list().stream().forEach(System.out::println);
		System.out.println("Remoção");
		alunoDAO.delete(4);
		alunoDAO.list().forEach(System.out::println);

		System.out.println("------------------");

		alunoDAO.list().stream().forEach(System.out::println);
		Aluno alunoParaAtualizar = alunoDAO.getById(3);

		alunoParaAtualizar.setNome("Joaquim");
		alunoParaAtualizar.setIdade(18);
		alunoParaAtualizar.setEstado("RN");

		alunoDAO.update(alunoParaAtualizar);

		alunoDAO.list().stream().forEach(System.out::println);

		System.out.println("------------------");

	}

}
