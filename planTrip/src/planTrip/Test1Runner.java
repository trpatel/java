package planTrip;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Test1Runner {
	public static void main(String[] args){
		Result result = JUnitCore.runClasses(Test1.class);
		for(Failure failure: result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
