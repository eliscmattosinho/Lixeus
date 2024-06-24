/*
* Author: Matheus Maqueda
 Esse arquivo faz a conexão com o banco de dados
*/

package com.Lixeus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/Lixeus?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection estabeleceConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException erro) {
            System.out.println("Erro na conexão: " + erro);
            return null;
        }
    }
}
