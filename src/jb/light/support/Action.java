/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb.light.support;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jan
 */
public class Action {

    public static final String cActionRefresh = "Refresh";
    public static final String cActionSwitchLightOff = "LightOff";
    public static final String cActionSwitchOn = "SwitchOn";
    public static final String cActionSwitchOff = "SwitchOff";
    public static final String cActionSwitchAllOn = "SwitchAllOn";
    public static final String cActionSwitchAllOff = "SwitchAllOff";
    public static final String cActionNone = "None";

    private final int mID;
    private ZonedDateTime mMade;
    private ZonedDateTime mProcess;
    private final String mType;
    private final String mSwitch;
    private final String mPar;
    private final boolean mReady;

    public Action(String pType) {
        mID = -1;
        mMade = ZonedDateTime.now();
        mProcess = null;
        mType = pType;
        mSwitch = "";
        mPar = "";
        mReady = false;
    }

    public Action(String pType, String pPar) {
        mID = -1;
        mMade = ZonedDateTime.now();
        mProcess = null;
        mType = pType;
        if (pType.equals(cActionSwitchOn) || pType.equals(cActionSwitchOff)){
            mSwitch = pPar;
            mPar = "";
        } else {
            mSwitch = "";
            mPar = pPar;
        }
        mReady = false;
    }

    public Action(ZonedDateTime pMoment, String pType, String pSwitch) {
        mID = -1;
        mMade = ZonedDateTime.now();
        mProcess = pMoment;
        mType = pType;
        mSwitch = pSwitch;
        mPar = "";
        mReady = false;
    }

    public Action(ZonedDateTime pMoment, String pType, String pSwitch, ZonedDateTime pEndTime) {
        mID = -1;
        mMade = ZonedDateTime.now();
        mProcess = pMoment;
        mType = pType;
        mSwitch = pSwitch;
        mPar = pEndTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        mReady = false;
    }

    public Action(int pID, String pMade, String pProcess, String pType, String pSwitch, String pPar, boolean pReady) {
        mID = pID;
        if (pMade == null) {
            mMade = null;
        } else {
            try {
                mMade = ZonedDateTime.parse(pMade);
            } catch (Exception pExc) {
                mMade = null;
            }
        }
        if (pProcess == null) {
            mProcess = null;
        } else {
            try {
                mProcess = ZonedDateTime.parse(pProcess);
            } catch (Exception pExc) {
                mProcess = null;
            }
        }
        mType = pType;
        mSwitch = pSwitch;
        mPar = pPar;
        mReady = pReady;
    }

    public int xID() {
        return mID;
    }

    public ZonedDateTime xMade() {
        return mMade;
    }

    public ZonedDateTime xProcess() {
        return mProcess;
    }

    public String xType() {
        return mType;
    }

    public String xSwitch() {
        return mSwitch;
    }

    public String xPar() {
        return mPar;
    }

    public boolean xReady() {
        return mReady;
    }
}
