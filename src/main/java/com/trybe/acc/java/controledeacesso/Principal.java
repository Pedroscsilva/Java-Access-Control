package com.trybe.acc.java.controledeacesso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe principal.
 * 
 * @author pedro
 *
 */
public class Principal {

  /**
   * Método principal.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Map<String, Integer> ages = new HashMap<String, Integer>() {
      private static final long serialVersionUID = 1L;
      {
        put("menor", 0);
        put("adulto", 0);
        put("idoso", 0);
        put("all", 0);
      }
    };
    short userInput;

    do {
      System.out.print("Entre com o número correspondente à opção desejada:\n"
          + "1 - Acessar o estabelecimento\n" + "2 - Finalizar sistema e mostar relatório");
      userInput = scanner.nextShort();
      switch (userInput) {
        case 1:
          System.out.println("Entre com a sua idade:");
          short userAge = scanner.nextShort();
          String ageRange = giveUserFeedback(userAge);
          ages.merge(ageRange, 1, Integer::sum);
          ages.merge("all", 1, Integer::sum);
          break;
        case 2:
          calculateAges(ages);
          break;
        default:
          System.out.println("Entre com uma opção válida!");
          userInput = 1;
          break;
      }
    } while (userInput == 1);
    scanner.close();
  }

  /**
   * Dá ao usuário um feedback sobre a liberação da catraca e retorna o tipo de usuário.
   * 
   */
  public static String giveUserFeedback(short userAge) {
    String printFeedback;
    String feedback;
    if (userAge < 18) {
      printFeedback = "cliente menor de idade";
      feedback = "menor";
    } else if (userAge < 50) {
      printFeedback = "cliente adulta";
      feedback = "adulto";
    } else {
      printFeedback = "adulta a partir de 50";
      feedback = "idoso";
    }
    System.out.println("Pessoa " + printFeedback + ", catraca liberada!");
    return feedback;
  }

  /**
   * Calcula a média de idades dos usuários.
   * 
   */
  public static void calculateAges(Map<String, Integer> ages) {
    System.out.println("----- Quantidade -----");
    System.out.println("menores: " + ages.get("menor"));
    System.out.println("adultas: " + ages.get("adulto"));
    System.out.println("a partir de 50: " + ages.get("idoso"));
    System.out.print("\n----- Percentual -----\n");
    DecimalFormat decimalFormat = new DecimalFormat("0.0#'%'");
    int all = ages.get("all");
    System.out.println("menores: " + decimalFormat.format((double) ages.get("menor") * 100 / all));
    System.out.println("adultas: " + decimalFormat.format((double) ages.get("adulto") * 100 / all));
    System.out
        .println("a partir de 50: " + decimalFormat.format((double) ages.get("idoso") * 100 / all));
    System.out.print("\nTOTAL: " + ages.get("all"));
  }
}
