/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panne;
import Entities.Role;
import Entities.TypeMateriel;
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
public class PanneServices {
     Connection connexion=MyBdConnexion.getInstance().getConnection();
 
  
      
     public void ajouterPAnne(Panne c)
  {
           try {
               String req="INSERT INTO `panne`(`id`, `description`, `solution`, `typeMateriel`) VALUES ('"+c.getId()+"','"+c.getDescription()+"','"+c.getSolution()+"','"+c.getTypeMateriel()+"')";
             
           
            Statement st=MyBdConnexion.getInstance().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisatur ajouté");
            
        } catch (SQLException ex) {
        }
    }
  
    public Panne rechercherPanne(int id) throws SQLException
        {
            
            
            try{    
            String requete="select * from panne where id='"+id+"'";
            
            PreparedStatement pst = connexion.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
             Panne u=null;
            
            while (rs.next()) {
                
                u=new Panne();
                u.setId(rs.getInt(1));
                u.setDescription(rs.getString(2));
                u.setSolution(rs.getString(3));
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(4)));                   
                System.out.println(u);
                return u;  
            }
              return u;
                     
            } catch (SQLException ex) {
        return null;
            }
           
           
        
        }
  
  
   public void modifierPanne(Panne u)
    {   System.out.println("ok");
        try {
            System.out.println("ok");
            String req="UPDATE `panne` SET `id`=?,`description`=?,`solution`=?,`typeMateriel`=? WHERE id=?";
            PreparedStatement pst=connexion.prepareStatement(req);
            System.out.println(u.getId());
            pst.setInt(1, u.getId());
            pst.setString(2, u.getDescription()); //num de var dans la requete 1er champ
            pst.setString(3, u.getSolution());
            pst.setString(4,String.valueOf(u.getTypeMateriel()));
             pst.setInt(5,u.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur modifié");
            
        } catch (SQLException ex) {
        }
    }
        
        
   public boolean supprimerPanne(Panne u) throws SQLException {
   String req = "DELETE FROM `panne` WHERE id=? ";
       PreparedStatement ps = connexion.prepareStatement(req);
       ps.setInt(1, u.getId());
       ps.executeUpdate();
              return true;
        
   } 
   
        public List<Panne> displayAll() throws SQLException, SQLException
         { 
            List<Panne> maListe=new ArrayList<>();
        try {
            
            String requete="SELECT * FROM `panne`";
            Statement st=connexion.createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next())
            {   System.out.println("ok");
                Panne u=new Panne();
                u.setId(rs.getInt(1));
                System.out.println(u.getId());
                u.setDescription(rs.getString(2));
                System.out.println(u.getDescription());
                u.setSolution(rs.getString(3));
                System.out.println(u.getSolution());
                u.setTypeMateriel(TypeMateriel.valueOf(rs.getString(4)));
                System.out.println(u.getTypeMateriel());

                maListe.add(u);
            }
          
            
        } catch (SQLException ex) {
        }
          return maListe;
         
}
    
    
}
