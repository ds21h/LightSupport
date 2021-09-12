/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb.light.support;

import java.time.ZonedDateTime;

/**
 *
 * @author Jan
 */
public class Current {

    public static final int cFaseNull = 0;
    public static final int cFaseDay = 1;
    public static final int cFaseTwilight = 2;
    public static final int cFaseEvening = 3;
    public static final int cFaseNight = 4;

    private ZonedDateTime mSunset;
    private ZonedDateTime mStartLightOn;
    private ZonedDateTime mLightOff;
    private ZonedDateTime mUpdate;
    private int mFase;
    private int mLightReading;

    public Current() {
        mSunset = ZonedDateTime.now();
        mStartLightOn = mSunset;
        mLightOff = mSunset;
        mUpdate = mSunset;
        mFase = 0;
        mLightReading = 0;
    }

    public Current(String pSunset, String pStartLightOn, String pLightOff, String pUpdate, int pFase, int pLightReading) {
        mSunset = ZonedDateTime.parse(pSunset);
        mStartLightOn = ZonedDateTime.parse(pStartLightOn);
        mLightOff = ZonedDateTime.parse(pLightOff);
        mUpdate = ZonedDateTime.parse(pUpdate);
        mFase = pFase;
        mLightReading = pLightReading;
    }

    public ZonedDateTime xSunset() {
        return mSunset;
    }

    public void xSunset(ZonedDateTime pSunset) {
        mSunset = pSunset;
    }

    public ZonedDateTime xStartLightOn() {
        return mStartLightOn;
    }

    public void xStartLightOn(ZonedDateTime pStartLightOn) {
        mStartLightOn = pStartLightOn;
    }

    public ZonedDateTime xLightOff() {
        return mLightOff;
    }

    public void xLightOff(ZonedDateTime pLightOff) {
        mLightOff = pLightOff;
    }

    public ZonedDateTime xUpdate() {
        return mUpdate;
    }

    public void xUpdate(ZonedDateTime pUpdate) {
        mUpdate = pUpdate;
    }

    public int xFase() {
        return mFase;
    }

    public void xFase(int pFase) {
        if (pFase >= cFaseNull) {
            if (pFase <= cFaseNight) {
                mFase = pFase;
            }
        }
    }

    public int xLightReading() {
        return mLightReading;
    }

    public void xLightReading(int pLightReading) {
        mLightReading = pLightReading;
    }

    public void xSetStartLightOn() {
        mStartLightOn = mSunset.minusHours(1);
    }

    public void xStartLightOnProcessed() {
        mStartLightOn = mSunset.plusDays(1).minusHours(1);
    }

    public void xLightOffProcessed() {
        mLightOff = mLightOff.plusDays(1);
    }

    public void xUpdateProcessed() {
        mUpdate = mUpdate.plusDays(1);
    }

}
