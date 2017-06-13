import pkg_cast.*;
import java.util.Date;
import java.util.Calendar;

public class TestProcessManager{
	public static void main(String[] args){

		ProcessManager pm=new ProcessManager();

		Calendar cal=Calendar.getInstance();
		Date[] dates=new Date[9];
		for(int i=0;i<9;i++){
			dates[i]=cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH , +1);
			System.out.printf("%tF\n",dates[i]);
		}
		try{
			//1001 , 2017/6/1
			pm.setKey(new Key(1001,dates[0]));

			pm.setProcessId(2);
			pm.setPlan(new Interval(dates[1],dates[2]));
			pm.setProcessId(3);
			pm.setPlan(new Interval(dates[3],dates[4]));
			pm.setProcessId(4);
			pm.setPlan(new Interval(dates[5],dates[6]));

			pm.setProcessId(2);
			pm.setStart(dates[1]);
			pm.setProcessId(2);
			pm.setEnd(dates[2]);
			
			pm.setProcessId(3);
			pm.setStart(dates[3]);
			pm.setProcessId(3);
			pm.setEnd(dates[4]);
			
			pm.setProcessId(4);
			pm.setStart(dates[5]);
			pm.setProcessId(4);
			pm.setEnd(dates[6]);
			
			pm.printPlanMap();
			pm.printStartMap();
			pm.printEndMap();
		}catch(Exception e){
			e.printStackTrace();
		}
		pm=new ProcessManager();
		try{
			//1001 , 2017/6/1
			pm.setKey(new Key(1001,dates[0]));

			pm.setProcessId(2);
			pm.setPlan(new Interval(dates[1],dates[2]));
			pm.setProcessId(3);
			pm.setPlan(new Interval(dates[3],dates[4]));
			pm.setProcessId(4);
			pm.setPlan(new Interval(dates[5],dates[6]));

			pm.setProcessId(2);
			pm.setStart(dates[2]);
			pm.setProcessId(2);
			pm.setEnd(dates[3]);
			pm.setProcessId(3);
			pm.setStart(dates[2]);
		
			pm.printPlanMap();
			pm.printStartMap();
			pm.printEndMap();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
