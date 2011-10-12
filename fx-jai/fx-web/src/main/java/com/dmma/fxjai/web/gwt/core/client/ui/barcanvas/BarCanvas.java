package com.dmma.fxjai.web.gwt.core.client.ui.barcanvas;

import java.util.Date;
import java.util.List;

import com.dmma.base.gwt.client.utils.BaseFormats;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.web.gwt.core.client.css.FxCssResource;
import com.dmma.fxjai.web.gwt.core.client.css.FxCssResource.FxCss;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class BarCanvas extends Composite {
	private static final int HEIGHT_PX     = 450;
	private static final int MARGIM_TOP    = 10;
	private static final int MARGIM_BOTTOM = 10;
	
	private static final int PADDING_LEFT = 10;
	private static final int BAR_WIDTH    = 5;
	private static final int BAR_SPACE    = 2;
	
	
	
	private FxCss fxCss =  FxCssResource.RES.style();
	private FlowPanel canvas;
	private Double currentMin;
	private Double currentMax;
	private Double currentDelta;
	private List<BarDTO> bars;

	
	
	public BarCanvas(){
		canvas = new FlowPanel();
		canvas.setStyleName(fxCss.canvas());
		fxCss.ensureInjected();
		initWidget(canvas);
	}
	
	
	
	public void setData(List<BarDTO> data) {
		canvas.clear();
		bars = data;
		if(bars == null || bars.isEmpty())
			return;
		detectMinMax();
		
		for(int i = 0; i < bars.size() ; i ++){
			renderOneBar(i,data.get(i));
		}
		canvas.setHeight(HEIGHT_PX+MARGIM_BOTTOM+MARGIM_TOP+"px");
		
		
		

	}

	

	private void renderOneBar(int i, BarDTO barDTO) {
		int bar_left = PADDING_LEFT + ((BAR_WIDTH + BAR_SPACE)*i);
		int bar_height = calculateHeight(barDTO);
		int bar_top  = calculateTop(barDTO);
		
		
		FlowPanel barBody = new FlowPanel();
		barBody.setStyleName(fxCss.bar());
		if(barDTO.getOpen() > barDTO.getClose())
			barBody.addStyleName(fxCss.barUp());
		else
			barBody.addStyleName(fxCss.barDown());
		barBody.getElement().getStyle().setTop((HEIGHT_PX - bar_top) + MARGIM_TOP, Unit.PX);
		barBody.getElement().getStyle().setHeight(bar_height, Unit.PX);
		barBody.getElement().getStyle().setLeft(bar_left, Unit.PX);
		barBody.setTitle(createTitle(barDTO));
		
		
		
		FlowPanel barStrike = new FlowPanel();
		barStrike.setStyleName(fxCss.barStrike());
		bar_height = calculateStrikeHeight(barDTO);
		bar_top  = calculateStrikeTop(barDTO);
		barStrike.getElement().getStyle().setTop((HEIGHT_PX - bar_top) + MARGIM_TOP, Unit.PX);
		barStrike.getElement().getStyle().setHeight(bar_height, Unit.PX);
		barStrike.getElement().getStyle().setLeft(bar_left+2, Unit.PX);

		canvas.add(barStrike);
		canvas.add(barBody);
		
	}
	
	
	
	
	private static String createTitle(BarDTO barDTO) {
		
		StringBuffer sb = new StringBuffer(); //BaseFormats.getFormattedDate(barDTO.get)"O: ");
		Date d = new Date(barDTO.getOpenDateTime()*1000l);
		sb.append(" D:").append(BaseFormats.getFormattedDateTimeGMT(d));
		sb.append(" O:").append(BaseFormats.getFormattedLongCurency(barDTO.getOpen()));
		sb.append(" H:").append(BaseFormats.getFormattedLongCurency(barDTO.getHigh()));
		sb.append(" L:").append(BaseFormats.getFormattedLongCurency(barDTO.getLow()));
		sb.append(" C:").append(BaseFormats.getFormattedLongCurency(barDTO.getClose()));
		sb.append(" V:").append(barDTO.getVolume());
		
		return sb.toString();
	}



	private int calculateTop(BarDTO barDTO) {
		Double max = barDTO.getClose();
		if(barDTO.getOpen() > barDTO.getClose()){
			max = barDTO.getOpen();
		}
		Double top = max - currentMin;
		top = (HEIGHT_PX *top)/currentDelta;
		return top.intValue();
	}
	private int calculateStrikeTop(BarDTO barDTO) {
		Double max = barDTO.getHigh();
		Double top = max - currentMin;
		top = (HEIGHT_PX *top)/currentDelta;
		return top.intValue();
	}



	private int calculateHeight(BarDTO barDTO) {
		Double max = barDTO.getClose();
		Double min = barDTO.getOpen();
		if(barDTO.getOpen() > barDTO.getClose()){
			max = barDTO.getOpen();
			min = barDTO.getClose();
		}
		
		Double heightDounle = max - min;
		Double heigh = (HEIGHT_PX *heightDounle)/currentDelta;
		
		return heigh.intValue();
	}
	private int calculateStrikeHeight(BarDTO barDTO) {
		Double max = barDTO.getHigh();
		Double min = barDTO.getLow();
		
		Double heightDounle = max - min;
		Double heigh = (HEIGHT_PX *heightDounle)/currentDelta;
		
		return heigh.intValue();
	}



	private void detectMinMax() {
		Double min = new Double(bars.get(0).getLow());
		Double max = new Double(bars.get(0).getHigh());
		for(BarDTO bar: bars){
			if(min > bar.getLow())
				min = bar.getLow();
			if(max < bar.getHigh())
				max = bar.getHigh();
		}
		
		currentMin = min;
		currentMax = max;
		currentDelta = max - min;
		
	}
	
}