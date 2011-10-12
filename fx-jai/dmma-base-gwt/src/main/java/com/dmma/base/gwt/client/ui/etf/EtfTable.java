package com.dmma.base.gwt.client.ui.etf;

import java.util.HashMap;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.resources.css.BaseCssResource;
import com.dmma.base.gwt.client.ui.etf.utils.EtfTableCellRendererInterface;
import com.dmma.base.gwt.client.ui.etf.utils.EtfTableUtils;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.base.gwt.client.utils.BaseFlexTableUtil;
import com.dmma.base.gwt.client.utils.BaseWidgetUtils;
import com.dmma.base.gwt.shared.keys.AppEPC;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class EtfTable<T extends IsSerializable, CMT extends IGwtEntityCM<T>> extends Composite implements EtfTableModelListener{
	interface MyUiBinder extends UiBinder<Widget, EtfTable<?,?>> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	private static final int ACTION_CELL_WIDTH = 20;
	private static final int SCROLL_BAR_WIDTH  = 17;
	
	private static final int SCROLLER_MIN_HEIGHT = 50;
	
	
	private final EtfTableModel<T, CMT> model;

	//flags
	private final boolean isFilterAvailable;
	private final boolean isEditLincAvailable;
		
	private final AppEvent editEventTemplate;
	private String         editMainParamKey; // (id || groupId || typeId || ...)
	private ImageResource  editIcon;
	
	private int heightDecreaseValue = -1;
	
	private HashMap<Integer, EtfTableCellRendererInterface<T>> allRenderers;
	
	@UiField BaseCssResource cssRes;
	@UiField FlexTable   tableHeader;
	@UiField ScrollPanel scroller;
	@UiField(provided=true)
	FlexTable   table = new FlexTable(){
		@Override
		public void onBrowserEvent(Event event){
			Element td = getEventTargetCell(event);
			if (td == null) return;
			Element tr = DOM.getParent(td);
			switch (DOM.eventGetType(event)) {
			case Event.ONMOUSEOVER: {
				tr.addClassName(cssRes.style().tableHooverRow());
				break;
			}
			case Event.ONMOUSEOUT: {
				tr.removeClassName(cssRes.style().tableHooverRow());
				break;
			}
			}
		}
	};
	
	public EtfTable(EtfTableModel<T, CMT> modell) {
		this(modell, false);
	}

	public EtfTable(EtfTableModel<T, CMT> modell, boolean isFilterAvailable) {
		this(modell, isFilterAvailable, false,"");
	}

	public EtfTable(EtfTableModel<T, CMT> modell, boolean isFilterAvailable, boolean isEditLincAvailable, String targetPresenter) {
		this.model = modell;
		this.model.setTableModelListener(this);
		this.isFilterAvailable = isFilterAvailable;
		this.isEditLincAvailable = isEditLincAvailable;
		
		this.initWidget(uiBinder.createAndBindUi(this));
		cssRes.style().ensureInjected();
		
		table.sinkEvents(Event.ONMOUSEOVER);
		table.sinkEvents(Event.ONMOUSEOUT);
		
		if(!GWT.isScript()){
			this.setTitle(this.getClass().getName());
		}
		createTableHeader();
		
		if(isEditLincAvailable)
			editEventTemplate = new AppEvent(targetPresenter, this.getClass().getName());
		else
			editEventTemplate = null;
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resizeMe();
			}
		});
		resizeMe();
		setDataNotFound();
	}

	private void createTableHeader(){
		int row = 0;
		FlexCellFormatter formatter  = tableHeader.getFlexCellFormatter();
		for(int i = 0; i< model.getColumnCount(); i++){
			tableHeader.setHTML(row, i, model.getColumnName(i));
			int width = model.getColumnWidth(i); 
			if(width>0)
				formatter.setWidth(row, i,  width+"px");
		}
		int i =  model.getColumnCount();

		if(isEditLincAvailable){
			tableHeader.setHTML(row, i, "&nbsp;");
			formatter.setWidth(row,  i, ACTION_CELL_WIDTH + "px");
			i++;
		}

		//tableHeader.setHTML(row, i, "&nbsp;");
		//formatter.setWidth(row,  i, "12px");

		tableHeader.getRowFormatter().setStyleName(row, cssRes.style().tableHeaderRow());
	}
	
	private void setDataNotFound() {
		table.removeAllRows();
		BaseFlexTableUtil.addDataNotFound(table);
		table.getRowFormatter().addStyleName(0, cssRes.style().noBorderTopRow());
	}

	public void setDataRequested() {
		table.removeAllRows();
		BaseFlexTableUtil.addAnimatedRequestingData(table);
		table.getRowFormatter().addStyleName(0, cssRes.style().noBorderTopRow());
	}

	private void setData() {
		table.removeAllRows();
		for(int i = 0; i < model.getRowCount(); i++){
			createOneRow(i);
		}
		table.getRowFormatter().addStyleName(0, cssRes.style().noBorderTopRow());
	}
	
	private void createOneRow(int rowIndex) {
		FlexCellFormatter formatter  = table.getFlexCellFormatter();
		int row = 	table.getRowCount();
		for(int columnIndex = 0; columnIndex< model.getColumnCount(); columnIndex++){
			if(model.isCellEditable(rowIndex, columnIndex)){
				// createEditableCell(row, columnIndex, rowIndex);
			}else{
				Object val = model.getValueAt(rowIndex, columnIndex);
				if(val==null)
					table.setHTML(row, columnIndex, "&nbsp;");
				else {
					String result = "&nbsp;";
					if(allRenderers != null && allRenderers.get(columnIndex) != null){
						result = allRenderers.get(columnIndex).getHTML(model.getEntity(rowIndex));
					}else{
						Class<?> clazz = model.getColumnClass(columnIndex) ;
						result = EtfTableUtils.getHTML(val, clazz);
					}
					table.setHTML(row, columnIndex, result);
				}
			}
		}

		// setWidth
		if(row == 0){
			for(int columnIndex = 0; columnIndex< model.getColumnCount(); columnIndex++){
				int width = model.getColumnWidth(columnIndex); 
				if(width>0)
					formatter.setWidth(row, columnIndex,  width+"px");
			}
		}

		if(isEditLincAvailable){
			// create Edit Event
			if(editMainParamKey == null || editMainParamKey.length()<1){
				editMainParamKey = AppEPC.ID;
			}

			AppEvent currentEvent = editEventTemplate.clone();
			currentEvent.addParam(editMainParamKey, model.getObjectId(rowIndex));
			table.setWidget(row, model.getColumnCount(), BaseWidgetUtils.createEditWidget(editIcon, currentEvent));
			formatter.setWidth(row, model.getColumnCount(), ACTION_CELL_WIDTH + "px");
			
		}

		// color row
		if(row%2 != 0)
			table.getRowFormatter().setStyleName(row, cssRes.style().tableZebraRow());
	}
	
	
	public void setEditMainParamKey(String editMainParamKey) {
		this.editMainParamKey = editMainParamKey;
	}
	
	public void addRenderer(int row, EtfTableCellRendererInterface<T> renderer){
		if(allRenderers == null )
			allRenderers = new HashMap<Integer, EtfTableCellRendererInterface<T>>();
		allRenderers.put(row, renderer);
	}
	
	public void setEditImageResource(ImageResource editIcon) {
		this.editIcon = editIcon;
	}
	
	private void resizeMe(){
		if(heightDecreaseValue < 0)
			return;
		
		int height = Window.getClientHeight();
		height -= heightDecreaseValue;
		if(height < SCROLLER_MIN_HEIGHT)
			height = SCROLLER_MIN_HEIGHT;
		scroller.setHeight(height + "px");
	
		if(isEditLincAvailable){
			int lastCell = BaseFlexTableUtil.maxCellCount(tableHeader)  - 1;
			
			if(scroller.getOffsetHeight() < table.getOffsetHeight()){
				tableHeader.getFlexCellFormatter().setWidth(0,  lastCell, (ACTION_CELL_WIDTH + SCROLL_BAR_WIDTH)+"px");
			}else{
				tableHeader.getFlexCellFormatter().setWidth(0,  lastCell, ACTION_CELL_WIDTH + "px");
			}	
		}
		// XXX a esli isEditLincAvailable == false  ?? ?to 4o ?
			
		
	}
	
	@Override
	public void onModelChanged(EtfTableModelEvent event) {
		int eventType = event.type;
		if(EtfTableModelEvent.DATA_REQUESTED == eventType) {
			setDataRequested();
		}else if(EtfTableModelEvent.DATA_RECEIVED == eventType){
			setData();
		}else if(EtfTableModelEvent.DATA_NOTFOUND == eventType){
			setDataNotFound();
		}else if(EtfTableModelEvent.DATA_UPDATED == eventType){
			/*if(model.getRowCount()>0){
				updateData(event);
				return;
			}*/
		}
	}
	
	public void setHeightDecreaseValue(int heightDecreaseValue) {
		this.heightDecreaseValue = heightDecreaseValue;
		resizeMe();
	}




}
