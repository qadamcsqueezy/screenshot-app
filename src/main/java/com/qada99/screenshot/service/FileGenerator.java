package com.qada99.screenshot.service;

import java.io.IOException;

import com.qada99.screenshot.config.enumeration.OutputType;

public class FileGenerator {

	private PdfGenerator pdfGenarator;

	public FileGenerator() {
		super();
		this.pdfGenarator = new PdfGenerator();

	}

	public void generate(Settings settings, String outputFile) throws IOException {

		if (settings.getOutputConfig().getType() == OutputType.PDF) {
			pdfGenarator.generate(settings, outputFile + ".pdf");
		}

	}
}
