package com.example.demo.utils;

import com.example.demo.dto.PeticionesDTO;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ReporteTabularLogos {

    public byte[] generatePdf(PeticionesDTO obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // Configuración del documento tamaño carta
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document document = new Document(pdfDoc, PageSize.LETTER);
        document.setMargins(70, 40, 80, 40); // top, right, bottom, left
        
        // Agregar manejador de eventos para el encabezado
        HeaderEventHandler headerHandler = new HeaderEventHandler();
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);
        
        // Crear tabla de contenido (2 columnas)
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
            .setWidth(UnitValue.createPercentValue(100))
            .setMarginTop(20);
        
        // Configuración de estilos
        DeviceRgb labelColor = new DeviceRgb(64, 64, 64);
        DeviceRgb valueColor = new DeviceRgb(32, 32, 32);
        
        // Agregar filas de ejemplo
        addRow(table, "Solicitante:", obj.getNombreSolicitante(), labelColor, valueColor);
        addRow(table, "Material Sol. :", obj.getClaveMaterial() + " - "+obj.getNombreMaterial(), labelColor, valueColor);
        addRow(table, "Area Sol. :", obj.getAreaSolicitada(), labelColor, valueColor);
        addRow(table, "Cant. Sol. :", ""+obj.getCantidadSolicitada(), labelColor, valueColor);
        addRow(table, "Precio Unitario:", "$ "+obj.getPrecioUnitario(),labelColor, valueColor);
        addRow(table, "Usos:", obj.getUsoMaterial(),
            labelColor, valueColor);
        addRow(table, "Fecha Emisión:", obj.getFechaPeticion(),labelColor, valueColor);
        addRow(table, "Especificaciones:", obj.getEspecificacion(), labelColor, valueColor);
        
        document.add(table);
        document.close();
        
        return baos.toByteArray();
    }
    
    private void addRow(Table table, String label, String value, 
                        DeviceRgb labelColor, DeviceRgb valueColor) {
        // Celda para la etiqueta
        Cell labelCell = new Cell()
            .add(new Paragraph(label)
            .setFontSize(10)
            .setFontColor(labelColor)
            .setBold()
            .setBorderRight(new SolidBorder(0.5f))
            .setPadding(5));
        
        // Celda para el valor
        Cell valueCell = new Cell()
            .add(new Paragraph(value))
            .setFontSize(10)
            .setFontColor(valueColor)
            .setPadding(5);
        
        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    // Clase interna para manejar el encabezado con logos
    private static class HeaderEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), docEvent.getDocument());
            Rectangle pageSize = page.getPageSize();
            
            try {
                // Logo izquierdo
                ImageData logoLeftData = ImageDataFactory.create(
                    new ClassPathResource("static/logos/logo1.jpg").getURL());
                Image logoLeft = new Image(logoLeftData);
                logoLeft.scaleToFit(100, 60);
                logoLeft.setFixedPosition(40, pageSize.getTop() - 60);
                
                // Logo derecho
                ImageData logoRightData = ImageDataFactory.create(
                    new ClassPathResource("static/logos/logo2.jpeg").getURL());
                Image logoRight = new Image(logoRightData);
                logoRight.scaleToFit(100, 60);
                logoRight.setFixedPosition(pageSize.getWidth() - 140, pageSize.getTop() - 60);
                
                // Dibujar logos en el canvas
                Canvas headerCanvas = new Canvas(canvas, pageSize);
                headerCanvas.add(logoLeft);
                headerCanvas.add(logoRight);
                
                // Línea separadora
                canvas.setStrokeColor(new DeviceRgb(200, 200, 200))
                      .setLineWidth(0.8f)
                      .moveTo(40, pageSize.getTop() - 70)
                      .lineTo(pageSize.getWidth() - 40, pageSize.getTop() - 70)
                      .stroke();
                
                headerCanvas.close();
            } catch (Exception e) {
                throw new RuntimeException("Error al agregar logos", e);
            }
        }
    }
}
