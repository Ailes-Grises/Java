public class human{
	private String name;
	private int age;

	public human(){ // 仮引数にvoid を書くとエラーになる．
	}

	public human(String name, int age){
		this.name=name; // 自分のクラスの変数にアクセスする際にはthisを使ってok. 
		this.age=age;
	}

	public String Name(){
		return this.name;
	}

	public int Age(){
		return this.age;
	}

	public void greeting(){
		System.out.println("Hello. My name is " + this.name + ". ");
	}
}
