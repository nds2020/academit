package ru.academit.nikitinds.csv;

import java.io.*;

public class Csv {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Для выполнения программы введите в указанном порядке, разделив пробелом, следующие параметры: " +
                    "путь к CSV-файлу, который необходимо прочитать; " +
                    "путь к HTML-файлу, в который необходимо сохранить результат выполнения программы");
            System.exit(-1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0])); PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"ru\">");
            writer.println("  <head>");
            writer.println("    <meta charset=\"utf-8\">");
            writer.println("    <title>Результат перевода CSV в HTML</title>");
            writer.println("    <style>");
            writer.println("      table {");
            writer.println("        border: 1px solid black;");
            writer.println("        border-collapse: collapse;");
            writer.println("      }");
            writer.println();
            writer.println("      td {");
            writer.println("        border: 1px solid black;");
            writer.println("        padding: 0 .5em;");
            writer.println("        vertical-align: top;");
            writer.println("      }");
            writer.println("    </style>");
            writer.println("  </head>");
            writer.println("  <body>");
            writer.println("    <table>");

            boolean surroundedByQuotes = false;
            int innerQuotesCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }

                if (!surroundedByQuotes) {
                    writer.println("      <tr>");
                    writer.print("        <td>");
                }

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        writer.print("&lt;");
                        continue;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&gt;");
                        continue;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp;");
                        continue;
                    }

                    if (line.charAt(i) == ',' && !surroundedByQuotes) {
                        writer.println("</td>");
                        writer.print("        <td>");
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
                    writer.println("      </tr>");
                }
            }

            writer.println("    </table>");
            writer.println("  </body>");
            writer.println("</html>");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}