package ru.academit.nikitinds.csv;

import java.io.*;

public class Csv {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.csv")); PrintWriter writer = new PrintWriter("output.html")) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"ru\">");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Результат перевода таблицы из CSV в HTML</title>");
            writer.println("<style>");
            writer.println("  table {");
            writer.println("    border: 1px solid black;");
            writer.println("    border-collapse: collapse;");
            writer.println("  }");
            writer.println();
            writer.println("  td {");
            writer.println("    border: 1px solid black;");
            writer.println("    padding: 0 .5em;");
            writer.println("    vertical-align: top;");
            writer.println("  }");
            writer.println("</style>");
            writer.println("<table>");

            boolean surroundedByQuotes = false;
            int innerQuotesCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (!surroundedByQuotes) {
                    writer.println("  <tr>");
                    writer.print("    <td>");
                }

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        writer.print("&lt");
                        continue;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&gt");
                        continue;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp");
                        continue;
                    }

                    if (line.charAt(i) == ',' && !surroundedByQuotes) {
                        writer.println("</td>");
                        writer.print("    <td>");
                        continue;
                    }

                    if (line.charAt(i) == '\"') {
                        if (!surroundedByQuotes) {
                            surroundedByQuotes = true;
                            continue;
                        }

                        if (innerQuotesCount % 2 == 0) {
                            if (i == line.length() - 1 || line.charAt(i + 1) == ',') {
                                surroundedByQuotes = false;
                                continue;
                            }

                            if (line.charAt(i + 1) == '\"') {
                                innerQuotesCount++;
                                continue;
                            }
                        }

                        innerQuotesCount++;
                    }

                    writer.print(line.charAt(i));
                }

                if (surroundedByQuotes) {
                    writer.print("<br>");
                } else {
                    writer.println("</td>");
                    writer.println("  </tr>");
                }
            }

            writer.println("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}