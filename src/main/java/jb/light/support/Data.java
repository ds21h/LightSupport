/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb.light.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan
 */
public class Data {

    private int mStatus;
    private String mText = "";
    public static final int cNotSet = -1;
    public static final int cOK = 0;
    public static final int cSQLite_not_found = 100;
    public static final int cSQL_error = 200;

    private Connection mConn;

    public Data() {
        mStatus = cNotSet;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            mConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Light?user=Light&password=Light");
            mStatus = cOK;
        } catch (SQLException pExc) {
            xWriteLog("SQLException!");
            mStatus = cSQL_error;
            mText = pExc.getMessage();
            xWriteLog("Connection failed: " + mText);
        } catch (Exception pExc) {
            mStatus = cSQL_error;
            mText = pExc.getMessage();
            xWriteLog("Unspecified Exception: " + mText);
        }
    }

    public int xStatus() {
        return mStatus;
    }

    public Setting xSetting() {
        double lLongitude;
        double lLattitude;
        int lLightOffHour;
        int lLightOffMin;
        int lLightOffPeriod;
        int lSensorLimit;
        int lPeriodDark;
        int lPeriodSec;
        Statement lStm;
        ResultSet lRes;
        Setting lSetting;
        String lSql = "SELECT Longitude, Lattitude, LightOffHour, LightOffMin, OffDuration, SensorLimit, PeriodDark, PeriodSec "
                + "FROM Setting "
                + "WHERE ID = 'Light';";

        mText = "";
        lSetting = new Setting();
        if (mStatus == cOK) {
            try {
                lStm = mConn.createStatement();
                lRes = lStm.executeQuery(lSql);
                if (lRes.next()) {
                    lLongitude = lRes.getDouble("Longitude");
                    lLattitude = lRes.getDouble("Lattitude");
                    lLightOffHour = lRes.getInt("LightOffHour");
                    lLightOffMin = lRes.getInt("LightOffMin");
                    lLightOffPeriod = lRes.getInt("OffDuration");
                    lSensorLimit = lRes.getInt("SensorLimit");
                    lPeriodDark = lRes.getInt("PeriodDark");
                    lPeriodSec = lRes.getInt("PeriodSec");
                    lSetting = new Setting(lLongitude, lLattitude, lLightOffHour, lLightOffMin, lLightOffPeriod, lSensorLimit, lPeriodDark, lPeriodSec);
                }
                lRes.close();
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xSetting: SQL error " + mText);
            }
        }
        return lSetting;
    }

    public void xChangeSetting(Setting pSetting) {
        Statement lStm;
        String lSql;

        if (mStatus == cOK) {
            lSql = "UPDATE Setting "
                    + "SET "
                    + "Longitude = '" + String.valueOf(pSetting.xLongitude()) + "', "
                    + "Lattitude = '" + String.valueOf(pSetting.xLattitude()) + "', "
                    + "LightOffHour = '" + String.valueOf(pSetting.xLightOffHour()) + "', "
                    + "LightOffMin = '" + String.valueOf(pSetting.xLightOffMin()) + "', "
                    + "OffDuration = '" + String.valueOf(pSetting.xLightOffPeriod()) + "', "
                    + "SensorLimit = '" + String.valueOf(pSetting.xSensorLimit()) + "', "
                    + "PeriodDark = '" + String.valueOf(pSetting.xPeriodDark()) + "', "
                    + "PeriodSec = '" + String.valueOf(pSetting.xPeriodSec()) + "' "
                    + "WHERE ID = 'Light';";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xChangeSetting: SQL error " + mText);
            }
        }
    }

    public List<Switch> xSwitches() {
        List<Switch> lSwitches;
        Switch lSwitch;
        int lSeqNumber;
        String lName;
        boolean lActive;
        int lPause;
        String lIP;
        Statement lStm;
        ResultSet lRes;
        String lSql = "SELECT SeqNumber, Name, Active, Pause, IP "
                + "FROM Switch "
                + "ORDER BY SeqNumber, Name;";

        lSwitches = new ArrayList<>();
        mText = "";
        if (mStatus == cOK) {
            try {
                lStm = mConn.createStatement();
                lRes = lStm.executeQuery(lSql);
                while (lRes.next()) {
                    lSeqNumber = lRes.getInt("SeqNumber");
                    lName = lRes.getString("Name");
                    lActive = lRes.getBoolean("Active");
                    lPause = lRes.getInt("Pause");
                    lIP = lRes.getString("IP");
                    if (lIP == null) {
                        lIP = "";
                    }
                    lSwitch = new Switch(lSeqNumber, lName, lActive, lPause, lIP);
                    lSwitches.add(lSwitch);
                }
                lRes.close();
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xwitches: SQL error " + mText);
            }
        }
        return lSwitches;
    }

    public Switch xSwitch(String pName) {
        Switch lSwitch;
        int lSeqNumber;
        String lName;
        boolean lActive;
        int lPause;
        String lIP;
        Statement lStm;
        ResultSet lRes;
        String lSql = "SELECT SeqNumber, Name, Active, Pause, IP "
                + "FROM Switch "
                + "WHERE Name = '" + pName + "';";

        mText = "";
        lSwitch = new Switch();
        if (mStatus == cOK) {
            try {
                lStm = mConn.createStatement();
                lRes = lStm.executeQuery(lSql);
                if (lRes.next()) {
                    lSeqNumber = lRes.getInt("SeqNumber");
                    lName = lRes.getString("Name");
                    lActive = lRes.getBoolean("Active");
                    lPause = lRes.getInt("Pause");
                    lIP = lRes.getString("IP");
                    if (lIP == null) {
                        lIP = "";
                    }
                    lSwitch = new Switch(lSeqNumber, lName, lActive, lPause, lIP);
                }
                lRes.close();
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xSwitch: SQL error " + mText);
            }
        }
        return lSwitch;
    }

    public void xChangeSwitch(Switch pSwitch) {
        Statement lStm;
        String lSql;
        int lActive;
        String lIP;

        if (pSwitch.xIP().equals("")) {
            lIP = "null";
        } else {
            lIP = "'" + pSwitch.xIP() + "'";
        }
        if (pSwitch.xActive()) {
            lActive = 1;
        } else {
            lActive = 0;
        }
        if (mStatus == cOK) {
            lSql = "UPDATE Switch "
                    + "SET "
                    + "SeqNumber = '" + pSwitch.xSeqNumber() + "', "
                    + "Active = '" + lActive + "', "
                    + "Pause = '" + pSwitch.xPause() + "', "
                    + "IP = " + lIP + " "
                    + "WHERE Name = '" + pSwitch.xName() + "';";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xChangeSwitch: SQL error " + mText);
            }
        }
    }

    public void xDeleteSwitch(String pSwitchId) {
        Statement lStm;
        String lSql;

        if (mStatus == cOK) {
            lSql = "DELETE from Switch "
                    + "WHERE Name = '" + pSwitchId + "';";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xDeleteSwitch: SQL error " + mText);
            }
        }
    }

    public void xNewSwitch(Switch pSwitch) {
        Statement lStm;
        String lSql;
        int lActive;

        if (pSwitch.xActive()) {
            lActive = 1;
        } else {
            lActive = 0;
        }
        if (mStatus == cOK) {
            lSql = "INSERT INTO Switch (SeqNumber, Name, Active, Pause, IP) "
                    + "VALUES ('" + pSwitch.xSeqNumber() + "', "
                    + "'" + pSwitch.xName() + "', "
                    + "'" + lActive + "', "
                    + "'" + pSwitch.xPause() + "', "
                    + "'" + pSwitch.xIP() + "'" + ");";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xNewSwitch: SQL error " + mText);
            }
        }
    }

    public Current xCurrent() {
        String lSunset;
        String lStartLightOn;
        String lLightOff;
        String lUpdate;
        int lFase;
        int lLightReading;
        Statement lStm;
        ResultSet lRes;
        Current lCurrent;
        String lSql = "SELECT Sunset, StartLightOn, LightOff, UpdateTime, Fase, LightReading "
                + "FROM Current "
                + "WHERE ID = 'Light';";

        mText = "";
        lCurrent = new Current();
        if (mStatus == cOK) {
            try {
                lStm = mConn.createStatement();
                lRes = lStm.executeQuery(lSql);
                if (lRes.next()) {
                    lSunset = lRes.getString("Sunset");
                    lStartLightOn = lRes.getString("StartLightOn");
                    lLightOff = lRes.getString("LightOff");
                    lUpdate = lRes.getString("UpdateTime");
                    lFase = lRes.getInt("Fase");
                    lLightReading = lRes.getInt("LightReading");
                    lCurrent = new Current(lSunset, lStartLightOn, lLightOff, lUpdate, lFase, lLightReading);
                }
                lRes.close();
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xCurrent: SQL error " + mText);
            }
        }
        return lCurrent;
    }

    public void xCurrent(Current pCurrent) {
        Statement lStm;
        String lSql;
        DateTimeFormatter lFormat;

        lFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        if (mStatus == cOK) {
            lSql = "UPDATE Current "
                    + "SET "
                    + "Sunset = '" + pCurrent.xSunset().format(lFormat) + "', "
                    + "StartLightOn = '" + pCurrent.xStartLightOn().format(lFormat) + "', "
                    + "LightOff = '" + pCurrent.xLightOff().format(lFormat) + "', "
                    + "UpdateTime = '" + pCurrent.xUpdate().format(lFormat) + "', "
                    + "Fase = '" + pCurrent.xFase() + "', "
                    + "LightReading = '" + pCurrent.xLightReading() + "' "
                    + "WHERE ID = 'Light';";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xCurrent_Update: SQL error " + mText);
            }
        }
    }

    public void xNewAction(Action pAction) {
        Statement lStm;
        String lSql;
        DateTimeFormatter lFormat;
        String lMade;
        String lProcess;

        if (mStatus == cOK) {
            lFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            if (pAction.xMade() == null) {
                lMade = "";
            } else {
                lMade = pAction.xMade().format(lFormat);
            }
            if (pAction.xProcess() == null) {
                lProcess = "";
            } else {
                lProcess = pAction.xProcess().format(lFormat);
            }

            lSql = "INSERT INTO Action (Created, Process, Type, Switch, Par, Done) "
                    + "VALUES ('" + lMade + "', '" + lProcess + "', '" + pAction.xType() + "', '" + pAction.xSwitch() + "', '" + pAction.xPar() + "', 0);";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xNewAction: SQL error " + mText);
            }
        }
    }

    public List<Action> xActions() {
        List<Action> lActions;
        Action lAction;
        int lID;
        String lType;
        String lSwitch;
        String lMade;
        String lProcess;
        String lPar;
        boolean lReady;
        Statement lStm;
        ResultSet lRes;
        String lSql = "SELECT ID, Created, Process, Type, Switch, Par, Done "
                + "FROM Action "
                + "WHERE Done = 0 "
                + "Order by Process, Created;";

        lActions = new ArrayList<>();
        mText = "";
        if (mStatus == cOK) {
            try {
                lStm = mConn.createStatement();
                lRes = lStm.executeQuery(lSql);
                while (lRes.next()) {
                    lID = lRes.getInt("ID");
                    lMade = lRes.getString("Created");
                    if (lMade != null && lMade.equals("")) {
                        lMade = null;
                    }
                    lProcess = lRes.getString("Process");
                    if (lProcess != null && lProcess.equals("")) {
                        lProcess = null;
                    }
                    lType = lRes.getString("Type");
                    lSwitch = lRes.getString("Switch");
                    lPar = lRes.getString("Par");
                    lReady = lRes.getBoolean("Done");
                    lAction = new Action(lID, lMade, lProcess, lType, lSwitch, lPar, lReady);
                    lActions.add(lAction);
                }
                lRes.close();
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xAkties: SQL error " + mText);
            }
        }

        return lActions;
    }

    public void xActionProcessed(Action pAction) {
        Statement lStm;
        String lSql;
        if (mStatus == cOK) {
            lSql = "UPDATE Action "
                    + "SET "
                    + "Done = 1 "
                    + "WHERE ID = " + pAction.xID() + ";";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xActionProcessed: SQL error " + mText);
            }
        }
    }

    public void xActionSwitchProcessed(Switch pSwitch) {
        Statement lStm;
        String lSql;
        if (mStatus == cOK) {
            lSql = "UPDATE Action "
                    + "SET "
                    + "Done = 1 "
                    + "WHERE Switch = '" + pSwitch.xName() + "';";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                xWriteLog("xActionSwitchProcessed: SQL error " + mText);
            }
        }
    }

    public final void xWriteLog(String pText) {
        LocalDateTime lNow;
        Statement lStm;
        String lSql;

        lNow = LocalDateTime.now();
        if (mStatus == cOK) {
            lSql = "INSERT INTO Log (Time, Text) "
                    + "VALUES ('" + lNow.toString() + "', '" + pText + "');";
            try {
                lStm = mConn.createStatement();
                lStm.executeUpdate(lSql);
                lStm.close();
            } catch (SQLException ex) {
                mStatus = cSQL_error;
                mText = ex.getMessage();
                System.out.println(lNow.toString() + " " + mText);
            }
        } else {
            System.out.println(lNow.toString() + " Status not OK: " + mStatus);
        }
        System.out.println(lNow.toString() + " " + pText);
    }

    public void xClose() {
        if (mConn != null) {
            try {
                mConn.close();
            } catch (SQLException ex) {
                mText = ex.getMessage();
            }
        }
    }
}
