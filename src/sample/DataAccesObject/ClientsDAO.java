package sample.DataAccesObject;

import sample.Module.Client;
import sample.utils.SqlConf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class ClientsDAO {
    public static ArrayList<Client> ClientGET(Integer clientID, String clientName, String clientType, String clientUniqueCode, String cityName, String regionName) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        ArrayList<Client> clients = new ArrayList<>();
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ClientSEL](?, ? , ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cStmt.setInt("client_id", clientID);
            cStmt.setString("name", clientName);
            cStmt.setString("type", clientType);
            cStmt.setString("cnp_cui", clientUniqueCode);
            cStmt.setString("city_name", cityName);
            cStmt.setString("region_name", regionName);

            rs = cStmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setClientID(rs.getInt("client_id"));
                client.setCityID(rs.getInt("city_id"));
                client.setClientName(rs.getString("name"));
                client.setClientType(rs.getString("type"));
                client.setClientStreet(rs.getString("street"));
                client.setClientBuildingNumber(rs.getString("building_number"));
                client.setClientUniqueCode(rs.getString("cnp_cui"));
                client.setClientEmailAddress(rs.getString("email_address"));
                client.setClientURL(rs.getString("url_client"));
                client.setClientCity(rs.getString("city_name"));
                client.setClientRegion(rs.getString("region_name"));

                clients.add(client);
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
        return clients;
    }

    public static void ClientINS(Client client) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ClientINS](?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.registerOutParameter("client_id", Types.INTEGER);
            cStmt.setString("city_id", String.valueOf(client.getCityID()));
            cStmt.setString("name", String.valueOf(client.getClientName()));
            cStmt.setString("type", String.valueOf(client.getClientType()));
            cStmt.setString("street", String.valueOf(client.getClientStreet()));
            cStmt.setString("building_number", String.valueOf(client.getClientBuildingNumber()));
            cStmt.setString("cnp_cui", String.valueOf(client.getClientUniqueCode()));
            cStmt.setString("email_address", String.valueOf(client.getClientEmailAddress()));
            cStmt.setString("url_client", String.valueOf(client.getClientURL()));

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
                client.setClientID(rs.getInt("client_id]"));
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

    public static void ClientUPD(Client client) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ClientUPD](?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("client_id", client.getClientID());
            cStmt.setInt("city_id", client.getCityID());
            cStmt.setString("name", String.valueOf(client.getClientName()));
            cStmt.setString("type", String.valueOf(client.getClientType()));
            cStmt.setString("street", String.valueOf(client.getClientStreet()));
            cStmt.setString("building_number", String.valueOf(client.getClientBuildingNumber()));
            cStmt.setString("cnp_cui", String.valueOf(client.getClientUniqueCode()));
            cStmt.setString("email_address", String.valueOf(client.getClientEmailAddress()));
            cStmt.setString("url_client", client.getClientURL());

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

    public static void ClientDEL(Client client) {
        CallableStatement cStmt = null;
        ResultSet rs = null;

        try {
            SqlConf.dbConnect();
            cStmt = SqlConf.getConnection().prepareCall("{call [dbo].[ClientDEL](?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            cStmt.setInt("client_id", client.getClientID());

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
