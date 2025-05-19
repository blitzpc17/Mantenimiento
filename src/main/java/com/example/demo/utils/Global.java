package com.example.demo.utils;

import com.example.demo.entity.InventarioEntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import org.krysalis.barcode4j.BarcodeConstants;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Color; // Para Color.WHITE
import java.awt.Font;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;

public final class Global {

    public static LocalDate fechaHoy = LocalDate.now();

    // Privatizar constructor para evitar instancias
    private Global() {
    }

    public static Image generarBarcodeParaPDF(String codigo, int dpi) {
        try {
            // 1. Generar el BufferedImage (código existente)
            Code128Bean barcodeGenerator = new Code128Bean();
            barcodeGenerator.setModuleWidth(0.8);

            // Configuración clave para quitar el texto
            barcodeGenerator.setFontSize(0); // Tamaño de fuente 0 oculta el texto
            barcodeGenerator.setMsgPosition(HumanReadablePlacement.HRP_NONE); // Elimina posición del text

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out,
                    "image/png",
                    dpi,
                    BufferedImage.TYPE_BYTE_BINARY,
                    false,
                    0);

            barcodeGenerator.generateBarcode(canvas, codigo);
            canvas.finish();

            // 2. Convertir BufferedImage a bytes PNG
            byte[] imageBytes = out.toByteArray();

            // 3. Crear ImageData y luego Image de iText
            ImageData imageData = ImageDataFactory.create(imageBytes);
            return new Image(imageData);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar código de barras: " + e.getMessage(), e);
        }
    }

    public static String FormatearFecha(LocalDate fecha, String formato) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        return fecha.format(formatter);
    }

}
