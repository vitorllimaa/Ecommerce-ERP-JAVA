
package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.modelo.Funcionarios;
import br.com.projeto.modelo.WebServiceCep;
import br.com.projeto.view.FRMTeleprincipal;
import br.com.projeto.view.FRM_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Funcionariodao {
    
     private Connection con;
  
  public Funcionariodao(){
      this.con = new conexão_banco().getconexão();
  }
    
     //cadastro de Funcionario
  public void cadastrarfuncionario(Funcionarios obj){
      
      
      try {
          
          String sql = "insert tb_funcionarios(nome,rg,cpf,email,senha, cargo, nivel_acesso, telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                  + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getRg());
          ps.setString(3, obj.getCpf());
          ps.setString(4, obj.getEmail());
          ps.setString(5, obj.getSenha());
          ps.setString(6, obj.getCargo());
          ps.setString(7, obj.getNivel_acesso());
          ps.setString(8, obj.getTelefone());
          ps.setString(9, obj.getCelular());
          ps.setString(10, obj.getCep());
          ps.setString(11, obj.getEndereço());
          ps.setInt(12, obj.getNumero());
          ps.setString(13, obj.getComplemento());
          ps.setString(14, obj.getBairro());
          ps.setString(15, obj.getCidade());
          ps.setString(16, obj.getEstado());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Cadastrado");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro1 :" + erro); 
       
      }
      
      
      
  }  
    
      //Lista dos Funcionario
  public List<Funcionarios> listarfuncionario(){
      
      try {
          List<Funcionarios> lista = new ArrayList<>();
          String sql = "select * from tb_funcionarios";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Funcionarios obj = new Funcionarios();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
              obj.setEmail(rs.getString("email"));
              obj.setSenha(rs.getString("senha"));
              obj.setCargo(rs.getString("cargo"));
              obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
          JOptionPane.showMessageDialog(null, "erro1: " +erro);
          return null;
      }
      
      
  
      
  }    
 
  //excluir clietne
  public void excluirFuncionario(Funcionarios obj){
       try {
          
          String sql = "delete from tb_funcionarios where id = ?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getId());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Excluído");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  } 
  
   //alterar cliente  
  public void alterarFuncionario(Funcionarios obj){
    
  try {
          
          String sql = "update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?, cargo=?, nivel_acesso=?, telefone=?,"
                  + "celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome());
          ps.setString(2, obj.getRg());
          ps.setString(3, obj.getCpf());
          ps.setString(4, obj.getEmail());
          ps.setString(5, obj.getSenha());
          ps.setString(6, obj.getCargo());
          ps.setString(7, obj.getNivel_acesso());
          ps.setString(8, obj.getTelefone());
          ps.setString(9, obj.getCelular());
          ps.setString(10, obj.getCep());
          ps.setString(11, obj.getEndereço());
          ps.setInt(12, obj.getNumero());
          ps.setString(13, obj.getComplemento());
          ps.setString(14, obj.getBairro());
          ps.setString(15, obj.getCidade());
          ps.setString(16, obj.getEstado());
          ps.setInt(17, obj.getId());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Alterado com Sucesso");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  
  
  
  
  }

 //busca de cliente
  public List<Funcionarios> Buscafuncionario(String nome){
      
      try {
          List<Funcionarios> lista = new ArrayList<>();
          String sql = "select * from tb_funcionarios where nome like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              Funcionarios obj = new Funcionarios();
              
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
              obj.setEmail(rs.getString("email"));
              obj.setSenha(rs.getString("senha"));
              obj.setCargo(rs.getString("cargo"));
              obj.setNivel_acesso(rs.getString("nivel_acesso"));
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

//Fazer login
  public void logar(String email, String senha){
      
      
      try {
          String sql = "select * from tb_funcionarios where email = ? and senha = ? ";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, email);
          ps.setString(2, senha);
          ResultSet rs = ps.executeQuery();
          
          if(rs.next()){
              if(rs.getString("nivel_acesso").equals("Administrador")){
                  
              JOptionPane.showMessageDialog(null, "Seja Bem vindo");
              FRMTeleprincipal tela = new FRMTeleprincipal();
              tela.Usuario_logado = rs.getString("nome");
              tela.setVisible(true);
              }
              //usuario limitado
              else if(rs.getString("nivel_acesso").equals("Usuário")){
              JOptionPane.showMessageDialog(null, "Seja Bem vindo");
              FRMTeleprincipal tela = new FRMTeleprincipal();
              tela.Usuario_logado = rs.getString("nome");
              tela.bt_historico.setEnabled(false);
              tela.bt_produtos_consulta.setEnabled(false);
              tela.btcontroledia.setVisible(false);
              tela.setVisible(true);
          }
            
          }else{
           JOptionPane.showMessageDialog(null, "Email ou Senha Invalida!");
           
           new FRM_Login().setVisible(true);
          }
         
          
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "erro: " +e);
          
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
  
  
  
}

