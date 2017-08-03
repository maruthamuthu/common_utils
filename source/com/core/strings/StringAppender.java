package com.core.strings;


/**
 * @author Maruthamuthu
 *         This class is used to append the value in StringBuilder
 */
public class StringAppender
{
	private StringBuilder builder;

	public StringAppender()
	{
		builder = new StringBuilder(150);
	}

	public StringAppender(int capacity)
	{
		builder = new StringBuilder(capacity);
	}

	public void append(Object value)
	{
		builder.append(value);
	}

	public void append(char value)
	{
		builder.append(value);
	}

	public void appendWithTrailingSpace(Object value)
	{
		builder.append(value);
		builder.append(' ');
	}

	public void appendWithLeadingSpace(Object value)
	{
		builder.append(' ');
		builder.append(value);
	}

	public void appendWithTrailingSpace(char value)
	{
		builder.append(value);
		builder.append(' ');
	}

	public void appendWithLeadingSpace(char value)
	{
		builder.append(' ');
		builder.append(value);
	}

	public void appendWithSpaces(Object value)
	{
		builder.append(' ');
		builder.append(value);
		builder.append(' ');
	}

	public void appendWithSpaces(char value)
	{
		builder.append(' ');
		builder.append(value);
		builder.append(' ');
	}

	public void appendOnStart(Object value)
	{
		builder.insert(0, value);
	}

	public void appendOnEnd(Object value)
	{
		builder.insert(builder.length() - 1, value);
	}

	public void appendOnStart(char value)
	{
		builder.insert(0, value);
	}

	public void appendOnEnd(char value)
	{
		builder.insert(builder.length() - 1, value);
	}

	public void insert(int offset, Object value)
	{
		builder.insert(offset, value);
	}

	public void insert(int offset, char value)
	{
		builder.insert(offset, value);
	}

	public boolean isEmpty()
	{
		return builder.length() == 0;
	}

	/**
	 *  This method does not reset the memory. Its actually assign the new instance to builder.
	 *  So the old instance is clean when GC happen.
	 */
	public void reset()
	{
		builder = new StringBuilder(50);
	}

	@Override
	public String toString()
	{
		return builder.toString();
	}
}

