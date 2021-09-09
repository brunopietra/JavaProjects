//minha versao

package ex2;

import java.sql.*;

public class DAO {
private Connection conexao;
	
	public DAO() {
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
	
	public boolean inserirPessoa(Pessoa pessoa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO pessoa (codigo, nome, sexo, altura, ocupacao) "
					       + "VALUES ("+pessoa.getCodigo()+ ", '" + pessoa.getNome() + "', '"  
					       + pessoa.getSexo() + "', '" + pessoa.getAltura() + "', '" + pessoa.getOcupacao() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarPessoa(Pessoa pessoa) {
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
	
	
	public Pessoa[] getPessoa() {
		Pessoa[] pessoa = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM pessoa");		
	         if(rs.next()){
	             rs.last();
	             pessoa = new Pessoa[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                pessoa[i] = new Pessoa(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getString("sexo").charAt(0), rs.getInt("altura"), rs.getString("ocupacao"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pessoa;
	}

	
}
