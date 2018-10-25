/*
 * Copyright (c) 2018, Joshua Filby <joshua@filby.me>
 * Copyright (c) 2018, Jordan Atwood <jordan.atwood423@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.util;

import com.google.common.base.CharMatcher;
import java.util.regex.Pattern;

/**
 * A set of utilities to use when dealing with text.
 */
public class Text
{
	private static final Pattern TAG_REGEXP = Pattern.compile("<[^>]*>");

	/**
	 * Removes all tags from the given `str`.
	 *
	 * @param str The string to remove tags from.
	 * @return The given `str` with all tags removed from it.
	 */
	public static String removeTags(String str)
	{
		return TAG_REGEXP.matcher(str).replaceAll("");
	}

	/**
	 * In addition to removing all tags, replaces nbsp with space, trims string and lowercases it
	 * @param str The string to standardize
	 *
	 * @return The given `str` that is standardized
	 */
	public static String standardize(String str)
	{
		return removeTags(str).replace('\u00A0', ' ').trim().toLowerCase();
	}

	/**
	 * Convert a string into Jagex username format
	 * Remove all non-ascii characters, replace nbsp with space, replace _- with spaces, and trim
	 *
	 * @param str The string to standardize
	 * @return The given `str` that is in Jagex name format
	 */
	public static String toJagexName(String str)
	{
		return CharMatcher.ascii().retainFrom(str.replace('\u00A0', ' ')).replaceAll("[_-]+", " ").trim();
	}

	/**
	 * In addition to removing all tags, replaces all <br> delimited text with spaces and all multiple continuous
	 * spaces with single space
	 *
	 * @param str The string to sanitize
	 * @return sanitized string
	 */
	public static String sanitizeMultilineText(String str)
	{
		return removeTags(str
			.replaceAll("-<br>", "-")
			.replaceAll("<br>", " ")
			.replaceAll("[ ]+", " "));
	}
}
