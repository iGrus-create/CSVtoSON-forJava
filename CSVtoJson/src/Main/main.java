package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class main {

    public static void main(String[] args) {
        String csvFile = "input.csv"; // 入力CSVファイルのパス
        String jsonFile = "output.json"; // 出力JSONファイルのパス
        String line; // CSVファイルから読み込んだ1行を格納する変数
        String csvSplitBy = ","; // CSVの区切り文字

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             FileWriter fw = new FileWriter(jsonFile)) {
            String[] headers = br.readLine().split(csvSplitBy); // CSVのヘッダー行を読み込み、カラム名を取得
            StringBuilder jsonBuilder = new StringBuilder(); // JSON文字列を構築するためのStringBuilder
            jsonBuilder.append("[\n"); // JSON配列の開始

            while ((line = br.readLine()) != null) { // CSVファイルの各行を読み込む
                String[] values = line.split(csvSplitBy); // 行をカンマで分割して値を取得
                jsonBuilder.append("  {\n"); // JSONオブジェクトの開始
                for (int i = 0; i < headers.length; i++) {
                    jsonBuilder.append("    \"").append(headers[i]).append("\": \"").append(values[i]).append("\""); // カラム名と値をJSON形式で追加
                    if (i < headers.length - 1) {
                        jsonBuilder.append(","); // 最後のカラムでない場合はカンマを追加
                    }
                    jsonBuilder.append("\n");
                }
                jsonBuilder.append("  }"); // JSONオブジェクトの終了
                if (br.ready()) {
                    jsonBuilder.append(","); // 最後の行でない場合はカンマを追加
                }
                jsonBuilder.append("\n");
            }
            jsonBuilder.append("]"); // JSON配列の終了

            // JSONをファイルに書き込む
            fw.write(jsonBuilder.toString());
            System.out.println("JSONファイルが出力されました: " + jsonFile); // 出力完了メッセージ
        } catch (IOException e) {
            e.printStackTrace(); // エラーメッセージを表示
        }
    }
}
    