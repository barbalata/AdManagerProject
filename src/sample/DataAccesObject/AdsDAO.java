package sample.DataAccesObject;

import sample.Module.Ad;
import sample.utils.SqlConf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class AdsDAO {
    //INSERT
    public static void setAd(Ad ad) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AdINS](?, ?, ?, ?, ?, ?, ?, ?, ? , ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("ad_id", Types.INTEGER);
            cStmt.setString("location_id", String.valueOf(ad.getLocationID()));
            cStmt.setString("client_id", String.valueOf(ad.getClientID()));
            cStmt.setString("author_id", String.valueOf(ad.getAuthorID()));
            cStmt.setString("start_date", String.valueOf(ad.getStartDate()));
            cStmt.setString("stop_date", String.valueOf(ad.getStopDate()));
            cStmt.setString("ad_size", String.valueOf(ad.getAdSize()));
            cStmt.setString("price_id", String.valueOf(ad.getPriceID()));
            cStmt.setString("ad_number_click", String.valueOf(ad.getAdClickNumber()));
            cStmt.setString("ad_number_view", String.valueOf(ad.getAdViewNumber()));

            boolean results = cStmt.execute();
            int rowsAffected = 0;

            //ad.setAdID(cStmt.getInt("ad_id"));

            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cStmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cStmt.getUpdateCount();
                }
                results = cStmt.getMoreResults();
            }
            while (rs.next()) {
                ad.setAdID(rs.getInt("ad_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
    }

    public static ArrayList<Ad> AdGET(Integer adID, String clientName, String authorName, Date startDate, Date stopDate) {
        CallableStatement cStmt = null;
        ResultSet rs;
        ArrayList<Ad> ads = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AdSEL](?, ? , ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("ad_id", adID);
            cStmt.setString("client_name", clientName);
            cStmt.setString("author_name", authorName);
            cStmt.setDate("start_date", startDate);
            cStmt.setDate("stop_date", stopDate);

            rs = cStmt.executeQuery();

            while (rs.next()) {
                Ad ad = new Ad();
                ad.setAdID(rs.getInt("ad_id"));
                ad.setLocationID(rs.getInt("location_id"));
                ad.setClientID(rs.getInt("client_id"));
                ad.setAuthorID(rs.getInt("author_id"));
                ad.setPriceID(rs.getInt("price_id"));
                ad.setStartDate(rs.getDate("start_date"));
                ad.setStopDate(rs.getDate("stop_date"));
                ad.setAdSize(rs.getString("ad_size"));
                ad.setAdClickNumber(rs.getInt("ad_number_click"));
                ad.setAdViewNumber(rs.getInt("ad_number_view"));
                ad.setAdClientName(rs.getString("client_name"));
                ad.setAdAuthorName(rs.getString("author_name"));
                ad.setContentID(rs.getInt("content_id"));

                ads.add(ad);
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
        return ads;
    }

    public static void AdINS(Ad ad) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AdINS](?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("ad_id", Types.INTEGER);
            cStmt.setInt("location_id", ad.getLocationID());
            cStmt.setInt("client_id", ad.getClientID());
            cStmt.setInt("content_id", ad.getContentD());
            cStmt.setInt("author_id", ad.getAuthorID());
            cStmt.setDate("start_date", new java.sql.Date(ad.getStartDate().getTime()));
            cStmt.setDate("stop_date", new java.sql.Date(ad.getStopDate().getTime()));
            cStmt.setInt("price_id", ad.getPriceID());
            cStmt.setInt("ad_number_click",ad.getAdClickNumber());
            cStmt.setInt("ad_number_view", ad.getAdViewNumber());
            cStmt.setString("ad_size", ad.getAdSize());

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
                ad.setAdID(rs.getInt("ad_id"));
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

    public static void AdUPD(Ad ad) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AdUPD](?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("ad_id", ad.getAdID());
            cStmt.setInt("location_id", ad.getLocationID());
            cStmt.setInt("client_id", ad.getClientID());
            cStmt.setInt("content_id", ad.getContentD());
            cStmt.setInt("author_id", ad.getAuthorID());
            cStmt.setString("ad_size", ad.getAdSize());
            cStmt.setInt("priceid", ad.getPriceID());
            cStmt.setDate("start_date", new java.sql.Date(ad.getStartDate().getTime()));
            cStmt.setDate("stop_date", new java.sql.Date(ad.getStopDate().getTime()));

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

    public static void AdDEL(Ad ad) {
        CallableStatement cStmt = null;
        ResultSet rs = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[AdDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("ad_id", ad.getAdID());

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
