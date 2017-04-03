package ra.com.br.reportcard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    public static final int ZERO = 0;
    public static final String REPORT_CARD = "reportCard";
    public static final String SUBJECT = "subject";
    private static final String JSON_RESPONSE = "{\n" +
            "   \"reportCard\": [\n" +
            "      {\n" +
            "         \"subject\": \"Math\",\n" +
            "         \"grade\": \"A+\",\n" +
            "         \"absent\": 0\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Geography\",\n" +
            "         \"grade\": \"B\",\n" +
            "         \"absent\": 2\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"History\",\n" +
            "         \"grade\": 8,\n" +
            "         \"absent\": 4\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Social Studies\",\n" +
            "         \"grade\": \"C\",\n" +
            "         \"absent\": 2\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Science\",\n" +
            "         \"grade\": \"B-\",\n" +
            "         \"absent\": 2\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Art\",\n" +
            "         \"grade\": \"B-\",\n" +
            "         \"absent\": 2\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Language\",\n" +
            "         \"grade\": \"A\",\n" +
            "         \"absent\": 4\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Philosophy\",\n" +
            "         \"grade\": \"F\",\n" +
            "         \"absent\": 2\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Music\",\n" +
            "         \"grade\": \"C+\",\n" +
            "         \"absent\": 0\n" +
            "      },\n" +
            "      {\n" +
            "         \"subject\": \"Physical Education\",\n" +
            "         \"grade\": \"C\",\n" +
            "         \"absent\": 6\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    public static ArrayList<ReportCard> getListReport() {
        ArrayList<ReportCard> arrayListReport = new ArrayList<ReportCard>();

        try {
            JSONObject jsonObject = new JSONObject(JSON_RESPONSE);

            JSONArray reportCard = jsonObject.getJSONArray(REPORT_CARD);
            if (reportCard.length() > ZERO) {
                for (int index = ZERO; index < reportCard.length(); index++) {
                    JSONObject objectReport = reportCard.getJSONObject(index);
                    arrayListReport.add(new ReportCard(objectReport.getString(SUBJECT)));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayListReport;
    }
}
