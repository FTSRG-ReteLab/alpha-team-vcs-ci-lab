import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TachographTest {

	Tachograph taho = new Tachograph();
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		long date = new Date().getTime();
		taho.update(date, 5,10);

		taho.table.get(date, 5);
		Assert.assertEquals(java.util.Optional.of(10), java.util.Optional.of(taho.table.get(date, 5)));
		//Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(taho.table.get(date, 10)));
		//Assert.assertEquals(java.util.Optional.of(date), java.util.Optional.of(taho.table.get(10, 5)));
	}

	
}
