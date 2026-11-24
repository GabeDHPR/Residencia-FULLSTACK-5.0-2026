package org.example;

public interface limpaPalavra {
    public static String limparPalavra(String palavras){
        return palavras.toLowerCase().replaceAll("[áàâã]", "a")
                .replaceAll("[éèê]", "e")
                .replaceAll("[óòõô]", "o")
                .replaceAll("[íìî]", "i")
                .replaceAll("[úùû]", "u");


    }
}
