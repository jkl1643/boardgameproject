package custom_asking;




public class CustomChange {
	private CustomDao customdao;

	public void setCustomChange(CustomDao customdao) {
		this.customdao = customdao;
	}

	public void changedata(Long count1, String title1, String content1) {
		System.out.println("count1 : " + count1);
		Custom custom = customdao.selectByCount(count1);
		System.out.println("수정2제먹 : " + custom.getTitle());
		System.out.println("바꿀타이틀 : " + title1);

		if(!custom.getTitle().isEmpty()){
			custom.changeTitle(title1);
			custom.changeContent(content1);
			customdao.update(custom);
		}
	}
}