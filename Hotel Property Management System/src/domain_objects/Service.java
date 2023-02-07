package domain_objects;

public abstract class Service { 
	String description;
	double cost;
	
	String getDescription() {
		return this.description;
	}
	
	double getCost() {
		return this.cost;
	}
	
}
