/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fournisseur;
import Entities.Materiel;
import Entities.Role;
import Entities.TypeMateriel;
import Entities.Utilisateur;
import Utiles.MyBdConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class MaterielService {
     Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterMateriel(Materiel c)
  {
           try {
               String req="INSERT INTO `materiel`(`id`, `dateAchat`, `dureeGarantie`, `marque`, `reference`, `typeMateriel`, `fournisseur_id`) VALUES ('"+c.getId()+"','"+c.getDateAchat()+"','"+c.getDureeGarantie()+"','"+c.getMarque()+"','"+c.getReference()+"','"+c.getTypeMateriel()+"','"+c.getFournisseur()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public Materiel rechercherMateriel(String id) throws SQLException
        {
            
            
            try{
            
            
          
            String requete="SELECT `id`, `dateAchat`, `dureeGarantie`, `marque`, `reference`, `typeMateriel`, `fournisseur_id` FROM `materiel` WHERE id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
            
            //pst.setInt(1, id);
            //pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery(requete);
             Materiel u=null;
             while (rs.next()) {
                
                u=new Materiel();
                u.setId(rs.getString(1));
                u.setDateAchat( rs.getDate(2));
                u.setDureeGarantie(rs.getInt(3));
                u.setMarque(rs.getString(4));
                u.setReference(rs.getString(5));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                u.setFournisseur(rs.getString(7));
              
                   
                System.out.println(u);
                return u;
                
                
               
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierMAteriel(Materiel u)
    {   
        try {
            String requet="UPDATE `materiel` SET `id`=?,`dateAchat`=?,`dureeGarantie`=?,`marque`=?,`reference`=?,`typeMateriel`=?, WHERE id=?";
            PreparedStatement pst=connexion.prepareStatement(requet);
            pst.setString(1, u.getId()); //num de var dans la requete 1er champ
            pst.setDate(2, u.getDateAchat());
            pst.setInt(3, u.getDureeGarantie());
            pst.setString(4, u.getMarque());
            pst.setString(5, u.getReference());
            pst.setString(6,String.valueOf(u.getTypeMateriel()));
            pst.setString(8, u.getId());
            
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
        }
    }
        
        
   public boolean supprimerMAteriel(Materiel u) throws SQLException {
   String req = "DELETE FROM `materiel` WHERE id=?";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Materiel> displayAll() throws SQLException, SQLException
         { 
            List<Materiel> maListe=new ArrayList<>();
        try {
            System.out.println("Ajout");
            //Materiel u=null;
            String requete="SELECT * FROM `materiel` ";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {System.out.println("Ajout el del");
                Materiel u=new Materiel();
                u.setId(rs.getString(1));
                System.out.println(u.getId());
                u.setDateAchat(rs.getDate(2));
                System.out.println(u.getDateAchat());
                u.setDureeGarantie(rs.getInt(3));
                 System.out.println(u.getDureeGarantie());
                u.setMarque(rs.getString(4));
                 System.out.println(u.getMarque());
                u.setReference(rs.getString(5));
                 System.out.println(u.getReference());
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                 System.out.println(u.getTypeMateriel());
               u.setFournisseur(rs.getString(7));
                System.out.println(u.getFournisseur());
                System.out.println("ajout");
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
             System.out.println(maListe.size());
          return maListe;
         
}
        
         public List<Materiel> displayAllMaterielParUtilisateur(String id) throws SQLException, SQLException
         { 
            List<Materiel> maListe=new ArrayList<>();
        try {
            System.out.println("ok");
            String requete="SELECT * FROM `materiel` A JOIN utilisateur_materiels B ON A.`id`=B.id_materiel WHERE B.id_utilisateur= '"+id+"'";
           
            Statement st=connexion.createStatement();
            
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {   System.out.println("ok");
                Materiel u=new Materiel();
                u.setId(rs.getString(1));
                System.out.println(u.getId());
                u.setDateAchat( rs.getDate(2));
                System.out.println(u.getDateAchat());
                u.setDureeGarantie(rs.getInt(3));
                System.out.println(u.getDureeGarantie());
                u.setMarque(rs.getString(4));
                System.out.println(u.getMarque());
                u.setReference(rs.getString(5));
                System.out.println(u.getReference());
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                System.out.println(u.getTypeMateriel());
               u.setFournisseur(rs.getString(7));
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return maListe;
         
}
         public List<Materiel> displayAllMaterielParournisseur(String id) throws SQLException, SQLException
         { 
            List<Materiel> maListe=new ArrayList<>();
        try {
            System.out.println("ok");
            String requete="SELECT * FROM `materiel` A JOIN fournisseur B on a.`fournisseur_id`=B.id WHERE b.id= '"+id+"'";
           
            Statement st=connexion.createStatement();
            
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {   System.out.println("ok");
                Materiel u=new Materiel();
                u.setId(rs.getString(1));
                System.out.println(u.getId());
                u.setDateAchat( rs.getDate(2));
                System.out.println(u.getDateAchat());
                u.setDureeGarantie(rs.getInt(3));
                System.out.println(u.getDureeGarantie());
                u.setMarque(rs.getString(4));
                System.out.println(u.getMarque());
                u.setReference(rs.getString(5));
                System.out.println(u.getReference());
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                System.out.println(u.getTypeMateriel());
               u.setFournisseur(rs.getString(7));
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return maListe;
         }
         
          public void AffecterMaterielUtilisateur(Utilisateur u, Materiel m)
          {
                 try {
               String req="INSERT INTO `utilisateur_materiels`(`id_materiel`, `id_utilisateur`) VALUES ('"+m.getId()+"','"+u.getCode()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
              
          }
}
    
    

