package com.douzone.mysite.action.board;


import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		
	
		
		switch(actionName) {
			case "list" : return new BoardAction();
			case "write" : return new WriteFormAction();
			case "add" : return new WriteAction();
			case "view" : return new ViewFormAction();
			case "modify" : return new ModifyFormAction();
			case "modifyact" : return new ModifyAction();
			case "rewrite" : return new ReWriteAction();
			case "rewriteadd" : return new WriteAction();
			case "delete" : return new DeleteAction();
			case "deleteform" : return new DeleteFormAction();
			case "search" : return new BoardAction();
			default : return new BoardAction();
		}
	
	}

}
