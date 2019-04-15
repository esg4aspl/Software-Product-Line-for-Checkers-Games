package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import core.*;

public class MoveFileWriter {

	private String filename;
	private PrintWriter pw;
	
	public MoveFileWriter() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		filename = "movelist-" + dateFormat.format(date);

		try {
			pw = new PrintWriter(new FileWriter(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeMoveToFile(AbstractMove move) {
		String content = move.toString();
		try {
			pw.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile() {
		pw.flush();
		pw.close();
	}

}
