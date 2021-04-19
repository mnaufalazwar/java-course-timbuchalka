package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        MahasiswaService service = new MahasiswaServiceImpl(JdbcUtils.getMahasiswaDao());

        Mahasiswa mahasiswa = new Mahasiswa("10214049", "Naufal", (float) 3.20, "Fisika");
        Alamat alamat = new Alamat("kamarung", "06", "04", "1", "1", "1", "1");

        mahasiswa.setAlamat(alamat);

//        if(service.insert(mahasiswa)){
//            System.out.println(mahasiswa.getNama() + " inserted");
//        }else{
//            System.out.println("failed");
//        }

        if(service.delete("10214049")){
            System.out.println(mahasiswa.getNama() + " deleted");
        }
        else {
            System.out.println("failed");
        }

    }
}
