/*import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.PeticionesEntity;
import com.example.demo.repository.PeticionesRepository;
import com.example.demo.servicios.PdfServicio; 

@Service
public class PdfServicio {

    public byte[] generarReportePDF(PeticionesEntity peticion) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Título del PDF
        document.add(new Paragraph("REPORTE DE PETICIÓN")
                .setBold()
                .setFontSize(18));

        // Datos de la petición
        document.add(new Paragraph("ID: " + peticion.getId()));
        document.add(new Paragraph("Solicitante: " + peticion.getNomsol()));
        document.add(new Paragraph("Área solicitada: " + peticion.getAresol()));
        document.add(new Paragraph("Cantidad solicitada: " + peticion.getCansol()));
        document.add(new Paragraph("Fecha de petición: " + peticion.getFepe()));
        document.add(new Paragraph("Especificación: " + peticion.getEspe()));
        document.add(new Paragraph("Fecha de elaboración: " + peticion.getFeel()));
        
        // Datos del inventario relacionado
        if (peticion.getTotalcan() != null) {
            document.add(new Paragraph("\nInformación del Inventario:"));
            document.add(new Paragraph("Material: " + peticion.getTotalcan().getNom()));
            document.add(new Paragraph("Clave: " + peticion.getTotalcan().getCla()));
            document.add(new Paragraph("Precio por unidad: $" + peticion.getTotalcan().getPre()));
            document.add(new Paragraph("Uso: " + peticion.getTotalcan().getUs()));
        }

        document.close();

        return out.toByteArray();
    }
}
import com.example.demo.entity.PeticionesEntity;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class PdfServicio {

    public byte[] generarReportePeticion(PeticionesEntity peticion) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Petición"));
            document.add(new Paragraph("ID: " + peticion.getId()));
            document.add(new Paragraph("Solicitante: " + peticion.getNomsol()));
            document.add(new Paragraph("Área: " + peticion.getAresol()));
            document.add(new Paragraph("Cantidad: " + peticion.getCansol()));
            document.add(new Paragraph("Fecha de Petición: " + peticion.getFepe()));
            document.add(new Paragraph("Especificación: " + peticion.getEspe()));
            document.add(new Paragraph("Fecha de Elaboración: " + peticion.getFeel()));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}*/ 
