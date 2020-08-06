package custom_asking;

import java.time.LocalDateTime;

public class CustomChange {
	private CustomDao customdao;

	public CustomChange(CustomDao customdao) {
		this.customdao = customdao;
	}
	
	public Long changedata(CustomRequest req) {
		
		Custom newRequest = new Custom(
			req.getTitle(),req.getContent(),req.getName(),req.getEmail(), LocalDateTime.now());
	
		customdao.update(newRequest);
		return newRequest.getCount();
	}
	
}
