/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.util.List;

/**
 *
 * @author lenovo
 * @param <U>
 */
public interface Iservice<U> {
    public void ajouter(U u); 
    public void supprimer(int id) ; 
    public void modifier(U u) ;
    public U getOneById(int id) ; 
    public List<U> getall() ; 
    
}
