package org.knf.dev.demo.helper;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.knf.dev.demo.entity.Employee;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHelper {

	public static ByteArrayInputStream 
	          employeePDFReport(List<Employee> employees) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.
			             getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Employee Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(6);

			// Add PDF Table Header ->
			Stream.of("ID", "Name", "Email", "Country", "Age", "Role").
			 forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.
				        getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (Employee employee : employees) {
				PdfPCell idCell = new PdfPCell(new Phrase(String.
				                       valueOf(employee.getId())));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell nameCell = 
				         new PdfPCell(new Phrase(employee.getName()));
				nameCell.setPaddingLeft(4);
				nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nameCell);

				PdfPCell emailCell = new PdfPCell(new Phrase(String.
				                     valueOf(employee.getEmail())));
				emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				emailCell.setPaddingRight(4);
				table.addCell(emailCell);

				PdfPCell countryCell = new PdfPCell(new Phrase(String.
				                 valueOf(employee.getCountry())));
				countryCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				countryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				countryCell.setPaddingRight(4);
				table.addCell(countryCell);

				PdfPCell ageCell = new PdfPCell(new Phrase(String.
				                     valueOf(employee.getAge())));
				ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				ageCell.setPaddingRight(4);
				table.addCell(ageCell);

				PdfPCell roleCell = new PdfPCell(new Phrase(String.
				            valueOf(employee.getRole())));
				roleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				roleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				roleCell.setPaddingRight(4);
				table.addCell(roleCell);
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}