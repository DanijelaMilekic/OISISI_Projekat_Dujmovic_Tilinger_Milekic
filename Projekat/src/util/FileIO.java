package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
	
	public static List<String> readFromFile(Files file) {
		List<String> list = new ArrayList<String>(); 
		
		try (BufferedReader br = new BufferedReader(new FileReader(file.filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fajl " + file.filename + " nije pronadjen: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja iz fajla " + file.filename + ": ");
			e.printStackTrace();
		}
		
		return list;
	}
	 
	public static boolean writeToFile(Files file, List<String> content) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.filename))) {
			for (String string : content) {
				bw.write(string);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja u fajl " + file.filename + ": ");
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean appendToFile(Files file, String content) {
		List<String> list = new ArrayList<>();
		list.add(content);
		return appendToFile(file, list);
	}
	
	public static boolean appendToFile(Files file, List<String> content) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.filename, true))) {
			for (String string : content) {
				bw.append(string);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja u fajl " + file.filename + ": ");
			e.printStackTrace();
			return false;
		}

		return true;
	}



}
