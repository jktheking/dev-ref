
package edu.jktheking.processflow.utils;
 
import java.util.Locale;
 
 
 
public class StringUtils {
 
    // Empty checks
    // -----------------------------------------------------------------------
 
    /**
     * <p>
     * Checks if a String is empty ("") or null.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }
 
    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }
 
    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }
 
    /**
     * <p>
     * Checks if a String is not empty (""), not null and not whitespace only.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null and not
     *         whitespace
     */
    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }
 
    // Trim
    // -----------------------------------------------------------------------
 
    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String,
     * handling <code>null</code> by returning <code>null</code>.
     * </p>
     * 
     * @param str
     *            the String to be trimmed, may be null
     * @return the trimmed string, <code>null</code> if null String input
     */
    public static String trim(String str) {
        return org.apache.commons.lang3.StringUtils.trim(str);
    }
 
    public static String trimToNull(String str) {
        return org.apache.commons.lang3.StringUtils.trimToNull(str);
    }
 
    public static String trimToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
    }
 
    // Equals
    // -----------------------------------------------------------------------
 
    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal.
     * </p>
     *
     * @param str1
     *            the first String, may be null
     * @param str2
     *            the second String, may be null
     * @return <code>true</code> if the Strings are equal, case sensitive, or
     *         both <code>null</code>
     */
    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.equals(str1, str2);
 
    }
 
    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal
     * ignoring the case.
     * </p>
     *
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered equal. Comparison is case insensitive.
     * </p>
     *
     * @param str1
     *            the first String, may be null
     * @param str2
     *            the second String, may be null
     * @return <code>true</code> if the Strings are equal, case insensitive, or
     *         both <code>null</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }
 
    // IndexOf
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>.
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchChar
     *            the character to find
     * @return the first index of the search character, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOf(String str, char searchChar) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchChar);
    }
 
    /**
     * <p>
     * Finds the first index within a String from a start position, handling
     * <code>null</code>.
     * </p>
     *
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>.
     * 
     * @param str
     *            the String to check, may be null
     * @param searchChar
     *            the character to find
     * @param startPos
     *            the start position,
     * @return the first index of the search character, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchChar, startPos);
    }
 
    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>.
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @return the first index of the search String, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOf(String str, String searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchStr);
    }
 
    /**
     * <p>
     * Finds the n-th index within a String, handling <code>null</code>.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @param ordinal
     *            the n-th <code>searchStr</code> to find
     * @return the n-th index of the search String, <code>-1</code> if no match
     *         or <code>null</code> string input
     */
    public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }
 
    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @param startPos
     *            the start position
     * @return the first index of the search String, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOf(String str, String searchStr, int startPos) {
 
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchStr, startPos);
    }
 
    /**
     * <p>
     * Case in-sensitive find of the first index within a String.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @return the first index of the search String, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOfIgnoreCase(String str, String searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr);
    }
 
    /**
     * <p>
     * Case in-sensitive find of the first index within a String from the
     * specified position.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @param startPos
     *            the start position
     * @return the first index of the search String, -1 if no match or
     *         <code>null</code> string input
     */
    public static int indexOfIgnoreCase(String str, String searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
    }
 
    // Contains
    // -----------------------------------------------------------------------
 
    /**
     * <p>
     * Checks if String contains a search character, handling <code>null</code>.
     *
     * <p>
     * A <code>null</code> or empty ("") String will return <code>false</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param searchChar
     *            the character to find
     * @return true if the String contains the search character, false if not or
     *         <code>null</code> string input
     */
    public static boolean contains(String str, char searchChar) {
        return org.apache.commons.lang3.StringUtils.contains(str, searchChar);
    }
 
    /**
     * <p>
     * Checks if String contains a search character, handling <code>null</code>.
     *
     * <p>
     * A <code>null</code> or empty ("") String will return <code>false</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param searchChar
     *            the character to find
     * @return true if the String contains the search character, false if not or
     *         <code>null</code> string input
     */
    public static boolean contains(String str, String searchStr) {
        return org.apache.commons.lang3.StringUtils.contains(str, searchStr);
    }
 
    /**
     * <p>
     * Checks if String contains a search String irrespective of case, handling
     * <code>null</code>.
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param searchStr
     *            the String to find, may be null
     * @return true if the String contains the search String irrespective of
     *         case or false if not or <code>null</code> string input
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        return org.apache.commons.lang3.StringUtils.containsIgnoreCase(str, searchStr);
    }
 
    // IndexOfAny chars
    // -----------------------------------------------------------------------
 
    /**
     * <p>
     * Search a String to find the first index of any character in the given set
     * of characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A
     * <code>null</code> or zero length search array will return <code>-1</code>
     * .
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchChars
     *            the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     */
    public static int indexOfAny(String str, char[] searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchChars);
    }
 
    // ContainsAny
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the String contains any character in the given set of
     * characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     * s
     * 
     * @param str
     *            the String to check, may be null
     * @param searchChars
     *            the chars to search for, may be null
     * @return the <code>true</code> if any of the chars are found,
     *         <code>false</code> if no match or null input
     */
    public static boolean containsAny(String str, char[] searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(str, searchChars);
    }
 
    // ContainsOnly
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the String contains only certain characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param valid
     *            an array of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     */
    public static boolean containsOnly(String str, char[] valid) {
        return org.apache.commons.lang3.StringUtils.containsOnly(str, valid);
    }
 
    /**
     * <p>
     * Checks if the String contains only certain characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param validChars
     *            a String of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     */
    public static boolean containsOnly(String str, String validChars) {
        return org.apache.commons.lang3.StringUtils.containsOnly(str, validChars);
    }
 
    // ContainsNone
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks that the String does not contain certain characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>true</code>.
     *
     * @param str
     *            the String to check, may be null
     * @param searchChars
     *            an array of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     */
    public static boolean containsNone(String str, char[] searchChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(str, searchChars);
    }
 
    /**
     * <p>
     * Checks that the String does not contain certain characters.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>true</code>.
     *
     * @param str
     *            the String to check, may be null
     * @param invalidChars
     *            a String of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     */
    public static boolean containsNone(String str, String invalidChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(str, invalidChars);
    }
 
    // IndexOfAny strings
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Find the first index of any of a set of potential substrings.
     *
     * A <code>null</code> String will return <code>-1</code>
     * </p>
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStrs
     *            the Strings to search for, may be null
     * @return the first index of any of the searchStrs in str, -1 if no match
     */
    public static int indexOfAny(String str, String[] searchStrs) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchStrs);
    }
 
    /**
     * <p>
     * Find the latest index of any of a set of potential substrings.
     * </p>
     *
     * A <code>null</code> search array will return <code>-1</code>.
     * 
     * @param str
     *            the String to check, may be null
     * @param searchStrs
     *            the Strings to search for, may be null
     * @return the last index of any of the Strings, -1 if no match
     */
    public static int lastIndexOfAny(String str, String[] searchStrs) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfAny(str, searchStrs);
    }
 
    // Substring
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>null</code>. An empty ("")
     * String will return "".
     * </p>
     *
     * @param str
     *            the String to get the substring from, may be null
     * @param start
     *            the position to start from
     * @return substring from start position, <code>null</code> if null String
     *         input
     */
    public static String substring(String str, int start) {
        return org.apache.commons.lang3.StringUtils.substring(str, start);
    }
 
    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     *
     *
     * <p>
     * The returned substring starts with the character in the
     * <code>start</code> position and ends before the <code>end</code>
     * position.
     * </p>
     *
     * @param str
     *            the String to get the substring from, may be null
     * @param start
     *            the position to start from
     * @param end
     *            the position to end at (exclusive)
     * @return substring from start position to end positon, <code>null</code>
     *         if null String input
     */
    public static String substring(String str, int start, int end) {
        return org.apache.commons.lang3.StringUtils.substring(str, start, end);
    }
 
    // Splitting
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the
     * separator.
     * </p>
     *
     * @param str
     *            the String to parse, may be null
     * @return an array of parsed Strings, <code>null</code> if null String
     *         input
     */
    public static String[] split(String str) {
        return org.apache.commons.lang3.StringUtils.split(str);
    }
 
    /**
     * <p>
     * Splits the provided text into an array, separator specified.
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     *
     * @param str
     *            the String to parse, may be null
     * @param separatorChar
     *            the character used as the delimiter
     * @return an array of parsed Strings, <code>null</code> if null String
     *         input
     */
    public static String[] split(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
    }
 
    /**
     * <p>
     * Splits the provided text into an array, separators specified.
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     *
     * @param str
     *            the String to parse, may be null
     * @param separatorChars
     *            the characters used as the delimiters, <code>null</code>
     *            splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String
     *         input
     */
    public static String[] split(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }
 
    public static String[] split(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars, max);
    }
 
    public static String[] splitByCharacterType(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterType(str);
    }
 
    // Replacing
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Replaces a String with another String inside a larger String, once.
     * </p>
     *
     *
     * @see #replace(String text, String searchString, String replacement, int
     *      max)
     * @param text
     *            text to search and replace in, may be null
     * @param searchString
     *            the String to search for, may be null
     * @param replacement
     *            the String to replace with, may be null
     * @return the text with any replacements processed, <code>null</code> if
     *         null String input
     */
    public static String replaceOnce(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnce(text, searchString, replacement);
    }
 
    /**
     * <p>
     * Replaces all occurrences of a String within another String.
     * </p>
     *
     *
     * @see #replace(String text, String searchString, String replacement, int
     *      max)
     * @param text
     *            text to search and replace in, may be null
     * @param searchString
     *            the String to search for, may be null
     * @param replacement
     *            the String to replace it with, may be null
     * @return the text with any replacements processed, <code>null</code> if
     *         null String input
     */
    public static String replace(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement);
    }
 
    public static String replace(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, max);
    }
 
    /**
     * <p>
     * Replaces all occurrences of Strings within another String.
     * </p>
     * 
     * @param text
     *            text to search and replace in
     * @param searchList
     *            the Strings to search for
     * @param replacementList
     *            the Strings to replace them with
     * @return the text with any replacements processed, <code>null</code> if
     *         null String input
     */
    public static String replaceEach(String text, String[] searchList, String[] replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
    }
 
    // Replace, character based
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Replaces all occurrences of a character in a String with another.
     * <p>
     * A <code>null</code> string input returns <code>null</code>. An empty ("")
     * string input returns an empty string.
     * </p>
     *
     * @param str
     *            String to replace characters in, may be null
     * @param searchChar
     *            the character to search for, may be null
     * @param replaceChar
     *            the character to replace, may be null
     * @return modified String, <code>null</code> if null string input
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChar, replaceChar);
    }
 
    /**
     * <p>
     * Replaces multiple characters in a String in one go. This method can also
     * be used to delete characters.
     * </p>
     *
     * @param str
     *            String to replace characters in, may be null
     * @param searchChars
     *            a set of characters to search for, may be null
     * @param replaceChars
     *            a set of characters to replace, may be null
     * @return modified String, <code>null</code> if null string input
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
    }
 
    // Case conversion
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Converts a String to upper case
     * </p>
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     *
     *
     * @param str
     *            the String to upper case, may be null
     * @return the upper cased String, <code>null</code> if null String input
     */
    public static String upperCase(String str) {
        return org.apache.commons.lang3.StringUtils.upperCase(str);
    }
 
    /**
     * <p>
     * Converts a String to upper case
     * </p>
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     *
     * @param str
     *            the String to upper case, may be null
     * @param locale
     *            the locale that defines the case transformation rules, must
     *            not be null
     * @return the upper cased String, <code>null</code> if null String input
     */
    public static String upperCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.upperCase(str, locale);
    }
 
    /**
     * <p>
     * Converts a String to lower case
     * </p>
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     *
     *
     * @param str
     *            the String to lower case, may be null
     * @return the lower cased String, <code>null</code> if null String input
     */
    public static String lowerCase(String str) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str);
    }
 
    /**
     * <p>
     * Converts a String to lower case
     * </p>
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     *
     * @param str
     *            the String to lower case, may be null
     * @param locale
     *            the locale that defines the case transformation rules, must
     *            not be null
     * @return the lower cased String, <code>null</code> if null String input
     */
    public static String lowerCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str, locale);
    }
 
    /**
     * <p>
     * Swaps the case of a String changing upper and title case to lower case,
     * and lower case to upper case.
     * </p>
     *
     * <ul>
     * <li>Upper case character converts to Lower case</li>
     * <li>Title case character converts to Lower case</li>
     * <li>Lower case character converts to Upper case</li>
     * </ul>
     *
     * @param str
     *            the String to swap case, may be null
     * @return the changed String, <code>null</code> if null String input
     */
    public static String swapCase(String str) {
        return org.apache.commons.lang3.StringUtils.swapCase(str);
    }
 
    // Count matches
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Counts how many times the substring appears in the larger String.
     * </p>
     *
     * <p>
     * A <code>null</code> or empty ("") String input returns <code>0</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param sub
     *            the substring to count, may be null
     * @return the number of occurrences, 0 if either String is
     *         <code>null</code>
     */
    public static int countMatches(String str, String sub) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, sub);
    }
 
    // Character Tests
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the String contains only unicode letters.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains letters, and is non-null
     */
    public static boolean isAlpha(String str) {
        return org.apache.commons.lang3.StringUtils.isAlpha(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only unicode letters and space (' ').
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains letters and space, and is
     *         non-null
     */
    public static boolean isAlphaSpace(String str) {
        return org.apache.commons.lang3.StringUtils.isAlphaSpace(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only unicode letters or digits.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains letters or digits, and is
     *         non-null
     */
    public static boolean isAlphanumeric(String str) {
        return org.apache.commons.lang3.StringUtils.isAlphanumeric(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only unicode letters, digits or space (
     * <code>' '</code>).
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String
     * (length()=0) will return <code>true</code>.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains letters, digits or space, and
     *         is non-null
     */
    public static boolean isAlphanumericSpace(String str) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only unicode digits. A decimal point is not
     * a unicode digit and returns false.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str) {
        return org.apache.commons.lang3.StringUtils.isNumeric(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only unicode digits or space (
     * <code>' '</code>). A decimal point is not a unicode digit and returns
     * false.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains digits or space, and is
     *         non-null
     */
    public static boolean isNumericSpace(String str) {
        return org.apache.commons.lang3.StringUtils.isNumericSpace(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only whitespace.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains whitespace, and is non-null
     */
    public static boolean isWhitespace(String str) {
        return org.apache.commons.lang3.StringUtils.isWhitespace(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only lowercase characters.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains lowercase characters, and is
     *         non-null
     */
    public static boolean isAllLowerCase(String str) {
        return org.apache.commons.lang3.StringUtils.isAllLowerCase(str);
    }
 
    /**
     * <p>
     * Checks if the String contains only uppercase characters.
     * </p>
     *
     * <p>
     * <code>null</code> will return <code>false</code>.
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if only contains uppercase characters, and is
     *         non-null
     */
    public static boolean isAllUpperCase(String str) {
        return org.apache.commons.lang3.StringUtils.isAllUpperCase(str);
    }
 
    // Reversing
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Reverses a String as per ;.
     * </p>
     *
     * <p>
     * A <code>null</code> String returns <code>null</code>.
     * </p>
     *
     * @param str
     *            the String to reverse, may be null
     * @return the reversed String, <code>null</code> if null String input
     */
    public static String reverse(String str) {
        return org.apache.commons.lang3.StringUtils.reverse(str);
    }
 
    public static String reverseDelimited(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.reverseDelimited(str, separatorChar);
    }
 
    // Difference
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Compares two Strings, and returns the portion where they differ. (More
     * precisely, return the remainder of the second String, starting from where
     * it's different from the first.)
     * </p>
     *
     * <p>
     * For example,
     * <code>difference("i am a machine", "i am a robot") -> "robot"</code>.
     * </p>
     *
     * @param str1
     *            the first String, may be null
     * @param str2
     *            the second String, may be null
     * @return the portion of str2 where it differs from str1; returns the empty
     *         String if they are equal
     */
    public static String difference(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.difference(str1, str2);
    }
}