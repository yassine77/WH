package com.nerazzurro.wh.ui.home;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class TimeShift {

    private LocalTime mStartAM;
    private LocalTime mStopAM;
    private LocalTime mStartPM;
    private LocalTime mStopPM;

    public LocalTime getStartAM() {
        return mStartAM;
    }

    public void setStartAM(LocalTime startAM) {
        mStartAM = startAM;
    }

    public LocalTime getStopAM() {
        return mStopAM;
    }

    public void setStopAM(LocalTime stopAM) {
        mStopAM = stopAM;
    }

    public LocalTime getStartPM() {
        return mStartPM;
    }

    public void setStartPM(LocalTime startPM) {
        mStartPM = startPM;
    }

    public LocalTime getStopPM() {
        return mStopPM;
    }

    public void setStopPM(LocalTime stopPM) {
        mStopPM = stopPM;
    }

    public TimeShift() {
    }

    public TimeShift(LocalTime startAM, LocalTime stopAM, LocalTime startPM, LocalTime stopPM) {
        mStartAM = startAM;
        mStopAM = stopAM;
        mStartPM = startPM;
        mStopPM = stopPM;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Duration Duration(){

        Duration duree = Duration.between(mStartAM, mStopAM).plus(Duration.between(mStartPM, mStopPM));

        return duree;
    }
}
