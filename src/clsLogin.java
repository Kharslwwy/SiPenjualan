/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bramantya
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class clsLogin {
    String usn, password, level;
    int flag;
    
    public void setUsername (String u)
    { usn = u; }
    public void setPassword (String p)
    { password = p ; }
    public void setlevel (String l )
    { level = l; }
    public String getUsername ()
    {return (usn); }
    public String getPassword()
    { return (password);}
    public String getLevel()
    {return (level); }
    public void setFlag (int f)
    {flag = f; }
    public int getFlag ()
    {return (flag);}
    
    public void cekLogin(){
      try{
            Koneksi k = new Koneksi();
        Connection cn = k.openKoneksi();
        Statement st = cn.createStatement();
            String sql= "SELECT level FROM usr WHERE nama='"+ getUsername()+"' AND pw='"+getPassword()+"'";
                   
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                flag = 1;
                level= rs.getString("level");
                JOptionPane.showMessageDialog(null, "Berhasil Login sebagai :"+level,"BERHASIL", JOptionPane.INFORMATION_MESSAGE);
            }else{
                flag = 0;
                JOptionPane.showMessageDialog(null, "Username / Password salah","GAGAL", JOptionPane.WARNING_MESSAGE);
            }
            rs.close();
            st.close();
            cn.close();
                     
                    
            }catch(SQLException sqe){
            JOptionPane.showMessageDialog(null,"Eror"+sqe.getMessage(),"gagal",JOptionPane.WARNING_MESSAGE);
        }
    }
      
}
