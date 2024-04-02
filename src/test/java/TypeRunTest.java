import org.junit.jupiter.api.Test;

import com.codeborne.selenide.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

import java.io.IOException;

public class TypeRunTest {

    @Test
    void TypeText() throws IOException {
        open("https://typerun.top/#rus_adv");

        List<Double> results = new ArrayList<>(); // Массив для записи результатов
        int totalSymbols = 0;// Переменная для общего кол-ва символов

        for (int i = 1; i <= 100; i++) {
            String text = $(".line1").getText(); // Забираем текст из строки

            // Разбиваем текст на отдельные символы в изначальном порядке
            List<Character> characters = new ArrayList<>();
            for (char c : text.toCharArray()) {
                if (c != '¶') {
                    characters.add(c);
                }
            }

            SelenideElement inline = $("#intext"); // Записываем поле ввода в переменную

            // Посылаем по одному знаку, пока не кончится строка
            for (char c : characters) {
                inline.sendKeys(String.valueOf(c));
            }

            inline.pressEnter();

            if (i == 1) { // Проверяем, первая ли это строка
                String symbolsOnFirstLine = $("[title='Всего символов в минуту']").getText(); // Забираем количество символов с первой строки
                double symbolsOnFirstLineValue = Double.parseDouble(symbolsOnFirstLine); // Парсим кол-во символов в double
                results.add(symbolsOnFirstLineValue); // Записываем кол-во символов с первой строки в массив с результатами

                // Записываем кол-во символов в файл
                FileWriter writer = new FileWriter("C:\\Users\\Владислав\\Downloads\\Result.txt", true);

                writer.write(i + " - " + symbolsOnFirstLine + " символов");
                writer.write("\n");

                writer.close();


            } else { //Если не первая строка
                String resultAfterSecondLine = $("[title='Символов в минуту на предыдущей строке']").getText(); // Забираем количество символов
                double resultAfterSecondLineValue = Double.parseDouble(resultAfterSecondLine); // Парсим кол-во символов в double
                results.add(resultAfterSecondLineValue); // Записываем кол-во символов со строки в массив с результатами

                // Записываем кол-во символов в файл
                FileWriter writer = new FileWriter("C:\\Users\\Владислав\\Downloads\\Result.txt", true);

                writer.write(i + " - " + resultAfterSecondLine + " символов");
                writer.write("\n");

                writer.close();
            }

            totalSymbols += characters.size(); // Прибавляем число символов к общему

            // Создаем показ промежуточных результатов каждые 25 строк
            if ((i != 100) && (i % 20 == 0)) {
                double  maxValue = Collections.max(results),
                        minValue = Collections.min(results),
                        averageValue = results.stream().mapToDouble(Double::doubleValue).average().getAsDouble();

                // Выводим в файл промежуточные результаты
                FileWriter writer = new FileWriter("C:\\Users\\Владислав\\Downloads\\Result.txt", true);

                writer.write("Общее кол-во символов: " + totalSymbols + "\n");
                writer.write("Максимальное значение: " + maxValue + "\n");
                writer.write("Минимальное значение: " + minValue + "\n");
                writer.write("Среднее кол-во символов: " + averageValue + "\n");

                writer.close();
            }

        }

        // Переменные для максимального, минимального и среднего арифметического значения
        double  maxValue = Collections.max(results),
                minValue = Collections.min(results),
                averageValue = results.stream().mapToDouble(Double::doubleValue).average().getAsDouble();

        // Выводим в файл результаты
        FileWriter writer = new FileWriter("C:\\Users\\Владислав\\Downloads\\Result.txt", true);

        writer.write("Общее кол-во символов: " + totalSymbols + "\n");
        writer.write("Максимальное значение: " + maxValue + "\n");
        writer.write("Минимальное значение: " + minValue + "\n");
        writer.write("Среднее кол-во символов: " + averageValue + "\n");

        writer.close();
    }
}