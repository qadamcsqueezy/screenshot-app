package com.qada99.screenshot.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfGenerator {

	public void generate(List<File> images, String outPut) throws IOException {
		PDDocument document = new PDDocument();
		for (File file : images) {
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			PDImageXObject image = PDImageXObject.createFromFile(file.getAbsolutePath(), document);
			float scale = 1f;
//			contentStream.drawImage(image, 0, 0, image.getWidth() * scale, image.getHeight() * scale);
			float margin = 60f;
			contentStream.drawImage(image, margin, margin, page.getMediaBox().getWidth() - margin * 2,
					page.getMediaBox().getHeight() - margin * 2);

			;
			contentStream.close();
		}

		document.save(outPut + ".pdf");
		document.close();
	}
}
