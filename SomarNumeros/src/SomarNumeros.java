import java.util.*;
class SomarNumeros {
	
	public static Scanner t = new Scanner(System.in);
	public static void main(String[] args) {
		//declaracao de variaveis
		int num1, num2, soma;
		
		//leituras
		System.out.println("Digite um numero: ");
		num1 = t.nextInt();
		System.out.println("Digite outro numero: ");
		num2 = t.nextInt();
		
		//somar
		soma = num1 + num2;
		
		//mostrar na tela
		System.out.println("Soma: " + soma);

	}

}

//observacao: o aluno deu total para si mesmo em sua auto avaliacao