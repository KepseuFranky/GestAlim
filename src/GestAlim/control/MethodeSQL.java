package GestAlim.control;

import GestAlim.model.Fournisseur;
import GestAlim.model.Gestionnaire;
import GestAlim.model.LigneFacture;
import GestAlim.model.ProduitEvolution;
import GestAlim.model.produit;
import GestAlim.util.DateUtil;
import GestAlim.control.MethodeSQL;
import GestAlim.model.Client;
import GestAlim.model.EvolutionClass;
import GestAlim.model.Facture;
import javafx.collections.FXCollections;

import java.sql.*;
import java.time.LocalDate;

public class MethodeSQL {
    /**
     * Connect to the database
     *
     * @return the Connection object
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:boutiq14_mbogni.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //System.out.println("Connect OK");
        } catch (SQLException e) {
            //System.out.println(e.getMessage()+" bbb");
        }
        return conn;
    }

    //Méthode pour les fournisseurs
    
    
    public static Integer getMaxIdFournisseur() {
        String sql="SELECT MAX(idFournisseur) FROM Fournisseur;";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
            	if((Integer)rs.getInt("MAX(idFournisseur)")==0) {
            		return 100;
            	}else {
            		return (Integer)rs.getInt("MAX(idFournisseur)")+1;
            	}
                //return (Integer)rs.getInt("MAX(idFournisseur)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 100;
    }
    
    public static void newFournisseur(Fournisseur fournisseur) {
        String sql = "INSERT INTO Fournisseur (idFournisseur,nom,adresse,tel) VALUES (?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, getMaxIdFournisseur());
            pstmt.setString(2, fournisseur.getName());
            pstmt.setString(3, fournisseur.getAddress());
            pstmt.setString(4, fournisseur.getTel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete a fournisseur specified by the id
     *
     * @param fournisseur
     */
    public static void deleteFournisseur(Fournisseur fournisseur) {
        String sql = "DELETE FROM Fournisseur WHERE idFournisseur = ? ";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, fournisseur.getId());
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update data of a fournisseur specified by the id
     * @param fournisseur
     **/
    public static void updateFournisseur(Fournisseur fournisseur) {

        String update = "UPDATE Fournisseur SET nom = '"+fournisseur.getName()+"' , " +
                "adresse = '"+fournisseur.getAddress()+"' ," +
                "tel= '"+fournisseur.getTel()+"' " +
                "WHERE idFournisseur = "+fournisseur.getId();

        String url = "jdbc:sqlite:boutiq14_mbogni.db";
        try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
             
             stmt.execute(update);
     } 
     catch (SQLException e) 
     {

     
         System.out.println(e.getMessage());
     }
    }

    
    public static void selectAllFournisseur(){
        String sql = "SELECT * FROM Fournisseur ;";
        
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	MainApp.fournisseurData.clear();
            // loop through the result set
            while (rs.next()) {
            	Fournisseur four= new Fournisseur(rs.getInt("idFournisseur"),rs.getString("nom"),rs.getString("adresse"),rs.getString("tel"));
                MainApp.fournisseurData.add(four);
            }
            //System.out.println(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Integer getMaxIdClient() {
        String sql="SELECT MAX(idClient) FROM client;";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
            	if((Integer)rs.getInt("MAX(idClient)")==0) {
            		return 1;
            	}else {
            		return (Integer)rs.getInt("MAX(idClient)");
            	}
                //return (Integer)rs.getInt("MAX(idClient)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1000;
    }
    
    public static void insertDataClient(Client unclient)
    {
        String insert = "insert into client values ("+(getMaxIdClient()+1)+","
        		+unclient.getNomClient()+"',"
        		+unclient.getIdClient()+"',"
        		+unclient.getAdresse()+"','"
        		+unclient.getBonus()+"','"
        		+unclient.getTelClient()+");";
        // Melie ici tu vas toimeme meme completer l'url
        String url = "jdbc:sqlite:boutiq14_mbogni.db";
        try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {
            
            stmt.execute(insert);
    } 
    catch (SQLException e) 
    {
    
        System.out.println(e.getMessage());
    } 

    }
	 public static void deleteDataClient(Client unclient)
    {
        String delete = "delete from client where idClient = '"+unclient.getIdClient()+"'";
        String url = "jdbc:sqlite:boutiq14_mbogni.db";
        try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {
            
            stmt.execute(delete);
    } 
    catch (SQLException e) 
    {
    
        System.out.println(e.getMessage());
    } 


    }
    public static void EditDataClient(Client unclient)
    {

       String update = "update client set nom ='"+unclient.getNomClient()+"',"+
       "idclient = '"+unclient.getIdClient()+"',"+
       "nom = '"+unclient.getNomClient()+"',"+
       "tel = '"+unclient.getTelClient()+"',"+
       "adresse = '"+unclient.getAdresse()+"',"+
       "bonus = '"+unclient.getBonus()+"' where idclient= '"+unclient.getIdClient()+"'";
       
       String url = "jdbc:sqlite:boutiq14_mbogni.db";
       try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {
            
            stmt.execute(update);
    } 
    catch (SQLException e) 
    {

    
        System.out.println(e.getMessage());
    } 

   }
    public static void selectAllClient(){
        String sql = "SELECT * FROM client ;";
        
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	MainApp.ClientData.clear();
            // loop through the result set
            while (rs.next()) {
            	Client four= new Client(rs.getInt("idClient"),rs.getString("nom"),rs.getInt("tel"),rs.getString("adresse"), rs.getInt("bonus"));
                MainApp.ClientData.add(four);
            }
            //System.out.println(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Integer getMaxIdProduit() {
        String sql="SELECT MAX(codePro) FROM produit;";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
            	//System.out.println(rs.getInt("MAX(codePro)"));
            	if((Integer)rs.getInt("MAX(codePro)")==0) {
            		return 100000;
            	}else {
            		return (Integer)rs.getInt("MAX(codePro)");
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 100000;
    }
   public static void insertDataProduit(produit unproduit)
   {
       String insert = "insert into produit values ("+(getMaxIdProduit()+1)+",'"
       		+unproduit.getNomProduit()+"','"
       	    +unproduit.getPrixVente()+"','"
       	    +unproduit.getPrixAchat()+"','"
       		+unproduit.getQuantite()+"','"
       		+unproduit.getDescription()+"','"
       	    +unproduit.getCodeFournisseur()+"','"
       		+DateUtil.format(unproduit.getDateInsertion())+"','"
       		+DateUtil.format(unproduit.getDatePeremption())+"','1','"
       		+unproduit.getIdCategorie()+"');";
       // Melie ici tu vas toimeme meme completer l'url
       String url = "jdbc:sqlite:boutiq14_mbogni.db";
       try (Connection conn = DriverManager.getConnection(url);
       Statement stmt = conn.createStatement()) {
           
           stmt.execute(insert);
   } 
   catch (SQLException e) 
   {
   
       System.out.println(e.getMessage());
   } 

   }
	 public static void deleteDataProduit(produit unproduit)
   {
       String delete = "delete from produit where codePro = '"+unproduit.getCodeProduit()+"'";
       String url = "jdbc:sqlite:boutiq14_mbogni.db";
       try (Connection conn = DriverManager.getConnection(url);
       Statement stmt = conn.createStatement()) {
           
           stmt.execute(delete);
   } 
   catch (SQLException e) 
   {
   
       System.out.println(e.getMessage());
   } 


   }
   public static void EditDataProduit(produit unproduit)
   {

      String update = "update produit set nomPro ='"+unproduit.getNomProduit()+"',"+
      "codePro = '"+unproduit.getCodeProduit()+"',"+
      "nomPro = '"+unproduit.getNomProduit()+"',"+
      "codeFour = '"+unproduit.getCodeFournisseur()+"',"+
      "qte = '"+unproduit.getQuantite()+"',"+
      "prixAchat = '"+unproduit.getPrixAchat()+"',"+
      "prixVente = '"+unproduit.getPrixVente()+"',"+
      "dateInsertion = '"+DateUtil.format(unproduit.getDateInsertion())+"',"+
      "datePeremption = '"+DateUtil.format(unproduit.getDatePeremption())+"',"+
      "description = '"+unproduit.getDescription()+"',"+
      "idCategorie = '"+unproduit.getIdCategorie()+"' where codePro = '"+unproduit.getCodeProduit()+"'";
      
      String url = "jdbc:sqlite:boutiq14_mbogni.db";
      try (Connection conn = DriverManager.getConnection(url);
       Statement stmt = conn.createStatement()) {
           
           stmt.execute(update);
   } 
   catch (SQLException e) 
   {
       System.out.println(e.getMessage());
   } 

  }

  public static void RemplissageListeProduit()
  {
      String sql = "SELECT * FROM produit";
      
      produit unproduit= new produit();
      try (Connection conn = MethodeSQL.connect();
               Statement stmt  = conn.createStatement();
               ResultSet rs    = stmt.executeQuery(sql)){
    	  MainApp.produitData.clear();
              // loop through the result set
              while (rs.next()) {
                   unproduit= new produit();
                   
                   unproduit.setNomProduit(rs.getString(2));
                   unproduit.setCodeProduit(rs.getInt(1));
                   unproduit.setCodeFournisseur(rs.getInt(7));
                   unproduit.setPrixAchat((rs.getInt(4)));
                   unproduit.setPrixVente((rs.getInt(3)));
                   unproduit.setQuantite(rs.getDouble(5));
                   unproduit.setDescription(rs.getString(6));
                   unproduit.setDateInsertion( DateUtil.parse(rs.getString(8)));

                   unproduit.setDatePeremption( DateUtil.parse(rs.getString(9)));
                   unproduit.setIdCategorie(rs.getInt(11));
                   MainApp.produitData.add(unproduit);
              }
              //System.out.println(3);
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
  }
  
  public static void  insertFacture(Facture facture) {
      String sql = "INSERT INTO Facture (idFac, date, remise, montant, modePaiement, idCaissiere, idClient)" +
              " VALUES(?,?,?,?,?,?,?) ;";

      try (Connection conn = connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setInt(1, getMaxIdFacture()+1);
          pstmt.setString(2, DateUtil.format(facture.getDate()));
          pstmt.setDouble(3, facture.getRemise());
          pstmt.setDouble(4, facture.getMontant());
          pstmt.setString(5, facture.getModePaiement());
          pstmt.setInt(6, facture.getIdCaissiere());
          pstmt.setString(7, facture.getNomClient());
          
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  /**
   * Delete a facture specified by the id
   *
   * @param facture
   */
  public static void deleteFacture(Facture facture) {
      String sql = "DELETE FROM Facture WHERE idFac = ? ;";

      try (Connection conn = connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

          // set the corresponding param
          pstmt.setInt(1, facture.getIdFacture());
          // execute the delete statement
          pstmt.executeUpdate();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  /**
   * Update data of a facture specified by the id
   * @param facture
   **/
  public static void updateFacture(Facture facture) {

      String sql = "UPDATE Facture SET date = '?' , " +
              "remise = '?' ," +
              "modePaiement = '?' ," +
              "idCaissiere = '?' ," +
              " nomClient = '?' " +
              "WHERE idFac = '?' ;";

      try (Connection conn = connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

          // set the corresponding param
          pstmt.setString(1, String.valueOf(facture.getDate()));
          pstmt.setDouble(2, facture.getRemise());
          pstmt.setString(3, facture.getModePaiement());
          pstmt.setInt(4, facture.getIdCaissiere());
          pstmt.setString(5, facture.getNomClient());
          pstmt.setInt(6, facture.getIdFacture());
          // update
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  /**
   * select all rows in the facture table
   */
  public static void selectAllFacture(){
      String sql = "SELECT * FROM Facture ;";
      
      try (Connection conn = connect();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)){
    	  MainApp.dataFacture.clear();
          // loop through the result set
          while (rs.next()) {
        	  Facture fac= new Facture();
        	  fac.setIdFacture(rs.getInt("idFac"));
        	  fac.setDate(DateUtil.parse(rs.getString("date")));
        	  fac.setRemise(rs.getDouble("remise"));
        	  fac.setMontant(rs.getDouble("montant"));
        	  fac.setModePaiement(rs.getString("modePaiement"));
        	  fac.setIdCaissiere(rs.getInt("idCaissiere"));
        	  fac.setNomClient(rs.getString("idClient"));
        	  MainApp.dataFacture.add(fac);
                        }
          //System.out.println(4);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  /**
   * Get the fournisseur whose idFacture is specified
   * @param facture
   */
 

  /**
   * Get the max id from the table facture
   */
  public static Integer getMaxIdFacture() {
      String sql="SELECT MAX(idFac) FROM Facture;";

      try (Connection conn = connect();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)) {

          // loop through the result set
          while (rs.next()) {
        	  if((Integer)rs.getInt("MAX(idFac)")==0) {
          		return 1000000;
          	}else {
          		return (Integer)rs.getInt("MAX(idFac)");
          	}
              //return (Integer)rs.getInt("MAX(idFac)");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return 1000000;
  }

  
  public static Integer getMaxIdEvolution() {
      String sql="SELECT MAX(idEvolution) FROM evolution;";

      try (Connection conn = connect();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)) {

          // loop through the result set
          while (rs.next()) {
              return (Integer)rs.getInt("MAX(idEvolution)");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return 10000;
  }
  
  public static void newEvolution(EvolutionClass evolution) {
      String sql = "INSERT INTO Evolution (idEvolution,codePro,nomPro,gestionnaire,quantite,categorie,action,dateModif,motif) VALUES(?,?,?,?,?,?,?,?,?)";

      try (Connection conn = connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
          
          pstmt.setInt(1, getMaxIdEvolution()+1);
          pstmt.setInt(2, evolution.getProduit().getCodeProduit());
          pstmt.setString(3, evolution.getProduit().getNomProduit());
          pstmt.setString(4, evolution.getGestionnaire());
          pstmt.setDouble(5, evolution.getProduit().getQuantite());
          pstmt.setInt(6, evolution.getProduit().getIdCategorie());
          pstmt.setString(7, evolution.getOperation());
          pstmt.setString(8, DateUtil.format(evolution.getProduit().getDateInsertion()) );
          pstmt.setString(9, evolution.getMotif());
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }
  public static void deleteEvolution(EvolutionClass evolution) {
      String sql = "DELETE FROM evolution WHERE idEvolution = ? ";

      try (Connection conn = connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

          // set the corresponding param
          pstmt.setInt(1, evolution.getId());
          // execute the delete statement
          pstmt.executeUpdate();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  public static void selectAllEvolutiuon(){
      String sql = "SELECT * FROM evolution ;";
      
      try (Connection conn = connect();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)){
    	  MainApp.listEvolutionClass.clear();
    	  
          // loop through the result set
          while (rs.next()) {
        	  EvolutionClass evol= new EvolutionClass(rs.getInt("idEvolution"),new ProduitEvolution(rs.getInt("codePro"), rs.getString("nomPro"), rs.getInt("quantite"), DateUtil.parse(rs.getString("dateModif")), rs.getInt("categorie")),rs.getString("action"), rs.getString("gestionnaire"), rs.getString("motif") );
          	 MainApp.listEvolutionClass.add(evol);
          }
          //System.out.println(5);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }
  
  public static Integer getMaxIdGestionnaire() {
      String sql="SELECT MAX(idGest) FROM gestionnaire;";

      try (Connection conn = connect();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)) {

          // loop through the result set
          while (rs.next()) {
        	  if((Integer)rs.getInt("MAX(idGest)")==0) {
            		return 100;
            	}else {
            		return (Integer)rs.getInt("MAX(idGest)");
            	}
              
        	  //return (Integer)rs.getInt("MAX(idGest)");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return 10;
  }
  
  public static void insertDataGestionnaire(Gestionnaire ungestionnaire)
  {
      String insert = "insert into gestionnaire values ("+(getMaxIdGestionnaire()+1)+",'"
      		+ungestionnaire.getNomEmployee()+"','"
      		+ungestionnaire.getTypeEmployee()+"','"
      		+ungestionnaire.getLoginEmployee()+"','"
      		+ungestionnaire.getPasswordEmployee()+"','"
      		+ungestionnaire.getActif()+"');";
      // Melie ici tu vas toimeme meme completer l'url
      String url = "jdbc:sqlite:boutiq14_mbogni.db";
      try (Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement()) {
          
          stmt.execute(insert);
  } 
  catch (SQLException e) 
  {
  
      System.out.println(e.getMessage());
  } 

  }
	 public static void deleteDataGestionnaire(Gestionnaire ungestionnaire)
  {
      String delete = "delete from gestionnaire where idGest = '"+ungestionnaire.getIdEmployee()+"'";
      String url = "jdbc:sqlite:boutiq14_mbogni.db";
      try (Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement()) {
          
          stmt.execute(delete);
  } 
  catch (SQLException e) 
  {
  
      System.out.println(e.getMessage());
  } 


  }
  public static void EditDataGestionnaire(Gestionnaire ungestionnaire)
  {

     String update = "update gestionnaire set idGest ='"+ungestionnaire.getIdEmployee()+"',"+
     "nomGest = '"+ungestionnaire.getNomEmployee()+"',"+
     "typeGest = '"+ungestionnaire.getTypeEmployee()+"',"+
     "login = '"+ungestionnaire.getLoginEmployee()+"',"+
     "pwd = '"+ungestionnaire.getPasswordEmployee()+"',"+
     "actif = '"+ungestionnaire.getActif()+"' where idGest='"+ungestionnaire.getIdEmployee()+"'";
     
     String url = "jdbc:sqlite:boutiq14_mbogni.db";
     try (Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement()) {
          
          stmt.execute(update);
  } 
  catch (SQLException e) 
  {

  
      System.out.println(e.getMessage());
  } 

 }

 public static void RemplissageListeGestionnaire()
 {
     String sql = "SELECT * FROM gestionnaire";
     
     Gestionnaire ungestionnaire= new Gestionnaire();
     try (Connection conn = MethodeSQL.connect();
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
    	 MainApp.employeeData.clear();
    	 MainApp.employeeData.add(new Gestionnaire(0, "BigBoss", 0, "root" , "root", 1));
             // loop through the result set
             while (rs.next()) {
                  ungestionnaire= new Gestionnaire();
                  ungestionnaire.setNomEmployee(rs.getString(2));
                  ungestionnaire.setIdEmployee(rs.getInt(1));
                  ungestionnaire.setTypeEmployee(rs.getInt(3));
                  ungestionnaire.setPasswordEmployee(rs.getString(5));
	     ungestionnaire.setLoginEmployee(rs.getString(4));	
                  ungestionnaire.setActif(rs.getInt(6));
                  MainApp.employeeData.add(ungestionnaire);
             }
             //System.out.println(6);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
 }public static Integer getMaxIdLigneFacture() {
     String sql="SELECT MAX(idLFac) FROM lignefacture;";

     try (Connection conn = connect();
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql)) {

         // loop through the result set
         while (rs.next()) {
        	 if((Integer)rs.getInt("MAX(idLFac)")==0) {
         		return 10000;
         	}else {
         		return (Integer)rs.getInt("MAX(idLFac)");
         	}
             //return (Integer)rs.getInt("MAX(idLFac)");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return 0;
 }
 
 public static void  insertLigneFacture(LigneFacture lignefacture) {
     String sql = "INSERT INTO lignefacture (idLFac, codePro, idFac, prixVente, qte, nom)" +
             " VALUES(?,?,?,?,?,?) ;";

     try (Connection conn = connect();
          PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setInt(1, getMaxIdLigneFacture()+1);
         pstmt.setInt(2, lignefacture.getCodePro());
         pstmt.setInt(3, lignefacture.getIdFacture());
         pstmt.setDouble(4, lignefacture.getPrixUnit());
         pstmt.setDouble(5, lignefacture.getQte());
         pstmt.setString(6, lignefacture.getNomPro());
         pstmt.executeUpdate();
         
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
 }
 public static void selectAllLigneFacture(){
     String sql = "SELECT * FROM lignefacture ;";
     
     try (Connection conn = connect();
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql)){
    	 MainApp.dataLigneFacture.clear();
         // loop through the result set
         while (rs.next()) {
             LigneFacture l= new LigneFacture(rs.getInt("idLFac"), rs.getInt("idFac"),rs.getInt("codePro"), rs.getDouble("prixVente"), rs.getDouble("qte"), rs.getString("nom") );
             MainApp.dataLigneFacture.add(l);
         }
         //System.out.println(7);
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
 }
}
