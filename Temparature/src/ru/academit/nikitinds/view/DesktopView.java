package ru.academit.nikitinds.view;

import ru.academit.nikitinds.controller.Controller;
import ru.academit.nikitinds.model.TemperatureScale;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements View {
    private static final int FIELD_WIDTH = 12;
    private JTextField resultField;
    private final Controller controller;

    public DesktopView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            panel.add(new JLabel("Выберите шкалу исходной температуры"));

            TemperatureScale[] scales = controller.getScales();

            JComboBox<TemperatureScale> initialScalesList = new JComboBox<>(scales);
            panel.add(initialScalesList);

            panel.add(new JLabel("Выберите шкалу результата"));

            JComboBox<TemperatureScale> resultScalesList = new JComboBox<>(scales);
            panel.add(resultScalesList);

            panel.add(new JLabel("Введите значение исходной температуры"));

            JTextField initialValueField = new JTextField(FIELD_WIDTH);
            panel.add(initialValueField);

            panel.add(new JLabel());

            JButton convertButton = new JButton("Перевести");
            panel.add(convertButton);

            panel.add(new JLabel("Результат"));

            resultField = new JTextField(FIELD_WIDTH);
            resultField.setEditable(false);
            panel.add(resultField);

            convertButton.addActionListener(e -> {
                try {
                    double initialValue = Double.parseDouble(initialValueField.getText());
                    controller.convert(
                            initialValue,
                            initialScalesList.getItemAt(initialScalesList.getSelectedIndex()),
                            resultScalesList.getItemAt(resultScalesList.getSelectedIndex()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Необходимо ввести число. " +
                            "Для разделения целой и дробной частей числа используйте \".\"",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JFrame frame = new JFrame("Конвертер температуры");
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("TempConv.png"));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.add(panel);
            frame.pack();
            frame.setMinimumSize(frame.getSize());
        });
    }

    @Override
    public void showResult(double value) {
        resultField.setText(Double.toString(value));
    }
}