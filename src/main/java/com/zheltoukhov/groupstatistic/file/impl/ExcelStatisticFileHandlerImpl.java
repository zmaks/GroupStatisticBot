package com.zheltoukhov.groupstatistic.file.impl;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import com.zheltoukhov.groupstatistic.file.AbstractStatisticFileHandler;
import com.zheltoukhov.groupstatistic.storage.entities.Invite;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelStatisticFileHandlerImpl extends AbstractStatisticFileHandler {
    private static final String SUFFIX = ".xlsx";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(ConfigProperties.getProperty("date-format"));
    @Override
    public void writeFile() throws IOException {
        /*System.out.println("Groups:");
        storage.getGroups().forEach(System.out::println);
        System.out.println("Inviters:");
        storage.getInviters().forEach(System.out::println);

        System.out.println();*/

        for (TelegramGroup group : storage.getGroups()) {
            List<Inviter> inviters = storage.getInviters()
                    .stream()
                    .filter(i -> i.getGroupId().equals(group.getChatId()))
                    .collect(Collectors.toList());
            File file = getFile(group);
            file.delete();
            createExcelFile(file, inviters);

        }

    }

    private void createExcelFile(File file, List<Inviter> inviters) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(file.getName())) {

            XSSFWorkbook workbook = new XSSFWorkbook();
            writeAllInviters(inviters, workbook);
            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " +file.getName() + " not found or busy by other process");
        }
    }

    private void writeAllInviters(List<Inviter> inviters, XSSFWorkbook workbook) {

        XSSFSheet sheet = workbook.createSheet("All Invites");
        int rownum = 0;
        Row rowhead = sheet.createRow(rownum++);
        rowhead.createCell(0).setCellValue("Invited By");
        rowhead.createCell(1).setCellValue("Invited By ID");
        rowhead.createCell(2).setCellValue("Invited User");
        rowhead.createCell(3).setCellValue("Invited User ID");
        rowhead.createCell(4).setCellValue("Invited When");

        for (Inviter inviter : inviters) {
            String invitedBy = inviter.getFullName() + " " + (inviter.getUserName() == null ? "" : inviter.getUserName());
            for (Invite invite : inviter.getInvites()) {
                Row row = sheet.createRow(rownum++);

                row.createCell(0).setCellValue(invitedBy);
                row.createCell(1).setCellValue(inviter.getUserId());
                String invited = invite.getInvitedFullName() + " " + (invite.getInvitedUserName() == null ? "" : invite.getInvitedUserName());
                row.createCell(2).setCellValue(invited);
                row.createCell(3).setCellValue(invite.getInvitedUserId());
                row.createCell(4).setCellValue(DATE_FORMAT.format(invite.getInvitedWhen()));
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

    }

    @Override
    protected String getSuffix() {
        return SUFFIX;
    }
}