
public interface DateTimeInterface {
    
    // Getters and setters for instance variables
    public int getHour();
    public void setHour(int hour);
    
    public int getMinute();
    public void setMinute(int minute);
    
    public int getDay();
    public void setDay(int day);
    
    public int getMonth();
    public void setMonth(int month);
    
    public int getYear();
    public void setYear(int year);
    
    public String getDayName();
    public void setDayName(String dayName);
    
    // Other methods
    public void setCurrentTime();
    public String toString();
    
}
