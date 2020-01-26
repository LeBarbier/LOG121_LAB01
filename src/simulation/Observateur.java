package simulation;

import java.util.Observable;
import java.util.Observer;

public interface Observateur {

    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void notifyObservers();
    public void notifyObservers(Object arg);
    public void deleteObservers();
    void setChanged(); // protected
    void clearChanged(); // protected
    public boolean hasChanged();
    public int countObservers();
}
