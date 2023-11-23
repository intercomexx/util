package util;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Cristiana
 */

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PdfParser {
	private String enderecoRecurso;
	public void setEnderecoRecurso(String enderecoRecurso){
		this.enderecoRecurso = enderecoRecurso; //endereço dos ficheiros
	}
	public String getConteudo(){
		File f = new File(this.enderecoRecurso);
		FileInputStream is = null;
		try{
			is = new FileInputStream(f);
		}
		catch(IOException e){
			System.out.println("ERRO: " + e.getMessage());
			return null;
		}
		PDDocument pdfDocument = null;
		try{
			PDFParser parser = new PDFParser(is);
			parser.parse();
			pdfDocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			return stripper.getText(pdfDocument);
		}
		catch (IOException e){
			return "ERRO: Não é possível abrir a stream" + e;
		}
		catch (Throwable e){
			// Fazemos um catch, uma vez que precisamos de fechar o recurso
			return "ERRO: Um erro ocorreu enquanto tentava obter o conteúdo do PDF" + e;
		}
		finally{
			if (pdfDocument != null){
				try{
					pdfDocument.close();
				}
				catch (IOException e){
					return "ERRO: Não foi possível fechar o PDF." + e;
				}
			}
		}
	}
}