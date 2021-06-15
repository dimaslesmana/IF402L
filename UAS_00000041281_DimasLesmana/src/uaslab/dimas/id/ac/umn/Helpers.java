package uaslab.dimas.id.ac.umn;

import java.util.*;
import java.text.SimpleDateFormat; 

public class Helpers {
	private static final String dateFormat = "^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$";
	private static final String name = "Dimas Lesmana";
	private static final String nim = "00000041281";

	public static String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public static boolean isDateValid(String date) {
		return date.matches(dateFormat);
	}
	
	public static boolean isUidExists(String uid) {
		for (User user : User.getUsers()) {
			if (user.getUserID().substring(1).equals(uid)) {
				System.out.println("User ID sudah terdaftar!");
				return true;
			}
		}
		
		return false;
	}
	
	public static void menuCredits() {
		System.out.println("------------------------------");
		System.out.println(String.format("Credits : %s (%s)", name, nim));
		System.out.println("------------------------------");
	}

}
