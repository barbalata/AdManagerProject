package sample.DataAccesObject;

import sample.Module.City;
import sample.Module.Client;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class CityDAO {
    public static void CityINS(City city) {
        CallableStatement cStmt = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[CityINS](?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("city_id", Types.INTEGER);
            cStmt.setString("city_name", String.valueOf(city.getCityName()));
            cStmt.setString("region_id", String.valueOf(city.getRegionID()));

            cStmt.execute();
            city.setCityID(cStmt.getInt("city_id"));
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

    public static void CityDEL(City city) {
        CallableStatement cStmt = null;
        ResultSet rs = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[CityDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("city_id", city.getCityID());

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

    public static void CityUPD(City city) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[CityUPD](?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("city_id", Types.INTEGER);
            cStmt.setString("city_name", String.valueOf(city.getCityName()));
            cStmt.setInt("region_id", city.getRegionID());

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

    public static ArrayList<City> CityGET(String cityName, Integer cityID, Integer regionID) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<City> cities = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[CitySEL](?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("city_id", cityID);
            cStmt.setString("city_name", cityName);
            cStmt.setInt("region_id", regionID);

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
                City city = new City();
                city.setCityID(rs.getInt("city_id"));
                city.setCityName(rs.getString("city_name"));
                city.setRegionID(rs.getInt("region_id"));

                cities.add(city);
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
        return cities;
    }

}
