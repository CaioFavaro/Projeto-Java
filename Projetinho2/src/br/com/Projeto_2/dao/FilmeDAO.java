/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Projeto_2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aluno
 */
public class FilmeDAO {
    
  public FilmeDAO() {
        
    }
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirFilme(FilmeDTO filmeDTO){
        
        try{
            
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "insert into cliente (nome, diretor, ano,"
                    + "distribuidora, nota, genero, estoque) values ( "
                    + "'" + filmeDTO.getNome() + "', "
                    + "'" + filmeDTO.getDiretor()+ "', "
                    + filmeDTO.getAno()+ ", "
                    + "'" + filmeDTO.getDistribuidora()+ "', "
                    + filmeDTO.getNota()+ ", "
                    + "'" + filmeDTO.getGenero()+ "', "
                    + filmeDTO.getEstoque()+ ", ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        
        catch(SQLException e) {
            
            System.out.println(e.getMessage());
            return false;
              
        }
        
        finally {
            
            ConexaoDAO.CloseDB();
        }
        
    }
    
    public boolean excluirFilme(FilmeDTO filmeDTO) {
        
        try{
            
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from cliente where id_cliente = " + filmeDTO.getId_filme();
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        finally{
            
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarFilme(FilmeDTO filmeDTO) {
        
        try{
            
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update cliente set "
                        + "nome = '" + filmeDTO.getNome()+ "',"
                        + "diretor = '" +  filmeDTO.getDiretor()+ "',"
                        + "ano = '" + filmeDTO.getAno()+ "',"
                        + "distribuidora = '" + filmeDTO.getDistribuidora()+ "',"
                        + "nota = '" + filmeDTO.getNota()+ "',"
                        + "genero = '" + filmeDTO.getGenero()+ "',"
                        + "estoque = '" + filmeDTO.getEstoque()+ "',"
                        + "where id_cliente = " + filmeDTO.getId_cliente();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
            
        }
        
        catch(Exception e){
            
            System.out.println(e.getMessage());
            return false;
            
        }
        
        finally {
            
            ConexaoDAO.CloseDB();
        }
    }
    
   public ResultSet consultarFilme(FilmeDTO filmeDTO, int opcao) {
        try {
           
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            switch (opcao){
                case 1:
                    comando = "Select c.* "+
                              "from filme c "+
                              "where nome like '" + filmeDTO.getNome()+ "%' " +
                              "order by c.nome";    
                break;
                case 2:
                    comando = "Select c.* "+
                              "from filme c " +
                              "where c.id_filme = " + filmeDTO.getId_cliente();
                break;
                case 3:
                    comando = "Select c.id_filme, c.nome "+
                              "from filme c ";
                break;
                
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
}

