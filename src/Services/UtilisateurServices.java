/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Materiel;
import Entities.Role;
import Entities.User;
import Entities.Utilisateur;
import Utiles.MyBdConnexion;
import java.sql.Connection;
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
public class UtilisateurServices {
    
    Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterUtilisateur(Utilisateur c)
  {
           try {
               String req="INSERT INTO `tab_user`(`USR_CODE`, `USR_EMAIL`, `USR_PWD`, fonction , `nom`, `prenom`, `role`) VALUES ('"+c.getCode()+"','"+c.getEmail()+"','"+c.getPassword()+"','"+null+"','"+c.getNom()+"','"+c.getPrenom()+"','"+c.getRole()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public Utilisateur rechercherUtilisateur(String id) throws SQLException
        {
            
            
            try{
            
            
          
            String requete="select * from tab_user where USR_CODE='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
            
            //pst.setInt(1, id);
            //pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery(requete);
             Utilisateur u=null;
            
            while (rs.next()) {
                
                u=new Utilisateur();
                u.setCode(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFonction(rs.getString(4));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setRole(Role.valueOf(rs.getString(7)));
              
                   
             //   System.out.println(u);
                return u;
                
                
               
            }
              return u;
                     
            } catch (SQLException ex) {
         //   Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        return null;
            }
           
           
        
        }
  
  
   public void modifierUtilisateur(Utilisateur u)
    {   
        try {
            String req="UPDATE tab_user SET USR_CODE=?,USR_EMAIL=?,USR_PWD=?,nom=?,prenom=?,role=? WHERE USR_CODE=?";
            PreparedStatement pst=connexion.prepareStatement(req);
            pst.setString(1, u.getCode());
            pst.setString(2, u.getEmail()); //num de var dans la requete 1er champ
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getNom());
            pst.setString(5,u.getPrenom());
            pst.setString(6,String.valueOf(u.getRole()));
            pst.setString(7, u.getCode());
          
            
            
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
   public void supprimerUtilisateur(Utilisateur u) throws SQLException {
   String req = "DELETE FROM `tab_user` WHERE USR_CODE=? ";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, u.getCode());
       ps.executeUpdate();
           
   } 
   
        public List<Utilisateur> displayAll() throws SQLException, SQLException
         { 
            List<Utilisateur> maListe=new ArrayList<>();
        try {
            
            String requete="SELECT * FROM `tab_user`";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {
                Utilisateur u=new Utilisateur();
                u.setCode(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFonction(rs.getString(4));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setRole(Role.valueOf( rs.getString(7)));
                ;
                
                //System.out.println(p);
                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return maListe;
         
}
        public Utilisateur login(String login, String password) {
		
		try {
                     Utilisateur u=null;
                    String requete="SELECT * FROM `tab_user` WHERE `USR_EMAIL`= '"+login+"'  AND  `USR_PWD`='"+password+"'";
                    PreparedStatement pst = connexion.prepareStatement(requete);
               //     pst.setString(1, "thatss");
               //     pst.setString(2, "pass");
            
                     ResultSet rs = pst.executeQuery(requete);
                    
            
            while (rs.next()) {
                
                System.out.println("good");
                u= new Utilisateur();
                u.setCode(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFonction(rs.getString(4));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setRole(Role.valueOf( rs.getString(7)));
                
               return u;
            }
            return u;
		} 
                catch (Exception e) {
                    return null;
		}
		
	}
        
       
    public List<Utilisateur> UtilisateurMateriel(String id) throws SQLException
    {
        List<Utilisateur> maListe=new ArrayList<>();
           try {
        String requete=" SELECT * FROM `tab_user`A JOIN utilisateur_materiels B on A.`USR_CODE`=B.id_utilisateur WHERE B.id_materiel='"+id+"'";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
                Utilisateur u= null;
                while (rs.next()) {
                
                System.out.println("good");
                u= new Utilisateur();
                u.setCode(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFonction(rs.getString(4));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setRole(Role.valueOf( rs.getString(7)));
                
                  maListe.add(u);
            }
    }
		 
                catch (Exception e) {
                  
		}
            return maListe;
            
    }
}

