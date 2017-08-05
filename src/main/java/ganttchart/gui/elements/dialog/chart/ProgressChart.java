package ganttchart.gui.elements.dialog.chart;

import ganttchart.model.Assignment;
import ganttchart.util.FileUtil;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Created by gwszymanowski on 2017-08-05.
 */
public class ProgressChart extends Dialog<ButtonType> {

    private Assignment assignment;

    public ProgressChart(Assignment assignment) {
        this.assignment = assignment;

        setTitle(FileUtil.concatenateString(assignment.getTitle(), " progress"));
        ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.APPLY);
        getDialogPane().getButtonTypes().addAll(loginButtonType);
        getDialogPane().setContent(createBody());
    }

    public LineChart<Number, Number> createBody() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Predicted duration in days");

        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.getData().addAll(estimatedProgressSeries(), currentProgressSeries());

        return lineChart;
    }

    private XYChart.Series estimatedProgressSeries() {
        XYChart.Series estimatedProgress = new XYChart.Series();
        estimatedProgress.setName("Estimated level");
        long duration = Math.toIntExact(assignment.getDuration() + 1);
        int start = 0;
        for(long i = duration; i >= 0; i--) {
            estimatedProgress.getData().add(new XYChart.Data(i, start));
            start++;
        }

        return estimatedProgress;
    }

    private XYChart.Series currentProgressSeries() {
        XYChart.Series currentProgress = new XYChart.Series();
        currentProgress.setName("Current progress");
        long duration = Math.toIntExact(assignment.getDuration() + 1);
        double start = 0;
        double completed = assignment.getCompleted();
        double incrementValue =  completed * 0.01;

        for(long i = duration; i >= 0; i--) {
            currentProgress.getData().add(new XYChart.Data(i, start));
            start+=incrementValue;
        }

        return currentProgress;
    }

}
