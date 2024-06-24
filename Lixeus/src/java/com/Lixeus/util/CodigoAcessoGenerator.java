/*
 * Author: Elis Mattosinho
 * Descrição: Classe para gerar códigos de acesso aleatórios utilizando caracteres
 * alfanuméricos (letras maiúsculas de A a Z e dígitos de 0 a 9). O tamanho máximo
 * do código gerado é de 6 caracteres, para autentificação dos Tickets.
 */

package com.Lixeus.util;

import java.util.Random;

public class CodigoAcessoGenerator {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TAMANHO_MAXIMO = 6;
    private final Random random;

    public CodigoAcessoGenerator() {
        random = new Random();
    }

    public String gerarCodigoAcesso() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            int index;
            index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index));
        }
        return sb.toString();
    }
}
