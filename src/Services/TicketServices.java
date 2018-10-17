/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.EtatTicket;
import Entities.Fournisseur;
import Entities.StatutTicket;
import Entities.Ticket;
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
public class TicketServices {
     Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterTicket(Ticket c)
  {
           try {
               String req="INSERT INTO `ticket`(`id`, `dateCreation`, `description`, `etatTicket`, `statutTicket`, `typeMateriel`, `materiel_id`, `panne_id`, `utilisateur_USR_CODE`) VALUES ('"+c.getId()+"','"+c.getDateCreation()+"','"+c.getDescription()+"','"+c.getEtatTicket()+"','"+c.getStatutTicket()+"','"+c.getTypeMateriel()+"','"+c.getMateriel()+"','"+c.getPanne()+"','"+c.getUtilisateur()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
             Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public Ticket rechercherTicket(String id) throws SQLException
        {  
            try{
            String requete="SELECT * FROM `ticket` WHERE id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
          
            ResultSet rs = pst.executeQuery(requete);
             Ticket u=null;
            while (rs.next()) {
                
                u=new Ticket();
                u.setId(rs.getInt(1));
                u.setDateCreation(Date.valueOf(rs.getString(2)));
                u.setDescription(rs.getString(3));
                u.setEtatTicket(EtatTicket.valueOf(rs.getString(4)));
                u.setStatutTicket(StatutTicket.valueOf(rs.getString(4)));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(4)));
                u.setMateriel(rs.getString(4));
                u.setPanne(rs.getInt(4));
                u.setUtilisateur(rs.getString(4));
                
                System.out.println(u);
                return u;
                  
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierTicket(Ticket u)
    {   
        try {
            String requet="UPDATE `ticket` SET `id`=?,`dateCreation`=?,`description`=?,`etatTicket`=?,`statutTicket`=?,`typeMateriel`=?,`materiel_id`=?,`panne_id`=?,`utilisateur_USR_CODE`=? WHERE id=?";
            PreparedStatement pst=connexion.prepareStatement(requet);
                pst.setInt(1,u.getId());
                pst.setDate(2, u.getDateCreation());
                pst.setString(3, u.getDescription());
                pst.setString(4, String.valueOf(u.getEtatTicket()));
                pst.setString(5, String.valueOf(u.getStatutTicket()));
                pst.setString(6, String.valueOf(u.getTypeMateriel()));
                pst.setString(7,u.getMateriel());
                pst.setInt(8,u.getPanne());
                pst.setString(9,u.getUtilisateur());
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
        }
    }
        
        
   public boolean supprimerTichet (Ticket u) throws SQLException {
   String req = "DELETE FROM `ticket` WHERE id=?";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setInt(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Ticket> displayAll() throws SQLException, SQLException
         { 
            List<Ticket> maListe=new ArrayList<>();
        try {
            Ticket u=null;
            String requete="SELECT * FROM ticket``";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                 u=new Ticket();
                u.setId(rs.getInt(1));
                u.setDateCreation(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setEtatTicket(EtatTicket.valueOf(rs.getString(4)));
                u.setStatutTicket(StatutTicket.valueOf(rs.getString(5)));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                u.setMateriel(rs.getString(7));
                u.setPanne(rs.getInt(8));
                u.setUtilisateur(rs.getString(9));

                maListe.add(u);
            }
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
        
    
        public List<Ticket> displayTicketParUtilisateur(String id) throws SQLException, SQLException
         { 
            List<Ticket> maListe=new ArrayList<>();
        try {
            Ticket u=null;
            String requete="SELECT * FROM `ticket` A JOIN tab_user B ON A.`utilisateur_USR_CODE`=B.USR_CODE WHERE A.`utilisateur_USR_CODE`='"+id+"'";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                 u=new Ticket();
                u.setId(rs.getInt(1));
                u.setDateCreation(rs.getDate(2));
                u.setDescription(rs.getString(3));
                u.setEtatTicket(EtatTicket.valueOf(rs.getString(4)));
                u.setStatutTicket(StatutTicket.valueOf(rs.getString(5)));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(6)));
                u.setMateriel(rs.getString(7));
                u.setPanne(rs.getInt(8));
                u.setUtilisateur(rs.getString(9));

                maListe.add(u);
            }
        } catch (SQLException ex) {
        }
          return maListe;
         }
    
        
}
