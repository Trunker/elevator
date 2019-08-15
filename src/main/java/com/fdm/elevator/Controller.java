package com.fdm.elevator;
 
public class Controller {
	private View view;
	private ElevatorScheduler eleSched;
	private Building building;
	
	public Controller(View view) {
		super();
		this.view = view;

	}

	public void setUpModels(BuildingFactory BuildingFactory,EleSchedulerFactory eleSchedulerFactory){
		int[] intValues=view.getInitValue(); //Stub
		
		Building buliding =BuildingFactory.getBuilding(intValues[0]); //verify
		ElevatorScheduler eleScheduler=eleSchedulerFactory.getScheduler(intValues[1],intValues[2], intValues[0]);//Verify
	}
	
	
}



//class BuildingFactory() {  //factoring design pattern: hidden all creation on the 
//	getBuilding(int floorCount){
//		List<Floors> floors=...;
//		for (int i =0;i<floorCount;i++) {
//			fllors.add(new Floor(i));
//		}
//		
//		return new Building()floors;
//	}
//}
