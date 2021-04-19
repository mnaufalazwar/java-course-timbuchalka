package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        MahasiswaService mahasiswaService = new MahasiswaServiceImpl(JdbcUtils.getMahasiswaDao());

        mahasiswaService.insert(new Mahasiswa("10214049", "Naufal", (float) 3.20, "Fisika"));
        mahasiswaService.insert(new Mahasiswa("10214050", "Azwar", (float) 3.20, "Fisika"));
        mahasiswaService.insert(new Mahasiswa("10214052", "Iftikar", (float) 3.20, "Fisika"));
        mahasiswaService.insert(new Mahasiswa("10214051", "Viedi", (float) 3.20, "TL"));
        mahasiswaService.insert(new Mahasiswa("10214053", "Ananda", (float) 3.20, "TL"));

    }
}
