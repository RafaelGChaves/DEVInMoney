package devInMoney.banco;



public class Utilitarios {


	public boolean cpfValido(String cpf) {

		if (cpf.length() != 11) {
			System.out.println("Quantidade de digitos inválida");
			return false;

		}

//		int ultimoNumero = 0, penultimoNumero = 0;
//		int i = 0, j = 10;
//		int soma;
//
//		// 105922526 39
//		soma = 0;
//		while (i < 9) {
//			
//			soma += (Integer.parseInt(cpf.charAt(i) + "") * j);
//			i++;
//			j--;
//
//			penultimoNumero = (soma * 10) / 11;
//			if (penultimoNumero > 9) {
//				penultimoNumero = 0;
//			}
//		}
//		soma = 0;
//		while (i < 9) {
//			j = 11;
//			
//			soma += (Integer.parseInt(cpf.charAt(i) + "") * j);
//
//			ultimoNumero = (soma * 10) / 11;
//
//		}
//		
//		System.out.println(penultimoNumero);
//		if (!cpf.equals(cpf.substring(0, 9) + penultimoNumero + ultimoNumero)) {
//			String teste = cpf.substring(0, 9) + Integer.toString(penultimoNumero) + Integer.toString(ultimoNumero);
//			System.out.println(teste + 	"cpf inválido");
//			return false;
//		}
		return true;
	}
}
