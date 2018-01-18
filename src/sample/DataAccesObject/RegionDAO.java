package sample.DataAccesObject;

import sample.Module.City;
import sample.Module.Client;
import sample.Module.Region;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class RegionDAO {
    //INSERT
    public static void setRegion(Region region){
        CallableStatement cStmt = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[RegionINS](?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("region_id", Types.INTEGER);
            cStmt.setString("region_name", region.getRegionName());
            cStmt.execute();

            region.setRegionID(cStmt.getInt("region_id"));
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public static void RegionDEL(Region region) {
        CallableStatement cStmt = null;
        ResultSet rs = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[RegionDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("region_id", region.getRegionID());

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

    public static void RegionUPD(Region region) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[RegionUPD](?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("region_id", Types.INTEGER);
            cStmt.setString("region_name", region.getRegionName());

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

    public static ArrayList<Region> RegionGET(String regionName, Integer regionID) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Region> regions = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[RegionSEL](?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("region_id", regionID);
            cStmt.setString("region_name", regionName);

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
                Region region = new Region();
                region.setRegionID(rs.getInt("region_id"));
                region.setRegionName(rs.getString("region_name"));

                regions.add(region);
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
        return regions;
    }

}
