package application;

public class ABMProcessInput {
	
	public static double[] ABMprocessInput(String LaunchLat, String LaunchLon, String OpRange, String Fceiling, String Mass, String Thrust) {
		String [] launchlat = LaunchLat.split(" ");
		String [] launchlon = LaunchLon.split(" ");
		
		double Launch_Lat = Double.parseDouble(launchlat[0]);
		double Launch_Lon = Double.parseDouble(launchlon[0]);
		
		if(launchlat[1].equals("S"))
		{
			Launch_Lat = Launch_Lat*(-1.0);
		}
		if(launchlon[1].equals("W"))
		{
			Launch_Lon = Launch_Lon*(-1.0);
		}
		
		double o_range = Double.parseDouble(OpRange);
		
		double vr = Double.parseDouble(Fceiling);
		
		double mass = Double.parseDouble(Mass);
		
		double thrust = Double.parseDouble(Thrust);
		
		
		
		double[] a_X_Y = WorldMap.ConvertTo2D(Launch_Lat, Launch_Lon);
		
		double x = a_X_Y[0];
		double y = a_X_Y[1];
		
		double[] data = {x, y, o_range, vr, mass, thrust, Launch_Lat, Launch_Lon};
		
		return data;
		
		
	}
	
	public static double[] IntcpCalc(double lat1, double lon1, double lat2, double lon2, double fc, double acc, double burntime, double angle, double maxh, double abm_lat, double abm_lon, double mass, double thrust) {
		
		double angler = Math.toRadians(angle);
		burntime = burntime/1000;
		double fc_dist = 0.5*acc*burntime*burntime*Math.cos(angler) + acc*burntime*Math.cos(angler)*(acc*burntime*Math.sin(angler)/9.8 + Math.sqrt((maxh-(fc*1000))/4.9));
		fc_dist = fc_dist/1000;
		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);
		
		
		double bearing = Math.atan2(Math.sin(lon2 - lon1) * Math.cos(lat2), Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        
		double lat_new = Math.asin(Math.sin(lat1) * Math.cos(fc_dist/6371) + Math.cos(lat1) * Math.sin(fc_dist/6371) * Math.cos(bearing));
	    double lon_new = lon1 + Math.atan2(Math.sin(bearing) * Math.sin(fc_dist/6371) * Math.cos(lat1), Math.cos(fc_dist/6371) - Math.sin(lat1) * Math.sin(lat_new));
	    
	    lat_new = Math.toDegrees(lat_new);
	    lon_new = Math.toDegrees(lon_new);
	    
	    double  R = 6371000;
		//converting to radian
		double fye1 = (lat_new*Math.PI)/180.0;
		double fye2 = (abm_lat*Math.PI)/180.0;
		double dfye = ((abm_lat-lat_new)*Math.PI)/180.0;
		double dlambda = ((abm_lon-lon_new)*Math.PI)/180.0;
		
		//haversine formula
		
		double a = Math.sin(dfye/2)*Math.sin(dfye/2) + Math.cos(fye1)*Math.cos(fye2)*Math.sin(dlambda/2)*Math.sin(dlambda/2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = R*c; //distance in metres
		
		double range = d/1000;  //range in kms
		
		double abm_theta = Math.atan(fc*1000/d);
		
		double intc_d = Math.sqrt((fc*fc*1000000) + d*d);
		
		double abm_acc = (thrust-(mass*9.8))/mass;
		
		double abm_time = Math.sqrt(2*intc_d/abm_acc);
		  
		double icbm_time = burntime + Math.sqrt((maxh-(fc*1000))/4.9) + (acc*burntime*Math.sin(angler))/9.8;
		
		double[] intc_data = {lat_new, lon_new, abm_time, icbm_time, range};
		
		
		
		return intc_data;
		
		
		

		
	}
	


}
