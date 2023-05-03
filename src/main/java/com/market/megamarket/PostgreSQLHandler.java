package com.market.megamarket;

import java.sql.*;

public class PostgreSQLHandler {
    private final String url;
    private final String user;
    private final String password;

    public PostgreSQLHandler(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getNameById(int id) throws SQLException {
        String name = null;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT name FROM products WHERE shtrih = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        name = rs.getString("name");
                    }
                }
            }
        }
        return name;
    }
    public Double getPriceById(int id) throws SQLException {
        double price = 0.0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT price FROM products WHERE shtrih = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        price = Double.parseDouble(rs.getString("price"));
                    }
                }
            }
        }
        return price;
    }
}
