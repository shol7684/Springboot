package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakkoUserInfoVO {
	
	public Integer id;
	public String connected_at;
	public Properties properties;
	public Kakao_account kakao_account;
	
	@Getter
	@Setter
	public class Kakao_account {
		
		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		
		@Getter
		@Setter
		public class Profile {
			
			public String nickname;
			
		}
	}
	
	
	@Getter
	@Setter
	public class Properties {
		
		public String nickname;
		
	}
}

