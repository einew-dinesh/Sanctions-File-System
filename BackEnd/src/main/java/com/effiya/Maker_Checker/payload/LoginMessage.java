package com.effiya.Maker_Checker.payload;

import lombok.Data;

@Data
public class LoginMessage {
	
	    String message;
	    Boolean status;
	    Integer userId;
	    String userRole;
	    public LoginMessage(String message, Boolean status) {
			super();
			this.message = message;
			this.status = status;
			
		}
		public LoginMessage(String message, Boolean status,Integer userId,String userRole) {
			super();
			this.message = message;
			this.status = status;
			this.userId= userId;
			this.userRole= userRole;
		}
	    
	    
}
