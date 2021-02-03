import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.service.FileGenerator;

public class Test {

	public static void main(String[] args) {

		FileGenerator fileGenerator = new FileGenerator();
		List<File> images = new ArrayList<>();
		images.add(new File("C:\\Users\\pc\\Desktop\\wireless.PNG"));
		images.add(new File("C:\\Users\\pc\\Desktop\\tele5.PNG"));
		images.add(new File("C:\\Users\\pc\\Desktop\\ex4.PNG"));
		System.out.println("start..");

		try {
			fileGenerator.generate(images, "C:\\Users\\pc\\Desktop\\file", OutputType.PDF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
		
	}

}
