
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.UsuarioDao;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class UsuariosReport {        
    
    public void generate(OutputStream outputStream) throws IOException, DocumentException {
        List<Usuario> lista;
        UsuarioDao usuarioDao = new UsuarioDao();
        lista = usuarioDao.listar();
        
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph par1 = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
        par1.add(new Phrase("Relatório de Clientes", fontTitulo));
        par1.setAlignment(Element.ALIGN_CENTER);
        par1.add(new Phrase(Chunk.NEWLINE));
        par1.add(new Phrase(Chunk.NEWLINE));
        document.add(par1);

        Paragraph listCli = new Paragraph();
        Font fontListCli = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

        listCli.setAlignment(Element.ALIGN_JUSTIFIED);
        listCli.add(new Phrase(Chunk.NEWLINE));
        listCli.add(new Phrase(Chunk.NEWLINE));
        document.add(listCli);

        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Codigo", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.RED)));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Nome", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.RED)));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Sobre Nome", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.RED)));
        PdfPCell cell4 = new PdfPCell(new Paragraph("fone", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.RED)));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        for (Usuario usuario : lista) {
            table.addCell(usuario.getNomeCompleto());
            table.addCell(usuario.getCpf());
            table.addCell(usuario.getEmail());
            table.addCell(usuario.getFone1());
        }

        document.add(table);

        document.close();

        document.add(new Paragraph("Test"));
        document.close();
    }
}
