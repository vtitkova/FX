package com.dmma.base.gwt.client.resources.img;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BaseImages extends ClientBundle {
	public static final BaseImages  IMG = (BaseImages)     GWT.create(BaseImages.class);

	@Source("com/dmma/base/gwt/client/resources/img/blancEstateBig.png")
	public ImageResource estateBig();

	@Source("com/dmma/base/gwt/client/resources/img/blancEstateSmall.png")
	public ImageResource estateSmall();

	@Source("com/dmma/base/gwt/client/resources/img/blancPortraitBig.png")
	public ImageResource portraitBig();

	@Source("com/dmma/base/gwt/client/resources/img/blancPortraitSmall.png")
	public ImageResource portraitSmall();



	@Source("com/dmma/base/gwt/client/resources/img/loading.gif")
	public ImageResource loading();

	@Source("com/dmma/base/gwt/client/resources/img/loadingSmall.gif")
	public ImageResource loadingSmall();

	@Source("com/dmma/base/gwt/client/resources/img/loadingStripe.gif")
	public ImageResource loadingStripe();

	@Source("com/dmma/base/gwt/client/resources/img/errorBig.png")
	public ImageResource errorBig();

	@Source("com/dmma/base/gwt/client/resources/img/infoBig.png")
	public ImageResource infoBig();

	//warning
	@Source("com/dmma/base/gwt/client/resources/img/warningBig.png")
	public ImageResource warningBig();


	//questtion
	@Source("com/dmma/base/gwt/client/resources/img/questionBig.png")
	public ImageResource questionBig();

	@Source("com/dmma/base/gwt/client/resources/img/ok_14.png")
	public ImageResource okSmall();


	@Source("com/dmma/base/gwt/client/resources/img/cancel_14.png")
	public ImageResource cancelSmall();

	//Button - cancel 
	@Source("com/dmma/base/gwt/client/resources/img/cancel.png")
	public ImageResource cancel();

	//Button - ok 
	@Source("com/dmma/base/gwt/client/resources/img/ok.png")
	public ImageResource ok();

	@Source("com/dmma/base/gwt/client/resources/img/info.gif")
	public ImageResource infoSmall();

	@Source("com/dmma/base/gwt/client/resources/img/edit.gif")
	public ImageResource editSmall();

	@Source("com/dmma/base/gwt/client/resources/img/statusGood.gif")
	public ImageResource goodSmall();

	@Source("com/dmma/base/gwt/client/resources/img/statusBad.gif")
	public ImageResource badSmall();

	@Source("com/dmma/base/gwt/client/resources/img/statusIndifferent.gif")
	public ImageResource indifferentSmall();

	@Source("com/dmma/base/gwt/client/resources/img/search.png")
	public ImageResource search();

	// PAGER
	@Source("com/dmma/base/gwt/client/resources/img/left.png")
	public ImageResource left();
	@Source("com/dmma/base/gwt/client/resources/img/left_fast.png")
	public ImageResource leftFast();
	@Source("com/dmma/base/gwt/client/resources/img/left_disabled.png")
	public ImageResource leftDisabled();
	@Source("com/dmma/base/gwt/client/resources/img/left_fast_disabled.png")
	public ImageResource leftFastDisabled();

	@Source("com/dmma/base/gwt/client/resources/img/right.png")
	public ImageResource right();
	@Source("com/dmma/base/gwt/client/resources/img/right_fast.png")
	public ImageResource rightFast();
	@Source("com/dmma/base/gwt/client/resources/img/right_disabled.png")
	public ImageResource rightDisabled();
	@Source("com/dmma/base/gwt/client/resources/img/right_fast_disabled.png")
	public ImageResource rightFastDisabled();

	
	@Source("com/dmma/base/gwt/client/resources/img/refresh.png")
	public ImageResource refresh();
	@Source("com/dmma/base/gwt/client/resources/img/arrowDownSmall.png")
	public ImageResource arrowDownSmall();

}
