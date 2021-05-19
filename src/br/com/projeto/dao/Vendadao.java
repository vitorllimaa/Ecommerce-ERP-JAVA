
package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.modelo.Fornecedores;
import br.com.projeto.modelo.Vendas;
import br.com.projeto.modelo.tabela_produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Vendadao {
    
    private Connection con;
  
  public Vendadao(){
      this.con = new conexão_banco().getconexão();
  }
    //cadastrar venda 
  public void cadastrarvenda(Vendas obj){
      
      
      try {
          
             
          String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes) value(?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getCliente_id().getId());
          ps.setString(2, obj.getData_venda());
          ps.setDouble(3, obj.getTotal_venda());
          ps.setString(4, obj.getObs());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"Venda Cadastrado");
          
          
      } catch (Exception e) {
          
          JOptionPane.showMessageDialog(null, e);
      }
      
  }
   
  //retorna ultima venda
  public int retornaultimavenda (){
          try {
              int idvenda = 0;
              
              String sql = "select max(id) id from tb_vendas";
              
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
              if(rs.next()){
                  Vendas p = new Vendas();
                  
                  p.setId(rs.getInt("id"));
                  
                  idvenda = p.getId();
              }
              
              return idvenda;
              
              
              
          } catch (SQLException e) {
              
              throw new RuntimeException();
          }
          }
  
  //Lista venda data
  public List<Vendas> listarvendas(LocalDate data_inicio, LocalDate data_final){
      
      try {
          List<Vendas> lista = new ArrayList<>();
          String sql = "select v.id, date_format(v.data_venda,'%d/%m/%y')as data_formatada, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                  + " inner join tb_clientes as c on(v.cliente_id = c.id)where v.data_venda BETWEEN ? AND ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, data_inicio.toString());
          ps.setString(2, data_final.toString());
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
             
              Vendas obj = new Vendas();
              Cliente c = new Cliente();
              
              obj.setId(rs.getInt("v.id"));
              obj.setData_venda(rs.getString("data_formatada"));
              c.setNome(rs.getString("c.nome"));
              obj.setTotal_venda(rs.getDouble("v.total_venda"));
              obj.setObs(rs.getString("v.observacoes"));
              
              obj.setCliente_id(c);
              lista.add(obj);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro:2 " +erro);
          return null;
      }
      
      
  
      
  }    
  
  //calucular total venda
  public double retornatotalvendapordata(LocalDate data_venda){
      try {
          
          double totalvenda = 0;
          
          String sql = "select sum(total_venda)as total from tb_vendas where data_venda = ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, data_venda.toString());
          
          ResultSet rs = ps.executeQuery();
          
          if(rs.next()){
              totalvenda = rs.getDouble("total");
          }
          return totalvenda;
      } catch (Exception e) {
          throw new RuntimeException();
      }
     
  }
  
}    
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

