package com.company;

public class JdbcUtils {

    private static MahasiswaDao mahasiswaDao;

    public static MahasiswaDao getMahasiswaDao(){
        if(mahasiswaDao == null){
            mahasiswaDao = new MahasiswaDaoImpl(Koneksi.getConnection());
        }
        return mahasiswaDao;
    }

}
