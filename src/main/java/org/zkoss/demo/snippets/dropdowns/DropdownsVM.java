/* DropdownsVM.java

	Purpose:
		
	Description:
		
	History:
		Fri Mar 27 10:41:56 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.demo.snippets.dropdowns;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Notification;

/**
 * @author rudyhuang
 */
public class DropdownsVM {
	private int rating;
	private TypeBean type = new TypeBean();

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public TypeBean getType() {
		return type;
	}

	public void setType(TypeBean type) {
		this.type = type;
	}

	@Command
	@NotifyChange("type")
	public void resetType() {
	}

	@Command
	@NotifyChange("type")
	public void submitType() {
	}

	@Command
	public void printVM() {
		Notification.show(String.format("Rating: %d, Type: %d, TypeA Rating: %d",
				rating, type.getType(), type.getTypeARating()));
	}
}
