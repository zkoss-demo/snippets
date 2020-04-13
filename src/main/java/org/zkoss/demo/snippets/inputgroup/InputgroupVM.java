/* InputgroupVM.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar 30 15:13:29 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.demo.snippets.inputgroup;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Popup;
import org.zkoss.zul.impl.XulElement;

/**
 * @author rudyhuang
 */
public class InputgroupVM {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Command
	public void openPopup(@BindingParam("popup") Popup popup,
	                      @BindingParam("ref") XulElement ref) {
		popup.open(ref, "after_start");
	}

	@Command
	@NotifyChange("value")
	public void chooseValue(@BindingParam("val") String val) {
		this.value = val;
	}
}
