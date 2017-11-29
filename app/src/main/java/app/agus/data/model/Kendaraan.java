package app.agus.data.model;

/**
 * Created by ghost on 25/11/17.
 */

public class Kendaraan {
    private String key;
    private String nama;
    private String no_polisi;
    private String no_telepon;
    private String alamat;

    public Kendaraan() {
    }

    public Kendaraan(String name, String no_polisi, String no_telepon, String alamat) {
        this.nama = name;
        this.no_polisi = no_polisi;
        this.no_telepon = no_telepon;
        this.alamat = alamat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public void setNo_polisi(String no_polisi) {
        this.no_polisi = no_polisi;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
