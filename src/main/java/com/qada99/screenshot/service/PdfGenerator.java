package com.qada99.screenshot.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfGenerator {

	public void generate(Settings settings, String outPut) throws IOException {
		PDDocument document = new PDDocument();
		for (File file : settings.getImages()) {
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			PDImageXObject image = PDImageXObject.createFromFile(file.getAbsolutePath(), document);
			float scale = 1f;
//			contentStream.drawImage(image, 0, 0, image.getWidth() * scale, image.getHeight() * scale);
			float padding = settings.getOutputConfig().getPadding();
			contentStream.drawImage(image, padding, padding, page.getMediaBox().getWidth() - padding * 2,
					page.getMediaBox().getHeight() - padding * 2);

			;
			contentStream.close();
		}

		document.save(outPut);
		document.close();
	}
}
