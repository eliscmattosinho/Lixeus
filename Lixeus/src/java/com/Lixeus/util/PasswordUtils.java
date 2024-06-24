/*
 * Author: Elis Mattosinho
 * Descrição: Esta classe oferece métodos utilitários para hash de senhas utilizando
 * o algoritmo SHA-256 (Conhecido na aula de Segurança da Informação) e para verificação de senhas hashadas.
 */

package com.Lixeus.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao hashear a senha", e);
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        // Verifica se a senha hashada fornecida corresponde à senha original
        return hashedPassword.equals(hashPassword(password));
    }
}
