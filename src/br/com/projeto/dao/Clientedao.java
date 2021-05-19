package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.modelo.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;






public class Clientedao {
    
  private Connection con;
  
  public Clientedao(){
      this.con = new conexão_banco().getconexão();
  }
    
   //cadastro de cliente 
  public void cadastrarcliente(Cliente obj){
      
      
      try {
          
          String sql = "insert tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                  + "value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getRg());
          ps.setString(3, obj.getCpf());
          ps.setString(4, obj.getEmail());
          ps.setString(5, obj.getTelefone());
          ps.setString(6, obj.getCelular());
          ps.setString(7, obj.getCep());
          ps.setString(8, obj.getEndereço());
          ps.setInt(9, obj.getNumero());
          ps.setString(10, obj.getComplemento());
          ps.setString(11, obj.getBairro());
          ps.setString(12, obj.getCidade());
          ps.setString(13, obj.getEstado());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Cadastrado");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
      
      
      
  }  
 
  //alterar cliente  
  public void alterarcliente(Cliente obj){
    
  try {
          
          String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,"
                  + "celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getRg());
          ps.setString(3, obj.getCpf());
          ps.setString(4, obj.getEmail());
          ps.setString(5, obj.getTelefone());
          ps.setString(6, obj.getCelular());
          ps.setString(7, obj.getCep());
          ps.setString(8, obj.getEndereço());
          ps.setInt(9, obj.getNumero());
          ps.setString(10, obj.getComplemento());
          ps.setString(11, obj.getBairro());
          ps.setString(12, obj.getCidade());
          ps.setString(13, obj.getEstado());
          ps.setInt(14, obj.getId());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Alterado com Sucesso");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  
  
  
  
  }
    
   //excluir clietne
  public void excluircliente(Cliente obj){
       try {
          
          String sql = "delete from tb_clientes where id =?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getId());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Excluído");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  }
    
   //Lista dos clientes
  public List<Cliente> listarCliente(){
      
      try {
          List<Cliente> lista = new ArrayList<>();
          String sql = "select * from tb_clientes";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Cliente obj = new Cliente();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
          JOptionPane.showMessageDialog(null, "erro11111111111: " +erro);
          return null;
      }
      
      
  
      
  } 
    
    //busca de cliente
  public List<Cliente> Buscacliente(String nome){
      
      try {
          List<Cliente> lista = new ArrayList<>();
          String sql = "select * from tb_clientes where nome like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Cliente obj = new Cliente();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
   public Cliente buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Cliente obj = new Cliente();

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
    
    //busca de cliente
  public Cliente Buscaporcpf(String cpf){
      
      try {
        
          String sql = "select * from tb_clientes where cpf like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, cpf);
          ResultSet rs = ps.executeQuery();
          
              Cliente obj = new Cliente();
          while(rs.next()){
              
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
      
      
  
      
  }
  
 
}


