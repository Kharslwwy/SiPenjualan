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
public class clsNota {
    protected String nnota, npesan;
    protected Date tgnota;
    protected int flag;
    public void setNoNota (String n)
    {nnota = n;}
    public void setNoPesan (String np)
    { npesan = np;}
    public void setTglNota (Date t)
    { tgnota = t;}
    public String getNoNota ()
    { return (nnota);}
    public String getNoPesan ()
    { return (npesan);}
    public Date getTglNota ()
    { return (tgnota);}
    
    public void setFlag(int f)
    { flag = f;}
    public int getFlag()
    { return (flag);}
    
    
    public void simpanNota() {
    try {
        Koneksi k = new Koneksi();
        Connection cn = k.openKoneksi();
        Statement st = cn.createStatement();

        String sql = "INSERT INTO nota VALUES ('"
                + getNoNota() + "', '"
                + getTglNota() + "', '"
                + getNoPesan() + "')";

        st.executeUpdate(sql);
        setFlag(1);
        st.close();
        cn.close();

        JOptionPane.showMessageDialog(null,
                "Data sudah disimpan dan Nota akan Dicetak",
                "SIMPAN",
                JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException sqe) {
                JOptionPane.showMessageDialog(null,
                "Gagal Simpan: " + sqe.getMessage(),
                "Gagal Simpan",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    public void autoNoNota() 
    {        
        try 
        {
            int hit = 0;
       
            Koneksi k = new Koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "SELECT COUNT(nonota) FROM nota";
            ResultSet rs = st.executeQuery(sql);
        
            if (rs.next())
            {
                if (Integer.parseInt(rs.getString(1)) == 0) 
                {    
                    setNoNota("NT001");
                    st.close();
                    cn.close();
                }
                else 
                {
                    sql = "SELECT MAX(MID(nonota, 3, 3)) FROM nota";
                    rs = st.executeQuery(sql);
                    rs.next();
                    hit = Integer.parseInt(rs.getString(1)) + 1;
                    
                    if (hit < 10) 
                        {setNoNota("NT00" + hit);}
                    else if (hit < 100)
                        {setNoNota("NT0" + hit);}
                    else 
                        {setNoNota("NT" + hit);}
                    st.close();
                    cn.close();
                }
            }
        }
        catch (SQLException sqe)
        {}
    }    
}       
    
    
