package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MahasiswaDaoImpl implements MahasiswaDao {

    private final Connection connection;

    private final String INSERT = "INSERT INTO `mahasiswa`(`nim`, `nama`, `ipk`, `jurusan`) VALUES (?,?,?,?)";

    private final String UPDATE = "UPDATE `mahasiswa` SET `nama` = ?, `ipk` = ?, `jurusan` = ? WHERE `nim` = ?";

    private final String DELETE = "DELETE from `mahasiswa` WHERE `nim` = ?";

    private final String SELECT_ALL = "SELECT `nim`, `nama`, `ipk`, `jurusan` FROM `mahasiswa`";

    private final String SELECT_BY_NIM = "SELECT `nim`, `nama`, `ipk`, `jurusan` FROM `mahasiswa` WHERE `nim` = ?";

    public MahasiswaDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean insert(Mahasiswa mahasiswa) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, mahasiswa.getNim());
            preparedStatement.setString(2, mahasiswa.getNama());
            preparedStatement.setFloat(3, mahasiswa.getIpk());
            preparedStatement.setString(4, mahasiswa.getJurusan());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, mahasiswa.getNama());
            preparedStatement.setFloat(2, mahasiswa.getIpk());
            preparedStatement.setString(3, mahasiswa.getJurusan());
            preparedStatement.setString(4, mahasiswa.getNim());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String nim) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, nim);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return false;
    }

    @Override
    public Mahasiswa getMahasiswaByNim(String nim) {
        PreparedStatement preparedStatement = null;
        ResultSet executeQuery = null;
        Mahasiswa mahasiswa = null;
        try{
            preparedStatement = connection.prepareStatement(SELECT_BY_NIM);
            preparedStatement.setString(1,nim);
            executeQuery = preparedStatement.executeQuery();
            if(executeQuery.next()){
                System.out.println("" + SELECT_BY_NIM);
                mahasiswa = new Mahasiswa(
                        executeQuery.getNString("nim"),
                        executeQuery.getNString("nama"),
                        executeQuery.getFloat("ipk"),
                        executeQuery.getNString("jurusan"));
            }
        }catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(executeQuery != null){
                    executeQuery.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mahasiswa;
    }

    @Override
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswas = new ArrayList<>();
        Statement statement = null;
        ResultSet executeQuery = null;
        try{
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(SELECT_ALL);
            while (executeQuery.next()){
                Mahasiswa mahasiswa = new Mahasiswa(
                        executeQuery.getNString("nim"),
                        executeQuery.getNString("nama"),
                        executeQuery.getFloat("ipk"),
                        executeQuery.getNString("jurusan")
                );
                mahasiswas.add(mahasiswa);
            }
        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (executeQuery != null) {
                    executeQuery.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return mahasiswas;
    }
}
