package se.metricspace.flex

class CalenderController {
    DateService dateService

    def index() {
        String selectedMonth = (params.month)?: Date.newInstance().format('yyyy-MM')
        List<String> months = dateService.getCalenderMonths()
        List<FlexDate> flexDates = (params.find) ? dateService.getDatesForMonth(selectedMonth): []
        [flexDates: flexDates, months: months, selectedMonth: selectedMonth]
    }
}
