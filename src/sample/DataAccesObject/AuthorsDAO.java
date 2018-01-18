package sample.DataAccesObject;

import sample.Module.Author;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class AuthorsDAO {
    public static void setAuthor(Author author) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AuthorINS](?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("author_id", Types.INTEGER);
            cStmt.setString("first_name", String.valueOf(author.getAuthorFirstName()));
            cStmt.setString("last_name", String.valueOf(author.getAuthorLastName()));
            cStmt.setString("phone", String.valueOf(author.getAuthorPhone()));
            cStmt.setString("email_address", String.valueOf(author.getAuthorEmailAddress()));

            boolean results = cStmt.execute();
            int rowsAffected = 0;

            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cStmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cStmt.getUpdateCount();
                }
                results = cStmt.getMoreResults();
            }
            while (rs != null && rs.next()) {
                author.setAuthorID(rs.getInt("author_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }

        System.out.println("Insert - DONE!");
    }

    public static void updAuthor(Author author) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AuthorUPD](?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("author_id", author.getAuthorID());
            cStmt.setString("first_name", String.valueOf(author.getAuthorFirstName()));
            cStmt.setString("last_name", String.valueOf(author.getAuthorLastName()));
            cStmt.setString("phone", String.valueOf(author.getAuthorPhone()));
            cStmt.setString("email_address", String.valueOf(author.getAuthorEmailAddress()));

            cStmt.execute();
            System.out.println("Done!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }

        System.out.println("Update - DONE!");
    }

    public static ArrayList<Author> getAuthor(String firstName, String lastName) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Author> authorVar = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AuthorSEL](?, ? ,?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("author_id", -1);
            cStmt.setString("first_name", firstName);
            cStmt.setString("last_name", lastName);

            boolean results = cStmt.execute();
            int rowsAffected = 0;

            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cStmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cStmt.getUpdateCount();
                }
                results = cStmt.getMoreResults();
            }
            while (rs != null && rs.next()) {
                Author authorTemp = new Author();
                authorTemp.setAuthorID(rs.getInt("author_id"));
                authorTemp.setAuthorPhone(rs.getString("phone"));
                authorTemp.setAuthorFirstName(rs.getString("first_name"));
                authorTemp.setAuthorEmailAddress(rs.getString("email_address"));
                authorTemp.setAuthorLastName(rs.getString("last_name"));

//                System.out.println(authorTemp.getAuthorID() + " " + authorTemp.getAuthorFirstName() + " "
//                        + authorTemp.getAuthorLastName() + " "
//                        + authorTemp.getAuthorPhone() + " "
//                        + authorTemp.getAuthorEmailAddress());

                authorVar.add(authorTemp);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        System.out.println("Select - DONE!");
        return authorVar;
    }

    public static void delAuthor(Author author) {
        CallableStatement cStmt = null;
        ResultSet rs = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AuthorDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("author_id", author.getAuthorID());

            cStmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        System.out.println("Delete - DONE!");
    }
}
