public class yuusya{
	private String name;
	private int age;
	private int hp;
	private int attack;
	private int defense;
	private int speed;

	public yuusya(){
	}

	public yuusya(human man, int hp, int attack, int defense, int speed){
		this.name=man.Name();
		this.age=man.Age();
		this.hp=hp;
		this.attack=attack;
		this.defense=defense;
		this.speed=speed;
		System.out.println("でんせつのゆうしゃ" + this.name + "さんじょう！！");
	}

	public yuusya(yuusya copy){
		this.name=copy.Name();
		this.age=copy.Age();
		this.hp=copy.Hp();
		this.attack=copy.Attack();
		this.defense=copy.Defense();
		this.speed=copy.Speed();
		System.out.println("でんせつのゆうしゃ" + this.name + "さんじょう！！");
	}

	public String Name(){
		return this.name;
	}
	public int Age(){
		return this.age;
	}
	public int Hp(){
		return this.hp;
	}
	public int Attack(){
		return this.attack;
	}
	public int Defense(){
		return this.defense;
	}
	public int Speed(){
		return this.speed;
	}

	public void kougeki(){
		System.out.println(this.name + "のこうげき！！");
		System.out.println(this.attack + "のダメージ！！");
		this.hp-=this.Attack();
		System.out.println(this.name + "のたいりょく : " + this.hp);
	}
}

