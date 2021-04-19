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

    private final String INSERT_ALAMAT = "INSERT INTO alamat(nim,nama_jalan,rt,rw,kode_desa,kode_kec,kode_kab,kode_prop) VALUES(?,?,?,?,?,?,?,?)";

    private final String UPDATE_ALAMAT = "UPDATE alamat SET nama_jalan=?, rt=?, rw=?,kode_desa=?,kode_kec=?,kode_kab=?,kode_prop=? WHERE nim=?";

    public MahasiswaDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean insert(Mahasiswa mahasiswa) {
        PreparedStatement preparedStatementMhs = null;
        PreparedStatement preparedStatementAlamat = null;
        try{
            connection.setAutoCommit(false);
            preparedStatementMhs = connection.prepareStatement(INSERT);
            preparedStatementMhs.setString(1, mahasiswa.getNim());
            preparedStatementMhs.setString(2, mahasiswa.getNama());
            preparedStatementMhs.setFloat(3, mahasiswa.getIpk());
            preparedStatementMhs.setString(4, mahasiswa.getJurusan());
            boolean isMhsInserted =  preparedStatementMhs.executeUpdate() > 0;

            preparedStatementAlamat = connection.prepareStatement(INSERT_ALAMAT);
            preparedStatementAlamat.setString(1, mahasiswa.getNim());
            preparedStatementAlamat.setString(2, mahasiswa.getAlamat().getNama_jalan());
            preparedStatementAlamat.setString(3, mahasiswa.getAlamat().getRT());
            preparedStatementAlamat.setString(4, mahasiswa.getAlamat().getRW());
            preparedStatementAlamat.setString(5, mahasiswa.getAlamat().getKode_desa());
            preparedStatementAlamat.setString(6, mahasiswa.getAlamat().getKode_kec());
            preparedStatementAlamat.setString(7, mahasiswa.getAlamat().getKode_kab());
            preparedStatementAlamat.setString(8, mahasiswa.getAlamat().getKode_prop());
            boolean isInsertedAlamat = preparedStatementAlamat.executeUpdate() > 0;

            if(isMhsInserted && isInsertedAlamat){
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }

        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            try{
                connection.rollback();
            } catch (SQLException e1){
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                if(preparedStatementMhs != null){
                    preparedStatementMhs.close();
                }
                if(preparedStatementAlamat != null){
                    preparedStatementAlamat.close();
                }
            } catch (SQLException e2){
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e2);
            }
        }
        return false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        PreparedStatement preparedStatementMhs = null;
        PreparedStatement preparedStatementAlamat = null;
        try{
            connection.setAutoCommit(false);
            preparedStatementMhs = connection.prepareStatement(UPDATE);
            preparedStatementMhs.setString(1, mahasiswa.getNama());
            preparedStatementMhs.setFloat(2, mahasiswa.getIpk());
            preparedStatementMhs.setString(3, mahasiswa.getJurusan());
            preparedStatementMhs.setString(4, mahasiswa.getNim());
            boolean isUpdatedMhs = preparedStatementMhs.executeUpdate() > 0;

            preparedStatementAlamat = connection.prepareStatement(UPDATE_ALAMAT);
            preparedStatementAlamat.setString(1, mahasiswa.getAlamat().getNama_jalan());
            preparedStatementAlamat.setString(2, mahasiswa.getAlamat().getRT());
            preparedStatementAlamat.setString(3, mahasiswa.getAlamat().getRW());
            preparedStatementAlamat.setString(4, mahasiswa.getAlamat().getKode_desa());
            preparedStatementAlamat.setString(5, mahasiswa.getAlamat().getKode_kec());
            preparedStatementAlamat.setString(6, mahasiswa.getAlamat().getKode_kab());
            preparedStatementAlamat.setString(7, mahasiswa.getAlamat().getKode_prop());
            preparedStatementAlamat.setString(8, mahasiswa.getNim());
            boolean isUpdatedAlamat = preparedStatementAlamat.executeUpdate() > 0;

            if(isUpdatedMhs && isUpdatedAlamat){
                connection.commit();
                return true;
            }
            else{
                connection.rollback();
            }
        } catch (SQLException e){
            Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            try{
                connection.rollback();
            } catch (SQLException e1){
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                if(preparedStatementMhs != null){
                    preparedStatementMhs.close();
                }
                if(preparedStatementAlamat != null){
                    preparedStatementAlamat.close();
                }
            } catch (SQLException e2){
                Logger.getLogger(MahasiswaDaoImpl.class.getName()).log(Level.SEVERE, null, e2);
            }
        }
        return false;
    }

    @Override
    public boolean delete(String nim) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE);
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
