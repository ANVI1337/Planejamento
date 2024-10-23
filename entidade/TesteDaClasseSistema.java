package entidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TesteDaClasseSistema {
	private Sistema sistema;

	@BeforeEach
	void metodoParaPecorrerTodososTestes(){
		sistema = new Sistema();
	}

	@AfterEach
	void MetodoParaLimparOsDadosAposProcessados(){
		sistema = null;
	}

	
	@DisplayName("1.1: Teste para cadastrar aluno")
	@Test
	void testeParaCadastrarAluno() {
		double[] nota = {7.0,8.0,9.0,10.0};
		String resultado = sistema.cadastrarAluno("João", nota);
		assertEquals("Aluno cadastrado com sucesso",resultado);
	}
	
	@DisplayName("1.1: Teste para cadastrar aluno novamente")
	@Test
	void testeParaCadastrarAlunoNovamente() {
		double[] nota = {7.0,8.0,9.0,10.0};
		String resultado = sistema.cadastrarAluno("João", nota);
		assertEquals("Aluno cadastrado com sucesso",resultado);
		
		 resultado = sistema.cadastrarAluno("João", nota);
	        assertEquals("Erro: já existe um aluno cadastrado com esse nome", resultado);
	}
	
	@DisplayName("1.2: Teste com cadastro de aluno vazio")
	@Test
	void testeParaCadastrarAlunoVazio() {
		double[] nota = {7.0,8.0,9.0,10.0};
		String resultado = sistema.cadastrarAluno("", nota);
		assertEquals("Aluno cadastrado com sucesso",resultado);
	}
	
	@DisplayName("1.3: Teste para editar nota negativa")
	@Test
	void testeParaEditarNotaNegativa() {
		double[] nota = {7.0,8.0,9.0,10.0};
		sistema.cadastrarAluno("Pedro", nota);
		
		sistema.editarNotaAluno("Pedro",3, -5.0);
		Aluno aluno = sistema.getAlunoPorNome("Pedro");
		assertEquals(8.0, aluno.getNotas().get(1));
	}
	
	@DisplayName("1.4: Teste para editar nomes com espaço")
	@Test
	void testeParaEditarNomesComEspaco() {
	    double[] nota = {7.0, 8.0, 9.0, 10.0};
	    sistema.cadastrarAluno("Maria", nota);
	    
	    sistema.editarNomeAluno("Maria", " ");
	    Aluno alunoDepois = sistema.getAlunoPorNome("Maria");
	    
	    assertEquals("Maria", alunoDepois.getNome());
	}


	
	@DisplayName("1.5: Teste para gerar relatorio sem aluno")
	@Test
	void testeParaEditarNomeComEspaco() {
		String relatorio = sistema.gerarRelatório();
		assertEquals("", relatorio);
	}
	
	@DisplayName("1.6: Teste para editar nota do Aluno em uma posição que não existe no indice.")
	@Test
	public void testEditarNotaAlunoIndiceInvalido() {
	    double[] notas = {7.0, 8.0, 9.0, 10.0};
	    sistema.cadastrarAluno("João", notas);

	    sistema.editarNotaAluno("João", 5, 8.0); 
	    Aluno aluno = sistema.getAlunoPorNome("João");
	    assertEquals(9.0, aluno.getNotas().get(2)); 
	}
	
	@DisplayName("1.7: Teste para cadastrar aluno mais de 4 notas.")
	@Test
	public void testCadastrarAlunoComMaisNotas() {
	    double[] notas = {7.0, 8.0, 9.0, 10.0, 6.0}; 
	    String resultado = sistema.cadastrarAluno("João", notas);
	    assertEquals("Aluno cadastrado com sucesso", resultado); 
	}
	
	@DisplayName("1.8: Teste para cadastrar com o nome variado entre minusculos e maiusculos")
	@Test
	public void testeCadastrarAlunoComNomeIgualMasVariandoEntreMaiusculoEMinusculo() {
	    Sistema sistema = new Sistema();
	    
	    String resultadoCadastro1 = sistema.cadastrarAluno("João", new double[]{7.5, 8.0, 6.5, 9.0});
	    assertEquals("Aluno cadastrado com sucesso", resultadoCadastro1);
	 
	    String resultadoCadastro2 = sistema.cadastrarAluno("joão", new double[]{8.5, 7.0, 9.5, 10.0});
	    assertEquals("Erro: já existe um aluno cadastrado com esse nome", resultadoCadastro2);
	}
	
	@DisplayName("1.9: Teste para editar nome maiusculo para minusculo")
	@Test
	public void testeEditarNomeComVariacaoMaiusculasMinusculas() {
	    Sistema sistema = new Sistema();
	    
	    String resultadoCadastro = sistema.cadastrarAluno("Maria", new double[]{6.0, 7.0, 8.0, 5.0});
	    assertEquals("Aluno cadastrado com sucesso", resultadoCadastro);
	    
	    sistema.editarNomeAluno("Maria", "maria");
	    
	    Aluno alunoRecuperado = sistema.getAlunoPorNome("maria");
	    assertNotNull(alunoRecuperado); // esse not null verificar se o resultado esperado é igual com o resultado.
	    assertEquals("Maria", alunoRecuperado.getNome());
	}
	
	@DisplayName("1.10: Teste para cadastrar novo aluno.")
	@Test
	public void testCadastrarNovoAluno() {
	    Sistema sistema = new Sistema();
	    
	    String resultado = sistema.cadastrarAluno("Maria", new double[]{8.0, 7.5, 9.0, 6.5});
	    
	    assertEquals("Aluno cadastrado com sucesso", resultado);
	    
	    Aluno aluno = sistema.getAlunoPorNome("Maria");
	    assertEquals("Maria", aluno.getNome()); 
	    assertEquals(4, aluno.getNotas().size()); 
	    assertEquals(8.0, aluno.getNotas().get(0)); 
	}
}
