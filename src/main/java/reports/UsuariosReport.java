package reports;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import dao.UsuarioDao;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class UsuariosReport extends PdfPageEventHelper {

    public void generate(OutputStream outputStream) throws IOException, DocumentException {

        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        List<Usuario> lista;
        UsuarioDao usuarioDao = new UsuarioDao();
        lista = usuarioDao.listar();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        writer.getInstance(document, outputStream);
        Rectangle rect = new Rectangle(100, 30, 550, 800);
        writer.setBoxSize("art", rect);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);

        document.open();

        Paragraph par1 = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
        par1.add(new Phrase(Chunk.NEWLINE));
        par1.add(new Phrase("Relatório de Clientes                       ", fontTitulo));
        par1.setAlignment(Element.ALIGN_LEFT);
        par1.add(new Phrase(Chunk.NEWLINE));
        document.add(par1);

        Paragraph par2 = new Paragraph();
        Font fontTitulo2 = new Font(Font.FontFamily.COURIER, 10, Font.ITALIC, BaseColor.BLACK);
        par2.add(new Phrase("Solution System free Web Server \n", fontTitulo2));
        par2.add(new Phrase("Equipe : Tiago / Robert / welington \n", fontTitulo2));
        par2.add(new Phrase("Data: " + data + " Hora: " + hora, fontTitulo2));
        par2.setAlignment(Element.ALIGN_LEFT);
        par2.add(new Phrase(Chunk.NEWLINE));
        par2.add(new Phrase(Chunk.NEWLINE));
        document.add(par2);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Nome", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
        PdfPCell cell2 = new PdfPCell(new Paragraph("CPF", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
        PdfPCell cell3 = new PdfPCell(new Paragraph("E-mail", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Fone", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));

        cell1.setBorder(0);
        cell2.setBorder(0);
        cell3.setBorder(0);
        cell4.setBorder(0);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        int cont = 0;

        for (Usuario usuario : lista) {
            PdfPCell nome = new PdfPCell(new Paragraph(usuario.getNomeCompleto(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell cpf = new PdfPCell(new Paragraph(usuario.getCpf(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell mail = new PdfPCell(new Paragraph(usuario.getEmail(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell fone = new PdfPCell(new Paragraph(usuario.getFone1(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));

            if (cont % 2 == 0) {
                nome.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cpf.setBackgroundColor(BaseColor.LIGHT_GRAY);
                mail.setBackgroundColor(BaseColor.LIGHT_GRAY);
                fone.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }

            nome.setBorder(0);
            cpf.setBorder(0);
            mail.setBorder(0);
            fone.setBorder(0);

            table.addCell(nome);
            table.addCell(cpf);
            table.addCell(mail);
            table.addCell(fone);

            cont++;
        }

        document.add(table);

        document.newPage();
        
        document.close();
    }
}
