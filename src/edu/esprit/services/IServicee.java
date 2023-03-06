/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.util.List;

/**
 *
 * @author ramzi
 */
public interface IServicee<T> {
    public void ajout(T t);
//    public void supprimer(T t);//(T t) just ahna bch nfaskhou kn mil id mch mn kol chy 
    public void suppprimer(String nom);
    public void modifier (T t,String s);
    public T getOneById(int id);
    public List<T> getAll();


}   

