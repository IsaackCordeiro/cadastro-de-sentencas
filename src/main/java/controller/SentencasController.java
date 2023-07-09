/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import model.Sentenca;
import view.TelaPrincipal;

/**
 *
 * @author isaac
 */
public class SentencasController {
    private TelaPrincipal tela;
    private ArrayList<Sentenca> sentencas;
    private ArrayList<String> listaSentencas = new ArrayList<>();
    
    public SentencasController(ArrayList<Sentenca> sentencas) {
        this.sentencas = sentencas;
        
        tela = new TelaPrincipal();
        tela.setVisible(true);
        
        // Funcionalidade do botão de salvar
        JButton saveButton = tela.getSaveButton();
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String sentenca = tela.getSentenceText().getText();
                JTextArea text = tela.getSentenceText();
                text.setText("");
                text.requestFocus();
                if(sentenca.length() > 80){
                    sentenca = sentenca.substring(0,       79);
                    Sentenca s = new Sentenca(sentenca);
                    sentencas.add(s); // Insere no array list do tipo Sentenca
                    listaSentencas.add(sentencas.indexOf(s)+1 + " - " + s.getSentenca()); // Insere no arraylist do tipo String para fazer a busca
                    JOptionPane.showMessageDialog(tela, "Sentença adicionada com sucesso", "Confirmação de registro", 1);
                }else if(sentenca.isEmpty()){
                    JOptionPane.showMessageDialog(tela, "Error! Sentença vazia", "Error", 0);
                }else{
                    Sentenca s = new Sentenca(sentenca);
                    sentencas.add(s);
                    listaSentencas.add(sentencas.indexOf(s)+1 + " - " + s.getSentenca());
                    JOptionPane.showMessageDialog(tela, "Sentença adicionada com sucesso", "Confirmação de registro", 1);
                }
            }
        });
        
        // Funcionalidades do botão Listar
        JButton listButton = tela.getListButton();
        listButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JTextArea textArea = tela.getListText();
                textArea.setText("");
                String aux;
                if(sentencas.isEmpty()){
                    JOptionPane.showMessageDialog(tela, "Não há sentenças registradas!", "Não há sentenças para exibir", 2);
                }else{
                    for(int i = 0 ; i < sentencas.size() ; i++){
                        aux = i+1 + " - " + sentencas.get(i).getSentenca();
                        textArea.append(aux + "\n"); // Adiciona as sentenças no TextArea
                    }
                }
            }
        });
        
        // Funcionalidades do botão Buscar
        JButton searchButton = tela.getSearchButton();
        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JTextArea textArea = tela.getListText();
                String wantedText = tela.getSearchText().getText();
                String aux;
                if(wantedText.isEmpty()){
                    JOptionPane.showMessageDialog(tela, "O campo expressão não pode estar vazio!\nFavor informar uma expressão.", "Campo de busca não preenchido", 2);
                }else{
                    ArrayList<String> foundSentencas = searchSentences(wantedText); // Chama a função de buscar Sentenças e armazena as sentenças encontradas em um Array
                    if(foundSentencas == null || foundSentencas.isEmpty()){
                        JOptionPane.showMessageDialog(tela, "Sentenças registradas não contém a expressão: " + wantedText, "Expressão não encontrada", 2);
                    }else{
                        textArea.setText("");
                        for(int i = 0 ; i < foundSentencas.size() ; i++){
                            aux = foundSentencas.get(i);
                            textArea.append(aux + "\n"); // Adicionar as sentenças encontradas no TextArea
                        }
                    }
                }
            }
        });
    }
    
     public ArrayList<String> searchSentences(String wantedText){
        ArrayList<String> foundSentences = new ArrayList<>();
        
        if(listaSentencas.isEmpty()){
            return null;
        }else{
            // Percorre o array de sentenças buscando os registros que contém a string buscada
            for(String s : listaSentencas){
                if(s.contains(wantedText)){
                    foundSentences.add(s);
                }
            }
            return foundSentences;
        }
    }
    
}
