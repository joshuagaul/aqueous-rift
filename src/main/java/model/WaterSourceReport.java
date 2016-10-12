package model;

//import javafx.beans.collections.FXCollections;
//import javafx.beans.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class WaterSourceReport {
    private static int number = 0;
    private Date date;
    private Time time;
    private String userId;
    private ObjectProperty<Location> _location = new SimpleObjectProperty<>();
    private ObjectProperty<WaterType> _type = new SimpleObjectProperty<>();
    private ObjectProperty<WaterCondition> _condition = new SimpleObjectProperty<>();
    
    public int getNumber() { return number; }

    public String getDate() { return date.toString(); }

    public String getTime() { return time.toString(); }

    public String getUserId() { return userId; }
    
    public Location getLocation() { return _location.get(); }
    public void setLocation(Location location) { _location.set(location); }
    public ObjectProperty getLocationProperty() { return _location; }

    public WaterType getType() { return _type.get(); }
    public void setType(WaterType type) { _type.set(type); }
    public ObjectProperty getTypeProperty() { return _type; }
    
    public WaterCondition getCondition() { return _condition.get(); }
    public void setCondition(WaterCondition condition) { _condition.set(condition); }
    public ObjectProperty getConditionProperty() { return _condition; }
    /**
     * Make a new water source report
     * 
     * @param user the user information who login the app
     * @param location the location information
     * @param type the type of the water
     * @param condition the condition of the water
     */
    public WaterSourceReport(User user
                            , Location location
                            , WaterType type
                            , WaterCondition condition) {
        number++;
        date = new Date();
        time = new Time();
        userId = user.getUserId();
        _location.set(location);
        _type.set(type);
        _condition.set(condition);
    }
}
