package edeneastapps.sentimentalmoodjournal;

import com.anychart.chart.common.dataentry.DataEntry;

import java.util.List;

public class ChartItem {
    private int type;
    private List<DataEntry> data;
    private String title;

    public ChartItem(int type, List<DataEntry> data, String title) {
        this.type = type;
        this.data = data;
        this.title = title;
    }

    /**
     * 0 = pie, 1 = column
     * @return
     */
    public int getType() {
        return type;
    }

    public List<DataEntry> getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }
}
