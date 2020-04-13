/* DrawerVM.java

	Purpose:
		
	Description:
		
	History:
		Mon Apr 13 11:43:16 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.demo.snippets.drawer;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

/**
 * @author rudyhuang
 */
public class DrawerVM {
	private boolean open = false;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Command
	@NotifyChange("open")
	public void open() {
		open = true;
	}
}
