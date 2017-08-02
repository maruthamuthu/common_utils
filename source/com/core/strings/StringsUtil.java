package com.core.strings;

/**
 * @author Maruthamuthu
 *         This class has a string utility methods.
 */

public class StringsUtil
{
	public static boolean isEmpty(CharSequence cs)
	{
		return (cs == null || cs.length() == 0);
	}

	public static boolean isNotEmpty(CharSequence cs)
	{
		return !isEmpty(cs);
	}
}
