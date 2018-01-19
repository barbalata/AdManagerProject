package sample.DataAccesObject;

import sample.Module.Author;
import sample.Module.Location;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class LocationDAO {
    public static void LocationINS (Location location) {
        CallableStatement cStmt = null;;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[LocationINS](?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("location_id", Types.INTEGER);
            cStmt.setBigDecimal("coordinate_x", location.getLocationCoordonateX());
            cStmt.setBigDecimal("coordinate_y", location.getLocationCoordonateY());
            cStmt.execute();

            location.setLocationID(cStmt.getInt("location_id"));
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

    public static void LocationUPD(Location location) {
        CallableStatement cStmt = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[LocationUPD](?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("location_id", location.getLocationID());
            cStmt.setBigDecimal("coordonate_x", location.getLocationCoordonateX());
            cStmt.setBigDecimal("coordonate_y", location.getLocationCoordonateY());
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

    public static ArrayList<Location> LocationSEL(Integer locationID) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Location> locations = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[LocationSEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("location_id", locationID);

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
                Location location = new Location();
                location.setLocationID(rs.getInt("location_id"));
                location.setLocationCoordonateX(rs.getBigDecimal("coordonate_x"));
                location.setLocationCoordonateY(rs.getBigDecimal("coordonate_y"));

                locations.add(location);
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
        return locations;
    }

    public static void LocationDEL(Location location) {
        CallableStatement cStmt = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[LocationDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("location_id", location.getLocationID());
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
