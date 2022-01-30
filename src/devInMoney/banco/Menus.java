package devInMoney.banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import devInMoney.conta.Conta;
import devInMoney.conta.TiposDeConta;

public class Menus {

	public static String cabecalho = "\n-- D e v I n B a n k --";
	public int opcao;
	public String usuarioAtivo;

	Scanner scanner = new Scanner(System.in);
	Utilitarios utilitarios = new Utilitarios();

	public void menuInicial(Banco banco) {
		String leitor;
		Conta usuarioAtivo = null;
		System.out.println(cabecalho);
		System.out.println("");
		System.out.println("O que você deseja fazer?");
		System.out.println("1 -> ENTRAR");
		System.out.println("2 - CRIAR CONTA");
		System.out.println("0 - SAIR");
		opcao = scanner.nextInt();
		switch (opcao) {
		case 1:
			while (usuarioAtivo == null) {
				System.out.println("Digite o número de sua conta");
				leitor = scanner.next();
				for (Conta c : banco.listaDeContas) {
					if (c.getNumeroConta().equals(leitor)) {
						usuarioAtivo = c;
						System.out.println("Bem vindo " + usuarioAtivo.getNome());
						while (opcao != 7) {
							menuGeral(usuarioAtivo, banco);
						}
					}

				}
				System.out.println("\n!!! Conta inexistente !!!\n");
			}

			break;

		case 2:
			int opcaoAgencia, opcaoConta, opcao;
			System.out.println("Olá, seja bem vindo! Preencha os campos abaixo: ");
			System.out.println("Nome");
			String nome = scanner.next();
			System.out.println("CPF");
			String cpf = scanner.next();
			if (!utilitarios.cpfValido(cpf)) {
				break;
			}
			System.out.println("Informe sua renda Mensal");
			double rendaMensal = scanner.nextDouble();
			System.out.println("Escolha a agência mais perto de você");
			System.out.println("1 ->" + Agencia.FLORIANOPOLIS.getAgencia() + "\n2 ->" + Agencia.SAO_JOSE.getAgencia());
			opcaoAgencia = scanner.nextInt();
			Agencia agencia = null;
			if (opcaoAgencia == 1) {
				agencia = Agencia.FLORIANOPOLIS;
			} else if (opcaoAgencia == 2) {
				agencia = Agencia.SAO_JOSE;
			} else {
				System.out.println("Agencia inválida");
				break;
			}
			System.out.println("Selecione um tipo de Conta: ");
			System.out.println("\n1 ->Conta Corrente : \nAqui você tem direito a cheque especial com valor referente a sua renda");
			System.out.println("\n2 -> Conta Poupança: \nSeu dinheiro rende 0.5% ao mês e pode retirar a qualquer hora");
			System.out.println("\n3 ->Conta Investimento: \nConheça nossos planos: \n");
			opcaoConta = scanner.nextInt();
			Conta novaConta = null;
			if (opcaoConta == 1) {
				TiposDeConta tipo = TiposDeConta.CONTA_CORRENTE;
				novaConta = banco.criaConta(nome, cpf, rendaMensal, 0.00, agencia, tipo);
			} else if (opcaoConta == 2) {
				TiposDeConta tipo = TiposDeConta.CONTA_POUPANCA;
				novaConta = banco.criaConta(nome, cpf, rendaMensal, 0.00, agencia, tipo);
			} else if (opcaoConta == 3) {
				TiposDeConta tipo = TiposDeConta.CONTA_INVESTIMENTO;
				opcao = scanner.nextInt();
				System.out.println("\n1 ->" + PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA + "\n2 ->"
						+ PlanosDeInvestimento.INVESTIDOR_PREMIUM);
				if (opcao == 1) {
					PlanosDeInvestimento plano = PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA;
					novaConta = banco.criaConta(nome, cpf, rendaMensal, 0.00, agencia, tipo, plano);
				} else if (opcao == 2) {
					PlanosDeInvestimento plano = PlanosDeInvestimento.INVESTIDOR_PREMIUM;
					novaConta = banco.criaConta(nome, cpf, rendaMensal, 0.00, agencia, tipo, plano);

				} else {
					System.out.println("Opção inválida");
					break;
				}
			} else {
				System.out.println("Opção inválida");
				break;
			}
			System.out.println("Bem vindo " + novaConta.getNome() + " ---- Número da conta: "
					+ novaConta.getNumeroConta() + "\nGuarde esse número com carinho");
			menuGeral(novaConta, banco);
			break;

		default:
			break;
		}

	}

	public int menuGeral(Conta usuario, Banco banco) {
		System.out.println("\nEscolha uma das opções abaixo:");
		System.out.println("\n1 -> Consultar saldo" + "\n2 -> Saque" + "\n3 -> Depósito" + "\n4 -> Transferência"
				+ "\n5 -> Extrato" + "\n6 -> Outras opções" + "\n7 -> Voltar");
		opcao = scanner.nextInt();
		Transacao transacao;
		switch (opcao) {
		case 1:
			System.out.println("Saldo: " + usuario.getSaldo());
			break;
		case 2:
			System.out.println("\nDigite o valor do saque: ");
			double valorSaque = scanner.nextDouble();
			transacao = usuario.saque(valorSaque);
			if (transacao != null) {
				banco.transacoes.add(transacao);
			}
			break;
		case 3:
			System.out.println("\nDigite o valor do depósito: ");
			double valorDeposito = scanner.nextDouble();
			transacao = usuario.deposito(valorDeposito);
			if (transacao != null) {
				banco.transacoes.add(transacao);
			}
			break;
		case 4:
			if (LocalDate.now().getDayOfWeek() != LocalDate.now().getDayOfWeek().SUNDAY
					|| LocalDate.now().getDayOfWeek() != LocalDate.now().getDayOfWeek().SATURDAY) {
				System.out.println("\nNão é possível realizar essa operação aos finais de semana.");
				menuGeral(usuario, banco);
			}
			System.out.println("\nDigite número da conta destino: ");
			String numeroContaDestino = scanner.next();
			Conta contaDestino = null;
			transacao = null;

			for (Conta cDestino : banco.listaDeContas) {
				if (cDestino.getNumeroConta().equals(numeroContaDestino)) {
					contaDestino = cDestino;
					System.out.println("Digite o valor do depósito: ");
					double valorTransferido = scanner.nextDouble();
					transacao = usuario.transferencia(contaDestino, valorTransferido);
				}

			}
			if (contaDestino == null) {
				System.out.println("**Conta inexistente**");
			}
			if (transacao != null) {
				banco.transacoes.add(transacao);
			}

			break;
		case 5:
			ArrayList<Transacao> transacoesUsuarioAtivo = new ArrayList<Transacao>();
			for (Transacao transacaoUsuario : banco.transacoes) {
				if (transacaoUsuario.contaOrigem != null
						&& transacaoUsuario.contaOrigem.getNumeroConta().equals(usuario.getNumeroConta())) {
					transacoesUsuarioAtivo.add(transacaoUsuario);
				}
				if (transacaoUsuario.contaDestino != null
						&& transacaoUsuario.contaDestino.getNumeroConta().equals(usuario.getNumeroConta())) {
					transacoesUsuarioAtivo.add(transacaoUsuario);
				}
			}
			usuario.extrato(transacoesUsuarioAtivo);
			break;
		case 6:
			if (usuario.getNumeroConta().substring(7).equals("1")) {
				menuCorrentista(usuario, banco);
			} else if (usuario.getNumeroConta().substring(7).equals("2")) {
				menuPoupancudo(usuario, banco);
			} else {
				menuInvestidor(usuario, banco);
			}
		case 7:
			menuInicial(banco);
			break;
		case 8:
			System.out.println("\n----TRANSACÕES USUÁRIOS----");
			banco.mostraTransacoes(usuario);
			System.out.println("\n----CONTAS ATIVAS----");
			banco.mostraContas(usuario);
			System.out.println("\n----CONTAS NEGATIVAS----");
			banco.mostraContasNegativas(usuario);
			System.out.println("\n---VALOR INVESTIDO----");
			banco.mostraValorTotal(usuario);
			System.out.println("\n------------ \n");
			break;

		default:

			break;
		}
		return opcao;
	}

	public void menuCorrentista(Conta usuario, Banco banco) {
		System.out.println("1 -> Consultar cheque especial" + "\n2 -> Voltar");
		opcao = scanner.nextInt();
		switch (opcao) {
		case 1:
			System.out.println("Saldo do cheque especial: R$ " + usuario.getSaldoChequeEspecial());
			break;

		case 2:
			menuGeral(usuario, banco);
			break;

		default:
			System.out.println("**Opção inválida**");
			break;
		}

	}

	public void menuPoupancudo(Conta usuario, Banco banco) {
		System.out.println("1 -> Simular rendimento" + "\n2 -> Voltar");
		opcao = scanner.nextInt();
		switch (opcao) {
		case 1:
			System.out
					.println("Para calcular o valor do rendimento \nDigite a rentabilidade anual e o número de meses");
			int rendimento = scanner.nextInt();
			int meses = scanner.nextInt();
			usuario.simulaRendimento(rendimento, meses);
			System.out.println("-- Operação finalizada --");
			break;

		case 2:
			menuInicial(banco);
			break;

		default:
			System.out.println("**Opção inválida**");
			break;
		}

	}

	public void menuInvestidor(Conta usuario, Banco banco) {
		System.out.println("\n1 -> Simular investimento " + "\n2 -> Alterar tipo de investimento" + "\n3 -> Voltar");
		opcao = scanner.nextInt();
		switch (opcao) {
		case 1:
			System.out.println("");
			double rendimento = scanner.nextDouble();
			int meses = scanner.nextInt();
			usuario.simulaRendimento(rendimento, 12);
			break;

		case 2:
			System.out.println("Escolha entre os tipos de investimento:");
			switch (opcao) {
			case 1:
				System.out.println("1 ->" + PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA.getNomeInvestimento() + "\n"
						+ PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA.getDescricaoInvestimento());
				break;
			case 2:
				System.out.println("2 ->" + PlanosDeInvestimento.INVESTIDOR_PREMIUM.getNomeInvestimento() + "\n"
						+ PlanosDeInvestimento.INVESTIDOR_PREMIUM.getDescricaoInvestimento());
				break;
		case 3:
				menuGeral(usuario, banco);
				break;

			default:
				System.out.println("**Opção inválida**");
				break;
			}

			break;

		default:
			System.out.println("**Opção inválida**");
			break;
		}

	}

}
