package custom_asking;


public class CustomChange {
	private CustomDao customdao;

	public CustomChange(CustomDao customdao) {
		this.customdao = customdao;
	}
	
	public void changedata(Long count1, String title1, String content1, String name1) {
		
		Custom custom = customdao.selectByCount(count1);
			
	
		customdao.update(custom);
		
	}
	
}

