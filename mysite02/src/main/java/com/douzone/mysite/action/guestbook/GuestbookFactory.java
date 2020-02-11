package com.douzone.mysite.action.guestbook;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestbookFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

		switch(actionName) {
			case "listform" : return new ListFormAction();
			case "deleteform" :return new DeleteFormAction();
			case "delete" :return new DeleteAction();
			case "add" :return  new AddAction();
			default : return new GuestbookAction();
		}
		
	}

}
