package uk.ac.glos.ct5025.s1804317.footballStats.UI;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class GameTimer {
    long timeStart = System.nanoTime();
    long timeFinish;
    StopWatch stopWatch = new StopWatch();

    public GameTimer(){
        stopWatch.start();
    }

    public long getStopWatch() {
        return stopWatch.getTime(TimeUnit.SECONDS);
    }

    public String getWatchTime(){
        long time = stopWatch.getTime(TimeUnit.SECONDS);
        long mintime = time/60;
        long sectime = time%60;
        mintime = Math.round(mintime);
        String mintimeStr = Long.toString(mintime);
        String sectimeStr = Long.toString(sectime);
        if (mintimeStr.length()==1){
            mintimeStr = "0"+mintimeStr;
        } if (sectimeStr.length()==1){
            sectimeStr = "0"+sectimeStr;
        }


        String outputTime = mintimeStr + ":" + sectimeStr;

        return outputTime;
    }

    public long getTimeElapsed(){
        timeFinish = System.nanoTime();
        long timeElapsed = timeFinish - timeStart;

        return timeElapsed;
    }

}
