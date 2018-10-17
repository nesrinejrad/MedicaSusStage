/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.EtatTicket;
import Entities.Fournisseur;
import Entities.Intervention;
import Entities.Materiel;
import Utiles.MyBdConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class InterventionServices {
    Connection connexion=MyBdConnexion.getInstance().getConnection();
      public void ajouterIntervention(Intervention c)
  {
           try {System.out.println("ok");
               String req="INSERT INTO `intervention`(`id`, `dateIntervention`, `description`, `etatIntervention`, `periode`, `materiel_id`, `ticket_id`) Values ('"+c.getId()+"','"+c.getDateIntervention()+"','"+c.getDescription()+"','"+c.getEtatIntervention()+"','"+c.getPeriode()+"','"+c.getMateriel()+"','"+c.getTicket()+"')";
               System.out.println("ok");
               System.out.println(c.toString());
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
               System.out.println("ok");
            st.executeUpdate(req);
               System.out.println("ok");
            System.out.println("Utilisatur ajouté");
               System.out.println("ok");
            
        } catch (SQLException ex) {
            Logger.getLogger(InterventionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public Intervention rechercherFournisseur(String id) throws SQLException
        {
            
            
            try{
            
            
          
            String requete="SELECT * FROM `intervention` WHERE id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
            
            //pst.setInt(1, id);
            //pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery(requete);
             Intervention u=null;
            while (rs.next()) {
                
                u=new Intervention();
                u.setId(rs.getString(1));
                
                u.setDateIntervention(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setEtatIntervention(EtatTicket.valueOf(rs.getString(4)));
                u.setMateriel(rs.getString(4));
                u.setTicket(rs.getInt(5));
              
                   
                System.out.println(u);
                return u;
                
                
               
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierIntervention(Intervention u)
    {   
        try {
            String requet="UPDATE `intervention` SET `id`=?,`dateIntervention`=?,`description`=?,`etatIntervention`=?,`periode`=?,`materiel_id`=?,`ticket_id`=? WHERE  id=?";
            PreparedStatement pst=connexion.prepareStatement(requet);
            pst.setString(1, u.getId()); //num de var dans la requete 1er champ
            pst.setDate(2,u.getDateIntervention());
            pst.setString(3, u.getDescription());
            pst.setString(4, String.valueOf(u.getEtatIntervention()));
            pst.setInt(5,u.getPeriode());
            pst.setString(6, u.getMateriel());
            pst.setInt(7, u.getTicket());
            pst.setString(8, u.getId());
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(InterventionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
   public boolean supprimerFournisseur(Intervention u) throws SQLException {
   String req = "DELETE FROM `intervention` WHERE id=?";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Intervention> displayAll() throws SQLException, SQLException
         { 
            List<Intervention> maListe=new ArrayList<>();
        try {
            Fournisseur fournisseur=null;
            String requete="SELECT * FROM intervention``";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                Intervention u=new Intervention();
              u.setId(rs.getString(1));
                u.setDateIntervention(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setEtatIntervention(EtatTicket.valueOf(rs.getString(4)));
                         u.setPeriode(rs.getInt(5));
                u.setMateriel(rs.getString(6));
                u.setTicket(rs.getInt(7));
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
        public List<Intervention> AfficherInterventionParMateriel(Materiel m)
        { List<Intervention> maListe=new ArrayList<>();
        try {
           // Intervention i=null;
            String requete="SELECT * FROM `intervention` A JOIN materiel B on A.`materiel_id`=B.id WHERE A.`materiel_id`='"+m.getId()+"'";
            Statement st=connexion.createStatement(); 
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                Intervention u=new Intervention();
              u.setId(rs.getString(1));
                u.setDateIntervention(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setEtatIntervention(EtatTicket.valueOf(rs.getString(4)));
                         u.setPeriode(rs.getInt(5));
                u.setMateriel(rs.getString(6));
                u.setTicket(rs.getInt(7));
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
             Logger.getLogger(InterventionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return maListe;
         }
    
}
