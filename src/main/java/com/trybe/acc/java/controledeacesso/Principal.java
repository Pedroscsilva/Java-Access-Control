package com.trybe.acc.java.controledeacesso;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Classe principal.
 * @author pedro
 *
 */
public class Principal {
  
  /**
   * Método principal.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Hashtable<String, Integer> ages = new Hashtable<String, Integer>();
    ages.put("menor", 0);
    ages.put("adulto", 0);
    ages.put("idoso", 0);
    ages.put("all", 0);
    short userInput;
    
    do {
      System.out.print("Entre com o número correspondente à opção desejada:\n"
          + "1 - Acessar o estabelecimento\n"
          + "2 - Finalizar sistema e mostar relatório");
      userInput = scanner.nextShort();
      if (userInput == 1) {
        System.out.println("Entre com a sua idade:");
        short userAge = scanner.nextShort();
        String ageRange = giveUserFeedback(userAge);
        ages.merge(ageRange, 1, Integer::sum);
        ages.merge("all", 1, Integer::sum);
      } else if (userInput == 2) {
        calculateAges(ages);
      } else {
        System.out.println("Entre com uma opção válida!");
        userInput = 1;
      }
    } while (userInput  == 1);
    scanner.close();
  }
  
  /**
   * Dá ao usuário um feedback sobre a liberação da catraca e retorna o tipo de usuário.
   * 
   */
  public static String giveUserFeedback(short userAge) {
    if (userAge < 18) {
      System.out.println("Pessoa cliente menor de idade, catraca liberada!");
      return "menor";
    } else if (userAge < 50) {
      System.out.println("Pessoa cliente adulta, catraca liberada!");
      return "adulto";
    } else {
      System.out.println("Pessoa adulta a partir de 50, catraca liberada!");
      return "idoso";
    }
  }
  
  /**
   * Calcula a média de idades do usuário.
   * 
   */
  public static void calculateAges(Hashtable<String, Integer> ages) {
    System.out.println("----- Quantidade -----");
    System.out.println("menores: " + ages.get("menor"));
    System.out.println("adultas: " + ages.get("adulto"));
    System.out.println("a partir de 50: " + ages.get("idoso"));
    System.out.print("\n----- Percentual -----\n");
    System.out.println("menores: " + new DecimalFormat("0.0#'%'")
        .format((double)ages.get("menor") * 100 / ages.get("all")));
    System.out.println("adultas: " + new DecimalFormat("0.0#'%'")
        .format((double)ages.get("adulto") * 100 / ages.get("all")));
    System.out.println("a partir de 50: " + new DecimalFormat("0.0#'%'")
        .format((double)ages.get("idoso") * 100 / ages.get("all")));
    System.out.print("\nTOTAL: " + ages.get("all"));
  }
}
