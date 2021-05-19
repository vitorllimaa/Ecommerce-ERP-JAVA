
package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.modelo.Fornecedores;
import br.com.projeto.modelo.tabela_produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Produtosdao {
    private Connection con;
  

    public Produtosdao(){
      this.con = new conexão_banco().getconexão();
  
}
    //cadastro de produto 
  public void Cadastrarproduto(tabela_produtos obj){
      
      
      try {
          
          String sql = "insert tb_produtos(descricao,preco,qtd_estoque,for_id)"
                  + "value(?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome_produto());
          ps.setDouble(2, obj.getPreco());
          ps.setInt(3, obj.getEstoque());
          ps.setInt(4, obj.getFornecedor().getId());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Cadastrado");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      } 
  }
     //Lista dos produtos
  public List<tabela_produtos> listarprodutos(){
      
      try {
          List<tabela_produtos> lista = new ArrayList<>();
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                  + "inner join tb_fornecedores as f on (p.for_id = f.id)";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              Fornecedores f = new Fornecedores();
              tabela_produtos obj = new tabela_produtos();
              
              obj.setId(rs.getInt("p.id"));
              obj.setNome_produto(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setEstoque(rs.getInt("p.qtd_estoque"));
              f.setNome(rs.getString(("f.nome")));
              obj.setFornecedor(f);
              
              lista.add(obj);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
      
      
  
      
  } 
    //alterar cliente
  public void alterarProduto(tabela_produtos obj){
    
  try {
          
          String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? "
                  + "where id=?";
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, obj.getNome_produto());
          ps.setDouble(2, obj.getPreco());
          ps.setInt(3, obj.getEstoque());
          ps.setInt(4, obj.getFornecedor().getId());
          ps.setInt(5, obj.getId());
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Alterado com Sucesso");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  
  
  
  
  }
  
   //excluir clietne
  public void excluircliente(tabela_produtos obj){
       try {
          
          String sql = "delete from tb_produtos where id =?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getId());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Excluído");
          
          
          
      } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null,"erro :" + erro); 
       
      }
  }
  
   //busca de cliente
  public List<tabela_produtos> Buscaproduto(String nome){
      
      try {
          List<tabela_produtos> lista = new ArrayList<>();
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                  + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              
              tabela_produtos obj = new tabela_produtos();
              Fornecedores f = new Fornecedores();
              obj.setId(rs.getInt("p.id"));
              obj.setNome_produto(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setEstoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString(("f.nome")));
              obj.setFornecedor(f);
              lista.add(obj);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
      
      
  
      
  } 
   //busca de produto
  public tabela_produtos Buscapocodigo (int id){
      
      try {
          
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                  + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.id like ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
          tabela_produtos obj = new tabela_produtos();
              Fornecedores f = new Fornecedores();
          while(rs.next()){
              
              
              obj.setId(rs.getInt("p.id"));
              obj.setNome_produto(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setEstoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString(("f.nome")));
              obj.setFornecedor(f);
              
           } 
          return obj;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro: " +erro);
          return null;
      }
  
      
  }
  
   // Baixa no estoque 
  public void baixaestoque(int id, int qtd_novo){
      try {
          String sql = "update tb_produtos set qtd_estoque = ? where id=?";
      PreparedStatement ps = con.prepareStatement(sql);
      
      ps.setInt(1, qtd_novo);
      ps.setInt(2, id);
      ps.execute();
      ps.close();
      
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
      }  
  }
  
  //retorna estoque
  public int retornaestoque(int id){
      try {
          int qtd_estoque = 0;
          
          String sql = "SELECT qtd_estoque from tb_produtos where id=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, id);
          
          ResultSet rs = ps.executeQuery();
          
          if(rs.next()){
              qtd_estoque = (rs.getInt("qtd_estoque"));
              
          }
          return qtd_estoque;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      
  }
  
   // Baixa no estoque 
  public void Adicionarestoque(int id, int qtd_novo){
      try {
          String sql = "update tb_produtos set qtd_estoque = ? where id=?";
      PreparedStatement ps = con.prepareStatement(sql);
      
      ps.setInt(1, qtd_novo);
      ps.setInt(2, id);
      ps.execute();
      ps.close();
      
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
      }  
  }
}
