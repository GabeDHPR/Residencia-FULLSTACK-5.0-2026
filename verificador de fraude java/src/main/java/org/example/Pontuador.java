package org.example;
import utills.MensagemCuidado;

import java.io.IOException;

public class Pontuador {

    public void calcular(String msg) throws IOException {
        Verifica verifica = new Verifica();
        int pontos = verifica.gramatica(msg);
        pontos += verifica.gatilhoUrgencia(msg);
        pontos += verifica.gatilhoDados(msg);
        pontos += verifica.gatilhoLink(msg);

        diagnostico(pontos);
    }

    private void diagnostico(int ponto) {
        if (ponto <= 1) {
            System.out.println("\n\n=================================");
            System.out.println("| MENSAGEM POTENCIALMENTE SEGURA! |");
            System.out.println("=================================");
        } else if (ponto <= 3) {
            MensagemCuidado.printMsgCuidado();
            System.out.println("------------------------------------------------------------------------");
            System.out.println("| Essa mensagem possui caracteristica que à caracterizam como suspeita |");
            System.out.println("------------------------------------------------------------------------");
        } else if (ponto <= 5) {
            MensagemCuidado.printMsgCuidado();
            System.out.println("------------------------------------------------");
            System.out.println("| Essa mensagem possui alto risco de ser golpe |");
            System.out.println("------------------------------------------------");
        } else if (ponto >= 6) {
            MensagemCuidado.printMsgCuidado();
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("| ai fodeu |");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}
