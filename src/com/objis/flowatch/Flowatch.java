package com.objis.flowatch;
import java.sql.*;

public class Flowatch {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/flowatch";
        String user = "root";
        String password = "AbbeillenoirK0022*"; // remplace par ton mot de passe MySQL

        try {
            // Connexion
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");

            // Requête pour afficher conducteurs et leurs véhicules
            String sql = """
                SELECT c.id AS conducteur_id, u.nom, u.email, v.marque, v.modele, v.localisation
                FROM conducteurs c
                JOIN utilisateurs u ON c.utilisateur_id = u.id
                JOIN vehicules v ON c.vehicule_id = v.id
                """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Conducteurs et leurs véhicules :");
            while (rs.next()) {
                int conducteurId = rs.getInt("conducteur_id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                String localisation = rs.getString("localisation");

                System.out.println("Conducteur #" + conducteurId + " - " + nom + " (" + email + ")");
                System.out.println("Véhicule : " + marque + " " + modele + " - Localisation : " + localisation);
                System.out.println("--------------------------------------");
            }

            // Fermeture
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Connexion fermée.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
