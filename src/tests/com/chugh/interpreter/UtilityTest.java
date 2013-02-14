package tests.com.chugh.interpreter;

import junit.framework.Assert;

import org.junit.Test;

import com.chugh.Utility;

public class UtilityTest {

	@Test
	public void testIsNumber() {
		Assert.assertTrue(Utility.isNumber("3"));
		Assert.assertTrue(Utility.isNumber("3.4"));
		Assert.assertFalse(Utility.isNumber("0.0.0"));
		Assert.assertFalse(Utility.isNumber("number3"));
		Assert.assertFalse(Utility.isNumber(""));
	}

}
