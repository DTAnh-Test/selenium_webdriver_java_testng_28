package javaSDET;

import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_String {

	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String[]newurl = url.split("//");
			// http:
			// the-internet.herokuapp.com/basic_auth
		url = newurl[0] + "//" + "admin" + ":" + "admin" +"@" + newurl[1];
		System.out.println(url);
	}

}
