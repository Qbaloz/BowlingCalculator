package bowlingCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BowlingCalculatorTest {
	
	private List<Integer> rollsList;
	private BowlingCalculator bowling;
	private int expectedResult;
	private boolean expectedStatus;

	@Before
	public void initialize() {
		bowling = new BowlingCalculator();
	}
	
	public BowlingCalculatorTest(List<Integer> rollsList, int expectedResult, boolean expectedStatus) {
		this.rollsList = rollsList;
		this.expectedResult = expectedResult;
		this.expectedStatus = expectedStatus;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> rollsList() {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<Integer>(Arrays.asList(10)), 10, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1)), 30, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5)), 45, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2)), 61, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10)), 71, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10)), 91, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10,10)), 121, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10,10,9,0)), 148, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10,10,9,0,8,2)), 158, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10,10,9,0,8,2,9,1)), 177, false },
			{ new ArrayList<Integer>(Arrays.asList(10,9,1,5,5,7,2,10,10,10,9,0,8,2,9,1,10)), 187, true },
			{ new ArrayList<Integer>(Arrays.asList(10,10,10,10,10,10,10,10,10)), 240, false },
			{ new ArrayList<Integer>(Arrays.asList(10,10,10,10,10,10,10,10,10,10,10,10)), 300, true },
			{ new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0)), 0, false },
			{ new ArrayList<Integer>(Arrays.asList(9,1,2,8,4,6,2,8,6,4,2,8,4,6,1,9,4,6,1,9,4)), 130, true },
			{ new ArrayList<Integer>(Arrays.asList(7,2,3,1,4,6,10,10,3,2,1,5,9,1,9,1,4,4)), 123, true },
			{ new ArrayList<Integer>(Arrays.asList(10,10,1,1)), 35, false },
		});
	}
	
	@Test
	public void bowlingResultChecker() {
		for(int i = 0; i < rollsList.size(); i++){
			bowling.roll(rollsList.get(i));
		}
		int result = bowling.getScore();
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void gameStatusChecker() {
		for(int i = 0; i < rollsList.size(); i++){
			bowling.roll(rollsList.get(i));
		}
		boolean gameStatus = bowling.isFinished();
		Assert.assertEquals(expectedStatus, gameStatus);
	}

}
