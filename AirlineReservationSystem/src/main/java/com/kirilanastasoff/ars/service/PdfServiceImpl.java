package com.kirilanastasoff.ars.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Service
public class PdfServiceImpl implements PdfService {
	private static final String PDF_RESOURCES = "/static/";
	private CustomerService customerService;
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	public PdfServiceImpl(CustomerService customerService, SpringTemplateEngine templateEngine) {
		super();
		this.customerService = customerService;
		this.templateEngine = templateEngine;
	}

	@Override
	public File generatePdf() throws IOException,DocumentException{
		Context context = getContext();
		String html = loadAndFillTemplate(context);
		return renderPdf(html);
	}

	@Override
	public File renderPdf(String html) throws IOException, DocumentException {
		File file = File.createTempFile("customerObj", ".pdf");
		OutputStream outputStream = new FileOutputStream(file);
		ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
		renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
		renderer.layout();
		renderer.createPDF(outputStream);
		outputStream.close();
		file.deleteOnExit();
		return file;
	}

	@Override
	public Context getContext() {
		Context context = new Context();
		context.setVariable("customerObj", customerService.getAllCustomers());
		return context;
	}

	@Override
	public String loadAndFillTemplate(Context context) {
		return templateEngine.process("pdf_customerAcounts", context);
	}

}
