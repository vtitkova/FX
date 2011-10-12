package com.dmma.base.gwt.client.ui.estateInfo;

import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.shared.interfaces.ContactInfoSmall;
import com.dmma.base.gwt.shared.interfaces.PlaceInfoSmall;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * The Cell used to render a {@link ContactInfoSmall}.
 */
public class PlaceCellSmall extends AbstractCell<PlaceInfoSmall> {
	public static int IMG_WIDTH  = 32; 
	public static int IMG_HEIGHT = 32; 
	private final String imageHtml;

	public PlaceCellSmall() {
		this.imageHtml = AbstractImagePrototype.create(BaseImages.IMG.estateSmall()).getHTML();
	}

	@Override
	public void render(Context context, PlaceInfoSmall info, SafeHtmlBuilder sb) {
		if (info == null) return;

		sb.appendHtmlConstant("<table cellpadding='0' cellspacing='0' style='width: 180px;'>");

		// Add the contact image.
		sb.appendHtmlConstant("<tr><td rowspan='2'  style='width: "+(IMG_WIDTH+8)+"px;'>");
		sb.appendHtmlConstant(createImageHtml(info.getSmallImageUrl()));
		sb.appendHtmlConstant("</td>");

		// Add the name and address.
		sb.appendHtmlConstant("<td style='color: blue; font-size: 95%;'>");
		sb.appendEscaped(info.getDisplayName());
		sb.appendHtmlConstant("</td></tr>");
		sb.appendHtmlConstant("<tr><td style='color: grey; font-size: 85%;'>");
		sb.appendEscaped(info.getInfo());
		sb.appendHtmlConstant("</td></tr>");
		sb.appendHtmlConstant("</table>");
		
		
	}
	
	private String createImageHtml(String imageUrl){
		if(imageUrl!=null){
			//TODO nado background :)
			//<img border="0" style="width: 32px; height: 32px; background: url(&quot;data:image/gif;base64,R0lGODlhFAAUAOZ/AHNzdZTH94W06CpTljl1ym+Oq3eRodjSqNzl2PL4/3ai2s69dsnKzmqVuC1DZ2VlZ5aiqaessCg6VGWX0+T0/rLb/SlqxcXl/7S1uJSXmxhZtrCxtBs8bgBbyuLq99r0/4yXnkV9zwVp1MyvTiQ7YwtMrJK56nKDlv///4SEh4q38TU7RUlJSoqLjZKSldGsPUpOU9fl+L/c8pydn93u9sHO6B9hvV2t+4BYWZO99G6En+zq1Kioq9FZMICdsb3h//v7/HyKmczQzjCD0p2eoMrr/+z5/+jlw5y75WZ5jtTs/4+QkvDy+U97w7/Aw0+Hxqe9ugBSwhlHgXm29RBz1xBkysXZ9jhjkxBdwC6J5JWdpH5/gUh7yE2AykWFx9zEc6bB6K1SSFvC/7q8u/n+/0WNy87CxVxmcHhmaFxdXnaq4r6/wvr4+obR/+v2/+729+DizLe6v1eQvbbN7YaGiJez3ICaqcuenG9vcEpbb3WRkYSOmtbXvNTKlZqdq////yH5BAEAAH8ALAAAAAAUABQAAAfmgH+Cg4SFhoeIiYIMYxEgBhmKGBB2DXJlQ1QieRuJPlkiHVFRWFwEDkuJBVUEE3U1HmQmEnSJOiFsKLo7RyoSKbYhQCgJRgdwOb+Cd2aFJwRMZG4UfTQmHEkKfj1hDIQnFh5vFAgLRWpPAQENOGg8hEEWMV98Bwt2AgFeV2cALk6E9tiwgmDECyh6JkiBAYDIGkMgNMz5IGREBTFTSDzAgEiLBjBKLsj40eYGBzxCEEEogeTDhQoqugyQAMDboQglFAhoMoDEChYPXCSKM8CBhJ9pAGTgqMhJiy0tZmCwqaiq1at/AgEAOw==&quot;) no-repeat scroll 0px 0px transparent;" src="http://212.76.249.185/Expenses/Expenses/clear.cache.gif" onload="this.__gwtLastUnhandledEvent=&quot;load&quot;;" class="gwt-Image">
			return "<img border='0' " +
					"style='width: "+IMG_WIDTH+"px; height: "+IMG_HEIGHT+"px;' " +
					"src='" + imageUrl + "'>";
		}else{
			return imageHtml;
		}
	}


}
