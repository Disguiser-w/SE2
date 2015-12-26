package presentation.special_ui;

import java.util.Observable;

class MyObservable extends Observable {
	public MyObservable(){
		
	}
	public void setData() {
		setChanged();
		notifyObservers();
	}
}
