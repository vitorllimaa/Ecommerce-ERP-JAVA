/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.cbd;

import javax.swing.JOptionPane;


public class conexao {
    
    public static void main(String[] args) {
        try {
            conexão_banco c = new conexão_banco();
            c.getconexão();
            JOptionPane.showMessageDialog(null, "conectado");
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
