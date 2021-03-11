package edu.jktheking.locale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumericLocale {

	public static void main(String[] args) throws IOException {
		Locale arabicLocale = new Locale.Builder().setLanguage("ar").setRegion("SA").build();

		Locale arabicLocaleExt = new Locale.Builder().setLanguage("ar").setRegion("SA")
				.setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-arab").build();

		Locale marathi = new Locale.Builder().setLanguage("mr").setRegion("IN").build();
		
		Locale hindi = new Locale.Builder().setLanguage("hi").setRegion("IN").build();
				
		
		Locale hindiWithExt = new Locale.Builder().setLanguage("hi").setRegion("IN")
				.setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-deva").build();

		
		
		Locale tamil = new Locale.Builder().setLanguage("ta").setRegion("IN").setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-tamldec").build();

		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(tamil);
		NumberFormat numberFormat = NumberFormat.getNumberInstance(tamil);
		System.out.println(dfs.getZeroDigit());
		System.out.println(dfs.getInternationalCurrencySymbol());
		double number = 456.789;
		System.out.println(numberFormat.format(number));

		BufferedWriter writer = new BufferedWriter(new FileWriter("locale.txt", StandardCharsets.UTF_8, true));
		writer.newLine();
		writer.append(dfs.getZeroDigit());
		writer.newLine();
		writer.append(dfs.getInternationalCurrencySymbol());
		writer.newLine();
		writer.append(numberFormat.format(number));
		writer.newLine();
		writer.close();
	}
}
