package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entidades.ContratoHora;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.enums.NivelFuncionario;

public class GestaoFuncionarios {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Informe o nome do departamento: ");
		String nomeDepart = sc.nextLine();
		System.out.println("Informe os dados do Funcionário:");
		System.out.print("Nome: ");
		String nomeFunc = sc.nextLine();
		System.out.print("Nível: ");
		String nivelFunc = sc.nextLine();
		System.out.print("Salário base: ");
		double salarioBaseFunc = sc.nextDouble();
		
		Funcionario funcionario = new Funcionario(nomeFunc, NivelFuncionario.valueOf(nivelFunc), salarioBaseFunc, new Departamento(nomeDepart));
				
		System.out.print("Quantos contratos este funcionário possui? ");
		int qtdeContratos = sc.nextInt();
		
		for (int i=1; i<=qtdeContratos; i++) {
			System.out.printf("Informe os dados do contrato #%d:%n", i);
			System.out.print("Data (DD/MM/AAAA): ");
			Date dataContrato = sdf.parse(sc.next());
			System.out.print("Valor por hora: ");
			double valorHora = sc.nextDouble();
			System.out.print("Duração (horas): ");
			int horas = sc.nextInt();
			//Instanciar o contrato
			ContratoHora contrato = new ContratoHora(dataContrato, valorHora, horas);
			funcionario.adicionarContrato(contrato);
		}
		
		System.out.println();
		System.out.print("Informe o mês e ano para o calculo dos proventos (MM/AAAA): ");
		String mesAno = sc.next();
		int mes = Integer.parseInt(mesAno.substring(0, 2));
		int ano = Integer.parseInt(mesAno.substring(3));
		
		System.out.println("Nome: " + funcionario.getNome());
		System.out.println("Departamento: " + funcionario.getDepartamento().getNome());
		System.out.println("Proventos para " + mesAno + ": " + String.format("%.2f", funcionario.proventos(ano, mes)));
		
		sc.close();
	}

}
