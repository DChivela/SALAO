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
import salao.model.Funcionarios;
import salao.view.AreaTrabalho;
import salao.view.FormularioLogin;

/**
 *
 * @author domin
 */
public class FuncionariosDAO {
    //Método construtor
    private Connection conn;
   
   public FuncionariosDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Funcionarios obj){
       try {
           //1º Criar o SQL
           String sql = "insert into Funcionarios (nome, cargo, morada, telefone, utilizador, senha)"
                   + "values(?,?,?,?,?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2, obj.getCargo());
           stmt.setString(3,obj.getMorada());
           stmt.setString(4,obj.getTelefone());
           stmt.setString(5,obj.getUtilizador());
           stmt.setString(6,obj.getSenha());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o funcionario"+e);
       }
   }
   
      public void Editar(Funcionarios obj){
       try {
           //1º Criar o SQL
           String sql = "update Funcionarios set nome=?, cargo=?, morada=?, telefone=?, utilizador=?, senha=? where FuncionarioID=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2, obj.getCargo());
           stmt.setString(3,obj.getMorada());
           stmt.setString(4,obj.getTelefone());
           stmt.setString(5,obj.getUtilizador());
           stmt.setString(6,obj.getSenha());
           stmt.setInt(7,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o funcionario"+e);
       }
   }
      
      public void Excluir(Funcionarios obj){
          try {
              String sql = "delete from Funcionarios where FuncionarioID=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Funcionario exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o funcionario"+e);
          }
      }
   
   public Funcionarios BuscarFuncionario(String nome){
       try {
           String sql = "select * from Funcionarios where nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Funcionarios obj = new Funcionarios();
           if(rs.next()){
               obj.setId(rs.getInt("FuncionarioID"));
               obj.setNome(rs.getString("Nome"));
               obj.setCargo(rs.getString("Cargo"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email")); 
               obj.setUtilizador(rs.getString("Utilizador")); 
               obj.setSenha(rs.getString("Senha")); 
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o Funcionário"+ erro);
       }
       return null;
    }
      
   //Método para listar os clientes do Banco
   public List<Funcionarios>Listar(){
       List<Funcionarios> lista = new ArrayList<>();
       try {
           String sql = "Select * from Funcionarios";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Funcionarios obj = new Funcionarios();
               obj.setId(rs.getInt("FuncionarioID"));
               obj.setNome(rs.getString("Nome"));
               obj.setCargo(rs.getString("Cargo"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone")); 
               obj.setUtilizador(rs.getString("Utilizador")); 
               obj.setSenha(rs.getString("Senha")); 
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }
   
public void efectuarLogin(String utilizador, String senha){
    try {
        String sql = "select * from funcionarios where Utilizador=? and Senha=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, utilizador);
        stmt.setString(2, senha);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            JOptionPane.showMessageDialog(null, "Seja bem-vindo ao sistema!");
            AreaTrabalho at = new AreaTrabalho();
            at.setVisible(true);
        } else{
            FormularioLogin login = new FormularioLogin();
            JOptionPane.showMessageDialog(null, "Dados Inválidos!");
            login.setVisible(true);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro:"+e);
    }
}


}

