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
import salao.model.Clientes;

/**
 *
 * @author domin
 */
public class ClientesDAO {
    //Método construtor
    private Connection conn;
   
   public ClientesDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Clientes obj){
       try {
           //1º Criar o SQL
           String sql = "insert into clientes (nome, morada, telefone, email)"
                   + "values(?,?,?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getMorada());
           stmt.setString(3,obj.getTelefone());
           stmt.setString(4,obj.getEmail());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o cliente"+e);
       }
   }
   
      public void Editar(Clientes obj){
       try {
           //1º Criar o SQL
           String sql = "update clientes set nome=?, morada=?, telefone=?, email=? where clienteID=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getMorada());
           stmt.setString(3,obj.getTelefone());
           stmt.setString(4,obj.getEmail());
           stmt.setInt(5,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o cliente"+e);
       }
   }
      
      public void Excluir(Clientes obj){
          try {
              String sql = "delete from clientes where clienteID=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Cliente exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o cliente"+e);
          }
      }
   
   public Clientes BuscarCliente(String nome){
       try {
           String sql = "select * from Clientes where Nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Clientes obj = new Clientes();
           if(rs.next()){
               obj.setId(rs.getInt("ClienteID"));
               obj.setNome(rs.getString("Nome"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email")); 
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o cliente"+ erro);
       }
       return null;
    }
   
      public Clientes BuscarClienteCodigo(int codigo){
       try {
           String sql = "select * from clientes where ClienteID = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, codigo);
           ResultSet rs = stmt.executeQuery();
           Clientes obj = new Clientes();
           if(rs.next()){
               obj.setId(rs.getInt("ClienteID"));
               obj.setNome(rs.getString("Nome"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email")); 
  
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o cliente"+ erro);
       }
       return null;
    }
      
   //Método para listar os clientes do Banco
   public List<Clientes>Listar(){
       List<Clientes> lista = new ArrayList<>();
       try {
           String sql = "Select * from clientes";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Clientes obj = new Clientes();
               obj.setId(rs.getInt("ClienteID"));
               obj.setNome(rs.getString("Nome"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email")); 
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }


}

