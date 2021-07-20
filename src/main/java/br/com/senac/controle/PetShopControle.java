package br.com.senac.controle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@ManagedBean(name = "petshopC")
public class PetShopControle {

	private BarChartModel barModel;
	
	public StreamedContent getGraphicText() {
		try {
			return DefaultStreamedContent.builder().contentType("imagem/petshop.jpg").stream(() -> {
				try {
					BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = bufferedImg.createGraphics();
					g2.drawString("This is a text", 0, 10);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(bufferedImg, "png", os);
					return new ByteArrayInputStream(os.toByteArray());
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}