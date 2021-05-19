package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Fornecedores;
import br.com.projeto.modelo.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;






public class Fornecedoredao {
    
  private Connection con;
  
  public Fornecedoredao(){
      this.con = new conexão_banco().getconexão();
  }
    
   //cadastro de Fornecedores 
  public void cadastrarFornecedores(Fornecedores obj){
      
      
      try {
          
          String sql = "insert tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                  + "value(?,?,?,?,?,?,?,?,?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getCpnj());
          ps.setString(3, obj.getEmail());
          ps.setString(4, obj.getTelefone());
          ps.setString(5, obj.getCelular());
          ps.setString(6, obj.getCep());
          ps.setString(7, obj.getEndereço());
          ps.setInt(8, obj.getNumero());
          ps.setString(9, obj.getComplemento());
          ps.setString(10, obj.getBairro());
          ps.setString(11, obj.getCidade());
          ps.setString(12, obj.getEstado());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Cadastrado");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
      
      
      
  }  
 
  //alterar Fornecedores  
  public void alterarFornecedores(Fornecedores obj){
    
  try {
          
          String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,"
                  + "celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getCpnj());
          ps.setString(3, obj.getEmail());
          ps.setString(4, obj.getTelefone());
          ps.setString(5, obj.getCelular());
          ps.setString(6, obj.getCep());
          ps.setString(7, obj.getEndereço());
          ps.setInt(8, obj.getNumero());
          ps.setString(9, obj.getComplemento());
          ps.setString(10, obj.getBairro());
          ps.setString(11, obj.getCidade());
          ps.setString(12, obj.getEstado());
          ps.setInt(13, obj.getId());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Alterado com Sucesso");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  
  
  
  
  }
    
   //excluir Fornecedores
  public void excluirFornecedores(Fornecedores obj){
       try {
          
          String sql = "delete from tb_fornecedores where id =?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getId());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Excluído");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  }
    
   //Lista dos Fornecedores
  public List<Fornecedores> listarFornecedores(){
      
      try {
          List<Fornecedores> lista = new ArrayList<>();
          String sql = "select * from tb_fornecedores";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Fornecedores obj = new Fornecedores();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCpnj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereço(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
              
              lista.add(obj);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
      
      
  
      
  } 
    
    //busca de Fornecedores
  public List<Fornecedores> BuscaFornecedores(String nome){
      
      try {
          List<Fornecedores> lista = new ArrayList<>();
          String sql = "select * from tb_fornecedores where nome like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Fornecedores obj = new Fornecedores();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCpnj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereço(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
              
              lista.add(obj);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
      
      
  
      
  } 
  
  //buscacep
   public Fornecedores buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereço(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
   
   public Fornecedores consultanome(String nome){
       try {
           
       
  String sql = "select*from tb_fornecedores where nome =?";
       PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome);
       ResultSet rs = ps.executeQuery();
          Fornecedores obj = new Fornecedores();
          
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCpnj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereço(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
              
              
          }
              
   return obj;
          } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro: " +e);
          return null;
       }  
       
       
   }
    
}
