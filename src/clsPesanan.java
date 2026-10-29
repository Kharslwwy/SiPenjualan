/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class clsPesanan {
    protected String nopsn, kodepel, kodebrg;
    protected int jumlah, harga, flag;
    protected Date tanggal;

    public void setNoPesan(String N) {
        nopsn = N;
    }

    public void setTanggal(Date Tg) {
        tanggal = Tg;
    }
    
    public void setKodePelanggan(String kdp){
        kodepel = kdp;
    }
    
    public void setKodeBarang(String kdb){
        kodebrg = kdb;
    }
    public void setJumlah(int jml){
        jumlah = jml;
    }
    public void setHarga(int hr){
        harga = hr;
    }
    public String getNoPesan() {
        return nopsn;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public String getKodePelanggan() {
        return kodepel;
    }
    public String getKodeBarang() {
        return kodebrg;
    }public int getJumlah() {
        return jumlah;
    }public int getHarga() {
        return harga;
    }
    public void setFlag(int F) {
        flag = F;
    }
    public int getFlag() {
        return flag;
    }
    
    public void simpanPesanan(){
    try
        {
            Koneksi k=new Koneksi();
            Connection cn=k.openKoneksi();
            Statement st=cn.createStatement();
            String sql = "INSERT INTO pesanan VALUES (";
                    sql += "'" + getNoPesan() + "', '"+getTanggal()+"',";
                    sql += "'"+getKodePelanggan()+"')";
                    
                   st.executeUpdate(sql);
                   setFlag(1);
                   st.close();
                   cn.close();
                   JOptionPane.showMessageDialog(null, "Data Pesan Berhasil disimpan",
                           "SIMPAN", JOptionPane.INFORMATION_MESSAGE);
                 
        }
        catch (SQLException sqe)
        {
            JOptionPane.showMessageDialog(null, "Gagal Simpan Pesan",
                           "Gagal Simpan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void simpanDetailPesan() {
    try {
        Koneksi k = new Koneksi();
        Connection cn = k.openKoneksi();
        Statement st = cn.createStatement();

        String sql = "INSERT INTO detailpesan(nopesan, kdbrg, jmlpesan, hrgpesan) VALUES("
                + "'" + getNoPesan() + "',"
                + "'" + getKodeBarang() + "',"
                + "'" + getJumlah() + "',"
                + "'" + getHarga() + "')";
        st.executeUpdate(sql);

        setFlag(2);
        st.close();
        cn.close();

        JOptionPane.showMessageDialog(null, "Detail berhasil disimpan",
                "SIMPAN", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException sqe) {
        JOptionPane.showMessageDialog(null, "Gagal Simpan Detail: " + sqe.getMessage(),
                "Gagal Simpan", JOptionPane.ERROR_MESSAGE);
    }
}

    public void updateStok(){
        try
        {
            Koneksi k=new Koneksi();
            Connection cn=k.openKoneksi();
            Statement st=cn.createStatement();
            String sql = "update barang set stok=stok-'"+getJumlah()+"' ";
                    
                    sql += "where kdbrg='" + getKodeBarang()+"'";
                    
                   st.executeUpdate(sql);
                   
                   st.close();
                   cn.close();
                   //JOptionPane.showMessageDialog(null, "Data Berhasil disimpan",
                     //      "SIMPAN", JOptionPane.INFORMATION_MESSAGE);
                 
        }
        catch (SQLException sqe)
        {
            //JOptionPane.showMessageDialog(null, "Gagal Simpan",
              //             "Gagal Simpan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void autoNoPsn() {
    try {
        int hit = 0;

        Koneksi k = new Koneksi();
        Connection cn = k.openKoneksi();
        Statement st = cn.createStatement();

        // Cek apakah tabel pesanan kosong
        String sql = "SELECT COUNT(*) FROM pesanan";
        ResultSet rs = st.executeQuery(sql);

        if (rs.next() && rs.getInt(1) == 0) {
            // Jika tidak ada data, mulai dari PS001
            setNoPesan("PS001");
        } else {
            // Ambil nomor pesanan terbesar
            sql = "SELECT MAX(MID(nopesan, 3, 3)) FROM pesanan";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String maxNo = rs.getString(1); // Bisa null kalau tabel kosong

                if (maxNo != null) {
                    hit = Integer.parseInt(maxNo) + 1;
                } else {
                    hit = 1; // kalau null berarti mulai dari 1
                }

                if (hit < 10) {
                    setNoPesan("PS00" + hit);
                } else if (hit < 100) {
                    setNoPesan("PS0" + hit);
                } else {
                    setNoPesan("PS" + hit);
                }
            }
        }

        rs.close();
        st.close();
        cn.close();
      } catch (SQLException | NumberFormatException e) {
          e.printStackTrace();
      }
    }

    
}
