/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import dao.ProdutoDao;
import dao.UsuarioDao;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import vo.Produto;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class ProdutoReport extends PdfPageEventHelper {

    private String formatFloat(float x) {
        return String.format("%.2f", x);
    }

    public void generate(OutputStream outputStream) throws IOException, DocumentException {

        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        List<Produto> lista;
        ProdutoDao produtoDao = new ProdutoDao();
        lista = produtoDao.listar();

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
        par1.add(new Phrase("Relatório de Produtos                       ", fontTitulo));
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

        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(100);
        table.setTotalWidth(new float[]{3, 4, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2});
        PdfPCell cell1 = new PdfPCell(new Paragraph("Cod", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Descricao", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell3 = new PdfPCell(new Paragraph("un", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell4 = new PdfPCell(new Paragraph("ncm", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell5 = new PdfPCell(new Paragraph("cst", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell6 = new PdfPCell(new Paragraph("cfop", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell7 = new PdfPCell(new Paragraph("qtd", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell8 = new PdfPCell(new Paragraph("valor", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell9 = new PdfPCell(new Paragraph("total", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell10 = new PdfPCell(new Paragraph("ICMS", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell11 = new PdfPCell(new Paragraph("IPI", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell12 = new PdfPCell(new Paragraph("al-icms", FontFactory.getFont("Arial", 7, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell13 = new PdfPCell(new Paragraph("al-ipi", FontFactory.getFont("Arial", 7, Font.NORMAL, BaseColor.DARK_GRAY)));
        PdfPCell cell14 = new PdfPCell(new Paragraph("Data/cad", FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.DARK_GRAY)));

        cell1.setBorder(0);
        cell2.setBorder(0);
        cell3.setBorder(0);
        cell4.setBorder(0);
        cell5.setBorder(0);
        cell6.setBorder(0);
        cell7.setBorder(0);
        cell8.setBorder(0);
        cell9.setBorder(0);
        cell10.setBorder(0);
        cell11.setBorder(0);
        cell12.setBorder(0);
        cell13.setBorder(0);
        cell14.setBorder(0);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);
        table.addCell(cell10);
        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);

        int cont = 0;

        float somaQtd = 0;
        float somaTotal = 0;
        float somaIcms = 0;
        float somaIpi = 0;

        String sSomaQtd;
        String sSomaTotal;
        String sSomaIcms;
        String sSomaIpi;

        String sQtd;
        String sValor;
        String sTotal;
        String sVicms;
        String sVipi;
        String sAicms;
        String sAipi;

        for (Produto produto : lista) {

            somaQtd += produto.getQtd();
            somaTotal += produto.getVtot();
            somaIcms += produto.getVicm();
            somaIpi += produto.getVipi();

            sQtd = formatFloat(produto.getQtd());
            sValor = formatFloat(produto.getVunt());
            sTotal = formatFloat(produto.getVtot());
            sVicms = formatFloat(produto.getVicm());
            sVipi = formatFloat(produto.getVipi());
            sAicms = formatFloat(produto.getAicm());
            sAipi = formatFloat(produto.getAipi());

            PdfPCell cod = new PdfPCell(new Paragraph(produto.getCodProd(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell desc = new PdfPCell(new Paragraph(produto.getDescricao(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell un = new PdfPCell(new Paragraph(produto.getUn(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell ncm = new PdfPCell(new Paragraph(produto.getNcm(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell cst = new PdfPCell(new Paragraph(produto.getCst(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell cfop = new PdfPCell(new Paragraph(produto.getCfop(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell qtd = new PdfPCell(new Paragraph(sQtd, FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell valor = new PdfPCell(new Paragraph(sValor, FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell total = new PdfPCell(new Paragraph(sTotal, FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell vIcms = new PdfPCell(new Paragraph(sVicms, FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell vIpi = new PdfPCell(new Paragraph(sVipi, FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell aIcms = new PdfPCell(new Paragraph(sAicms + "%", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell aIpi = new PdfPCell(new Paragraph(sAipi + "%", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
            PdfPCell teste = new PdfPCell(new Paragraph(produto.getDataHoraAtual(), FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));

            if (cont % 2 == 0) {
                cod.setBackgroundColor(BaseColor.LIGHT_GRAY);
                desc.setBackgroundColor(BaseColor.LIGHT_GRAY);
                un.setBackgroundColor(BaseColor.LIGHT_GRAY);
                ncm.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cst.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cfop.setBackgroundColor(BaseColor.LIGHT_GRAY);
                if (produto.getQtd() < 6) {
                    qtd.setBackgroundColor(BaseColor.ORANGE);
                } else {
                    qtd.setBackgroundColor(BaseColor.LIGHT_GRAY);
                }
                valor.setBackgroundColor(BaseColor.LIGHT_GRAY);
                total.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vIcms.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vIpi.setBackgroundColor(BaseColor.LIGHT_GRAY);
                aIcms.setBackgroundColor(BaseColor.LIGHT_GRAY);
                aIpi.setBackgroundColor(BaseColor.LIGHT_GRAY);
                teste.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }

            cod.setBorder(0);
            desc.setBorder(0);
            un.setBorder(0);
            ncm.setBorder(0);
            cst.setBorder(0);
            cfop.setBorder(0);
            qtd.setBorder(0);
            valor.setBorder(0);
            total.setBorder(0);
            vIcms.setBorder(0);
            vIpi.setBorder(0);
            aIcms.setBorder(0);
            aIpi.setBorder(0);
            teste.setBorder(0);

            table.addCell(cod);
            table.addCell(desc);
            table.addCell(un);
            table.addCell(ncm);
            table.addCell(cst);
            table.addCell(cfop);
            table.addCell(qtd);
            table.addCell(valor);
            table.addCell(total);
            table.addCell(vIcms);
            table.addCell(vIpi);
            table.addCell(aIcms);
            table.addCell(aIpi);
            table.addCell(teste);

            cont++;
        }

        sSomaQtd = formatFloat(somaQtd);
        sSomaTotal = formatFloat(somaTotal);
        sSomaIcms = formatFloat(somaIcms);
        sSomaIpi = formatFloat(somaIpi);

        PdfPCell cod = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("Arial", 6, Font.BOLDITALIC, BaseColor.BLACK)));
        PdfPCell desc = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell un = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell ncm = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell cst = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell cfop = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell qtd = new PdfPCell(new Paragraph(sSomaQtd, FontFactory.getFont("Arial", 6, Font.BOLDITALIC, BaseColor.BLACK)));
        PdfPCell valor = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell total = new PdfPCell(new Paragraph(sSomaTotal, FontFactory.getFont("Arial", 6, Font.BOLDITALIC, BaseColor.BLACK)));
        PdfPCell vIcms = new PdfPCell(new Paragraph(sSomaIcms, FontFactory.getFont("Arial", 6, Font.BOLDITALIC, BaseColor.BLACK)));
        PdfPCell vIpi = new PdfPCell(new Paragraph(sSomaIpi, FontFactory.getFont("Arial", 6, Font.BOLDITALIC, BaseColor.BLACK)));
        PdfPCell aIcms = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell aIpi = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));
        PdfPCell teste = new PdfPCell(new Paragraph("", FontFactory.getFont("Arial", 6, Font.NORMAL, BaseColor.BLACK)));

        cod.setBorder(0);
        desc.setBorder(0);
        un.setBorder(0);
        ncm.setBorder(0);
        cst.setBorder(0);
        cfop.setBorder(0);
        qtd.setBorder(0);
        valor.setBorder(0);
        total.setBorder(0);
        vIcms.setBorder(0);
        vIpi.setBorder(0);
        aIcms.setBorder(0);
        aIpi.setBorder(0);
        teste.setBorder(0);

        table.addCell(cod);
        table.addCell(desc);
        table.addCell(un);
        table.addCell(ncm);
        table.addCell(cst);
        table.addCell(cfop);
        table.addCell(qtd);
        table.addCell(valor);
        table.addCell(total);
        table.addCell(vIcms);
        table.addCell(vIpi);
        table.addCell(aIcms);
        table.addCell(aIpi);
        table.addCell(teste);
        document.add(table);

        document.close();
    }
}
