package com.autoqa.qa2auto;

import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
      try {
          extracted();
      } finally {
          ConnectionManager.closePool();
      }
    }

    private static void extracted() throws SQLException {
        try (var connection = ConnectionManager.get()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }

}
