package domain;

import Controle.Machine;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ManagedBean(name = "chartBean")
@RequestScoped
public class ChartBean implements Serializable {

    @ManagedProperty("#{machineController}")
    private MachineController machineController;

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModel();
        configureBarModel();
    }

    public void setMachineController(MachineController machineController) {
        this.machineController = machineController;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createBarModel() {
        barModel = new BarChartModel();
        barModel.setTitle("Machines and Acquisition Years");

        List<Machine> machines = machineController.getItems();
        ChartSeries machineSeries = new ChartSeries();
        machineSeries.setLabel("Machines Acquired");

        Map<String, String> colorMap = new HashMap<>(); 

       
        machines.forEach(machine -> {
            String acquisitionYear = String.valueOf(machine.getAcquisitionYear());
            int count = machineSeries.getData().get(acquisitionYear) != null ?
                    machineSeries.getData().get(acquisitionYear).intValue() + 1 : 1;

            machineSeries.set(acquisitionYear, count);

            
            colorMap.putIfAbsent(acquisitionYear, getRandomColor());
        });

        barModel.addSeries(machineSeries);

        
        setSeriesColors(colorMap);
    }

    private void configureBarModel() {
       
        barModel.setLegendPosition("ne");
        barModel.getAxis(AxisType.X).setLabel("Acquisition Year");
        barModel.getAxis(AxisType.Y).setLabel("Number of Machines");
    }

    private void setSeriesColors(Map<String, String> colorMap) {
        StringBuilder colors = new StringBuilder();
        colorMap.forEach((year, color) -> {
            colors.append(color).append(",");
        });

       
        if (colors.length() > 0) {
            colors.deleteCharAt(colors.length() - 1);
        }

        barModel.setSeriesColors(colors.toString());
    }

    private String getRandomColor() {
    Random random = new Random();
    
    int red = random.nextInt(256);
    int green = random.nextInt(256);
    int blue = random.nextInt(256);
    return String.format("#%02x%02x%02x", red, green, blue);
    }
}
