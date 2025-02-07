package application;

public class ICBMTrajectoryCalc {
	
   public static double[] BurnoutPoint(double Burntime, double Acceleration, double Angle, double lat1, double lon1, double lat2, double lon2){
	   
	   double[] Burn_Cod = new double[2];
	   
	   double angler = Math.toRadians(Angle);
	
	   double b_dist = (Acceleration*Math.pow(Burntime, 2)*Math.cos(angler))/2.0;
	   //burnoutpoint[1] = (Acceleration*Math.pow(Burntime, 2)*Math.sin(Angle))/2.0;  HEIGHT AT BURNOUT
	   b_dist = b_dist/1000;
	   
	    lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);
		
		
		double bearing = Math.atan2(Math.sin(lon2 - lon1) * Math.cos(lat2), Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
       
		double lat_new = Math.asin(Math.sin(lat1) * Math.cos(b_dist/6371) + Math.cos(lat1) * Math.sin(b_dist/6371) * Math.cos(bearing));
	    double lon_new = lon1 + Math.atan2(Math.sin(bearing) * Math.sin(b_dist/6371) * Math.cos(lat1), Math.cos(b_dist/6371) - Math.sin(lat1) * Math.sin(lat_new));
	    
	    lat_new = Math.toDegrees(lat_new);
	    lon_new = Math.toDegrees(lon_new);
	   
	   Burn_Cod[0] = lat_new;
	   Burn_Cod[1] = lon_new;
	   
	   return Burn_Cod;
	   
   }
   
   public double[] ApogeePoint(double MaxHeight, double burntime, double acceleration, double angle) {
	   
	   double[] apogeepoint = new double[2];
	   double r = (acceleration*Math.pow(burntime, 2)*Math.cos(angle))/2.0;
	   double t = (burntime*acceleration*Math.sin(angle))/9.8;
	   apogeepoint[0] = r + (acceleration*burntime*Math.cos(angle)*t);
	   //apogeepoint[1] = MaxHeight; //HEIGHT AT APOGEE
	   
	   return apogeepoint;
	   
   }
}
