package com.company;

import java.util.List;

public class MahasiswaServiceImpl implements MahasiswaService {

    private final MahasiswaDao mahasiswaDao;

    public MahasiswaServiceImpl(MahasiswaDao mahasiswaDao) {
        this.mahasiswaDao = mahasiswaDao;
    }

    @Override
    public boolean insert(Mahasiswa m) {
        return mahasiswaDao.insert(m);
    }

    @Override
    public boolean update(Mahasiswa m) {
        return mahasiswaDao.update(m);
    }

    @Override
    public boolean delete(String nim) {
        return mahasiswaDao.delete(nim);
    }

    @Override
    public Mahasiswa getMahasiswaByNim(String nim) {
        return mahasiswaDao.getMahasiswaByNim(nim);
    }

    @Override
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaDao.getAllMahasiswa();
    }

}
