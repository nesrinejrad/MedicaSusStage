/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Demande;
import Entities.Fournisseur;
import Entities.Materiel;
import Entities.StatutTicket;
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
public class DemandeService {
     Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterFourniseur(Demande c)
  {
           try {
               String req="INSERT INTO `demande`(`id`, `dateDemande`, `description`, `status`, `typeMateriel`, `utilisateur_USR_CODE`) VALUES ('"+c.getId()+"','"+c.getDateDemande()+"','"+c.getDescription()+"','"+c.getStatus()+"','"+c.getTypeMateriel()+"','"+c.getUtilisateur_code()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
        }
    }
  
    public Demande rechercherFournisseur(String id) throws SQLException
        {
            
            
            try{
            
            
          
            String requete="SELECT * FROM `demande` WHERE id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);
             Demande u=null;
            while (rs.next()) {
                
                u=new Demande();
                u.setId(rs.getInt(1));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(2)));
                u.setDateDemande(rs.getDate(3));
                u.setDescription(rs.getString(4));
                u.setStatus(StatutTicket.valueOf(rs.getString(5)));
                u.setUtilisateur_code(rs.getString(6));
                        
                   
                System.out.println(u);
                return u;
                
                
               
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierDemande(Demande u)
    {   
        try {
            String requet="UPDATE `demande` SET `id`=?,`dateDemande`=?,`description`=?,`status`=?,`typeMateriel`=?,`utilisateur_USR_CODE`=? WHERE  id=?";
            PreparedStatement pst=connexion.prepareStatement(requet);
            pst.setInt(1, u.getId()); //num de var dans la requete 1er champ
            pst.setDate(2, u.getDateDemande());
            pst.setString(3, u.getDescription());
            pst.setString(4, String.valueOf(u.getStatus()));
            pst.setString(5,String.valueOf( u.getTypeMateriel()));
            pst.setString(6, u.getUtilisateur_code());

            
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
        }
    }
        
        
   public boolean supprimerDemande(Demande u) throws SQLException {
   String req = "DELETE FROM `demande` WHERE id=?";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setInt(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Demande> displayAll() throws SQLException, SQLException
         { 
            List<Demande> maListe=new ArrayList<>();
        try {
            Demande u=null;
            String requete="SELECT * FROM demande``";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                u=new Demande();
                u.setId(rs.getInt(1));
                u.setDateDemande(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setStatus(StatutTicket.valueOf(rs.getString(4)));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(5)));
              u.setUtilisateur_code(rs.getString(6)); //num de var dans la requete 1er champ
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
        
         public List<Demande> displayAllDemandeParUtilisateru(String id) throws SQLException, SQLException
         { 
            List<Demande> maListe=new ArrayList<>();
        try {
            Demande u=null;
            String requete="SELECT * FROM `demande`A JOIN tab_user B on A.`utilisateur_USR_CODE`=B.USR_CODE WHERE A.`utilisateur_USR_CODE`='"+id+"'";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                u=new Demande();
                u.setId(rs.getInt(1));
                u.setDateDemande(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setStatus(StatutTicket.valueOf(rs.getString(4)));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(5)));
              u.setUtilisateur_code(rs.getString(6)); //num de var dans la requete 1er champ
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
}
