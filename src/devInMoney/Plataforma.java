package devInMoney;

import java.util.Scanner;

import devInMoney.banco.Banco;
import devInMoney.banco.Menus;

public class Plataforma {

	Banco meuBanco = new Banco();
	Scanner scanner = new Scanner(System.in);
	Menus menu = new Menus();

	public static void main(String[] args) {

		Banco meuBanco = new Banco();
		Menus menu = new Menus();

		menu.menuInicial(meuBanco);

	}
}
