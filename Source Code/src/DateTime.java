
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The DateTime class represents a specific date and time, including the hour,
 * minute, day,
 * month, year, and day name of the week.
 */
public class DateTime implements DateTimeInterface{

    // instance variables
    private int hour; // current hour
    private int minute; // current minute
    private int day; // current day
    private int month; // current month
    private int year; // current year
    private String dayName; // current day name of the week

    /**
     * Constructs a DateTime object with the Current hour, minute, day, month,
     * year, and day name of the week.
     */
    public DateTime() {
        // This Function will set the Current Date and Time
        setCurrentTime();
    }

    /**
     * Constructs a DateTime object with the specified hour, minute, day, month,
     * year, and
     * day name of the week.
     *
     * @param hour    the current hour (0-23)
     * @param minute  the current minute (0-59)
     * @param day     the current day of the month (1-31)
     * @param month   the current month (1-12)
     * @param year    the current year (e.g., 2023)
     * @param dayName the current day name of the week (e.g., "Tuesday")
     * @throws IllegalArgumentException if any of the input parameters are out of
     *                                  range
     */
    public DateTime(int hour, int minute, int day, int month, int year, String dayName) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || day < 1 || day > 31 ||
                month < 1 || month > 12 || year < 0 || dayName == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayName = dayName;
    }

    /**
     * Returns the current hour.
     *
     * @return the current hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the current hour.
     *
     * @param hour the current hour (0-23)
     * @throws IllegalArgumentException if the input parameter is out of range
     */
    public void setHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.hour = hour;
    }

    /**
     * Returns the current minute.
     *
     * @return the current minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the current minute.
     *
     * @param minute the current minute (0-59)
     * @throws IllegalArgumentException if the input parameter is out of range
     */
    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.minute = minute;
    }

    /**
     * Returns the current day of the month.
     *
     * @return the current day of the month
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the current day of the month.
     *
     * @param day the current day of the month (1-31)
     * @throws IllegalArgumentException if the input parameter is out of range
     */
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.day = day;
    }

    /**
     * 
     * Returns the current month.
     * 
     * @return the current month
     */
    public int getMonth() {
        return month;
    }

    /**
     * 
     * Sets the current month.
     * 
     * @param month the current month (1-12)
     * @throws IllegalArgumentException if the input parameter is out of range
     */
    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.month = month;
    }

    /**
     * 
     * Returns the current year.
     * 
     * @return the current year
     */
    public int getYear() {
        return year;
    }

    /**
     * 
     * Sets the current year.
     * 
     * @param year the current year (e.g., 2023)
     * @throws IllegalArgumentException if the input parameter is negative
     */
    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.year = year;
    }

    /**
     * 
     * Returns the current day name of the week.
     * 
     * @return the current day name of the week
     */
    public String getDayName() {
        return dayName;
    }

    /**
     * 
     * Sets the current day name of the week.
     * 
     * @param dayName the current day name of the week (e.g., "Tuesday")
     * @throws IllegalArgumentException if the input parameter is null
     */
    public void setDayName(String dayName) {
        if (dayName == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        this.dayName = dayName;
    }

    /**
     * 
     * Sets the current time to the current system time.
     */
    public void setCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        this.hour = now.getHour();
        this.minute = now.getMinute();
        this.day = now.getDayOfMonth();
        this.month = now.getMonthValue();
        this.year = now.getYear();
        this.dayName = now.getDayOfWeek().toString();
    }

    /**
     * 
     * Returns a String representation of this DateTime object in the format
     * "dd/mm/yyyy hh:mm DayName".
     * 
     * @return a String representation of this DateTime object
     */
    @Override
    public String toString() {
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time = LocalTime.of(hour, minute);
        return date.toString() + " (" + dayName.toLowerCase() + ") " + time.toString();
    }

}
