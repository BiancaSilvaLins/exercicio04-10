/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniceub.biancalins.hibernatejpa.controller;

import com.uniceub.biancalins.hibernatejpa.dao.PersistenceManager;

/**
 *
 * @author bianca.lins
 */
public class Main {
    
    public static void main(String[] args) {
        PersistenceManager.getInstance().initializeContext();
        PersistenceManager.getInstance().destroyContext();
    }
    
}
