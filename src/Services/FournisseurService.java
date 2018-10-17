/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fournisseur;
import Entities.Materiel;
import Entities.TypeMateriel;
import Utiles.MyBdConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class FournisseurService {
     Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterFourniseur(Fournisseur c)
  {
           try {
               String req="INSERT INTO `fournisseur`(`id`, `adresse`, `email`, `nom`, `telephone`) VALUES ('"+c.getId()+"','"+c.getAdresse()+"','"+c.getEmail()+"','"+c.getNom()+"','"+c.getTelephone()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
        }
    }
  
    public Fournisseur rechercherFournisseur(String id) throws SQLException
        {
            
            
            try{
            
            
          
            String requete="SELECT * FROM `fournisseur` WHERE id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
            
            //pst.setInt(1, id);
            //pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery(requete);
             Fournisseur u=null;
            while (rs.next()) {
                
                u=new Fournisseur();
                u.setId(rs.getString(1));
                u.setAdresse(rs.getString(2));
                u.setNom(rs.getString(3));
                u.setTelephone(rs.getString(4));
                
              
                   
                System.out.println(u);
                return u;
                
                
               
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierFournisseur(Fournisseur u)
    {   
        try {
            String requet="UPDATE `fournisseur` SET `id`=?,`adresse`=?,`email`=?,`nom`=?,`telephone`=? WHERE id=?";
            PreparedStatement pst=connexion.prepareStatement(requet);
            pst.setString(1, u.getId()); //num de var dans la requete 1er champ
            pst.setString(2, u.getAdresse());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getNom());
            pst.setString(5, u.getTelephone());
            pst.setString(6, u.getId());

            
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
        }
    }
        
        
   public boolean supprimerFournisseur(Fournisseur u) throws SQLException {
   String req = "DELETE FROM `fournisseur` WHERE id=?";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Fournisseur> displayAll() throws SQLException, SQLException
         { 
            List<Fournisseur> maListe=new ArrayList<>();
        try {
            Fournisseur fournisseur=null;
            String requete="SELECT * FROM fournisseur``";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                Fournisseur u=new Fournisseur();
                u.setId(rs.getString(1));
                u.setAdresse(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setNom(rs.getString(4));
                u.setTelephone(rs.getString(5));
             
                
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
}
