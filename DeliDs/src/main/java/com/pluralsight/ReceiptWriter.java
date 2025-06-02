package com.pluralsight;

import com.pluralsight.Food.Order;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for writing a text receipt for a given Order.
 * Receipt is saved under “receipts/yyyyMMdd-HHmmss.txt”.
 */
public class ReceiptWriter {
    private static final String RECEIPTS_DIR = "src/main/resources/receipts";

    public static void writeReceipt(Order order) throws IOException {
        // 1) Ensure receipts directory exists
        File dir = new File(RECEIPTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 2) Format filename: yyyyMMdd-HHmmss.txt
        String timestamp = order.getDateTime()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String filename = RECEIPTS_DIR + File.separator + timestamp + ".txt";

        // 3) Write order.toString() into that file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(order.toString());
        }
    }
}
