package com.example.library20;

import com.example.library20.book.BookDto;
import com.example.library20.newspaper.NewspaperDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.library20.utils.GenerateUtils.generateSomeBooks;
import static com.example.library20.utils.GenerateUtils.generateSomeNewsPapers;

@SpringBootApplication
public class Application {

	static Random r = new Random();

	public static List<BookDto> books = new ArrayList<>();

	public static List<NewspaperDto> newspapers = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		books = generateSomeBooks(r.nextInt(3, 6));
		newspapers = generateSomeNewsPapers(r.nextInt(3, 6));
		writeBookData(books);
		writeNewspaperData(newspapers);
	}

	public static void writeBookData(List<BookDto> booksToWrite) {
		try {
			File booksStorage = new File("src/main/resources/books.json");
			FileWriter writer = new FileWriter(booksStorage);
			JSONArray booksJson = new JSONArray();
			booksToWrite.forEach(b -> {
				JSONObject bookJson = new JSONObject(b);
				booksJson.put(bookJson);
			});

			writer.write(booksJson.toString());
			writer.close();
		}
		catch (Exception e) {
			System.out.println("Impossible to write file");
		}
	}

	public static void writeNewspaperData(List<NewspaperDto> npToWrite) {
		try {
			File npsStorage = new File("src/main/resources/nps.json");
			FileWriter writer = new FileWriter(npsStorage);
			JSONArray npsJson = new JSONArray();
			npToWrite.forEach(b -> {
				JSONObject npJson = new JSONObject(b);
				npsJson.put(npJson);
			});

			writer.write(npsJson.toString());
			writer.close();
		}
		catch (Exception e) {
			System.out.println("Impossible to write file");
		}
	}
}
