package com.company;

import java.util.List;

public interface MahasiswaDao {

    public boolean insert(Mahasiswa mahasiswa);

    public boolean update(Mahasiswa mahasiswa);

    public boolean delete(String nim);

    public Mahasiswa getMahasiswaByNim(String nim);

    public List<Mahasiswa> getAllMahasiswa();

}
