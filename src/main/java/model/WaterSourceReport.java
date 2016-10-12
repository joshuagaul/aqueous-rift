package model;

import javafx.beans.collections.FXCollections;
import javafx.beans.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class WaterSourceReport {
    private static IntegerProperty _number = new SimpleIntegerProperty();
    private ObjectProperty<Date> _date = new SimpleObjectProperty<>();
    private ObjectProperty<Time> _time = new SimpleObjectProperty<>();
    private StringProperty _name = new SimpleStringProperty();
    private ObjectProperty<Location> _location = new SimpleObjectProperty<>();
    private ObjectProperty<WaterType> _type = new SimpleObjectProperty<>();
    private ObjectProperty<Condition> _condition = new SimpleObjectProperty<>();
    
    public Integer getNumber() { return _number.get(); }

    public ObjectProperty getDateProperty() { return _date; }
    public Date getDate() { return _date.get(); }
    public void setDate(Date data) { _date.set(date); }

    public ObjectProperty getTimeProperty() { return _time; }
    public Time getTime() { _time.get(); }
    public void setDate(Time time) { _time.set(time); }

    public String getName() { return _name.get(); }
    
    public Double getLocation() { return _location.get(); }
    public void setLocation() { _location.set(); }
    public void setLocation(Double location) { _location.set(location); }

    /**
     * Make a new water source report
     * 
     *
     * 
     */
    public WaterSourceReport() {
    
    }
}
