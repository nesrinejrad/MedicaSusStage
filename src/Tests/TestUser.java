/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Role;
import Entities.User;
import Entities.Utilisateur;
import Services.UtilisateurServices;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class TestUser {
    public static void main(String[] args) throws SQLException {
       UtilisateurServices us= new UtilisateurServices();
        Utilisateur u = new Utilisateur();
   

    u.setCode("code1");
    u.setEmail("login");
    u.setPassword("pass");
    u.setRole(Role.Administrateur);
    u.setNom("nom");
    u.setPrenom("prenom");
       /* List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
        utilisateurs=us.displayAll();
        System.out.println(utilisateurs.size());
        Date d= Date.valueOf("2018-03-2");
        System.out.println(d);
        System.out.println(d.getYear());*/
       
        
    
    }}
