package application;

public class ProcessInput {

	public static double[] processInput(String LaunchLat, String LaunchLon, String TargetLat, String TargetLon, String Mass, String Thrust, String Angle) {
		String [] launchlat = LaunchLat.split(" ");
		String [] launchlon = LaunchLon.split(" ");
		String [] targetlat = TargetLat.split(" ");
		String [] targetlon = TargetLon.split(" ");
		
		double Launch_Lat = Double.parseDouble(launchlat[0]);
		double Launch_Lon = Double.parseDouble(launchlon[0]);
		double Target_Lat = Double.parseDouble(targetlat[0]);
		double Target_Lon = Double.parseDouble(targetlon[0]);
		
		if(launchlat[1].equals("S"))
		{
			Launch_Lat = Launch_Lat*(-1.0);
		}
		if(launchlon[1].equals("W"))
		{
			Launch_Lon = Launch_Lon*(-1.0);
		}
		if(targetlat[1].equals("S"))
		{
			Target_Lat = Target_Lat*(-1.0);
		}
		if(targetlon[1].equals("W"))
		{
			Target_Lon = Target_Lon*(-1.0);
		}
		
		double mass = Double.parseDouble(Mass);
		
		double thrust = Double.parseDouble(Thrust);
		
		double angle = Double.parseDouble(Angle);
		
		//System.out.println(Launch_Lat + "\n" + Launch_Lon + "\n" + Target_Lat + "\n" + Target_Lon + "\n" + mass + "\n" + thrust + "\n" + angle);
		double  R = 6371000;
		//converting to radian
		double fye1 = (Launch_Lat*Math.PI)/180.0;
		double fye2 = (Target_Lat*Math.PI)/180.0;
		double dfye = ((Target_Lat-Launch_Lat)*Math.PI)/180.0;
		double dlambda = ((Target_Lon-Launch_Lon)*Math.PI)/180.0;
		
		//haversine formula
		
		double a = Math.sin(dfye/2)*Math.sin(dfye/2) + Math.cos(fye1)*Math.cos(fye2)*Math.sin(dlambda/2)*Math.sin(dlambda/2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = R*c; //distance in metres
		
		double range = d/1000;  //range in kms
		
	    double angler = (angle*Math.PI)/180;  //angle in radians
	    
	    double acclrtn = (thrust -(mass*9.8))/mass; //engine acceleration
	    
	    //burntime
	    double burntime = Math.sqrt(d/(acclrtn*Math.sqrt((acclrtn*acclrtn/96.04) + (acclrtn*Math.sin(angler)/9.8)) + acclrtn*Math.cos(angler)/2)); 
	    
	    //maximum altitude 
	    double maxh = 0.5*acclrtn*Math.pow(burntime,2)*Math.sin(angler) +((Math.pow(acclrtn,2)*Math.pow(burntime,2)*Math.pow(Math.sin(angler),2))/19.6); 
	    //maxh = maxh/1000;
	    
	    //time of flight 
	    double ftime = burntime + (2*acclrtn*burntime*Math.sin(angler) + Math.sqrt(Math.pow(2*acclrtn*burntime*Math.sin(angler),2) + 39.2*acclrtn*Math.pow(burntime,2)*Math.sin(angler)))/19.6; 
	    //ftime = ftime/60;
	    
	    double apogee_time = burntime + ((acclrtn*burntime)*Math.sin(angler))/9.8;
	    
	    double reentry_time = apogee_time + Math.sqrt((maxh-100000.0)/4.9);
	    
	    double vfx = acclrtn*burntime*Math.cos(angler); 
	    double vfy = Math.sqrt(Math.pow(acclrtn*burntime*Math.sin(angler),2) + 9.8*acclrtn*Math.pow(burntime,2)*Math.sin(angler)); 
	    		
	    //final velocity 
	    double vf = Math.sqrt(vfy*vfy + vfx*vfx); 
	    //vf = vf*3.6;
	    
	    double[] BurnoutPointPos = new double[2];
	    double[] ApogeePointPos = new double[2];
	    
	    ICBMTrajectoryCalc t = new ICBMTrajectoryCalc();
	    
	   
	    
	    
	    ApogeePointPos = t.ApogeePoint(maxh, burntime, acclrtn, angler);
	    
	   
	    
	    double Apogee_X = ApogeePointPos[0];
	    double Apogee_Y = ApogeePointPos[1];
	    
	    double[] data = {ftime, Launch_Lat, Launch_Lon, Target_Lat, Target_Lon, burntime, apogee_time, reentry_time, acclrtn, angle, maxh};
	    return data;
		
	}
	
	/*public static void Gen_Calc(double Launch_lat, double Launch_lon, double Target_lat, double Target_lon, double Mass, double Thrust, double Angle){
		
		double  R = 6371000;
		//converting to radian
		double fye1 = (Launch_lat*Math.PI)/180.0;
		double fye2 = (Target_lat*Math.PI)/180.0;
		double dfye = ((Target_lat-Launch_lat)*Math.PI)/180.0;
		double dlambda = ((Target_lon-Launch_lon)*Math.PI)/180.0;
		
		//haversine formula
		
		double a = Math.sin(dfye/2)*Math.sin(dfye/2) + Math.cos(fye1)*Math.cos(fye2)*Math.sin(dlambda/2)*Math.sin(dlambda/2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = R*c; //distance in metres
		
		double range = d/1000;  //range in kms
		
	    double angler = (Angle*Math.PI)/180;  //angle in radians
	    
	    double acclrtn = (Thrust -(Mass*9.8))/Mass; //engine acceleration
	    
	    //burntime
	    double burntime = Math.sqrt(d/(acclrtn*Math.sqrt((acclrtn*acclrtn/96.04) + (acclrtn*Math.sin(angler)/9.8)) + acclrtn*Math.cos(angler)/2)); 
	    
	    //maximum altitude 
	    double maxh = 0.5*acclrtn*Math.pow(burntime,2)*Math.sin(angler) +((Math.pow(acclrtn,2)*Math.pow(burntime,2)*Math.pow(Math.sin(angler),2))/19.6); 
	    //maxh = maxh/1000;
	    
	    //time of flight 
	    double ftime = burntime + (2*acclrtn*burntime*Math.sin(angler) + Math.sqrt(Math.pow(2*acclrtn*burntime*Math.sin(angler),2) + 39.2*acclrtn*Math.pow(burntime,2)*Math.sin(angler)))/19.6; 
	    //ftime = ftime/60;
	    
	    double vfx = acclrtn*burntime*Math.cos(angler); 
	    double vfy = Math.sqrt(Math.pow(acclrtn*burntime*Math.sin(angler),2) + 9.8*acclrtn*Math.pow(burntime,2)*Math.sin(angler)); 
	    		
	    //final velocity 
	    double vf = Math.sqrt(vfy*vfy + vfx*vfx); 
	    //vf = vf*3.6;
	    
	    double[] BurnoutPointPos = new double[2];
	    double[] ApogeePointPos = new double[2];
	    
	    ICBMTrajectoryCalc t = new ICBMTrajectoryCalc();
	    
	   
	    BurnoutPointPos = t.BurnoutPoint(burntime, acclrtn, angler);
	    
	    ApogeePointPos = t.ApogeePoint(maxh, burntime, acclrtn, angler);
	    
	    double Burnout_X = BurnoutPointPos[0];
	    double Burnout_Y = BurnoutPointPos[1];
	    
	    double Apogee_X = ApogeePointPos[0];
	    double Apogee_Y = ApogeePointPos[1];
	    
	    System.out.println(Burnout_X+"\n"+Burnout_Y+"\n"+Apogee_X+"\n"+Apogee_Y);
	    
	     
	}
	*/

	

}




