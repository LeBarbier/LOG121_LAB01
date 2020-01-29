package simulation;

import java.util.Observer;

public interface Observateur {

    void addObserver(Observer o);
    void deleteObserver(Observer o);
    void notifyObservers();
    void notifyObservers(Object arg);
    void deleteObservers();
    void setChanged(); // protected
    void clearChanged(); // protected
    boolean hasChanged();
    int countObservers();
}
