package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Transaction;
import model.UtilityBill;
import repositories.EntityNotFoundException;
import repositories.bill.BillRepository;
import repositories.transaction.TransactionRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class PDFgenerator {
    private final Long userId;
    private final Date startingDate;
    private final Date endingDate;
    private final BillRepository billRepositoryMySQL;
    private final TransactionRepository transactionRepositoryMySQL;

    public PDFgenerator(Long userId, Date startingDate, Date endingDate, BillRepository billRepositoryMySQL, TransactionRepository transactionRepositoryMySQL) {
        this.userId = userId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.billRepositoryMySQL = billRepositoryMySQL;
        this.transactionRepositoryMySQL = transactionRepositoryMySQL;
    }

    public void generateReport() throws EntityNotFoundException, FileNotFoundException, DocumentException {


        Document document=new Document();
        PdfWriter.getInstance(document,new FileOutputStream(userId+".pdf"));

        document.open();
        Paragraph paragraph=new Paragraph("Employee "+userId+" report from: "+startingDate.toString()+"---"+endingDate.toString()+"\n\n");
        document.add(paragraph);

        generateTransactionsTable(document);

        generateBillsTable(document);

        document.close();
    }

    public void generateTransactionsTable(Document document) throws DocumentException, EntityNotFoundException {
        List<Transaction> transactionList=transactionRepositoryMySQL.betweenDates(startingDate,endingDate,userId);

        document.add(new Paragraph("\t\t\t\t\tTransactions"));
        PdfPTable table=new PdfPTable(5);
        PdfPCell idCell=new PdfPCell(new Paragraph("idTransaction"));
        PdfPCell valueCell=new PdfPCell(new Paragraph("Value"));
        PdfPCell idFromCell=new PdfPCell(new Paragraph("From ID"));
        PdfPCell idToCell=new PdfPCell(new Paragraph("To ID"));
        PdfPCell dateCell=new PdfPCell(new Paragraph("Transaction Date"));
        table.addCell(idCell);
        table.addCell(valueCell);
        table.addCell(idFromCell);
        table.addCell(idToCell);
        table.addCell(dateCell);
        for(Transaction transaction: transactionList){
            idCell=new PdfPCell(new Paragraph(transaction.getId().toString()));
            valueCell=new PdfPCell(new Paragraph(transaction.getValue().toString()));
            idFromCell=new PdfPCell(new Paragraph(transaction.getId_from().toString()));
            idToCell=new PdfPCell(new Paragraph(transaction.getId_to().toString()));
            dateCell=new PdfPCell(new Paragraph(transaction.getTransactionDate().toString()));
            table.addCell(idCell);
            table.addCell(valueCell);
            table.addCell(idFromCell);
            table.addCell(idToCell);
            table.addCell(dateCell);
        }

        document.add(table);
    }

    public void generateBillsTable(Document document) throws DocumentException, EntityNotFoundException{
        List<UtilityBill> utilityBills=billRepositoryMySQL.betweenDates(startingDate,endingDate,userId);

        document.add(new Paragraph("\t\t\t\t\tUtility Bills"));
        PdfPTable table=new PdfPTable(5);
        PdfPCell idCell=new PdfPCell(new Paragraph("idBill"));
        PdfPCell valueCell=new PdfPCell(new Paragraph("Value"));
        PdfPCell idFromCell=new PdfPCell(new Paragraph("Account ID"));
        PdfPCell dateCell=new PdfPCell(new Paragraph("Payment Date"));
        PdfPCell descriptionCell=new PdfPCell(new Paragraph("Description"));
        table.addCell(idCell);
        table.addCell(valueCell);
        table.addCell(idFromCell);
        table.addCell(dateCell);
        table.addCell(descriptionCell);
        for(UtilityBill utilityBill: utilityBills){
            idCell=new PdfPCell(new Paragraph(utilityBill.getId().toString()));
            valueCell=new PdfPCell(new Paragraph(utilityBill.getValue().toString()));
            idFromCell=new PdfPCell(new Paragraph(utilityBill.getId_account().toString()));
            dateCell=new PdfPCell(new Paragraph(utilityBill.getPayDate().toString()));
            descriptionCell=new PdfPCell(new Paragraph(utilityBill.getDescription()));
            table.addCell(idCell);
            table.addCell(valueCell);
            table.addCell(idFromCell);
            table.addCell(dateCell);
            table.addCell(descriptionCell);
        }

        document.add(table);

    }
}
