package com.example.demo.controller;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InventarioDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserCorrect;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.PeticionesRepository;
import com.example.demo.repository.UserCorrectRepository;
import com.example.demo.servicios.InventarioServicio;
/* 
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;*/

import java.io.IOException;
import com.example.demo.utils.Global;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

	@Autowired
	private InventarioServicio inventarioServicio;

	@Autowired
	private InventarioRepository repoIn;

	@Autowired
	private CategoryRepository repocate;

	@Autowired
	private PeticionesRepository repopeti;

	@GetMapping("/")
	public String gestionarInventario(Model model) {
		return "inventario/InventaryList";
	}

	@GetMapping(value = "/listar", name = "inventario.listar")
	@ResponseBody // Opcional si usas @RestController
	public ResponseEntity<?> listarInventario() {
		List<InventarioDTO> materialoficial = repoIn.listarInventario();
		return ResponseEntity.ok(materialoficial); // Retorna JSON
	}

	@GetMapping(value = "/etiqueta", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarEtiquetaPDF(
			@RequestParam("id") Integer idRegistro,
			@RequestParam("tipo") String tipo) throws IOException {

		if (tipo == null || tipo.isEmpty()) {
			return ResponseEntity.badRequest().body("El parámetro 'tipo' es requerido (BAR o QR)".getBytes());
		}

		// 1. Configuración de dimensiones (1 cm = 28.35 puntos)
		float anchoEtiqueta = 10f * 28.35f; // 10 cm
		float altoEtiqueta = 5f * 28.35f; // 5 cm
		float tamanoCodigo = 4f * 28.35f; // 4 cm
		float margen = 0.5f * 28.35f; // 0.5 cm de margen

		InventarioEntity obj = repoIn.buscarporidmateriales(idRegistro);
		String contenido = obj.getCla();
		String descripcion = obj.getNom();

		// 2. Crear documento PDF
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
		Document document = new Document(pdfDoc, new PageSize(anchoEtiqueta, altoEtiqueta));

		try {
			Image codigoImage;

			// 3. Generar código según el tipo
			if ("BAR".equalsIgnoreCase(tipo)) {
				codigoImage = Global.generarBarcodeParaPDF(contenido, 300);// generarCodigoBarras(contenido,
																			// tamanoCodigo);
			} else if ("QR".equalsIgnoreCase(tipo)) {
				codigoImage = generarCodigoQR(contenido, tamanoCodigo);
			} else {
				document.close();
				return ResponseEntity.badRequest().body("Tipo no válido. Use BAR o QR".getBytes());
			}

			float anchoTexto = anchoEtiqueta - tamanoCodigo - 2 * margen;
			float xTexto = 0;
			float yTexto = 0;
			float altoTexto = altoEtiqueta - 2 * margen;

			if ("BAR".equalsIgnoreCase(tipo)) {

				float anchoBarras = 9f * 28.35f; // 10 cm
				float altoBarras = 3f * 28.35f;
				// 4. Posicionar el código en la parte izquierda
				codigoImage.setFixedPosition(margen, altoEtiqueta - altoBarras - margen);
				codigoImage.setWidth(anchoBarras);
				codigoImage.setHeight(altoBarras);
				document.add(codigoImage);

				// 5. Configurar área para texto
				xTexto = margen;// tamanoCodigo + 2 * margen;
				yTexto = -2 * margen;// altoBarras;
				anchoTexto = anchoBarras;

			} else {

				// 4. Posicionar el código en la parte izquierda
				codigoImage.setFixedPosition(margen, altoEtiqueta - tamanoCodigo - margen);
				codigoImage.setWidth(tamanoCodigo);
				codigoImage.setHeight(tamanoCodigo);
				document.add(codigoImage);

				// 5. Configurar área para texto
				xTexto = tamanoCodigo + 2 * margen;
				yTexto = margen;

			}

			Paragraph paragraph = new Paragraph(descripcion)
					.setTextAlignment(TextAlignment.CENTER)
					.setFixedPosition(xTexto, yTexto, anchoTexto)
					.setHeight(altoTexto)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);

			document.add(paragraph);
			document.close();

			// 6. Preparar respuesta
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "etiqueta-" + tipo + "-" + obj.getCla() + ".pdf");

			return ResponseEntity.ok()
					.headers(headers)
					.body(baos.toByteArray());

		} catch (Exception e) {
			document.close();
			return ResponseEntity.internalServerError()
					.body(("Error al generar etiqueta: " + e.getMessage()).getBytes());
		}

	}

	private Image generarCodigoQR(String contenido, float tamano) throws Exception {
		try {
			// Configurar parámetros del QR

			// Generar matriz QR
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			int dimension = Math.round(tamano);
			BitMatrix bitMatrix = qrCodeWriter.encode(
					contenido,
					BarcodeFormat.QR_CODE,
					dimension,
					dimension)/*
								 * hints)
								 */;

			// Convertir a imagen
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
			ImageData imageData = ImageDataFactory.create(baos.toByteArray());

			return new Image(imageData);
		} catch (WriterException e) {
			// Generar QR alternativo si falla
			String mensajeError = "Error en QR";
			BitMatrix bitMatrix = new QRCodeWriter().encode(
					mensajeError,
					BarcodeFormat.QR_CODE,
					100,
					100);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
			ImageData imageData = ImageDataFactory.create(baos.toByteArray());

			return new Image(imageData);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveRegistro(@RequestBody InventarioDTO obj) {
		try {

			System.out.println(obj.toString());

			System.out.println(obj.getIdMateriales());

			if (obj.getIdMateriales() == null) {
				inventarioServicio.crearRegistroInventario(
						0, obj.getNombreMaterial(), obj.getClave(), obj.getCantidad(),
						Global.FormatearFecha(Global.fechaHoy, "yyyy-MM-dd"), obj.getPrecioUnitario(), obj.getUsos(),
						obj.getIdSeccion(),
						obj.getAreaUbicacion(), obj.getTipoMaterial(), obj.getNoSerie());

			} else {

				inventarioServicio.modificarregistrodeinventario(obj.getIdMateriales(), obj.getNombreMaterial(),
						obj.getClave(), obj.getCantidad(),
						Global.FormatearFecha(Global.fechaHoy, "yyyy-MM-dd"),
						obj.getPrecioUnitario(), obj.getUsos(), obj.getAreaUbicacion(),
						obj.getNoSerie(), obj.getTipoMaterial());

			}

			return ResponseEntity.ok().body("Registro guardado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al guardar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<?> EliminarRegistro(@RequestBody InventarioDTO obj) {

		System.out.println(obj.getIdMateriales());

		try {

			inventarioServicio.eliminar(obj.getIdMateriales());

			return ResponseEntity.ok().body("Registro eliminado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al eliminar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}

	}

}
