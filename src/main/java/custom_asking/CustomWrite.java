package custom_asking;

import java.time.LocalDateTime;



public class CustomWrite {
	private CustomDao customdao;

	public CustomWrite(CustomDao customdao) {
		this.customdao = customdao;
	}

	public Long inputdata(CustomRequest req) {
		
		Custom newRequest = new Custom(
			req.getTitle(),req.getContent(),req.getName(),req.getEmail(), LocalDateTime.now());
	
		customdao.insert(newRequest);
		return newRequest.getCount();
	}
}
