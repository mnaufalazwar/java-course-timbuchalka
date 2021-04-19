package com.company;

import java.util.List;

public interface MahasiswaService {

    public boolean insert(Mahasiswa m);

    public boolean update(Mahasiswa m);

    public boolean delete(String nim);

    public Mahasiswa getMahasiswaByNim(String nim);

    public List<Mahasiswa> getAllMahasiswa();

}
