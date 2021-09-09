/*
package ti2ccEx2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		DAO dao = new DAO();
		
		dao.conectar();
		
		System.out.println("Escolha uma das seguintes opcoes: 1-listar, 2-inserir, 3-excluir, 4-atualizar, 5-sair:");
		int resp = tec.nextInt();
		
		while(resp == 1 || resp == 2 || resp == 3 || resp == 4) {
			if (resp == 1) {
				System.out.println("==== Mostrar pessoas === ");
				Pessoa[] pessoa = dao.getPessoa();
				for(int i = 0; i < pessoa.length; i++) {
					System.out.println(pessoa[i].toString());
				}
				
			}
			tec.close();
		}
	}

}
*/
package ex2;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		//estabelece coneccao com o servidor
		dao.conectar();
		Scanner t = new Scanner(System.in);

		
		//Inserir um elemento na tabela
		System.out.println("Digite a opcao desejada [1-Listar, 2-Inserir, 3-Excluir, 4-Atualizar, 5-Sair]:");
		int resp = t.nextInt();
		
		while(resp == 1 || resp == 2 || resp == 3 || resp == 4) {
			if(resp == 1) {
				//mostrar todos os elementos
				Pessoa[] pessoa = dao.getPessoa();
				System.out.println("==== Mostrar usuários === ");		
				for(int i = 0; i < pessoa.length; i++) {
					System.out.println(pessoa[i].toString());
				}
			}
			else if(resp == 2) {
				
				//inserir nova pessoa a lista
				System.out.println("Codigo: ");
				int codigo = t.nextInt();
				
				System.out.println("Nome: ");
				String nome = t.next();
				
				
				System.out.println("Sexo: ");
				String strsexo = t.next();
				char sexo = strsexo.charAt(0);
				
				System.out.println("Altura: ");
				int altura = t.nextInt();
				
				System.out.println("Ocupacao: ");
				String ocupacao = t.next();
				
				Pessoa pessoa = new Pessoa(codigo, nome, sexo, altura, ocupacao);
				if(dao.inserirPessoa(pessoa) == true) {
					System.out.println("Inserção com sucesso -> " + pessoa.toString());
				}
				
			}
			else if(resp == 3) {
				//excluir pessoa com base em seu IP
				System.out.println("Digite o codigo da pessoa que voce deseja excluir:");
				int codigo = t.nextInt();
				dao.excluirPessoa(codigo);
			}
			
			else if(resp == 4) {
				//alterar uma pessoa a partir de todos os seus atributos
				System.out.println("Digite o codigo da pessoa que voce deseja alterar:");
				int codigo = t.nextInt();
				System.out.println("Nome: ");
				String nome = t.next();
				
				
				System.out.println("Sexo: ");
				String strsexo = t.next();
				char sexo = strsexo.charAt(0);
				
				System.out.println("Altura: ");
				int altura = t.nextInt();
				
				System.out.println("Ocupacao: ");
				String ocupacao = t.next();
				
				Pessoa pessoa = new Pessoa(codigo, nome, sexo, altura, ocupacao);
			
				dao.atualizarPessoa(pessoa);
				
			}
			
			System.out.println("\nDigite a opcao desejada [1-Listar, 2-Inserir, 3-Excluir, 4-Atualizar, 5-Sair]:");
			resp = t.nextInt();
		}
		
		
		dao.close();
		t.close();
	}
}