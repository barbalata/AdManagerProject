package sample.DataAccesObject;

import sample.Module.Price;
import sample.utils.SqlConf;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 18/01/2018.
 */

public class PriceDAO {

    public static void PriceINS (Price price) {
        CallableStatement cStmt = null;;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[PriceINS](?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("price_id", Types.INTEGER);
            cStmt.setString("price_unit", price.getPriceUnite());
            cStmt.setBigDecimal("price_cost_click", price.getPriceCostClick());
            cStmt.setBigDecimal("price_cost_view", price.getPriceCostView());
            cStmt.execute();

            price.setPriceID(cStmt.getInt("price_id"));
            System.out.println("Insert - DONE!");
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

    }

    public static void PriceUPD(Price price) {
        CallableStatement cStmt = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[PriceUPD](?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("price_id", price.getPriceID());
            cStmt.setString("price_unit", price.getPriceUnite());
            cStmt.setBigDecimal("price_cost_click", price.getPriceCostClick());
            cStmt.setBigDecimal("price_cost_view", price.getPriceCostView());
            cStmt.execute();

            System.out.println("Update - DONE!");
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

    }

    public static ArrayList<Price> PriceSEL(Integer priceID) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Price> prices = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[PriceSEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("price_id", priceID);

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
                Price price = new Price();
                price.setPriceID(rs.getInt("price_id"));
                price.setPriceCostClick(rs.getBigDecimal("price_cost_click"));
                price.setPriceCostView(rs.getBigDecimal("price_cost_click"));
                price.setPriceUnite(rs.getString("price_unit"));

                prices.add(price);
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
        return prices;
    }

    public static void PriceDEL(Price price) {
        CallableStatement cStmt = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[PriceDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("price_id", price.getPriceID());
            cStmt.execute();
            System.out.println("Delete - DONE!");
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
    }
}

