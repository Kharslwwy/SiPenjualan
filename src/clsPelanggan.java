/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class clsPelanggan {
    protected String kode, nama, alamat, telepon;
    protected int flag;

    public void setKode(String kd) {
        kode = kd;
    }

    public void setNama(String nm) {
        nama = nm;
    }
    
    public void setAlamat(String alm) {
        alamat = alm;
    }

    public void setTelepon(String tlp) {
        telepon = tlp;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public String getTelepon() {
        return telepon;
    }

    public void setFlag(int F) {
        flag = F;
    }

    public int getFlag() {
        return flag;
    }
    
    public void simpan()
    {
        try
        {
            Koneksi k=new Koneksi();
            Connection cn=k.openKoneksi();
            Statement st=cn.createStatement();
            String sql = "INSERT INTO pelanggan VALUES (";
                    sql += "'" + getKode() + "', '"+getNama()+"',";
                    sql += "'" + getAlamat() + "',' "+getTelepon()+"');";
                    
                   st.executeUpdate(sql);
                   setFlag(1);
                   st.close();
                   cn.close();
                   JOptionPane.showMessageDialog(null, "Data Berhasil disimpan",
                           "SIMPAN", JOptionPane.INFORMATION_MESSAGE);
                 
        }
        catch (SQLException sqe)
        {
            JOptionPane.showMessageDialog(null, "Gagal Simpan",
                           "Gagal Simpan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void ubah()
    {
       try
        {
            Koneksi k=new Koneksi();
            Connection cn=k.openKoneksi();
            Statement st=cn.createStatement();
            String sql = "UPDATE pelanggan SET ";
                    sql+= "nmplg='" + getNama() + "', almplg='"+getAlamat()+"', ";
                    sql+="tlpplg='"+getTelepon()+"' where ";
                    sql+="kdplg='"+getKode()+"'";

                   st.executeUpdate(sql);
                   setFlag(2);
                   st.close();
                   cn.close();
                   JOptionPane.showMessageDialog(null, "Data Berhasil diUbah  ",
                           "Ubah", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException sqe)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal diUbah",
                           "Gagal Ubah", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hapus()
    {
        try
            {
                Koneksi k = new Koneksi();
                Connection cn = k.openKoneksi();
                Statement st = cn.createStatement();
                String sql = "delete from pelanggan ";
                sql += "where kdplg='" + getKode() + "'";

                st.executeUpdate(sql);
                setFlag(3);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus",
                        "HAPUS", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException sqe)
            {
                JOptionPane.showMessageDialog(null, "Data GAGAL diHapus",
                        "Gagal Hapus", JOptionPane.ERROR_MESSAGE);
            }
    }
    
   public void tampil() {
        try {
            Koneksi k = new Koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();

            String sql = "SELECT * FROM pelanggan WHERE kdplg = '" + getKode().trim() + "'";

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                setFlag(4);
                setKode(rs.getString("kdplg"));
                setNama(rs.getString("nmplg"));
                setAlamat(rs.getString("almplg"));
                setTelepon(rs.getString("tlpplg"));
            } else {
                setFlag(0); // Tidak ditemukan
            }

            rs.close();
            st.close();
            cn.close();
        } catch (SQLException sqe) {
            JOptionPane.showMessageDialog(null, "Kesalahan tampil(): " + sqe.getMessage());
        }
    }

    
    public void autoKode()
    {
        try
        {
            int hit=0;
            
            Koneksi k=new Koneksi();
            Connection cn=k.openKoneksi();
            Statement st=cn.createStatement();
            String sql ="select count(kdplg)from pelanggan";
            ResultSet rs=st.executeQuery(sql);
            
            if (rs.next())
            {
                if (Integer.parseInt(rs.getString(1))==0)
                {
                    setKode("P0001");
                    st.close();
                    rs.close();
                }
                else
                {
                    sql="select Max(mid(kdplg,2,4)) from pelanggan";
                    rs=st.executeQuery(sql);
                    rs.next();
                    hit = (Integer.parseInt(rs.getString(1)))+1;
                    if (hit<10)
                    { setKode ("P000"+hit);}
                    else if (hit<100)
                    { setKode("P00"+hit);}
                    else if (hit<1000)
                    { setKode("P0"+hit);}
                    else if (hit<10000)
                    { setKode("P"+hit);}
                    st.close();
                    rs.close();
                }
            }
        }
        catch (SQLException sqe)
        {}
    }

}
