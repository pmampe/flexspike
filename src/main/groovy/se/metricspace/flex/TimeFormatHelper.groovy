package se.metricspace.flex

class TimeFormatHelper {
    static String toHHMMString(int time) {
        boolean isNegative = (time<0)
        time = Math.abs(time)
        int hh = time / 60
        int mm = time % 60
        return ((isNegative) ? "-" : "")+String.format("%02d:%02d", hh, mm)
    }
}