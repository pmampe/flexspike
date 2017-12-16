package se.metricspace.flex

class DateFormatterTagLib {
    static String namespace = 'suflex'
    static Map defaultEncodeAs = [taglib:'html']

    def deltaTimeAsHHMM = { attrs, body ->
//       int delta = Integer.parseInt(attrs.delta)
       int delta = (int)attrs.delta
       boolean isNegative = delta < 0
       delta = Math.abs(delta)
       int hh = delta / 60
       int mm = delta % 60

       out << body() << ( ((isNegative) ? "-" : "")+String.format("%02d:%02d", hh, mm))
    }
}
