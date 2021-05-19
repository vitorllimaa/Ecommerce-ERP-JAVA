
package br.com.projeto.modelo;

public class Fornecedores extends Cliente{
    
    
    private String cpnj;

    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }
    
     @Override
    public String toString(){
        return  this.getNome();
    }
    
}
