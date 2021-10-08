package model;

public class PessoasDAO {
	private int codigo;
	private String nome;
	private char sexo;
	private int altura;
	private String ocupacao;
	
	
	public PessoasDAO() {
		this.codigo = -1;
		this.nome = "";
		this.sexo = '*';
		this.altura = 0;
		this.ocupacao = "";
		
	}
	
	public PessoasDAO(int codigo, String login, char sexo, int altura, String ocupacao) {
		this.codigo = codigo;
		this.nome = login;
		this.sexo = sexo;
		this.altura = altura;
		this.ocupacao = ocupacao;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		if (codigo >= 0) {
			this.codigo = codigo;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		if (sexo == 'F' || sexo == 'M') {
			this.sexo = sexo;
		}
		
	}
	
	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", sexo=" + sexo + ", altura=" + altura + " cm, ocupacao=" + ocupacao + "]";
	}

}
