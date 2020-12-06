package Application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Digite o caminho do arquivo: ");
		String caminhoDigitado = sc.nextLine();

		File caminho = new File(caminhoDigitado);
		String pasta = caminho.getParent();
		
		boolean success = new File (pasta + "\\out").mkdir();
		System.out.println("Pasta criada " + success);
		
		String alvo = pasta + "\\out\\summary.csv";
		String alvo2 = pasta + "\\out\\arquivoMonstro.csv";
		System.out.println("ArquivoTopCriado" + alvo2);
		
		try (BufferedWriter b1 = new BufferedWriter (new FileWriter(alvo2))){
			b1.write("Te amo amorzinho");
		}
		catch (IOException e) {
			System.out.println("Ocorreu um erro " + e.getMessage());
		}
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoDigitado))){
		
			String item = br.readLine();
			while (item != null) {
				String[] campos = item.split(",");
				String name = campos[0];
				Double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);
				
				list.add(new Product(name, preco, quantidade));
				
				
				item = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(alvo))){
				for (Product peca : list) {
					bw.write(peca.getName() + peca.total());
					bw.newLine();
					
				}
				
				System.out.println(alvo + "Criado");
				
			}
			catch (IOException e) {
				System.out.println("Erro ao escrever arquivo " + e.getMessage());
				
			}
			
		}
		catch (IOException e) {
			System.out.println("Erro ao ler o arquivo " + e.getMessage());
		}

		sc.close();

	}

}
