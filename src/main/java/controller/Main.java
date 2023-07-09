/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Sentenca;

/**
 *
 * @author Isaack Anthony Cordeiro Arcanjo
 * Matrícula: 2020204017
 */
public class Main {
    public static void main(String[] args){
        ArrayList<Sentenca> bancoDeDados = new ArrayList<>();
        SentencasController s = new SentencasController(bancoDeDados);
    }
}
