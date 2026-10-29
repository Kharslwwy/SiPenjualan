import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class clsBarang {
    protected String kode, nama, sat;
    protected int hrg, stok, flag;

    public void setKode(String Kd) {
        kode = Kd;
    }

    public void setNama(String Nm) {
        nama = Nm;
    }

    public void setSatuan(String St) {
        sat = St;
    }

    public void setHarga(int Hr) {
        hrg = Hr;
    }

    public void setStok(int Sk) {
        stok = Sk;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getSatuan() {
        return sat;
    }

    public int getHarga() {
        return hrg;
    }

    public int getStok() {
        return stok;
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
            String sql = "INSERT INTO barang VALUES (";
                    sql += "'" + getKode() + "', ";
                    sql += "'" + getNama() + "', ";
                    sql += "'" + getSatuan() + "', ";
                    sql += getHarga() + ", ";
                    sql += getStok() + ");";
                    
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
            String sql = "UPDATE barang SET ";
                    sql += "nmbrg='" + getNama() + "', ";
                    sql += "sat='" + getSatuan() + "', ";
                    sql += "hrgbrg=" + getHarga() + ", ";
                    sql += "stok=" + getStok() + " ";
                    sql += "WHERE kdbrg='" + getKode() + "';";

                   st.executeUpdate(sql);
                   setFlag(2);
                   st.close();
                   cn.close();
                   JOptionPane.showMessageDialog(null, "Data Berhasil diUbah ",
                           "Ubah", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException sqe)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal diUbah",
                           "Gagal Ubah", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void hapus()
    {
        try
            {
                Koneksi k = new Koneksi();
                Connection cn = k.openKoneksi();
                Statement st = cn.createStatement();
                String sql = "delete from barang ";
                sql += "where kdbrg='" + getKode() + "'";

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
                        "Gagal Hapus", JOptionPane.WARNING_MESSAGE);
            }
    }
    
     public void tampil() {
        try {
            Koneksi k = new Koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();

            String sql = "SELECT * FROM barang WHERE kdbrg = '" + getKode().trim() + "'";

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                setFlag(4);
                setKode(rs.getString("kdbrg"));
                setNama(rs.getString("nmbrg"));
                setSatuan(rs.getString("sat"));
                setHarga(rs.getInt("hrgbrg"));
                setStok(rs.getInt("stok"));
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
            String sql ="select count(kdbrg)from barang";
            ResultSet rs=st.executeQuery(sql);
            
            if (rs.next())
            {
                if (Integer.parseInt(rs.getString(1))==0)
                {
                    setKode("B0001");
                    st.close();
                    rs.close();
                }
                else
                {
                    sql="select Max(mid(kdbrg,2,4)) from barang";
                    rs=st.executeQuery(sql);
                    rs.next();
                    hit = (Integer.parseInt(rs.getString(1)))+1;
                    if (hit<10)
                    { setKode ("B000"+hit);}
                    else if (hit<100)
                    { setKode("B00"+hit);}
                    else if (hit<1000)
                    { setKode("B0"+hit);}
                    else if (hit<10000)
                    { setKode("B"+hit);}
                    st.close();
                    rs.close();
                }
            }
        }
        catch (SQLException sqe)
        {}
    }
    
    
}
