/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import salao.jdbc.ConexaoBanco;
import salao.model.Servicos;

/**
 *
 * @author domin
 */
public class ServicosDAO {
   private Connection conn;
   
   public ServicosDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Servicos obj){
       try {
           //1º Criar o SQL
           String sql = "insert into Servico (descricao, preco)"
                   + "values(?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getDescricao());
           stmt.setDouble(2,obj.getPreco());

           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Serviço registado com sucesso!");
       } catch (SQLException erro) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o serviço"+erro);
       }
   }
   
      public void Editar(Servicos obj){
       try {
           //1º Criar o SQL
           String sql = "update Servico set descricao=?, preco=? where ServicoID=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getDescricao());
           stmt.setDouble(2,obj.getPreco());
           stmt.setInt(3,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Serviço editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o serviço"+e);
       }
   }
      
      public void Excluir(Servicos obj){
          try {
              String sql = "delete from Servico where ServicoID=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Servço exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o servico"+e);
          }
      }
   
   public Servicos BuscarProdutos(String nome){
       try {
           String sql = "Select * from Servico where descricao = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Servicos obj = new Servicos();
            if(rs.next()){
                obj.setId(rs.getInt("ServicoID"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));

                
            }   
            return obj;
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o produto"+ erro);
       }
       return null;
    }
   
   public Servicos BuscarServicosCodigo(int id){
       try {
           String sql = "Select * from Servico ServicoID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Servicos obj = new Servicos();
            if(rs.next()){
                obj.setId(rs.getInt("ServicoID"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                
            }   
            return obj;
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o serviço"+ erro);
       }
       return null;
    }
   
   //Método para listar os produtos  do Banco de Dados
   public List<Servicos>Listar(){
       List<Servicos> lista = new ArrayList<>();
       try {
           String sql = "Select * from Servico";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Servicos obj = new Servicos();
               obj.setId(rs.getInt("ServicoID"));
               obj.setDescricao(rs.getString("Descricao"));
               obj.setPreco(rs.getDouble("Preco"));
               
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }
      public List<Servicos>Filtrar(String nome){
       List<Servicos> lista = new ArrayList<>();
       try {
           String sql = "Select * from Servico where descricao like ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Servicos obj = new Servicos();
               obj.setId(rs.getInt("ServicoID"));
               obj.setDescricao(rs.getString("Descricao"));
               obj.setPreco(rs.getDouble("Preco"));

               
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }
      
}

