package sample.DataAccesObject;

import sample.Module.Content;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;

public class ContentDAO {
    public static void ContentINS (Content content) {
        CallableStatement cStmt = null;;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ContentINS](?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("content_id", Types.INTEGER);
            cStmt.setString("title", content.getContentTitle());
            cStmt.setString("body", content.getContentBody());
            cStmt.setString("media", content.getContentMedia());
            cStmt.execute();

            content.setContentID(cStmt.getInt("content_id"));
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

        System.out.println("Insert - DONE!");
    }

    public static void ContentUPD(Content content) {
        CallableStatement cStmt = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ContentUPD](?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("content_id", content.getContentID());
            cStmt.setString("title", content.getContentTitle());
            cStmt.setString("body", content.getContentBody());
            cStmt.setString("media", content.getContentMedia());
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

        System.out.println("Update - DONE!");
    }

    public static ArrayList<Content> ContentSEL(Integer contentID) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Content> contents = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ContentSEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("content_id", contentID);

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
                Content content = new Content();
                content.setContentID(rs.getInt("content_id"));
                content.setContentBody(rs.getString("body"));
                content.setContentTitle(rs.getString("title"));
                content.setContentMedia(rs.getString("media"));

                contents.add(content);
                System.out.println("Select - DONE!");
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
        return contents;
    }

    public static void ContentDEL(Content content) {
        CallableStatement cStmt = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ContentDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("content_id", content.getContentID());
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
