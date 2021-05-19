
package br.com.projeto.dao;

import br.com.projeto.cbd.conexão_banco;
import br.com.projeto.modelo.Cliente;
import br.com.projeto.modelo.Itens_Vendas;
import br.com.projeto.modelo.Vendas;
import br.com.projeto.modelo.tabela_produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;








public class Item_vendadao {
    
    
    private Connection con;
  
  public Item_vendadao(){
      this.con = new conexão_banco().getconexão();
  }
    
    // cadastra item
    public void cadastraItem(Itens_Vendas obj){
        
        try {
          
             
          String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal) value(?,?,?,?)";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, obj.getVenda().getId());
          ps.setInt(2, obj.getPdt().getId());
          ps.setInt(3, obj.getQtd());
          ps.setDouble(4, obj.getSubtotal());
          
          
          ps.execute();
          ps.close();
          JOptionPane.showMessageDialog(null,"item registrado");
          
          
      } catch (Exception e) {
          
          JOptionPane.showMessageDialog(null, e);
      }
     
        
        
    }
    
    //lista de itens de venda
    public List<Itens_Vendas> listaritens(int venda_id){
      
      try {
          List<Itens_Vendas> lista = new ArrayList<>();
          String sql = "select i.id, p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                  + " inner join tb_produtos as p on(i.produto_id = p.id)where i.venda_id = ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, venda_id);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
             
              tabela_produtos pd = new tabela_produtos();
              Itens_Vendas ivd = new Itens_Vendas();
        
              pd.setNome_produto(rs.getString("p.descricao"));
               ivd.setQtd(rs.getInt("i.qtd"));
              pd.setPreco(rs.getDouble("p.preco"));
              ivd.setSubtotal(rs.getDouble("i.subtotal"));
              
              ivd.setPdt(pd);
              lista.add(ivd);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro:2 " +erro);
          return null;
      }
      
      
  
      
  }    
      //lista de itens de venda
    public List<Itens_Vendas> lista(){
      
      try {
          List<Itens_Vendas> lista = new ArrayList<>();
          String sql = "select * from tb_itensvendas";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
             
              
              Itens_Vendas ivd = new Itens_Vendas();
              ivd.setId(rs.getInt("id"));
              ivd.setQtd(rs.getInt("qtd"));
        
              
              
              
              lista.add(ivd);
           } 
          return lista;
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "erro:2 " +erro);
          return null;
      }
      
      
  
      
  }   
 
    
}
