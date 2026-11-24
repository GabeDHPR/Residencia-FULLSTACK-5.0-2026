package org.example;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BrazilianPortuguese;
import org.languagetool.rules.RuleMatch;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.limpaPalavra.limparPalavra;

public class Verifica implements limpaPalavra {




    public int gramatica(String msg) throws IOException {
        JLanguageTool lang = new JLanguageTool(new BrazilianPortuguese());

        List<RuleMatch> testatdos = lang.check(msg);

        if(testatdos.isEmpty()) {
            return 0;
        }else {
            System.out.println("==============================================");
            System.out.println("\tErros gramaticais foram encontrados");
            System.out.println("-----------------------------------------------");
            for (RuleMatch testatdo : testatdos) {
                System.out.println("--> " + testatdo.getMessage());
            }
            System.out.println("==============================================");

            return 1;
        }
    }

    public int gatilhoUrgencia(String mensagem) throws FileNotFoundException {

        ArrayList<String> lista = new ArrayList<>();
        Set<String> palavrasDoArquivo = new HashSet<>();

        InputStream input = getClass().getResourceAsStream("/palavrasUrgencia.txt");

        if (input == null) {
            throw new FileNotFoundException("palavrasDados.txt não encontrado em resources.");
        }

        try (Scanner leitorArquivo = new Scanner(input)) {
            leitorArquivo.useDelimiter(",");
            while (leitorArquivo.hasNext()) {

                String termoDig = leitorArquivo.next().trim();
                String palavra = limparPalavra(termoDig);
                if (!palavra.isEmpty()) {
                    palavrasDoArquivo.add(palavra);
                }
            }
        }

        String mensagemLimpa = limparPalavra(mensagem);

        for (String termoSuspeito : palavrasDoArquivo ) {
            if (mensagemLimpa.matches(".*\\b" + termoSuspeito + "\\b.*")) {
                lista.add(termoSuspeito);
            }
        }
        if (!lista.isEmpty()){
            System.out.println("\n==============================================");
            System.out.println("\t\tGatilhos de urgencia detectados");
            System.out.println("-----------------------------------------------");
            for (String s : lista) {
                System.out.println("--> " + s);
            }
            System.out.println("==============================================");
        }
        if(!lista.isEmpty()){
            return 1;
        }
        return 0;
    }


    public int gatilhoDados(String mensagem) throws FileNotFoundException {
        ArrayList<String> lista = new ArrayList<>();

        Set<String> palavrasDoArquivo = new HashSet<>();

        InputStream input = getClass().getResourceAsStream("/palavrasDados.txt");

        if (input == null) {
            throw new FileNotFoundException("palavrasDados.txt não encontrado em resources.");
        }

        try (Scanner leitorArquivo = new Scanner(input)) {
            leitorArquivo.useDelimiter(",");
            while (leitorArquivo.hasNext()) {

                String termoDig = leitorArquivo.next().trim();
                String palavra = limparPalavra(termoDig);
                if (!palavra.isEmpty()) {
                    palavrasDoArquivo.add(palavra);
                }
            }
        }

        String mensagemLimpa = limparPalavra(mensagem);

        for (String termoSuspeito : palavrasDoArquivo ) {
            if (mensagemLimpa.matches(".*\\b" + termoSuspeito + "\\b.*")) {
                lista.add(termoSuspeito);
            }
        }
        if (!lista.isEmpty()){
            System.out.println("\n==============================================");
            System.out.println("\tGatilhos de pedido de dados detectados");
            System.out.println("-----------------------------------------------");
            for (String s : lista) {
                System.out.println("--> " + s);
            }
            System.out.println("==============================================");
        }
        if (!lista.isEmpty()){
            return 1;
        }
        return 0;
    }

    public int gatilhoLink(String mensagem) {
        int alertas = 0;

        String msg = mensagem.toLowerCase();

        boolean temLink = msg.contains("http://") || msg.contains("https://");

        if (temLink) {
            if (msg.contains("http://")) {
                System.out.println("\n==============================================");
                System.out.println("\t\tDetectados problemas com links");
                System.out.println("-----------------------------------------------");
                System.out.println("--> O link usa protocolo http e não o https (conexão não segura).");
                alertas++;
            }

            String[] encurtadores = {
                    "bit.ly", "tinyurl.com", "cutt.ly", "t.co",
                    "encurta.net", "is.gd", "abre.ai", "goo.gl",
                    "ow.ly", "shorte.st", "rb.gy"
            };

            for (String encurtador : encurtadores) {
                if (msg.contains(encurtador)) {
                    System.out.println("--> O link usa um encurtador suspeito (" + encurtador + ")");
                    System.out.println("\n==============================================");
                    alertas++;
                    break;
                }
            }
        }

        if (alertas > 0){
            return 1;
        }
        return 0;
    }
}
