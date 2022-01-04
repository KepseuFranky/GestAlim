package GestAlim.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class testConnexionBD {
    /**
    * Connect to a sample database
    */
   public static Connection connect() {
       Connection conn = null;
       try {
           // db parameters
           String url = "jdbc:sqlite:boutiq14_mbogni.db";
           // create a connection to the database
           conn = DriverManager.getConnection(url);
           
           System.out.println("Connection to SQLite has been established.");
           return conn;
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       } 
       return conn;
   }
   

 
      
   public static void createTable() {
       // SQLite connection string
	   String url ="jdbc:sqlite:boutiq14_mbogni.db";
       
       // SQL statement for creating a new table
       String sql = "CREATE TABLE IF NOT EXISTS  categorie (\r\n" + 
       		"  idCat INT(10)   NOT NULL   PRIMARY KEY,\r\n" + 
       		"  nomCat VARCHAR(60) NOT NULL)\r\n" + 
       		"";
       String sql2 = "CREATE TABLE IF NOT EXISTS Fournisseur (\r\n" + 
       		"  idFournisseur INT(4) NOT NULL DEFAULT 0000 PRIMARY KEY,\r\n" + 
       		"  nom VARCHAR(45) NOT NULL DEFAULT 'ND',\r\n" + 
       		"  tel VARCHAR(13) NULL,\r\n" + 
       		"  adresse VARCHAR(45) NULL)\r\n" + 
       		"";
       String sql3 = "CREATE TABLE IF NOT EXISTS  gestionnaire (\r\n" + 
       		"  idGest INT(10)   NOT NULL   PRIMARY KEY, \r\n" + 
       		"  nomGest VARCHAR(45) NOT NULL,\r\n" + 
       		"  typeGest TINYINT(1) NOT NULL,\r\n" + 
       		"  login VARCHAR(20) NOT NULL UNIQUE,\r\n" + 
       		"  pwd VARCHAR(20) NOT NULL,\r\n" + 
       		"  actif TINYINT(1) NOT NULL DEFAULT '0')\r\n" + 
       		"";
       String sql4 = "CREATE TABLE IF NOT EXISTS  facture (\r\n" + 
       		"  idFac INT(10)   NOT NULL   PRIMARY KEY,\r\n" + 
       		"  codePaiement VARCHAR(60),\r\n" + 
       		"  date VARCHAR(12) NOT NULL,\r\n" + 
       		"  remise DECIMAL(4,2) NOT NULL,\r\n" + 
       		"  montant DECIMAL(10,2) NOT NULL,\r\n" + 
       		"  tel VARCHAR(15) NULL DEFAULT NULL,\r\n" + 
       		"  modePaiement TINYINT(1) NOT NULL DEFAULT '0',\r\n" + 
       		"  idCaissiere INT(10)   NOT NULL,\r\n" + 
       		"  idClient INT(4) NOT NULL)\r\n" + 
       		"";
       String sql5 = "CREATE TABLE IF NOT EXISTS  produit (\r\n" + 
       		"  codePro INT(6)   NOT NULL PRIMARY KEY,\r\n" + 
       		"  nomPro VARCHAR(100) NOT NULL DEFAULT 'ND',\r\n" + 
       		"  prixVente DECIMAL(8,2) NULL,\r\n" + 
       		"  prixAchat DECIMAL(8,2) NOT NULL,\r\n" + 
       		"  qte DECIMAL(10,2)   NOT NULL,\r\n" + 
       		"  description VARCHAR(1000) NOT NULL DEFAULT 'RAS',\r\n" + 
       		"  codeFour VARCHAR(12) NOT NULL,\r\n" + 
       		"  dateInsertion VARCHAR(12) NULL DEFAULT CURRENT_TIMESTAMP,\r\n" + 
       		"  datePeremption VARCHAR(12) NOT NULL,\r\n" + 
       		"  actif TINYINT(1) NOT NULL DEFAULT '1',\r\n" + 
       		"  idCategorie INT(10)   NOT NULL)\r\n" + 
       		"";
       String sql6 = "CREATE TABLE IF NOT EXISTS  gestionstock (\r\n" + 
       		"  idStock INT(10)   NOT NULL   PRIMARY KEY,\r\n" + 
       		"  qte INT(10)   NOT NULL,\r\n" + 
       		"  dateStock VARCHAR(12) NOT NULL,\r\n" + 
       		"  operation TINYINT(1) NOT NULL DEFAULT '0',\r\n" + 
       		"  idGest INT(10)   NOT NULL,\r\n" + 
       		"  codePro INT(8)   NOT NULL)\r\n" + 
       		"";
       String sql7 = "CREATE TABLE IF NOT EXISTS  lignefacture (\r\n" + 
       		"  idLFac INT(10)   NOT NULL   PRIMARY KEY,\r\n" + 
       		"  codePro INT(6)   NOT NULL,\r\n" +
       		"  nom VARCHAR(45) NOT NULL,\r\n"+
       		"  idFac INT(10)   NOT NULL,\r\n" + 
       		"  prixVente DECIMAL(10,2) NOT NULL,\r\n" + 
       		"  qte SMALLINT(4)   NOT NULL)\r\n" + 
       		"";
       String sql8 = "CREATE TABLE IF NOT EXISTS  client (\r\n" + 
       		"  idClient INT(10)   NOT NULL   PRIMARY KEY, \r\n" + 
       		"  nom VARCHAR(45) NOT NULL,\r\n" + 
       		"  tel INT(13) NOT NULL,\r\n" + 
       		"  adresse VARCHAR(45) NOT NULL UNIQUE,\r\n" + 
       		"  bonus INT(10) NOT NULL,\r\n" + 
       		"  actif TINYINT(1) NOT NULL DEFAULT '1')\r\n" + 
       		"";
             	       
       String sql9="CREATE TABLE IF NOT EXISTS evolution(\r\n" + 
       		"idEvolution INT(10) NOT NULL PRIMARY KEY,\r\n" + 
       		"codePro INT(10) NOT NULL,\r\n" + 
       		"nomPro VARCHAR(100) NOT NULL,\r\n" + 
       		"gestionnaire VARCHAR(100) NOT NULL,\r\n" + 
       		"quantite DECIMAL(5,2) NOT NULL,\r\n" + 
       		"categorie INT(5) NOT NULL,\r\n" + 
       		"action VARCHAR(20) NOT NULL,\r\n" + 
       		"dateModif VARCHAR(20) NOT NULL,\r\n" + 
       		"Motif VARCHAR(200) NOT NULL)";
       
       try (Connection conn = DriverManager.getConnection(url);
               Statement stmt = conn.createStatement()) {
           // create a new table
           stmt.execute(sql);
           System.out.println("OK");
           stmt.execute(sql2);
           System.out.println("OK");
           stmt.execute(sql3);
           System.out.println("OK");
           stmt.execute(sql4);
           System.out.println("OK");
           stmt.execute(sql5);
           System.out.println("OK");
           stmt.execute(sql6);
           System.out.println("OK");
           stmt.execute(sql7);
           System.out.println("OK");
           stmt.execute(sql8);
           System.out.println("OK");
           stmt.execute(sql9);
           System.out.println("OK");
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }


/**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
	   testConnexionBD t = new testConnexionBD();
	   connect();
       createTable();
       
	   
       
   }
}
