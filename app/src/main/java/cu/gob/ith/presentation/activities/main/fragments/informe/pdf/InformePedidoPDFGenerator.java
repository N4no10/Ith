package cu.gob.ith.presentation.activities.main.fragments.informe.pdf;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.ImageType;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Producto;
import dagger.hilt.android.qualifiers.ApplicationContext;

public class InformePedidoPDFGenerator {
    private Document document;
    private final Context context;

    @Inject
    public InformePedidoPDFGenerator(@ApplicationContext Context context) {
        this.context = context;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(String nombPDF) throws FileNotFoundException {
        try {
            PdfWriter pdfWriter = new PdfWriter(providePathPDF(nombPDF));
            this.document = new Document(provideCreateDocumentPDF(pdfWriter));

            /*ImageData id = ImageDataFactory.create("");
            document.add(new Image(id));*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void PDFInformeBuild(String nombre, InformePedido informePedido) {
        try {
            setDocument(nombre);
            buildContentPDF(informePedido.getDatosPedido());
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String providePathPDF(String nombrePdf) {
        File carpeta = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS, "MisPedidos");
        if (!carpeta.exists()) {
            carpeta.mkdir();
            carpeta = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/MisPedidos", "Documentos");
            carpeta.mkdir();
        }
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + File.separator + "MisPedidos/Documentos/" + nombrePdf + ".pdf";
    }


    private PdfDocument provideCreateDocumentPDF(PdfWriter pdfWriter) {
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        return pdfDocument;
    }

    private void buildContentPDF(DatosPedido datosPedido) {
        headerTitle(datosPedido);
        addTableClienteAndProveedor(datosPedido);
        addTableRelacionProductos(datosPedido.getProductoList());
        addImporteTotal(datosPedido.getImporteTotal());
        addTableElaboradoAndRecibido();
    }

    private void headerTitle(DatosPedido datosPedido) {
        Table table = new Table(new float[]{150, 300, 150});
        table.addCell(new Cell());
        table.addCell(new Cell().add(new Paragraph(context.getString(R.string.solicitud_pdf) +
                "\n" + datosPedido.getNumber()).setTextAlignment(TextAlignment.CENTER).setBold()));
        table.addCell(new Cell().add(new Paragraph(context.getString(R.string.fecha_pdf) +
                "\n" + datosPedido.getDate())
                .setTextAlignment(TextAlignment.CENTER).setBold()));
        table.setMarginBottom(25);

        document.add(table);
    }

    private void addTableClienteAndProveedor(DatosPedido datosPedido) {
        Table table = new Table(new float[]{300, 300});
        addCliente(datosPedido, table);
        addProveedor(datosPedido, table);

        document.add(table);
    }

    private void addCliente(DatosPedido datosPedido, Table table) {
        table.addCell(new Cell(0, 2).add(paragraphTitleAndContent(
                context.getString(R.string.cliente_pdf), datosPedido.getClient() != null ? datosPedido.getClient() : ""))
                .setBorderBottom(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        table.addCell(new Cell(0, 2).add(paragraphTitleAndContent(
                context.getString(R.string.direccion_pdf), datosPedido.getAddress() != null ? datosPedido.getAddress() : ""))
                .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        if (datosPedido.getTipoCliente().equals("2"))
            table.addCell(new Cell(0, 2).add(paragraphTitleAndContent(
                    context.getString(R.string.negocio_pdf), datosPedido.getDescripcionTipoCliente() != null ? datosPedido.getDescripcionTipoCliente() : ""))
                    .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        table.addCell(new Cell(0, 2).add(paragraphTitleAndContent(
                context.getString(R.string.cuenta_pdf), datosPedido.getBankAccount() != null ? datosPedido.getBankAccount() : ""))
                .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        table.addCell(new Cell(0, 2).add(paragraphTitleAndContent(
                context.getString(R.string.sucursal_pdf), datosPedido.getSucursal() != null ? datosPedido.getSucursal() : ""))
                .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
    }

    private void addProveedor(DatosPedido datosPedido, Table table) {
        table.addCell(addCellSinBorde(new Cell(0, 2)
                .add(paragraphTitleAndContent(context.getString(R.string.proveedor_pdf),
                        datosPedido.getProvider() != null ? datosPedido.getProvider() : ""))
                .setPaddingTop(16f)
                .setTextAlignment(TextAlignment.LEFT)));

        table.addCell(addCellSinBorde(new Cell(0, 2)
                .add(paragraphTitleAndContent(context.getString(R.string.direccion_pdf),
                        datosPedido.getAddressITH() != null ? datosPedido.getAddressITH() : ""))
                .setTextAlignment(TextAlignment.LEFT)));

        table.addCell(addCellSinBorde(new Cell(0, 2)
                .add(paragraphTitleAndContent(context.getString(R.string.cuenta_pdf),
                        datosPedido.getBankAccount() != null ? datosPedido.getBankAccount() : ""))
                .setTextAlignment(TextAlignment.LEFT)));

        table.addCell(addCellSinBorde(new Cell(0, 2)
                .add(paragraphTitleAndContent(context.getString(R.string.sucursal_pdf),
                        datosPedido.getSucursal() != null ? datosPedido.getSucursal() : ""))
                .setTextAlignment(TextAlignment.LEFT)));
    }

    private void addTableRelacionProductos(List<Producto> productoList) {
        document.add(new Paragraph(context.getString(R.string.productos_pdf))
                .setMarginTop(25f)
                .setTextAlignment(TextAlignment.LEFT).setFontSize(20));

        float[] columns = new float[]{100f, 100f, 100f, 100f, 100f, 100f};
        Table table = new Table(columns);
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.numero_pdf))));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.referencia_pdf))));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.um_pdf))));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.cantidad_pdf))));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.precio_pdf))));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.importe_pdf))));

        table.addCell(new Cell().add(paragraphHeader("")));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.descripcion_pdf))));
        table.addCell(new Cell().add(paragraphHeader("")));
        table.addCell(new Cell().add(paragraphHeader("")));
        table.addCell(new Cell().add(paragraphHeader("")));
        table.addCell(new Cell().add(paragraphHeader("")));

        for (int i = 0; i < productoList.size(); i++)
            addProductoToTable(i + 1, productoList.get(i), table);

        document.add(table);
    }

    private void addProductoToTable(int number, Producto producto, Table table) {
        table.addCell(addCellSinBorde(new Cell().add(new Paragraph("" + number)
                .setTextAlignment(TextAlignment.CENTER))));
        table.addCell(addCellSinBorde(new Cell()
                .add(new Paragraph(producto.getReferencia())
                        .setTextAlignment(TextAlignment.LEFT))));
        table.addCell(addCellSinBorde(new Cell()
                .add(new Paragraph(producto.getCodUm())
                        .setTextAlignment(TextAlignment.CENTER))));
        table.addCell(addCellSinBorde(new Cell()
                .add(new Paragraph("" + producto.getCantProducto())
                        .setTextAlignment(TextAlignment.CENTER))));
        table.addCell(addCellSinBorde(new Cell()
                .add(new Paragraph("" + producto.getPv())
                        .setTextAlignment(TextAlignment.CENTER))));
        table.addCell(addCellSinBorde(new Cell()
                .add(new Paragraph("" + producto.getImporte())
                        .setTextAlignment(TextAlignment.CENTER))));
        table.addCell(addCellSinBorde(new Cell()));
        table.addCell(addCellSinBorde(new Cell(0, 5)
                .add(new Paragraph(producto.getDescripcion())
                        .setTextAlignment(TextAlignment.LEFT))).setPaddingBottom(20f));
    }

    private void addImporteTotal(float importeTotal) {
        Table table = new Table(new float[]{200f, 200f, 200f});
        table.addCell(addCellSinBorde(new Cell()));
        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.total_a_pagar_pdf))));
        table.addCell(new Cell().add(paragraphHeader(" " + importeTotal)
                .setTextAlignment(TextAlignment.RIGHT)));
        document.add(table);
    }

    private void addTableElaboradoAndRecibido() {
        Table table = new Table(new float[]{300f, 150f, 150f});
        table.setMarginTop(25f);

        table.addCell(new Cell().add(paragraphHeader(context.getString(R.string.elaborado_pdf))));
        table.addCell(new Cell(0, 2).add(paragraphHeader(
                context.getString(R.string.recibido_pdf))));

        table.addCell(new Cell().add(new Paragraph(context.getString(R.string.nombre_pdf) +
                "\n" + context.getString(R.string.firma_pdf) + "\n" + context.getString(R.string.fecha_pdf))));
        table.addCell(new Cell(0, 2).add(new Paragraph(context.getString(R.string.nombre_pdf) +
                "\n" + context.getString(R.string.cargo_pdf) + "\n" + context.getString(R.string.ci_pdf))));
        table.addCell(new Cell());
        table.addCell(new Cell().add(new Paragraph(context.getString(R.string.firma_pdf))
                .setTextAlignment(TextAlignment.LEFT)));
        table.addCell(new Cell().add(new Paragraph(context.getString(R.string.fecha_pdf))
                .setTextAlignment(TextAlignment.LEFT)));

        document.add(table);
    }

    private Paragraph paragraphTitleAndContent(String title, String content) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Text(title).setBold());
        paragraph.add(" " + content);
        paragraph.setTextAlignment(TextAlignment.LEFT);
        return paragraph;
    }

    private Paragraph paragraphHeader(String text) {
        return new Paragraph(text).setBold()
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Cell addCellSinBorde(Cell cell) {
        cell.setBorderLeft(Border.NO_BORDER);
        cell.setBorderRight(Border.NO_BORDER);
        cell.setBorderBottom(Border.NO_BORDER);
        cell.setBorderTop(Border.NO_BORDER);

        return cell;
    }

}
