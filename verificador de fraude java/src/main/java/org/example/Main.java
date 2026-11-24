package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Verifica verifica = new Verifica();
        Pontuador pon = new Pontuador();

        Scanner sc = new Scanner(System.in);
        String msg;
        String op;

        System.out.println("============================");
        System.out.println("\t\tPEGA FRAUDE");
        System.out.println("============================");

        do {
            System.out.print("Cole a mensagem dúvidosa aqui para verificar possivel fraude: ");
            msg = sc.nextLine();

            pon.calcular(msg);

            System.out.print("Tecle enter para testar nova mensagem ou digite 'sair' para encerrar: ");
            op = sc.nextLine();


        }while(!op.toUpperCase().equals("SAIR"));



    }
}