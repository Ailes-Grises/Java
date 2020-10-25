public class main{
	public static void main(String[] args){
		human naoto = new human("naoto", 23);
		naoto.greeting();

		yuusya Y = new yuusya(naoto, 300, 30, 30, 5);
		Y.kougeki();
		// yuusya Z = new yuusya(Y);
		// Z.kougeki();
		monster A = new monster("スライム", 3, 20, 5, 5, 2);
		A.kougeki();
		monster B = new monster("バハムート", 80, 4000, 400, 300, 400);
		B.kougeki();
	}
}
