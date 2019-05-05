package jdbc;

import entities.Page;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {



    private static Statement stmt;
    private static Connection conn;
    private static User usr;
    private static Page p;



    public static void init() {
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapplication", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






    public static void saveUser(String imie, String nazwisko, String login, String email, String haslo) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, imie);
            ps.setString(3, nazwisko);
            ps.setString(4, login);
            ps.setString(5, haslo);
            ps.setString(6, email);
            ps.setString(7, "https://idh-racemanagement.com/assets/img/img-default.jpg");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkUser(String login, String haslo) {
        ResultSet rs;
        boolean flag = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT imie,nazwisko,login,email,haslo,id,url FROM USERS where login like '" + login
                    + "'and haslo like'" + haslo + "'");
            while (rs.next()) {
                flag = true;
                String imie = rs.getString(1);
                String nazwisko = rs.getString(2);
                String l = rs.getString(3);
                String email = rs.getString(4);
                String h = rs.getString(5);
                int id = rs.getInt(6);
                String url = rs.getString(7);
                usr = new User(imie, nazwisko, l, email, h, id, url);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static User getUser() {
        return usr;
    }

    public static void updateUrl(String param) {

        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE users SET url ='" + param + "' WHERE id =" + usr.getId());
            usr.setUrl(param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateHtml(String param, String title) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into html values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, param);
            ps.setString(3, title);
            ps.setInt(4, usr.getId());
            ps.setString(5, "silver");
            ps.setString(6, "white");
            ps.setString(7, "silver");
            ps.setString(8, "12px");
            ps.setString(9, "normal");
            ps.setString(10, "black");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Page> getPages() {
        ResultSet rs;
        List<Page> p = new ArrayList<Page>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM html where userId like " + usr.getId());
            Page page;
            while (rs.next()) {
                page = new Page(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                p.add(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public static void setSite(Page page) {
        p = page;
    }

    public static Page getFirstPage() {
        ResultSet rs;
        boolean flag = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM html where userId = " + usr.getId() + " order by id");

                rs.next();
                flag = true;
                int id  = rs.getInt(1);
                String text  = rs.getString(2);
                String title = rs.getString(3);
                int userId  = rs.getInt(4);
                String headerColor  = rs.getString(5);
                String footerColor  = rs.getString(6);
                String containerColor  = rs.getString(7);
                String fontSize  = rs.getString(8);
                String fontWeight  = rs.getString(9);
                String fontColor  = rs.getString(10);

                p = new Page(id, text, title, userId, headerColor, footerColor, containerColor, fontSize, fontWeight, fontColor);

        }catch(SQLException exc){
                exc.printStackTrace();
        }

        return p;
    }

    public static void setColor(String hcolor, String fcolor, String ccolor, String fontSize, String fontWeight, String fontColor) {
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE html SET headerColor ='" + hcolor + "',footerColor = '" + fcolor + "',containerColor = '" + ccolor
                    + "', fontSize = '" + fontSize + "', fontWeight = '" + fontWeight + "', fontColor = '" + fontColor
                    + "' WHERE id =" + p.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Page getPage(){
        return p;
    }

    public static void editHtml(String param) {
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE html SET text ='" + param + "' WHERE id =" + p.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSite(Page page) {
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM html  WHERE id =" + page.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }














}
