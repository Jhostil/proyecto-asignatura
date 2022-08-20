package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.AdminServicio;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ReportesBean implements Serializable {

    @Getter
    @Setter
    private Integer cantProductos;

    @Getter
    @Setter
    private Integer cantUsuarios;

    @Getter
    @Setter
    private Integer cantVentas;

    @Getter
    @Setter
    private List<Categoria> categorias;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter
    @Setter
    private DonutChartModel donutModel;

    @Getter
    @Setter
    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        this.cantProductos=productoServicio.cantProductos();
        this.cantUsuarios=adminServicio.cantUsuarios();
        this.cantVentas=adminServicio.cantVentas();
        this.categorias=categoriaServicio.listarCategorias();
        createDonutModel();
        createBarModel();
    }
    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        categorias.forEach(System.out::println);
        for (int i=0; i<categorias.size();i++){
            values.add(adminServicio.cantProductosPorCategorias(categorias.get(i).getCodigo()));
            labels.add(categorias.get(i).getNombre());
        }
        dataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("#6610f2");
        bgColors.add("#28a74");
        bgColors.add("#6c757d");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        donutModel.setData(data);
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Ventas");

        List<Number> values = new ArrayList<>();
        values.add(adminServicio.ventasMes(2021,8));
        values.add(adminServicio.ventasMes(2021,9));
        values.add(adminServicio.ventasMes(2021,10));
        values.add(adminServicio.ventasMes(2021,11));
        values.add(adminServicio.ventasMes(2021,12));
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Agosto");
        labels.add("Septiembre");
        labels.add("Octubre");
        labels.add("Noviembre");
        labels.add("Diciembre");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafico");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
}
