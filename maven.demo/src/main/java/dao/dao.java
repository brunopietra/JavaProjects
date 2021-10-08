 package dao;
import java.sql.*;

import model.PessoasDAO;

import java.util.List;

public class dao {

private Connection conexao;
	
	public dao() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "celebridades";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	private List<PessoasDAO> pessoas;
	private int maxId = 0;



	public int getMaxId() {
		return maxId;
	}

	/*
	public dao(String filename) throws IOException {
		file = new File(filename);
		produtos = new ArrayList<PessoasDAO>();
		if (file.exists()) {
			readFromFile();
		}

	}
	*/

	public void add(PessoasDAO pessoa) {
		try {
			pessoas.add(pessoa);
			this.maxId = (pessoa.getCodigo() > this.maxId) ? pessoa.getCodigo() : this.maxId;
			
		} catch (Exception e) {
			System.out.println("ERRO ao gravar '" + pessoa.getNome() + "' no disco!");
		}
	}

	/*
	public Produto get(int id) {
		for (Produto produto : produtos) {
			if (id == produto.getId()) {
				return produto;
			}
		}
		return null;
	}
	*/
	
	public void update(PessoasDAO p) {
		int index = pessoas.indexOf(p);
		if (index >= 0) {
			pessoas.set(index, p);
		}
	}

	public void remove(PessoasDAO p) {
		int index = pessoas.indexOf(p);
		if (index >= 0) {
			pessoas.remove(index);
			
		}
	}

	public List<PessoasDAO> getAll() {
		return pessoas;
	}

	public PessoasDAO get(int codigo) {
		for (PessoasDAO pessoa : pessoas) {
			if (codigo == pessoa.getCodigo()) {
				return pessoa;
			}
		}
		return null;
	}



	
	
	/*
	public boolean inserirPessoa(PessoasDAO pessoa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO pessoa (codigo, nome, sexo, altura, ocupacao) "
					       + "VALUES ("+ pessoa.getCodigo() + ", '" + pessoa.getNome() + "', '"  
					       + pessoa.getSexo() + "', '" + pessoa.getAltura() + "', '" + pessoa.getOcupacao() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarPessoa(PessoasDAO pessoa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE pessoa SET nome = '" + pessoa.getNome() + "', sexo = '" + pessoa.getSexo() + "', altura=" 
					   + pessoa.getAltura() + "', ocupacao = '"  
				       + pessoa.getOcupacao() 
					   + " WHERE codigo = " + pessoa.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirPessoa(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM pessoa WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public PessoasDAO[] getPessoa() {
		PessoasDAO[] pessoa = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM pessoa");		
	         if(rs.next()){
	             rs.last();
	             pessoa = new PessoasDAO[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                pessoa[i] = new PessoasDAO(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getString("sexo").charAt(0), rs.getInt("altura"), rs.getString("ocupacao"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pessoa;
	}
	*/

}
