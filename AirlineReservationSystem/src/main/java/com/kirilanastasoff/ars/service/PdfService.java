package com.kirilanastasoff.ars.service;

import java.io.File;
import java.io.IOException;

import org.thymeleaf.context.Context;

import com.lowagie.text.DocumentException;

public interface PdfService {

	File generatePdf()throws IOException,DocumentException;
	
	File renderPdf(String html)throws IOException,DocumentException;
	
	Context getContext();
	
	String loadAndFillTemplate(Context context);
}
